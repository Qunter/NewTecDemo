package qunter.newtecdemo.util;

import qunter.newtecdemo.entity.DetailEntity;
import qunter.newtecdemo.entity.NewsEntity;
import qunter.newtecdemo.entity.StoryDetailsEntity;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

public interface ComInterface {
    @GET("theme/7")
    Call<NewsEntity> getNews();
    @GET("news/{id}")
    Call<StoryDetailsEntity> getDetail(@Path("id") int id);
}