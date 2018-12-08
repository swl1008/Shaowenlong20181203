package swl.bwie.com.mvp181205.view.fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.ref.WeakReference;

import cn.bingoogolapple.qrcode.zxing.QRCodeEncoder;
import swl.bwie.com.mvp181205.R;
import swl.bwie.com.mvp181205.view.MainActivity;
import swl.bwie.com.mvp181205.view.ScanActivity;

public class CardFragment extends Fragment {
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private ImageView imageView;
    private String name;
    private Button btn_sao;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_card,container,false);

        sharedPreferences = getActivity().getSharedPreferences("User",Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        imageView = view.findViewById(R.id.image);
        btn_sao = view.findViewById(R.id.btn_sao);

        name = sharedPreferences.getString("name",null);


        btn_sao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkPermission();
            }
        });

        createQRCode();



        return view;
    }
    private void checkPermission() {
        //第一步，判断系统版本是否为6.0以上
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            //第二步：checkSelfPermission判断有没有此权限
            //第一个参数：上下文
            //第二个参数：我们想要判断的权限，此处为相机权限
            //PackageManager.PERMISSION_GRANTED 条件，权限有没有被授予
            if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                //如果没授予，则申请权限
                //第一个：上下文
                //第二个：要申请的权限数组，此处为相机权限
                //第三个：请求码，startActivityForResult一样
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA},100);
            }else{
                startActivity(new Intent(getActivity(), ScanActivity.class));
            }
        }else {
            startActivity(new Intent(getActivity(), ScanActivity.class));
        }
    }

    /**
     * 接受权限请求结果
     * @param requestCode 请求码
     * @param permissions 我们请求的权限数组
     * @param grantResults 返回结果数组
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //如果requestCode匹配，切权限申请通过
        if(requestCode == 100 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            startActivity(new Intent(getActivity(), ScanActivity.class));
        }
    }


    private void createQRCode() {
        QRTask qrTask = new QRTask(getActivity(),imageView,name);
        qrTask.execute(name);
    }


    @SuppressLint("NewApi")
    class QRTask extends AsyncTask<String,Void,Bitmap> {
        private WeakReference<Context> mContext;
        private WeakReference<ImageView> imageView;

        public QRTask(Context context,ImageView imageView1,String ed) {
            mContext = new WeakReference<>(context);
            imageView = new WeakReference<>(imageView1);
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            String str = params[0];
            if (TextUtils.isEmpty(str)){
                return null;
            }
            int size = mContext.get().getResources().getDimensionPixelSize(R.dimen.qr_code_size);
            return QRCodeEncoder.syncEncodeQRCode(str,size);
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            if (bitmap != null){
                imageView.get().setImageBitmap(bitmap);
            }else{
                Toast.makeText(mContext.get(), "生成失败", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
