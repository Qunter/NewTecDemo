package qunter.newtecdemo.util;

import android.support.annotation.NonNull;

import org.reactivestreams.Publisher;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.FlowableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.internal.schedulers.IoScheduler;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BaseComApi {


    /**
     *
     * @param call retrofit的call
     * @param <T>  泛型
     * @return Flowable
     */
    public static <T> Flowable<T> create(@NonNull final Call<T> call) {
        return Flowable.create(new FlowableOnSubscribe<T>() {
            @Override
            public void subscribe(final FlowableEmitter<T> e) throws Exception {
                call.enqueue(new Callback<T>() {
                    @Override
                    public void onResponse(Call<T> call, Response<T> response) {
                        e.onNext(response.body());
                        e.onComplete();
                        call.cancel();
                    }

                    @Override
                    public void onFailure(Call<T> call, Throwable t) {
                        e.onError(t);
                        e.onComplete();
                        call.cancel();
                    }
                });
            }
        }, BackpressureStrategy.BUFFER);
    }

    /**
     * 后台线程执行同步，主线程执行异步操作
     * 并且拦截所有错误，不让app崩溃
     *
     * @param <T> 数据类型
     * @return Transformer
     */
    public static <T> FlowableTransformer<T, T> background() {
        return new FlowableTransformer<T, T>() {
            @Override
            public Publisher<T> apply(Flowable<T> upstream) {
                return upstream.subscribeOn(new IoScheduler())
                        .observeOn(AndroidSchedulers.mainThread())
                        .onErrorResumeNext(Flowable.<T>empty());
            }
        };
    }

}