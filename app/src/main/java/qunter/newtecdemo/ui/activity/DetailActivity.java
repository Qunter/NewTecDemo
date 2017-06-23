package qunter.newtecdemo.ui.activity;

import android.os.Bundle;
import android.view.MenuItem;
import android.webkit.WebView;

import butterknife.BindView;
import butterknife.ButterKnife;
import qunter.newtecdemo.R;
import qunter.newtecdemo.base.BaseActivity;
import qunter.newtecdemo.base.BasePresenter;
import qunter.newtecdemo.mvp.presenter.DetailPresenter;
import qunter.newtecdemo.mvp.view.IDetailView;

/**
 * Created by Administrator on 2017/6/23.
 */

public class DetailActivity extends BaseActivity<IDetailView,DetailPresenter> implements IDetailView{

    @BindView(R.id.webView)
    WebView webView;

    @Override
    protected DetailPresenter createPresenter() {
        return new DetailPresenter(getIntent().getExtras().getInt("id"));
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_story_detail);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        presenter.getDataAndLoad();

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //点击小箭头返回
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void initWebView(String body) {
        webView.loadDataWithBaseURL("file:///android_asset/", body, "text/html", "UTF-8", null);
    }
    /**
     * webView加载数据
     */

}

