package qunter.newtecdemo.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Administrator on 2017/6/19.
 */

// 泛型V ：表示Activity本身，泛型P：表示需要关联的Presenter
public abstract class BaseActivity<V,P extends BasePresenter<V>> extends AppCompatActivity {
    protected P presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = createPresenter();
        presenter.attachView((V)this);
        initViews(savedInstanceState);
    }
    /**
     * 创建一个与之关联的Presenter
     * @return
     */
    protected abstract P  createPresenter();
    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    /**
     * 初始化控件
     */
    protected abstract void initViews(Bundle savedInstanceState);
}
