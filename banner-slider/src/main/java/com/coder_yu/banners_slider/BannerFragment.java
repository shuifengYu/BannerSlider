package com.coder_yu.banners_slider;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bannerslider.coder_yu.banners_slider.R;
import com.bumptech.glide.Glide;
import com.coder_yu.banners_slider.utils.CollectionsUitl;
import com.coder_yu.banners_slider.utils.ViewPagerUtil;
import com.coder_yu.banners_slider.utils.DpAndPxUtil;
import com.coder_yu.banners_slider.widget.AutoSlidingViewPager;

import java.util.ArrayList;


public class BannerFragment extends Fragment {

    private static final String PARAM_IMAGES = "images";
    private static final String PARAM_CONFIG = "config";
    private static final String TAG = "BannerFragment";

    private AutoSlidingViewPager mViewPager;
    private BannerPagerAdapter mPagerAdapter;

    private ArrayList<BannerEntity> mBannerList;
    private ArrayList<ImageView> indicates;
    private LinearLayout indicateLine;
    private int lastPosition;
    private boolean pageChanged;
    private OnBannerClickedListener mListener;
    private UIConfig mUiConfig;

    private Handler mHandler;
    private Runnable mRunnable;
    private Context mContext;
    private boolean autoSliding = false;

    public interface OnBannerClickedListener {
        void onClicked(BannerEntity bannerEntity);
    }

    public void setOnBannerClickedListener(OnBannerClickedListener listener) {
        this.mListener = listener;
    }

    public static BannerFragment newInstance(ArrayList<BannerEntity> imagePathList) {
        return newInstance(imagePathList, new UIConfig.Builder().build());
    }

    public static BannerFragment newInstance(ArrayList<BannerEntity> imagePathList, UIConfig config) {
        if (CollectionsUitl.isEmpty(imagePathList)) {
            return null;
        }
        BannerFragment fragment = new BannerFragment();
        Bundle args = new Bundle();
        args.putSerializable(PARAM_IMAGES, imagePathList);
        args.putSerializable(PARAM_CONFIG, config);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnBannerClickedListener) {
            this.mListener = (OnBannerClickedListener) context;
        }
        this.mContext = context;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mBannerList = (ArrayList<BannerEntity>) getArguments().getSerializable(PARAM_IMAGES);
            mUiConfig = (UIConfig) getArguments().getSerializable(PARAM_CONFIG);
            decorateBanners(mBannerList);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_banner, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        scheduleNextBannerSliding();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        indicateLine = (LinearLayout) view.findViewById(R.id.fm_banner_indicateline);
        initIndicateLine();
        if (autoSliding) {
            mHandler = new Handler();
            mRunnable = new Runnable() {
                @Override
                public void run() {
                    if (mViewPager == null) {
                        return;
                    }
                    int curr = mViewPager.getCurrentItem();
                    mViewPager.setCurrentItem(curr + 1, true);
                    scheduleNextBannerSliding();
                }
            };
        }
        mViewPager = (AutoSlidingViewPager) view.findViewById(R.id.fm_banner_viewpager);
        ViewPagerUtil.controlViewPagerSpeed(mViewPager,mUiConfig.slidingTime);
        mViewPager.setActionListener(new AutoSlidingViewPager.ActionListener() {
            @Override
            public void onActionDown() {
                stopBannerSliding();
            }

            @Override
            public void onActionUp() {
                scheduleNextBannerSliding();
            }
        });
        mPagerAdapter = new BannerPagerAdapter(mBannerList);
        mViewPager.setAdapter(mPagerAdapter);
        mPagerAdapter.notifyDataSetChanged();
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                lastPosition = position;
                pageChanged = true;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (pageChanged && state == ViewPager.SCROLL_STATE_IDLE) {
                    if (lastPosition == mBannerList.size() - 1) {
                        mViewPager.setCurrentItem(1, false);
                        setIndicateSelected(1);
                    } else if (lastPosition == 0) {
                        mViewPager.setCurrentItem(mBannerList.size() - 2, false);
                        setIndicateSelected(mBannerList.size() - 2);
                    } else {
                        setIndicateSelected(lastPosition);
                    }
                    pageChanged = false;
                }

            }
        });
        mViewPager.setOffscreenPageLimit(2);
        mViewPager.setCurrentItem(1);
    }

    private void initIndicateLine() {
        if (CollectionsUitl.getSize(mBannerList) <= 1) {
            indicateLine.setVisibility(View.GONE);
            autoSliding = false;
            return;
        }
        FrameLayout.LayoutParams p = (FrameLayout.LayoutParams) indicateLine.getLayoutParams();
        int marginBottom = (int)DpAndPxUtil.dp2px(mContext,mUiConfig.indicatesMarginBottomDP);
        int marginLeft = (int)DpAndPxUtil.dp2px(mContext,mUiConfig.indicatesMarginLeftDP);
        int marginRight = (int)DpAndPxUtil.dp2px(mContext,mUiConfig.indicatesMarginRightDP);
        p.setMargins(marginLeft,0,marginRight,marginBottom);
        p.gravity = mUiConfig.indicatesGravity;
        indicateLine.setLayoutParams(p);
        indicateLine.removeAllViews();
        indicates = new ArrayList();
        for (int i = 0; i < mBannerList.size() - 2; i++) {
            ImageView imageView = new ImageView(getContext());
            int border = (int) DpAndPxUtil.dp2px(getActivity(), 4);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(border, border);
            int margin = (int) DpAndPxUtil.dp2px(getActivity(), 2);
            params.setMargins(margin, 0, margin, 0);
            imageView.setLayoutParams(params);
            imageView.setBackgroundResource(mUiConfig.indicateUnSelected);
            indicateLine.addView(imageView);
            indicates.add(imageView);
        }
        setIndicateSelected(1);
        autoSliding = true;
    }

    private void scheduleNextBannerSliding() {
        if (!autoSliding) {
            return;
        }
        if (mHandler == null || mRunnable == null) {
            return;
        }
        mHandler.postDelayed(mRunnable, mUiConfig.duration);
    }

    private void stopBannerSliding() {
        if (mHandler == null || mRunnable == null) {
            return;
        }
        mHandler.removeCallbacks(mRunnable);
    }

    private void setIndicateSelected(int position) {
        for (ImageView imageView : indicates) {
            imageView.setBackgroundResource(mUiConfig.indicateUnSelected);
        }
        indicates.get(position - 1).setBackgroundResource(mUiConfig.indicateSelectedRes);
    }

    @Override
    public void onStop() {
        super.onStop();
        stopBannerSliding();
    }

//    public void updateBanners(ArrayList<BannerEntity> bannerList) {
//        stopBannerSliding();
//        this.mBannerList = new ArrayList<>();
//        this.mBannerList.addAll(bannerList);
//        decorateBanners(mBannerList);
//        mPagerAdapter.setDatas(mBannerList);
//        initIndicateLine();
//        scheduleNextBannerSliding();
//        mPagerAdapter.notifyDataSetChanged();
//    }

    public void decorateBanners(ArrayList<BannerEntity> bannerList) {
        if (CollectionsUitl.getSize(bannerList) <= 1) {
            return;
        }
        bannerList.add(bannerList.get(0));
        bannerList.add(0, bannerList.get(bannerList.size() - 2));
    }

    class BannerPagerAdapter extends PagerAdapter {

        private ArrayList<BannerEntity> mDatas;

        public BannerPagerAdapter(ArrayList<BannerEntity> mDatas) {
            this.mDatas = mDatas;
        }

//        public void setDatas(ArrayList<BannerEntity> imagePaths){
//            this.mDatas = imagePaths;
//        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            Log.d(TAG, "instantiateItem position=" + position);
            final ImageView imageView = new ImageView(getActivity());
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener == null) {
                        return;
                    }
                    mListener.onClicked(mDatas.get(position));
                }
            });
            imageView.setScaleType(mUiConfig.scaleType);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewPager.LayoutParams.MATCH_PARENT, ViewPager.LayoutParams.MATCH_PARENT));
            Glide.with(getActivity()).load(mDatas.get(position).imageUrl)
                    .placeholder(mUiConfig.imageLoadingRes).error(mUiConfig.imageLoadFailed).into(imageView);
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public int getCount() {
            return mDatas.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return object == view;
        }

    }

}
