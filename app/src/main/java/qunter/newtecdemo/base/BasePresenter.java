package qunter.newtecdemo.base;

import java.lang.ref.WeakReference;

// 泛型V:表示与之关联的Activity
public class BasePresenter<V> {
    //View 的引用
    protected WeakReference<V> mReference;//使用弱引用,避免内存泄漏

    /**
     * 连接上View模型，类型于Activity与Fragment的连接onTachActivity()
     * @param view
     */
    public void attachView(V view){
        mReference=new WeakReference<V>(view);
    }
    /**
     * 断开与View模型的连接，类型于Activity与Fragment的断开ondeTachActivity()
     * 防止后面做一些无用的事情
     */
    public void detachView(){
        if (mReference!=null) {
            mReference.clear();
        }
    }
    protected V getView(){
        return mReference.get();
    }
}