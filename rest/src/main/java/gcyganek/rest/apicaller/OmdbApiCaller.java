package gcyganek.rest.apicaller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import gcyganek.rest.model.ApiCallerEndStatus;
import gcyganek.rest.model.OmdbMovieRatings;
import gcyganek.rest.urlbuilder.OmdbApiUrlBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.HttpStatusCodeException;

import java.util.Objects;

public class OmdbApiCaller extends ApiCaller implements Runnable {

    private final Logger logger = LoggerFactory.getLogger(OmdbApiCaller.class);

    public OmdbApiCaller(String imdbId, OmdbMovieRatings movieRatings) {
        this.imdbId = imdbId;
        this.movieRatings = movieRatings;
    }

    @Override
    public void run() {
        String jsonResponseString;
        try {
            jsonResponseString = callRemoteApi(new OmdbApiUrlBuilder());
        } catch (HttpStatusCodeException e) {
            handleResponseError(e, logger);
            return;
        }

        if (jsonResponseString == null) {
            movieRatings.setEndStatus(ApiCallerEndStatus.API_ERROR);
            return;
        }

        ObjectMapper mapper = new ObjectMapper();
        try {

            JsonNode jsonResponse = mapper.readTree(jsonResponseString);

            boolean requestWasValid = checkIfMovieWasFound(jsonResponse);
            if (!requestWasValid) return;

            JsonNode reviewsArrayNode = jsonResponse.get("Ratings");

            movieRatings.setMovieName(jsonResponse.get("Title").asText());

            if (reviewsArrayNode.isArray()) {
                for (JsonNode reviewNode: reviewsArrayNode) {
                    String ratingValue = reviewNode.get("Value").asText();
                    String ratingSource = reviewNode.get("Source").asText();
                    movieRatings.addRating(ratingSource, ratingValue);
                }
            }

        } catch (JsonProcessingException e) {
            logger.error(e.getMessage());
            movieRatings.setEndStatus(ApiCallerEndStatus.INTERNAL_SERVER_ERROR);
            return;
        }

        movieRatings.setEndStatus(ApiCallerEndStatus.OK);
    }


    private boolean checkIfMovieWasFound(JsonNode jsonResponse) {
        JsonNode response = jsonResponse.get("Error");

        if (response != null) {
            String responseString = response.asText();

            if (Objects.equals(responseString, "Incorrect IMDb ID.")) {
                movieRatings.setEndStatus(ApiCallerEndStatus.MOVIE_NOT_FOUND);
            }
            else {
                movieRatings.setEndStatus(ApiCallerEndStatus.API_ERROR);
            }

            return false;
        }

        return true;
    }
}
