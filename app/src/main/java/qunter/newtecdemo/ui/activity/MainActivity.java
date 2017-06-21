package qunter.newtecdemo.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import qunter.newtecdemo.R;
import qunter.newtecdemo.entity.NewsEntity;
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
        Call<NewsEntity> call = ComApi.getInstance().getData();
        call.enqueue(new Callback<NewsEntity>() {//异步请求
            @Override
            public void onResponse(Call<NewsEntity> call, final Response<NewsEntity> response) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        /*
                        if (response.isSuccessful() && response.body().isSuccess()) {
                            resultTv.setText(response.body().get().getUpdateContent());
                        } else {
                            resultTv.setText("失败");
                        }
                        */
                        storyEntity = response.body().getStories();
                        Toast.makeText(getApplicationContext(),response.toString(),Toast.LENGTH_SHORT).show();
                        Log.e("onResponse", storyEntity.get(1).getTitle());
                        Toast.makeText(getApplicationContext(),"成功了，老铁",Toast.LENGTH_SHORT).show();
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
}
