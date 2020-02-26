package learning.shinesdev.mymoviesapi.data.api.response;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import learning.shinesdev.mymoviesapi.model.MovieEntity;

@SuppressWarnings("ALL")
public class MovieApiResponse {

    public MovieApiResponse() {
        this.results = new ArrayList<>();
    }

    @SerializedName("page")
    private long page;

    @SerializedName("total_pages")
    private long totalPages;

    @SerializedName("total_results")
    private long totalResults;

    @SerializedName("results")
    private List<MovieEntity> results;

    public long getPage() {
        return page;
    }

    public void setPage(long page) {
        this.page = page;
    }

    public long getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(long totalPages) {
        this.totalPages = totalPages;
    }

    public long getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(long totalResults) {
        this.totalResults = totalResults;
    }

    public List<MovieEntity> getResults() {
        return results;
    }

    public void setResults(List<MovieEntity> results) {
        this.results = results;
    }
}
