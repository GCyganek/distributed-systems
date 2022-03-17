package gcyganek.rest.urlbuilder;

import gcyganek.rest.urltemplate.TmdbApiUrlTemplates;

import java.text.MessageFormat;

public class TmdbApiUrlBuilder {

    public TmdbApiUrlBuilder() {
    }

    public String buildMovieReviewsUrl(String imdbId) {
        return MessageFormat.format(TmdbApiUrlTemplates.MOVIE_REVIEWS_URL, imdbId);
    }
}
