package saftyclock.luminous.com.httpexecute.retrofit;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by anupamchugh on 09/01/17.
 */

public interface APIInterface {


    // https://reqres.in/api/users/2
    @POST("/api/users")
    Call<User> createUser(@Body User user);





    // https://reqres.in/api/users?page=2
    @GET("/api/users?")
    Call<UserList> doGetUserList(@Query("page") String page);





    /*
    https://reqres.in/api/users?
    {
        "name": "morpheus",
            "job": "leader"
    }
    */
    @FormUrlEncoded
    @POST("/api/users?")
    Call<UserList> doCreateUserWithField(@Field("name") String name, @Field("job") String job);




}
