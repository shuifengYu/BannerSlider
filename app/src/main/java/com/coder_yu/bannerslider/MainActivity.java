package com.coder_yu.bannerslider;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.Toast;

import com.coder_yu.banners_slider.BannerEntity;
import com.coder_yu.banners_slider.BannerFragment;
import com.coder_yu.banners_slider.UIConfig;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements BannerFragment.OnBannerClickedListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ArrayList<BannerEntity> imagesEntityList = new ArrayList<>();
        imagesEntityList.add(new BannerEntity("http://www.baidu.com", getUri(R.drawable.img_loading)));
        imagesEntityList.add(new BannerEntity("http://www.baidu.com", Uri.parse("http://img4.imgtn.bdimg.com/it/u=1551505495,801926913&fm=26&gp=0.jpg")));
        imagesEntityList.add(new BannerEntity("http://www.baidu.com", Uri.parse("http://pic17.nipic.com/20111122/6759425_152002413138_2.jpg")));
        imagesEntityList.add(new BannerEntity("http://www.baidu.com", Uri.parse("http://img3.duitang.com/uploads/item/201601/28/20160128114023_ZX3f5.jpeg")));
        imagesEntityList.add(new BannerEntity("http://www.baidu.com", Uri.parse("http://img5.duitang.com/uploads/item/201503/05/20150305192547_Tewcf.jpeg")));
        imagesEntityList.add(new BannerEntity("http://www.baidu.com", Uri.parse("http://pic1.shejiben.com/case/2013/03/19/20130319011036-33b4293c-2s.jpg")));
        imagesEntityList.add(new BannerEntity("http://www.baidu.com", Uri.parse("http://img5q.duitang.com/uploads/item/201504/07/20150407H2725_RjuLn.jpeg")));
        imagesEntityList.add(new BannerEntity("http://www.baidu.com", Uri.parse("http://pic39.nipic.com/20140312/18155867_171330557309_2.jpg")));
        UIConfig uiConfig = new UIConfig.Builder()
                .imageLoadFailedRes(R.drawable.img_load_failed)
                .imageLoadingRes(R.drawable.img_loading)
                .indicatesMarginBottomDP(30)
                .duration(1500)
                .indicatesMarginRightDP(20)
                .indicatesMarginLeftDP(30)
                .indicatesGravity(Gravity.BOTTOM|Gravity.RIGHT)
                .scaleType(ImageView.ScaleType.CENTER_INSIDE)
                .slidingTime(1000)
                .build();
        BannerFragment fragment = BannerFragment.newInstance(imagesEntityList,uiConfig);

        addFragment(fragment);
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                imagesEntityList.clear();
//                imagesEntityList.add(new BannerEntity("http://www.baidu.com",  Uri.parse("http://pic17.nipic.com/20111122/6759425_152002413138_2.jpg")));
//                imagesEntityList.add(new BannerEntity("http://www.baidu.com",  Uri.parse("http://tupian.enterdesk.com/2013/mxy/12/11/4/2.jpg")));
//                imagesEntityList.add(new BannerEntity("http://www.baidu.com",  Uri.parse("http://pic.58pic.com/58pic/14/83/48/80V58PICtps_1024.jpg")));
//                imagesEntityList.add(new BannerEntity("http://www.baidu.com",  Uri.parse("http://imgsrc.baidu.com/imgad/pic/item/1e30e924b899a9017c518d1517950a7b0208f5a9.jpg")));
//                BannerFragment fragment = BannerFragment.newInstance(imagesEntityList);
//                addFragment(fragment);
//            }
//        }, 7000);
    }

    private void addFragment(BannerFragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.main_banner_container, fragment).commit();
    }

    private Uri getUri(int resID) {
        Uri uri = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://"
                + getResources().getResourcePackageName(resID) + "/"
                + getResources().getResourceTypeName(resID) + "/"
                + getResources().getResourceEntryName(resID));
        return uri;
    }

    @Override
    public void onClicked(BannerEntity bannerEntity) {

        Toast.makeText(getBaseContext(), "banner:" + bannerEntity, Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this,MainActivity.class));
    }
}
