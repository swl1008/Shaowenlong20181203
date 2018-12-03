package swl.bwie.com.shaowenlong201812033;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SearchActivity extends AppCompatActivity {

    private WaterFall waterFall;
    private EditText editText;
    private Button button,button1;
    private List<String> list = new ArrayList<>();
    private WaterFall water_search;
    private WaterFall water_hot;
    private TitleBarView title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        init();
    }
    private void init(){
        water_search = findViewById(R.id.water_fall_search);
        water_hot = findViewById(R.id.water_fall_hot);
        button = findViewById(R.id.clear);
        title = findViewById(R.id.title);
        //点击
        title.setOnButtonClientListener(new TitleBarView.onButtonClientListener() {
            private String uuid1;
            @Override
            public void onButtonClick(final String str) {
                if (str.equals("")){
                    return;
                }else{
                    final TextView textView = new TextView(SearchActivity.this);
                    //随机字符串当做唯一标识
                    UUID uuid = UUID.randomUUID();
                    textView.setTag(uuid);
                    uuid1 = String.valueOf(textView.getTag());
                    WaterDao.getInstance(SearchActivity.this).add(str,uuid1);
                    textView.setText(str);
                    textView.setTextSize(20);
                    textView.setBackgroundResource(R.drawable.shape_bg);
                    water_search.addView(textView);
                    //点击提示
                    textView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(SearchActivity.this,str,Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
        //搜索发现
        String[] str = new String[]{
                "电动牙刷","尤妮佳","沐浴露","日东红茶","榴莲","豆豆鞋",
                "雅诗兰黛","考拉三周年人气热销榜","电动牙刷"
        };
        for(int i=0;i<str.length;i++){
            TextView textView1 = new TextView(SearchActivity.this);
            textView1.setText(str[i]);
            textView1.setTextSize(20);
            textView1.setBackgroundResource(R.drawable.shape_bg);
            water_hot.addView(textView1);
        }

        //清空历史
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WaterDao.getInstance(SearchActivity.this).delAll();
                water_search.removeAllViews();
            }
        });

    }


}
