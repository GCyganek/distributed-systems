package gcyganek.rest.apicaller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import gcyganek.rest.model.ApiCallerEndStatus;
import gcyganek.rest.model.TmdbMovieRatings;
import gcyganek.rest.urlbuilder.TmdbApiUrlBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.HttpStatusCodeException;

public class TmdbApiCaller extends ApiCaller implements Runnable {

    private final Logger logger = LoggerFactory.getLogger(TmdbApiCaller.class);

    public TmdbApiCaller(String imdbId, TmdbMovieRatings movieRatings) {
        this.imdbId = imdbId;
        this.movieRatings = movieRatings;
    }

    @Override
    public void run() {
        String jsonResponseString;
        try {
            jsonResponseString = callRemoteApi(new TmdbApiUrlBuilder());
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

            JsonNode reviewsArrayNode = jsonResponse.get("results");

            if (reviewsArrayNode.isArray()) {
                for (JsonNode reviewNode: reviewsArrayNode) {
                    try {
                        String authorName = reviewNode.get("author_details").get("name").asText();
                        String reviewRating = reviewNode.get("author_details").get("rating").asText();
                        movieRatings.addRating(authorName, reviewRating);
                    }
                    catch (NullPointerException | NumberFormatException e) {
                        continue;
                    }
                }
            }

        } catch (JsonProcessingException e) {
            movieRatings.setEndStatus(ApiCallerEndStatus.INTERNAL_SERVER_ERROR);
            logger.error(e.getMessage());
            return;
        }

        movieRatings.setEndStatus(ApiCallerEndStatus.OK);
    }

}
