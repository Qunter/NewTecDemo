package qunter.newtecdemo.mvp.view;

import java.util.List;

import qunter.newtecdemo.entity.NewsEntity;

/**
 * Created by Administrator on 2017/6/23.
 */

public interface IMainView {
    void initRecycleView(List<NewsEntity.StoriesBean> storyEntity);
}
