package swl.bwie.com.mvp181205.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

import swl.bwie.com.mvp181205.R;
import swl.bwie.com.mvp181205.adapter.MyBaseAdapter;
import swl.bwie.com.mvp181205.bean.NewsBean;
import swl.bwie.com.mvp181205.presenter.LoginPresenter;
import swl.bwie.com.mvp181205.view.fragment.CardFragment;
import swl.bwie.com.mvp181205.view.fragment.DataFragment;

public class LoginActivity extends AppCompatActivity{

    private ViewPager viewPager;
    private List<Fragment> flist;
    private RadioGroup group;
//    private ListView listView;
//    private MyBaseAdapter md;
//    private LoginPresenter loginpresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
//        listView = findViewById(R.id.lv);
//        md = new MyBaseAdapter(this);
//        listView.setAdapter(md);
//
//
//        loginpresenter = new LoginPresenter(this);
//
//        loginpresenter.show();
        viewPager = findViewById(R.id.viewpager);
        group = findViewById(R.id.group);
        flist = new ArrayList<>();
        flist.add(new DataFragment());
        flist.add(new CardFragment());
        FragmentPagerAdapter fadapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return flist.get(i);
            }

            @Override
            public int getCount() {
                return flist.size();
            }
        };
        //设置适配器
        viewPager.setAdapter(fadapter);
        //滑动fragment切换底部按钮
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                switch (i){
                    case 0:
                        group.check(R.id.button1);
                        break;
                    case 1:
                        group.check(R.id.button2);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        //点击按钮切换fragment
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.button1:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.button2:
                        viewPager.setCurrentItem(1);
                        break;
                }
            }
        });
    }
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        loginpresenter.onDetach();
//    }
//
//    @Override
//    public void showData(Object data){
//        NewsBean newsBean= (NewsBean)data;
//        List<NewsBean.DataBean> data1 = newsBean.getData();
//        md.setData(data1);
//    }
}
