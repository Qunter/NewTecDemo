package qunter.newtecdemo.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.youth.banner.Banner;

import butterknife.BindView;
import butterknife.ButterKnife;
import qunter.newtecdemo.R;
import qunter.newtecdemo.ui.base.BaseActivity;


public class MainActivity extends BaseActivity {
    @BindView(R.id.main_banner)
    Banner banner;
    @BindView(R.id.main_recycler)
    RecyclerView recyclerView;
    @Override
    protected void initVariablesAndService() {

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }
}
