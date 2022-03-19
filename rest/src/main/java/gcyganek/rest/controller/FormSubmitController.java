package gcyganek.rest.controller;

import gcyganek.rest.apicaller.OmdbApiCaller;
import gcyganek.rest.apicaller.TmdbApiCaller;
import gcyganek.rest.model.*;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class FormSubmitController {

    @PostMapping(value = "/form-submit", produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView formSubmittedHandling(@ModelAttribute FormWithImdbId form) {

        OmdbMovieRatings omdbMovieRatings = new OmdbMovieRatings();
        TmdbMovieRatings tmdbMovieRatings = new TmdbMovieRatings();

        Thread omdbApiCaller = new Thread(new OmdbApiCaller(form.imdbId(), omdbMovieRatings));
        Thread tmdbApiCaller = new Thread(new TmdbApiCaller(form.imdbId(), tmdbMovieRatings));

        omdbApiCaller.start();
        tmdbApiCaller.start();

        try {
            omdbApiCaller.join();
            tmdbApiCaller.join();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }

        return createAndGetModelAndView(List.of(tmdbMovieRatings, omdbMovieRatings), omdbMovieRatings.getMovieName());
    }

    private ModelAndView createAndGetModelAndView(List<MovieRatings> movieRatingsList, String movieTitle) {
        ModelAndView mav = new ModelAndView("movie-ratings");

        movieRatingsList.forEach(movieRatings -> processMovieRatings(mav, movieRatings));

        mav.addObject("movieTitle", movieTitle);
        mav.addObject("averageMovieRating", getAverageMovieRatingFromMovieRatingsList(movieRatingsList));

        return mav;
    }

    private double getAverageMovieRatingFromMovieRatingsList(List<MovieRatings> movieRatingsList) {
        List<MovieRatings> sourcesToConsiderList = movieRatingsList.stream()
                .filter(movieRatings -> movieRatings.getEndStatus() == ApiCallerEndStatus.OK
                            && movieRatings.getMovieRatings().size() != 0)
                .toList();

        if (sourcesToConsiderList.size() == 0)
            return -1;

        return sourcesToConsiderList.stream()
                .map(MovieRatings::getAverageRating)
                .reduce(0.0, Double::sum) / sourcesToConsiderList.size();
    }

    private void processMovieRatings(ModelAndView mav, MovieRatings movieRatings) {
        ApiCallerEndStatus endStatus = movieRatings.getEndStatus();

        if (endStatus == ApiCallerEndStatus.OK) {
            String attributeName = getMovieRatingsAttributeName(movieRatings);
            mav.addObject(attributeName, movieRatings);
        }
        else {
            String attributeName = getErrorAttributeName(movieRatings);
            mav.addObject(attributeName, endStatus);
        }
    }

    private String getMovieRatingsAttributeName(MovieRatings movieRatings) {
        return switch (movieRatings.getApiName()) {
            case OMDb_API -> "omdbMovieRatings";
            case TMDB_API -> "tmdbMovieRatings";
        };
    }

    private String getErrorAttributeName(MovieRatings movieRatings) {
        return switch (movieRatings.getApiName()) {
            case OMDb_API -> "omdbMovieRatingsError";
            case TMDB_API -> "tmdbMovieRatingsError";
        };
    }

}
