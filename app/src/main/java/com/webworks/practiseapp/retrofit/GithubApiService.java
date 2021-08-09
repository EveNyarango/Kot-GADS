package com.webworks.practiseapp.retrofit;

import com.webworks.practiseapp.model.Repository;
import com.webworks.practiseapp.model.SearchResponse;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public interface GithubApiService {

    @GET("search/repositories")
    Call<SearchResponse> searchRepositories(@QueryMap Map<String, String>options);
 
    @GET("users/{username}/repos")
    Call<List<Repository>>searchRepositoriesByUser(@Path("username") String gitUser);

}
