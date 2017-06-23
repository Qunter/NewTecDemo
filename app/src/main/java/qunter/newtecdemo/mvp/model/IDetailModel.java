package qunter.newtecdemo.mvp.model;

import java.util.List;

import qunter.newtecdemo.entity.NewsEntity;

/**
 * Created by Administrator on 2017/6/23.
 */

public interface IDetailModel {
    void startGetDetail(int id,onDetailListener listener);
    interface onDetailListener{//数据加载完成后的监听回调
        void onComplete(String title,String body);
    }
}
