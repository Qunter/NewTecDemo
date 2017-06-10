package qunter.newtecdemo.module;

import android.util.Log;

import dagger.Module;
import dagger.Provides;
import qunter.newtecdemo.ApiService;

/**
 * Created by Administrator on 2017/6/10.
 */

@Module
public class UserModule {
    @Provides
    public ApiService provideApiService(){
        Log.e("UserModule","provideUserManger");
        return new ApiService();
    }
}
