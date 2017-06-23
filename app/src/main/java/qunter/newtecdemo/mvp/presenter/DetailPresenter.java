package qunter.newtecdemo.mvp.presenter;

import qunter.newtecdemo.base.BasePresenter;
import qunter.newtecdemo.mvp.model.DetailModel;
import qunter.newtecdemo.mvp.model.IDetailModel;
import qunter.newtecdemo.mvp.view.IDetailView;

/**
 * Created by Administrator on 2017/6/23.
 */

public class DetailPresenter extends BasePresenter<IDetailView> {
    IDetailModel iDetailModel;
    int id;
    public DetailPresenter(int id){
        iDetailModel = new DetailModel();
        this.id = id;
    }
    public void getDataAndLoad(){
        if (getView() != null && iDetailModel != null) {
            // 加载数据
            iDetailModel.startGetDetail(id, new IDetailModel.onDetailListener() {
                @Override
                public void onComplete(String title, String body) {
                    getView().initWebView(body);
                }
            });
        }
    }

}
