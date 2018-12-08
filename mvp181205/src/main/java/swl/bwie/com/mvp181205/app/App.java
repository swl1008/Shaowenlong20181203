package swl.bwie.com.mvp181205.app;

import android.app.Application;
import android.graphics.Bitmap;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;

import swl.bwie.com.mvp181205.R;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        UMConfigure.init(this,"5a12384aa40fa3551f0001d1","umeng",UMConfigure.DEVICE_TYPE_PHONE,"");
        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");
        ImageLoader.getInstance().init(
                new ImageLoaderConfiguration.Builder(this)
                        .memoryCacheSizePercentage(13)
                        .diskCacheSize(50*1024*1024)
                        .defaultDisplayImageOptions(new DisplayImageOptions.Builder()
                                .cacheInMemory(true)
                                .cacheOnDisk(true)
                                .bitmapConfig(Bitmap.Config.RGB_565)
                                .showImageForEmptyUri(R.mipmap.ic_launcher)
                                .showImageOnFail(R.mipmap.ic_launcher)
                                .showImageOnLoading(R.mipmap.ic_launcher)
                                .build())
                        .build()

        );
    }
}
