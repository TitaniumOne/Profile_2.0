package com.example.carson_ho.toptabbar.API;


import com.example.carson_ho.toptabbar.Zhihu;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Yvan on 2016/6/1.
 */
public interface ZhihuApi {

    @GET("api/4/news/latest")
    Call<Zhihu> getZhihuNewsLatest();
}
