package gcyganek.rest.model;

import com.fasterxml.jackson.databind.JsonNode;
import gcyganek.rest.constants.OmdbRatingSources;

import java.util.Objects;

public class OmdbMovieRatings extends MovieRatings<JsonNode> {

    @Override
    public void addRating(JsonNode rating) {
        String ratingValue = rating.get("Value").asText();
        String ratingSource = rating.get("Source").asText();

        if (Objects.equals(ratingSource, OmdbRatingSources.IMDB))
        {
            addImdbRating(ratingValue);
        }
        else if (Objects.equals(ratingSource, OmdbRatingSources.METACRITIC)
                || Objects.equals(ratingSource, OmdbRatingSources.ROTTEN_TOMATOES))
        {
            addRottenTomatoesOrMetacriticRating(ratingValue);
        }
    }

    public void addImdbRating(String rating) throws NumberFormatException {
        movieRatings.add(
                Double.parseDouble(rating.substring(0, 3))
        );
    }

    public void addRottenTomatoesOrMetacriticRating(String rating) {
        movieRatings.add(
                (double) Integer.parseInt(rating.substring(0, 2)) / 10
        );
    }

}
