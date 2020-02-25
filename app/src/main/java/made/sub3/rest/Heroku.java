package made.sub3.rest;


import made.sub3.model.BasePageable;
import made.sub3.model.Student;
import made.sub3.model.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Heroku {

    @POST("/api/auth/login")
    Call<User> login(@Body User user);

    @GET("/students/getAllStudentPageAble")
    Call<BasePageable<Student>> getStudents(@Header("Authorization") String auth, @Query("page") String page, @Query("size") String size);
    //MOVIE SEARCH AUTOCOMPLETE
//    @GET("/posts")
//    Call<List<MainItem>> search();

//    @POST("/posts/{id}")
//    Call<String> edit(@Body() MainItem mainItem);

//    //TOP RATED MOVIES
//    @GET("movie/top_rated")
//    Call<MoviesResponse> topRated(@Query("api_key") String apiKey);
//
//    //MOVIE DETAIL
//    @GET("/3/movie/{id}")
//    Call<MovieResponse> movieDetails(@Path("id") int movieID, @Query("api_key") String apiKey);
//
//    //MOVIE IMAGES
//    @GET("/movie/{id}/images")
//    Call<ImagesResponse> movieImages(@Query("api_key") String apiKey, @Path("id") int movieID);
}
