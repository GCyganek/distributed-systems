package gcyganek.rest.urlbuilder;

import gcyganek.rest.urltemplate.OmdbApiUrlTemplates;

import java.text.MessageFormat;

public class OmdbApiUrlBuilder {

    public OmdbApiUrlBuilder() {
    }

    public String buildMovieDataUrl(String imdbId) {
        return MessageFormat.format(OmdbApiUrlTemplates.MOVIE_DATA_URL, imdbId);
    }
}
