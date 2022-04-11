package main;

import com.mysql.cj.jdbc.Driver;
import data.Movie;

import java.sql.*;
import java.util.ArrayList;

public class MoviesJDBCTest {
    private static Connection connection = null;

    public static void main(String[] args) throws SQLException {
        // connect to student db
        DriverManager.registerDriver(new Driver());
        connection = DriverManager.getConnection(
                "jdbc:mysql://" + Config.DB_HOST + ":3306/brice",
                Config.DB_USER,
                Config.DB_PW
        );

        // use a PreparedStatement to insert a movie record into your table.
        // be sure to use getGeneratedKeys() to save the id of the newly created movie record
        PreparedStatement ps = connection.prepareStatement("insert into movies (title, year, director, actors, rating, poster, genre, plot) values (?, ?, ?, ?, ?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
        ps.setString(1, "Ex Title");
        ps.setInt(2, 2022);
        ps.setString(3, "Ex Director");
        ps.setString(4, "Ex Act1, Ex Act2");
        ps.setDouble(5, 9.5);
        ps.setString(6, "exposterurl.com");
        ps.setString(7, "Ex Genre");
        ps.setString(8, "THEPLOTTHEPLOTTHEPLOT");
        ps.executeUpdate();

        ResultSet newKeys = ps.getGeneratedKeys();
        newKeys.next();
        int newId = newKeys.getInt(1);
        System.out.println("new record id is " + newId);
        newKeys.close();

        // use a Statement and ResultSet to fetch the records from your movies table
        ArrayList<Movie> movies = new ArrayList<>();

        Statement st = connection.createStatement();
        ResultSet movieRows = st.executeQuery("select * from movies");
        // iterate over the rows and print out fields for each row
        while(movieRows.next()) { // for each row in the resultSet
            // make a car object from that row
            Movie movie = new Movie(movieRows.getString("title"),
                    movieRows.getDouble("rating"),
                    movieRows.getString("poster"),
                    movieRows.getInt("year"),
                    movieRows.getString("genre"),
                    movieRows.getString("director"),
                    movieRows.getString("plot"),
                    movieRows.getString("actors"),
                    movieRows.getString("imDbID"),
                    movieRows.getString("runtime"),
                    movieRows.getString("MPAA"),
                    movieRows.getString("trailerURL"),
                    movieRows.getBoolean("favorite"),
                    movieRows.getInt("id"));

            movies.add(movie);
        }

        System.out.println(movies);

        // use a PreparedStatement to update the movie record
        ps = connection.prepareStatement("update movies set title = ? where id = ?");
        ps.setString(1, "Updated Title");
        ps.setInt(2, 1);
        ps.executeUpdate();

        // use a PreparedStatement to delete the movie record
        ps = connection.prepareStatement("delete from movies where id = ?");
        ps.setInt(1, 3);
        ps.executeUpdate();

        // close the connection
        ps.close();
        st.close();
        connection.close();
    }
}
