package com.example.retrofit_lib;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("/todos")
    Call<List<Todo>> getTodos();//list all the todos
    @GET("/todos/{id}")
    Call<Todo> getTodo( @Path("id") int i);
    @GET("/todos")
    Call<List<Todo>> getTodoUsingQuery(@Query("UserId") int UserId,@Query("completed") boolean completed);
    @POST("/todos")
    Call<Todo> postTodo(@Body Todo todo);

}
