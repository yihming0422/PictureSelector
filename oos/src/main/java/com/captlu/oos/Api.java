package com.captlu.oos;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Api {

    @POST("/add")
    @FormUrlEncoded
    Observable<Result<Integer>> addVideo(@Field("uri")String uri, @Field("imguri")String imguri, @Field("means")String means, @Field("md5")String md5);
}
