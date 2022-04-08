package Dao;

import com.mysql.cj.jdbc.Driver;
import data.Movie;
import main.Config;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlMoviesDao implements MoviesDao{
    // connection will be needed for all methods
    private Connection connection;

    public MySqlMoviesDao() {
        // make connection in constructor
        try {
            DriverManager.registerDriver(new Driver());
            connection = DriverManager.getConnection(
                    "jdbc:mysql://" + Config.DB_HOST + ":3306/brice",
                    Config.DB_USER,
                    Config.DB_PW
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Movie> all() throws SQLException {
        ArrayList<Movie> movies = new ArrayList<>();

        Statement st = connection.createStatement();
        ResultSet movieRows = st.executeQuery("select title, year, director, actors, rating, poster, genre, plot, id from movies");

        while(movieRows.next()) {
            Movie movie = new Movie(movieRows.getString("title"),
                    movieRows.getDouble("rating"),
                    movieRows.getString("poster"),
                    movieRows.getInt("year"),
                    movieRows.getString("genre"),
                    movieRows.getString("director"),
                    movieRows.getString("plot"),
                    movieRows.getString("actors"),
                    movieRows.getInt("id"));
            movies.add(movie);
        }
        st.close();
        return movies;
    }

    // TODO: switch statement for prepared statement
    @Override
    public Movie findOne(int idInput) {
        Statement st;
        Movie movie = null;
        try {
            st = connection.createStatement();
            ResultSet movieRows = st.executeQuery("select title, year, director, actors, rating, poster, genre, plot from movies where id = idInput");

            while (movieRows.next()) {
                movie = new Movie(movieRows.getString("title"),
                        movieRows.getDouble("rating"),
                        movieRows.getString("poster"),
                        movieRows.getInt("year"),
                        movieRows.getString("genre"),
                        movieRows.getString("director"),
                        movieRows.getString("plot"),
                        movieRows.getString("actors"),
                        movieRows.getInt("id"));
            }
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movie;
    }

    @Override
    public void insert(Movie movie) {
        PreparedStatement ps;
        try {
            ps = connection.prepareStatement("insert into movies " +
                    "(title, year, director, actors, rating, poster, genre, plot) " +
                    "values (?, ?, ?, ?, ?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, movie.getTitle());
            ps.setInt(2, movie.getYear());
            ps.setString(3, movie.getDirector());
            ps.setString(4, movie.getActors());
            ps.setDouble(5, movie.getRating());
            ps.setString(6, movie.getPoster());
            ps.setString(7, movie.getGenre());
            ps.setString(8, movie.getPlot());
            ps.executeUpdate();

            ResultSet newKeys = ps.getGeneratedKeys();
            newKeys.next();
            int newId = newKeys.getInt(1);
            System.out.println("new record id is " + newId);

            newKeys.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insertAll(Movie[] movies) throws SQLException {
        for (Movie movie : movies){
            insert(movie);
        }
    }

    // TODO: needs an id parameter?
    @Override
    public void update(Movie movie) throws SQLException {

    }

    @Override
    public void delete(int id) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("delete from movies where id = ?");
        ps.setInt(1, id);
        ps.executeUpdate();
        ps.close();
    }

    public void cleanUp(){
        System.out.println("Calling cleanup...");
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
