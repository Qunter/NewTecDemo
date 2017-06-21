package qunter.newtecdemo.util;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;

public class NetClient {
    //暂时放在这里
    private static final String URL = "https://news-at.zhihu.com/api/4/theme/";

    /**
     * 获取Retrofit适配器。
     *
     * @return 网络适配器
     */
    public static Retrofit newRetrofit() {
        return new Retrofit.Builder().baseUrl(URL).client(getClient().build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    /**
     * 获取 OkHttpClient
     *
     * @return OkHttpClient
     */
    public static OkHttpClient.Builder getClient() {
        return new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(300, TimeUnit.SECONDS)
                .addInterceptor(new HttpLoggingInterceptor());//日志


    }
}