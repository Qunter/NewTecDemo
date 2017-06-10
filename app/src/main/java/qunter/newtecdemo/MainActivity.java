package qunter.newtecdemo;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import qunter.newtecdemo.components.DaggerUserComponet;


public class MainActivity extends AppCompatActivity {
    @Inject
    ApiService mApiService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //rebuild一下,自动生成 DaggerUserComponet 类
        DaggerUserComponet.create().inject(this);
        mApiService.register();
    }
}
