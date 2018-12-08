package swl.bwie.com.mvp181205.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ListView;

import com.recker.flybanner.FlyBanner;

import java.util.ArrayList;
import java.util.List;

import swl.bwie.com.mvp181205.R;
import swl.bwie.com.mvp181205.adapter.MyBaseAdapter;
import swl.bwie.com.mvp181205.bean.NewsBean;
import swl.bwie.com.mvp181205.presenter.LoginPresenter;
import swl.bwie.com.mvp181205.view.IView;

public class DataFragment extends Fragment implements IView {
    private ListView listView;
    private MyBaseAdapter adapter;
    private FlyBanner flyBanner;
    private String path = "http://api.expoon.com/AppNews/getNewsList/type/1/p/1";
    private LoginPresenter loginpresenter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_data,container,false);

        listView = view.findViewById(R.id.lv);
        flyBanner = view.findViewById(R.id.banner);
        adapter = new MyBaseAdapter(getActivity());
        listView.setAdapter(adapter);
        loginpresenter = new LoginPresenter(this);
        loginpresenter.show();

        List<String> list=new ArrayList<>();
        list.add("http://f.expoon.com/sub/news/2016/01/21/887844_230x162_0.jpg");
        list.add("http://f.expoon.com/sub/news/2016/01/21/580828_230x162_0.jpg");
        list.add("http://f.expoon.com/sub/news/2016/01/21/745921_230x162_0.jpg");
        flyBanner.setImagesUrl(list);

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        loginpresenter.onDetach();
    }

    @Override
    public void showData(Object data){
        NewsBean newsBean= (NewsBean)data;
        List<NewsBean.DataBean> data1 = newsBean.getData();
        adapter.setData(data1);
    }
}
