package com.anthonytepach.app.data.interfaces;

import com.anthonytepach.app.data.model.M_Posts;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ComputerFormsPost {

    @GET("post/{id}")
    Call<M_Posts> getPost(@Path("id") String id);

    @GET("posts")
    Call<List<M_Posts>> getPosts();
}
