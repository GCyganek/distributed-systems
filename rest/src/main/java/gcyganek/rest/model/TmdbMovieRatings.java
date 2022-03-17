package gcyganek.rest.model;

public class TmdbMovieRatings extends MovieRatings<Double> {

    @Override
    public void addRating(Double rating) {
        movieRatings.add(rating);
    }
}
