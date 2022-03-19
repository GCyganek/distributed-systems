package gcyganek.rest.model;

public class TmdbMovieRatings extends MovieRatings {

    public TmdbMovieRatings() {
        this.apiName = ApiName.TMDB_API;
    }

    public void addRating(String name, String rating) throws NumberFormatException, NullPointerException {
        movieRatings.put(name, Double.valueOf(rating));
    }
}
