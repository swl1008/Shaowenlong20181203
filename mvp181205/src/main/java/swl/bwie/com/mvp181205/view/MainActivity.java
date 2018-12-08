package swl.bwie.com.mvp181205.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;


import swl.bwie.com.mvp181205.R;
import swl.bwie.com.mvp181205.presenter.LoginPresenter;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,IView{

    private EditText username,pass;
    private Button btn_login,btn_three;
    private CheckBox check_remember,check_auto;
    private LoginPresenter loginpresenter;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = findViewById(R.id.name);
        pass = findViewById(R.id.pass);
        btn_login = findViewById(R.id.btn_login);
        btn_three = findViewById(R.id.btn_three);
        check_auto = findViewById(R.id.check_auto);
        check_remember = findViewById(R.id.check_remember);
        sharedPreferences = getSharedPreferences("User",MODE_PRIVATE);
        editor = sharedPreferences.edit();

        loginpresenter = new LoginPresenter(this);

        btn_three.setOnClickListener(this);
        boolean ischeck = sharedPreferences.getBoolean("ischeck", false);
        if (ischeck){
            String name = sharedPreferences.getString("name", null);
            String pa= sharedPreferences.getString("pwd", null);

            username.setText(name);
            pass.setText(pa);
            check_remember.setChecked(true);
        }
        //取出自动登录的状态值
        boolean isauto = sharedPreferences.getBoolean("isauto", false);
        if(isauto){
            Intent intent = new Intent(MainActivity.this,LoginActivity.class);
            startActivity(intent);
        }
        check_auto.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    //设置记住密码为勾选
                    check_remember.setChecked(true);
                }else{
                    //清空
                    editor.clear();
                    editor.commit();
                }
            }
        });


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = username.getText().toString();
                String pwd = pass.getText().toString();
                loginpresenter.login(name,pwd);
                if (check_remember.isChecked()){
                    editor.putString("name",name);
                    editor.putString("pwd",pwd);

                    editor.putBoolean("ischeck",true);
                    editor.commit();
                }else{
                    editor.clear();
                    editor.commit();
                }
                if (check_auto.isChecked()){
                    editor.putBoolean("isauto",true);
                    editor.commit();
                }
            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_three:
                //获得UMShareAPI实例
                UMShareAPI umShareAPI =  UMShareAPI.get(MainActivity.this);

                //开始登录
                //第一个参数：上下文
                //第二个参数，登录哪种平台
                //第三个参数，添加回调
                umShareAPI.getPlatformInfo(MainActivity.this, SHARE_MEDIA.QQ, new UMAuthListener() {
                    /**
                     * 开始登录回调
                     * @param share_media
                     */
                    @Override
                    public void onStart(SHARE_MEDIA share_media) {
                        Log.i("tag", "UMAuthListener onStart");
                    }

                    /**
                     * 登录完成
                     * @param share_media
                     * @param i
                     * @param map
                     */
                    @Override
                    public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
                        //头像，昵称
                        Log.i("tag", "UMAuthListener onComplete");

                        //获取名字
                        String name  = map.get("screen_name");
                        //获取头像
                        String img  = map.get("profile_image_url");
                        Toast.makeText(MainActivity.this,""+name,Toast.LENGTH_SHORT).show();;
                        Log.i("tag", "name is "+ name);
                        Log.i("tag", "img is "+ img);
                    }

                    /**
                     * 登录失败
                     * @param share_media
                     * @param i
                     * @param throwable
                     */
                    @Override
                    public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
                        Log.i("tag", "UMAuthListener onError" + throwable.getLocalizedMessage());
                    }

                    /**
                     * 登录取消
                     * @param share_media
                     * @param i
                     */
                    @Override
                    public void onCancel(SHARE_MEDIA share_media, int i) {
                        Log.i("tag", "UMAuthListener onCancel");
                    }
                });
                break;
            default:
                break;
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        loginpresenter.onDetach();
    }

    @Override
    public void showData(Object data) {
        Toast.makeText(MainActivity.this,""+data,Toast.LENGTH_SHORT).show();
        if (data.equals("登录成功")){
            startActivity(new Intent(MainActivity.this,LoginActivity.class));
        }
    }
}
