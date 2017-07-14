package com.bannerslider.coder_yu.bannerslider;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements BannerFragment.OnBannerClickedListener{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayList<BannerEntity> imagesEntityList = new ArrayList<>();
        imagesEntityList.add(new BannerEntity("http://www.baidu.com","http://img4.imgtn.bdimg.com/it/u=1551505495,801926913&fm=26&gp=0.jpg"));
        imagesEntityList.add(new BannerEntity("http://www.baidu.com","http://pic17.nipic.com/20111122/6759425_152002413138_2.jpg"));
        imagesEntityList.add(new BannerEntity("http://www.baidu.com","http://tupian.enterdesk.com/2013/mxy/12/11/4/2.jpg"));
        imagesEntityList.add(new BannerEntity("http://www.baidu.com","http://pic.58pic.com/58pic/14/83/48/80V58PICtps_1024.jpg"));
        imagesEntityList.add(new BannerEntity("http://www.baidu.com","http://imgsrc.baidu.com/imgad/pic/item/1e30e924b899a9017c518d1517950a7b0208f5a9.jpg"));
        BannerFragment fragment = BannerFragment.newInstance(imagesEntityList);
        getSupportFragmentManager().beginTransaction().add(R.id.main_banner_container, fragment).commit();
    }

    @Override
    public void onClicked(BannerEntity bannerEntity) {
        Toast.makeText(getBaseContext(), "banner:" + bannerEntity, Toast.LENGTH_SHORT).show();
    }
}
