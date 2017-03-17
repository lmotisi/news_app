package com.example.lmotisi.myapp;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIInterface {

    @GET("?json=get_recent_posts")
    Call<Response> getLastPosts();

    @GET("?json=get_category_posts")
    Call<Response> getPostsByCategory(@Query("id") int categoryId);

    @GET("?json=get_category_index")
    Call<ListCategories> getCategories();


}
