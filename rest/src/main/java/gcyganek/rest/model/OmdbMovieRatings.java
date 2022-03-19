package gcyganek.rest.model;

import gcyganek.rest.constants.OmdbRatingSources;

import java.util.Objects;

public class OmdbMovieRatings extends MovieRatings {

    public OmdbMovieRatings() {
        this.apiName = ApiName.OMDb_API;
    }

    public void addRating(String name, String rating) {

        if (Objects.equals(name, OmdbRatingSources.IMDB))
        {
            addImdbRating(name, rating);
        }
        else if (Objects.equals(name, OmdbRatingSources.ROTTEN_TOMATOES))
        {
            addRottenTomatoesRating(name, rating);
        }
        else if (Objects.equals(name, OmdbRatingSources.METACRITIC))
        {
            addMetacriticRating(name, rating);
        }
    }

    public void addImdbRating(String name, String rating) throws NumberFormatException {
        movieRatings.put(
                name, Double.parseDouble(rating.substring(0, 3))
        );
    }

    public void addRottenTomatoesRating(String name, String rating) {
        movieRatings.put(
                name, (double) Integer.parseInt(rating.split("%")[0]) / 10
        );
    }

    public void addMetacriticRating(String name, String rating) {
        movieRatings.put(
                name, (double) Integer.parseInt(rating.split("/")[0]) / 10
        );
    }

}
