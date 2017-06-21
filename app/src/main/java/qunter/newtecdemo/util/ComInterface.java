package qunter.newtecdemo.util;

import qunter.newtecdemo.entity.NewsEntity;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ComInterface {
    @GET("7")
    Call<NewsEntity> getNews();

}