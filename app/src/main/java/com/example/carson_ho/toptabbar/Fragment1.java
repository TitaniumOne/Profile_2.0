package com.example.carson_ho.toptabbar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.example.carson_ho.toptabbar.API.ZhihuApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Carson_Ho on 16/7/22.
 */
public class Fragment1 extends Fragment {

    public static final String BASE_URL = "http://news-at.zhihu.com/";
    ConvenientBanner convenientBanner;
    View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment1,container,false);

        convenientBanner = (ConvenientBanner)view.findViewById(R.id.convenientBanner);

        Retrofit retrofit = new Retrofit.Builder()//获取Retrofit对象
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())//采用链式结构绑定Base url
                .build();//执行操作
        //获取API接口的实现类的实例对象
        ZhihuApi service = retrofit.create(ZhihuApi.class);
        Call<Zhihu> call =  service.getZhihuNewsLatest();
        call.enqueue(new Callback<Zhihu>() {
            @Override
            public void onResponse(Call<Zhihu> call, Response<Zhihu> response) {
                System.out.println("onResponse==================================");
                System.out.println(response.body().getDate());
                List<Story> storiesList = response.body().getStories();
                //循环打印获取的数据
                for(Story stories : storiesList){
                    List<String> imagesList = stories.getImages();
                    for(String imagesurl : imagesList){
                        System.out.println(imagesurl);
                    }
                    System.out.println("type:" + stories.getType());
                    System.out.println("id:" + stories.getId());
                    System.out.println("ga_prefix:" + stories.getGaPrefix());
                    System.out.println("title:" + stories.getTitle());
                }

                System.out.println("stories:");
                List<TopStory> top_storiesList = response.body().getTopStories();
                for(TopStory topStory : top_storiesList){
                    System.out.println("image:" + topStory.getImage());
                    System.out.println("type:" + topStory.getType());
                    System.out.println("id:" + topStory.getId());
                    System.out.println("ga_prefix:" + topStory.getGaPrefix());
                    System.out.println("title:" + topStory.getTitle());
                }
                //将相关数据显示在轮播控件上
                convenientBanner.setPages(new CBViewHolderCreator<BannerHolderView>() {
                    @Override
                    public BannerHolderView createHolder() {
                        return new BannerHolderView();
                    }
                },top_storiesList)
                        .setPageIndicator(new int[]{R.mipmap.dot_blur,R.mipmap.dot_focus})
                        .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT);
                convenientBanner.setScrollDuration(1000);
                convenientBanner.startTurning(5000);

            }

            @Override
            public void onFailure(Call<Zhihu> call, Throwable t) {
                System.out.println("onFailure========================================");
            }
        });
        return view;
    }

}
