package main;

import Dao.InMemoryMoviesDao;
import Dao.MySqlMoviesDao;
import com.google.gson.Gson;
import data.Movie;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name="MovieServlet", urlPatterns="/movies/*")
public class MovieServlet extends HttpServlet {

//    InMemoryMoviesDao moviesDao = new InMemoryMoviesDao();
    MySqlMoviesDao moviesDao = new MySqlMoviesDao();

    public boolean isNumeric(String string){
        try {
            Double d = Double.parseDouble(string);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response){
        response.setContentType("application/json");
        PrintWriter out;
        try {
            out = response.getWriter();
            String [] uriParts = request.getRequestURI().split("/");
            String movieString;
            if (isNumeric(uriParts[uriParts.length - 1])){
                int targetId = Integer.parseInt(uriParts[uriParts.length - 1]);
                movieString = new Gson().toJson(moviesDao.findOne(targetId));
            } else {
                movieString = new Gson().toJson(moviesDao.all().toArray());
            }
            out.println(movieString);
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        Movie[] newMovies = new Gson().fromJson(request.getReader(), Movie[].class);
        PrintWriter out;
        try {
            moviesDao.insertAll(newMovies);
            out = response.getWriter();
            out.println("Movie(s) added");
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");

        String [] uriParts = request.getRequestURI().split("/");
        int targetId = Integer.parseInt(uriParts[uriParts.length - 1]);

        Movie updateMovie = new Gson().fromJson(request.getReader(), Movie.class);
        updateMovie.setId(targetId);

        PrintWriter out;
        try {
            moviesDao.update(updateMovie);
            out = response.getWriter();
            out.println("Movie UPDATE successful");
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response){
        response.setContentType("application/json");

        String [] uriParts = request.getRequestURI().split("/");
        int targetId = Integer.parseInt(uriParts[uriParts.length - 1]);

        try {
            moviesDao.delete(targetId);
            PrintWriter out = response.getWriter();
            out.println("Movie DELETE successful");
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void destroy() {
        moviesDao.cleanUp();
        super.destroy();
    }
}
