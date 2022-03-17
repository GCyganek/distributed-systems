package gcyganek.rest.controller;

import gcyganek.rest.apicaller.OmdbApiCaller;
import gcyganek.rest.apicaller.TmdbApiCaller;
import gcyganek.rest.model.FormWithImdbId;
import gcyganek.rest.model.OmdbMovieRatings;
import gcyganek.rest.model.TmdbMovieRatings;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FormSubmitController {

    @PostMapping("/form-submit")
    public void formSubmittedHandling(@ModelAttribute FormWithImdbId form) {

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

        System.out.println(omdbMovieRatings.getAverageRating());
        omdbMovieRatings.getMovieRatings().forEach(a -> System.out.print(a + " "));
        System.out.println(omdbMovieRatings.getEndStatus());
        System.out.println();
        System.out.println(tmdbMovieRatings.getAverageRating());
        System.out.println(tmdbMovieRatings.getEndStatus());
    }

}
