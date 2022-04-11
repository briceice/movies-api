package data;

public class Movie {

    private String title;
    private Double rating;
    private String poster;
    private Integer year;
    private String genre;
    private String director;
    private String plot;
    private String actors;
    private String imDbID;
    private String runtime;
    private String MPAA;
    private String trailerURL;
    private Boolean favorite;
    private int id;

    public Movie() {
    }

    public Movie(String title, Double rating, String poster, Integer year, String genre, String director, String plot, String actors,
                 String imDbID, String runtime, String MPAA, String trailerURL, Boolean favorite, int id) {
        this.title = title;
        this.rating = rating;
        this.poster = poster;
        this.year = year;
        this.genre = genre;
        this.director = director;
        this.plot = plot;
        this.actors = actors;
        this.imDbID = imDbID;
        this.runtime = runtime;
        this.MPAA = MPAA;
        this.trailerURL = trailerURL;
        this.favorite = favorite;
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getImDbID() {
        return imDbID;
    }

    public void setImDbID(String imDbID) {
        this.imDbID = imDbID;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public String getMPAA() {
        return MPAA;
    }

    public void setMPAA(String MPAA) {
        this.MPAA = MPAA;
    }

    public String getTrailerURL() {
        return trailerURL;
    }

    public void setTrailerURL(String trailerURL) {
        this.trailerURL = trailerURL;
    }

    public Boolean getFavorite() {
        return favorite;
    }

    public void setFavorite(Boolean favorite) {
        this.favorite = favorite;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
