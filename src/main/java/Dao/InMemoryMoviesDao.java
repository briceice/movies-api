package Dao;

import data.Movie;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InMemoryMoviesDao implements MoviesDao {
    private ArrayList<Movie> movies = new ArrayList<>();
    private int nextId = 1;

    @Override
    public List<Movie> all() throws SQLException {
        return movies;
    }

    @Override
    public void insertAll(Movie[] newMovies) throws SQLException {
        // setting the id of each movie, and incrementing the nextId
        // AND adding each movie to the movies list
        for(Movie movie : newMovies) {
            movie.setId(nextId++);
            movies.add(movie);
        }
    }

    @Override
    public void update(Movie updatedMovie) throws SQLException {
        for (Movie movie : movies) {
            if(movie.getId() == updatedMovie.getId()) {
                if(updatedMovie.getTitle() != null) {
                    movie.setTitle(updatedMovie.getTitle());
                }
                if(updatedMovie.getRating() != null) {
                    movie.setRating(updatedMovie.getRating());
                }
                if(updatedMovie.getPoster() != null) {
                    movie.setPoster(updatedMovie.getPoster());
                }
                if(updatedMovie.getYear() != null) {
                    movie.setYear(updatedMovie.getYear());
                }
                if(updatedMovie.getGenre() != null) {
                    movie.setGenre(updatedMovie.getGenre());
                }
                if(updatedMovie.getPlot() != null) {
                    movie.setPlot(updatedMovie.getPlot());
                }
                if(updatedMovie.getDirector() != null) {
                    movie.setDirector(updatedMovie.getDirector());
                }
            }
        }
    }

    @Override
    public void delete(int targetId) throws SQLException {
        // find the movie in our movies arraylist with id = targetId
        for (int i = 0; i < movies.size(); i++) {
            Movie movie = movies.get(i);
            if(movie.getId() == targetId) { // delete it if you find it
                movies.remove(i);
                break;
            }
        }
    }

    @Override
    public void cleanUp() throws SQLException {

    }

    // NO SUPPORT YET
    @Override
    public Movie findOne(int id) {
        return null;
    }

    // NO SUPPORT YET
    @Override
    public void insert(Movie movie) {

    }

}