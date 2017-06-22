package qunter.newtecdemo.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.functions.Consumer;
import qunter.newtecdemo.R;
import qunter.newtecdemo.entity.NewsEntity;
import qunter.newtecdemo.ui.adapter.NewsAdapter;
import qunter.newtecdemo.ui.base.BaseActivity;
import qunter.newtecdemo.util.ComApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends BaseActivity {
    @BindView(R.id.main_banner)
    Banner banner;
    @BindView(R.id.main_recycler)
    RecyclerView recyclerView;
    private final int GETDATA=0x00;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==GETDATA){
                LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                recyclerView.setLayoutManager(layoutManager);
                NewsAdapter adapter = new NewsAdapter(getApplicationContext(),storyEntity,recyclerView);
                recyclerView.setAdapter(adapter);
            }
        }
    };
    private List<NewsEntity.StoriesBean> storyEntity = new ArrayList<NewsEntity.StoriesBean>();
    @Override
    protected void initVariablesAndService() {

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        start();
    }
    //开始请求
    public void start() {
        ComApi.getInstance().getData()
                .doOnNext(new Consumer<NewsEntity>() {
                    @Override
                    public void accept(NewsEntity newsEntity) throws Exception {
                        storyEntity = newsEntity.getStories();
                        handler.sendEmptyMessage(GETDATA);
                    }
                })
                .doOnError(new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                    }
                })
                .subscribe();
    }
    //Retrofit开始请求
    /*
    public void start() {
        Call<NewsEntity> call = ComApi.getInstance().getData();
        call.enqueue(new Callback<NewsEntity>() {//异步请求
            @Override
            public void onResponse(Call<NewsEntity> call, final Response<NewsEntity> response) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        storyEntity = response.body().getStories();
                        handler.sendEmptyMessage(GETDATA);
                    }
                });
            }

            @Override
            public void onFailure(Call<NewsEntity> call, final Throwable t) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),"失败了，老铁",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
    */
}
