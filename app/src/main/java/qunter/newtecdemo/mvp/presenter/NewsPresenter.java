package qunter.newtecdemo.mvp.presenter;

import java.util.List;

import qunter.newtecdemo.base.BasePresenter;
import qunter.newtecdemo.entity.NewsEntity;
import qunter.newtecdemo.mvp.model.INewsModel;
import qunter.newtecdemo.mvp.model.NewsModel;
import qunter.newtecdemo.mvp.view.IMainView;

/**
 * Created by Administrator on 2017/6/23.
 */

public class NewsPresenter extends BasePresenter<IMainView>{
    INewsModel iNewsModel;
    public NewsPresenter(){
        iNewsModel = new NewsModel();
    }
    public void getDataAndLoad(){
        if (getView() != null && iNewsModel != null) {
            // 加载数据
            iNewsModel.startGetData(new INewsModel.onDataListener() {
                @Override
                public void onComplete(List<NewsEntity.StoriesBean> storyEntity) {
                    getView().initRecycleView(storyEntity);
                }
            });
        }
    }

}
