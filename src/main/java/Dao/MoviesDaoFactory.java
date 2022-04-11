package Dao;

public class MoviesDaoFactory {

    public enum DaoType {MYSQL, IN_MEMORY}

    public static MoviesDao getMoviesDao(DaoType daoType) {
        switch (daoType){
            case MYSQL:
                return new MySqlMoviesDao();
            case IN_MEMORY:
                return new InMemoryMoviesDao();
            default:
                return null;
        }
    }
}
