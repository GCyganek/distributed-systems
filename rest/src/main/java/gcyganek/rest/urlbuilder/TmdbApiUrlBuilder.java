package gcyganek.rest.urlbuilder;

import gcyganek.rest.apikeys.ApiKeys;
import gcyganek.rest.constants.urltemplate.TmdbApiUrlTemplates;

import java.text.MessageFormat;

public class TmdbApiUrlBuilder implements ApiUrlBuilder {

    public TmdbApiUrlBuilder() {
    }

    public String buildMovieDataUrl(String imdbId) {
        return MessageFormat.format(TmdbApiUrlTemplates.MOVIE_REVIEWS_URL, imdbId, ApiKeys.TMDB_API_KEY);
    }
}
