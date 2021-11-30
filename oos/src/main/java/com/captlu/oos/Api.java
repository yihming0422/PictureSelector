package com.captlu.oos;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Api {
    @GET("/{size}")
    Observable<VosBean> getSize(@Path("size")int size);
    @POST("/add")
    @FormUrlEncoded
    Observable<Result<Integer>> addVideo(@Field("uri") String uri, @Field("imguri") String imguri, @Field("means") String means, @Field("md5") String md5);
}
