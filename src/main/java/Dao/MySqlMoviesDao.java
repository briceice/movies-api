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

    @Override
    public Movie findOne(int id) throws SQLException {
        PreparedStatement ps;
        Movie movie = null;

        ps = connection.prepareStatement("select * from movies where id = ?");
        ps.setInt(1, id);

        ResultSet rs = ps.executeQuery();
        while(rs.next()) {
            movie = new Movie(rs.getString("title"),
                    rs.getDouble("rating"),
                    rs.getString("poster"),
                    rs.getInt("year"),
                    rs.getString("genre"),
                    rs.getString("director"),
                    rs.getString("plot"),
                    rs.getString("actors"),
                    rs.getInt("id"));
        }
        rs.close();
        ps.close();

        return movie;
    }

    @Override
    public void insert(Movie movie) throws SQLException {
        PreparedStatement ps;
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
    }

    @Override
    public void insertAll(Movie[] movies) throws SQLException {
        for (Movie movie : movies){
            insert(movie);
        }
    }

    @Override
    public void update(Movie movie) throws SQLException {
        // 1. fetch the movie to be changed from the database
        Movie updateMovie = findOne(movie.getId());

        // 2. change the fields in the fetched movie that the movie parameter has changed (look for != null)
        if(movie.getTitle() != null) {
            updateMovie.setTitle(movie.getTitle());
        }
        if(movie.getRating() != null) {
            updateMovie.setRating(movie.getRating());
        }
        if(movie.getPoster() != null) {
            updateMovie.setPoster(movie.getPoster());
        }
        if(movie.getYear() != null) {
            updateMovie.setYear(movie.getYear());
        }
        if(movie.getGenre() != null) {
            updateMovie.setGenre(movie.getGenre());
        }
        if(movie.getPlot() != null) {
            updateMovie.setPlot(movie.getPlot());
        }
        if(movie.getDirector() != null) {
            updateMovie.setDirector(movie.getDirector());
        }

        // 3. do your update query
        PreparedStatement ps;
        ps = connection.prepareStatement("update movies set title = ?, rating = ?, poster = ?, year = ?, genre = ?," +
                " plot = ?, director = ? where id = ?");
        ps.setString(1, updateMovie.getTitle());
        ps.setDouble(2, updateMovie.getRating());
        ps.setString(3, updateMovie.getPoster());
        ps.setInt(4, updateMovie.getYear());
        ps.setString(5, updateMovie.getGenre());
        ps.setString(6, updateMovie.getPlot());
        ps.setString(7, updateMovie.getDirector());
        ps.setInt(8, updateMovie.getId());
        ps.executeUpdate();
        ps.close();
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
