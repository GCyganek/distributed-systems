package gcyganek.rest.model;

import java.util.ArrayList;
import java.util.List;

public abstract class MovieRatings<T> {
    protected List<Double> movieRatings = new ArrayList<>();
    protected ApiCallerEndStatus endStatus;

    public List<Double> getMovieRatings() {
        return movieRatings;
    }

    public ApiCallerEndStatus getEndStatus() {
        return endStatus;
    }

    public void setEndStatus(ApiCallerEndStatus endStatus) {
        this.endStatus = endStatus;
    }

    public double getAverageRating() {
        double sum = movieRatings.stream().reduce(0.0, Double::sum);
        return sum / movieRatings.size();
    }

    public abstract void addRating(T rating);
}
