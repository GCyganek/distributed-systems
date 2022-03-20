package gcyganek.rest.model;

import gcyganek.rest.util.DoubleFormatter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

public abstract class MovieRatings {
    protected Map<String, Double> movieRatings = new HashMap<>();
    protected ApiCallerEndStatus endStatus;
    protected ApiName apiName;
    protected String movieName;

    public Map<String, Double> getMovieRatings() {
        return movieRatings;
    }

    public ApiCallerEndStatus getEndStatus() {
        return endStatus;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public void setEndStatus(ApiCallerEndStatus endStatus) {
        this.endStatus = endStatus;
    }

    public ApiName getApiName() {
        return apiName;
    }

    public double getAverageRating() {
        double sum = movieRatings.values().stream().reduce(0.0, Double::sum);
        double result = sum / movieRatings.size();
        return DoubleFormatter.trimDoubleToOneDecimalPlace(result);
    }

    public abstract void addRating(String name, String rating);
}
