package gcyganek.rest.urlbuilder;

import gcyganek.rest.apikeys.ApiKeys;
import gcyganek.rest.constants.urltemplate.OmdbApiUrlTemplates;

import java.text.MessageFormat;

public class OmdbApiUrlBuilder implements ApiUrlBuilder {

    public OmdbApiUrlBuilder() {
    }

    public String buildMovieDataUrl(String imdbId) {
        return MessageFormat.format(OmdbApiUrlTemplates.MOVIE_DATA_URL, ApiKeys.OMDb_API_KEY, imdbId);
    }
}
