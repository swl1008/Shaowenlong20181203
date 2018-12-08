package swl.bwie.com.mvp181205.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import cn.bingoogolapple.qrcode.core.QRCodeView;
import cn.bingoogolapple.qrcode.zxing.ZXingView;
import swl.bwie.com.mvp181205.R;

public class ScanActivity extends AppCompatActivity implements QRCodeView.Delegate {

    private ZXingView zXingView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);
        zXingView = findViewById(R.id.zxing);
        zXingView.setDelegate(this);
    }
    @Override
    protected void onResume() {
        super.onResume();
        zXingView.startCamera();
        zXingView.startSpotAndShowRect();
        zXingView.openFlashlight();

    }

    @Override
    protected void onStop() {
        super.onStop();
        zXingView.stopCamera();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        zXingView.onDestroy();
    }

    @Override
    public void onScanQRCodeSuccess(String result) {

    }

    @Override
    public void onCameraAmbientBrightnessChanged(boolean isDark) {

    }

    @Override
    public void onScanQRCodeOpenCameraError() {

        Log.e("ff","ff");
    }


}
