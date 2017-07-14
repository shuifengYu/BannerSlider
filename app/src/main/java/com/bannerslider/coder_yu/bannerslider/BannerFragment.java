package com.bannerslider.coder_yu.bannerslider;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bannerslider.coder_yu.utils.CollectionsUitl;
import com.bannerslider.coder_yu.utils.DpAndPxUtil;
import com.bumptech.glide.Glide;

import java.util.ArrayList;


public class BannerFragment extends Fragment {

    private static final String PARAM_IMAGES = "images";
    private static final String TAG = "BannerFragment";

    private ViewPager mViewPager;
    private BannerPagerAdapter mPagerAdapter;

    private ArrayList<BannerEntity> imagePaths;
    private ArrayList<ImageView> indicates;
    private LinearLayout indicateLine;
    private int lastPosition;
    private boolean pageChanged;
    private OnBannerClickedListener mListener;

    public interface OnBannerClickedListener{
        void onClicked(BannerEntity bannerEntity);
    }

    public void setOnBannerClickedListener(OnBannerClickedListener listener){
        this.mListener = listener;
    }
    public static BannerFragment newInstance(ArrayList<BannerEntity> imagePathList) {
        if (CollectionsUitl.isEmpty(imagePathList)) {
            return null;
        }
        BannerFragment fragment = new BannerFragment();
        Bundle args = new Bundle();
        args.putSerializable(PARAM_IMAGES, imagePathList);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            imagePaths = (ArrayList<BannerEntity>) getArguments().getSerializable(PARAM_IMAGES);
            if (CollectionsUitl.isEmpty(imagePaths)) {
                return;
            }
            imagePaths.add(imagePaths.get(0));
            imagePaths.add(0, imagePaths.get(imagePaths.size() - 2));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_banner, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        indicateLine = (LinearLayout) view.findViewById(R.id.fm_banner_indicateline);
        initIndicateLine();
        mViewPager = (ViewPager) view.findViewById(R.id.fm_banner_viewpager);
        mPagerAdapter = new BannerPagerAdapter(imagePaths);
        mViewPager.setAdapter(mPagerAdapter);
        mPagerAdapter.notifyDataSetChanged();
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                Log.d(TAG, "onPageSelected position=" + position);
                lastPosition = position;
                pageChanged = true;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (pageChanged && state == ViewPager.SCROLL_STATE_IDLE) {
                    if (lastPosition == imagePaths.size() - 1) {
                        mViewPager.setCurrentItem(1, false);
                        setIndicateSelected(1);
                    } else if (lastPosition == 0) {
                        mViewPager.setCurrentItem(imagePaths.size() - 2, false);
                        setIndicateSelected(imagePaths.size() - 2);
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

    private void setIndicateSelected(int position) {
        for (ImageView imageView : indicates) {
            imageView.setSelected(false);
        }
        indicates.get(position - 1).setSelected(true);
    }

    private void initIndicateLine() {
        if (CollectionsUitl.isEmpty(imagePaths)) {
            return;
        }
        indicates = new ArrayList();
        for (int i = 0; i < imagePaths.size() - 2; i++) {
            ImageView imageView = new ImageView(getContext());
            int border = (int) DpAndPxUtil.dp2px(getActivity(), 4);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(border, border);
            int margin = (int) DpAndPxUtil.dp2px(getActivity(), 2);
            params.setMargins(margin, margin, margin, margin);
            imageView.setLayoutParams(params);
            imageView.setBackgroundResource(R.drawable.aide_selector_indicate);
            indicateLine.addView(imageView);
            indicates.add(imageView);
        }

        indicates.get(0).setSelected(true);

    }


    class BannerPagerAdapter extends PagerAdapter {

        private ArrayList<BannerEntity> imagePaths;

        public BannerPagerAdapter(ArrayList<BannerEntity> imagePaths) {
            this.imagePaths = imagePaths;
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            Log.d(TAG, "instantiateItem position=" + position);
            final ImageView imageView = new ImageView(getActivity());
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    startActivity(ActWebview.newIntent(getActivity(), "曹操专车", imagePaths.get(position).url));
                    if(mListener == null){
                        return;
                    }
                    mListener.onClicked(imagePaths.get(position));
                }
            });
            imageView.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewPager.LayoutParams.MATCH_PARENT, ViewPager.LayoutParams.MATCH_PARENT));
            Glide.with(getActivity()).load(imagePaths.get(position).imageUrl).placeholder(R.drawable.aide_banner_img_def).into(imageView);
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public int getCount() {
            return imagePaths.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return object == view;
        }

    }
}
