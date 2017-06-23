package qunter.newtecdemo.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import qunter.newtecdemo.R;
import qunter.newtecdemo.base.BaseActivity;
import qunter.newtecdemo.entity.NewsEntity;
import qunter.newtecdemo.mvp.presenter.NewsPresenter;
import qunter.newtecdemo.mvp.view.IMainView;
import qunter.newtecdemo.ui.adapter.NewsAdapter;


public class MainActivity extends BaseActivity<IMainView,NewsPresenter> implements IMainView {
    @BindView(R.id.main_recycler)
    RecyclerView recyclerView;

    @Override
    protected NewsPresenter createPresenter() {
        return new NewsPresenter();
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        presenter.getDataAndLoad();
    }

    /**
     * 初始化recycleView
     */
    @Override
    public void initRecycleView(List<NewsEntity.StoriesBean> storyEntity) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        NewsAdapter adapter = new NewsAdapter(getApplicationContext(),storyEntity,recyclerView);
        recyclerView.setAdapter(adapter);
    }
}
