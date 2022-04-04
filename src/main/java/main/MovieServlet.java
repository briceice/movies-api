package main;

import com.google.gson.Gson;
import data.Movie;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name="MovieServlet", urlPatterns="/movies/*")
public class MovieServlet extends HttpServlet {

    ArrayList<Movie> movies = new ArrayList<>();
    int nextId = 1;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response){
        response.setContentType("application/json");
        PrintWriter out;
        try {
            out = response.getWriter();
            String movieString = new Gson().toJson(movies.toArray());
            out.println(movieString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");

        Movie[] newMovies = new Gson().fromJson(request.getReader(), Movie[].class);
        for (Movie movie : newMovies){
            movie.setId(nextId++);
            movies.add(movie);
        }

        PrintWriter out;
        try {
            out = response.getWriter();
            out.println("Movie(s) added");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");

        String [] uriParts = request.getRequestURI().split("/");
        int targetId = Integer.parseInt(uriParts[uriParts.length - 1]);

        Movie updateMovie = new Gson().fromJson(request.getReader(), Movie.class);

        for (Movie movie : movies){
            if (movie.getId() == targetId){
                movie.setTitle(updateMovie.getTitle());
                movie.setRating(updateMovie.getRating());
                movie.setPoster(updateMovie.getPoster());
                movie.setYear(updateMovie.getYear());
                movie.setGenre(updateMovie.getGenre());
                movie.setDirector(updateMovie.getDirector());
                movie.setPlot(updateMovie.getPlot());
                movie.setActors(updateMovie.getActors());
            }
        }

        PrintWriter out;
        try {
            out = response.getWriter();
            out.println("Movie UPDATE successful");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
