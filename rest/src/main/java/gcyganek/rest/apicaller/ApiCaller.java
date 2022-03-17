package gcyganek.rest.apicaller;

import gcyganek.rest.model.ApiCallerEndStatus;
import gcyganek.rest.model.MovieRatings;
import gcyganek.rest.urlbuilder.ApiUrlBuilder;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

public abstract class ApiCaller {

    protected String imdbId;
    protected MovieRatings movieRatings;

    protected String callRemoteApi(ApiUrlBuilder urlBuilder) throws HttpStatusCodeException {
        String movieDataUrl = urlBuilder.buildMovieDataUrl(imdbId);

        RestTemplate restTemplate = new RestTemplate();

        return restTemplate.getForObject(movieDataUrl, String.class);
    }

    protected void handleResponseError(HttpStatusCodeException e, Logger logger) {
        if (e.getStatusCode() == HttpStatus.UNAUTHORIZED) {
            movieRatings.setEndStatus(ApiCallerEndStatus.API_KEY_ERROR);
            logger.warn("Api key invalid!");
        }
        else if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
            movieRatings.setEndStatus(ApiCallerEndStatus.MOVIE_NOT_FOUND);
        }
        else {
            movieRatings.setEndStatus(ApiCallerEndStatus.API_ERROR);
            logger.warn("Failed to receive data from api: " + e.getStatusCode() + " " + e.getResponseBodyAsString());
        }
    }
}
