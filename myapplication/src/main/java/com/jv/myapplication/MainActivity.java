package com.jv.myapplication;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import com.jv.widget.view.CoordinatorTabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private CoordinatorTabLayout collapsingToolbarLayout;
    private ViewPager vpContainer;
    private List<Fragment> mFragments;
    private final String[] mTitles = {"Android", "iOS", "前端", "拓展"};
    private int[] mImageArray, mColorArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //设置取消 默认titleBar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, //设置隐藏状态栏
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        initFragments();
        initViewPager();

        mImageArray = new int[]{
                R.mipmap.bg_android,
                R.mipmap.bg_ios,
                R.mipmap.bg_js,
                R.mipmap.bg_other};
        mColorArray = new int[]{
                android.R.color.holo_blue_light,
                android.R.color.holo_red_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_green_light};


        collapsingToolbarLayout = (CoordinatorTabLayout) findViewById(R.id.coordinator_tab_layout);
        collapsingToolbarLayout.setTitle("Jv-Widget")
                .setBackEnable(true)
                .setImageAndScrimColorArray(mImageArray, mColorArray)
                .setWithViewPager(vpContainer);

    }

    private void initViewPager() {
        vpContainer = (ViewPager) findViewById(R.id.vp_container);
        vpContainer.setOffscreenPageLimit(4);
        vpContainer.setAdapter(new MyVpAdapter(getSupportFragmentManager()));
    }

    private void initFragments() {
        mFragments = new ArrayList<>();
        for (String title : mTitles) {
            mFragments.add(MainFragment.getInstance(title));
        }
    }

    class MyVpAdapter extends FragmentStatePagerAdapter {

        public MyVpAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        switch (item.getItemId()) {
            case R.id.action_about:
                break;
            case R.id.action_about_me:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        setContentView(R.layout.activity_main);
//        initFragments();
//        initViewPager();
//        mColorArray = new int[]{
//                android.R.color.holo_blue_light,
//                android.R.color.holo_red_light,
//                android.R.color.holo_orange_light,
//                android.R.color.holo_green_light};
//
//        mCoordinatorTabLayout = (CoordinatorTabLayout) findViewById(R.id.coordinatortablayout);
//        mCoordinatorTabLayout.setTitle("Demo")
//                .setBackEnable(true)
//                .setContentScrimColorArray(mColorArray)
//                .setLoadHeaderImagesListener(new LoadHeaderImagesListener() {
//                    @Override
//                    public void loadHeaderImages(ImageView imageView, TabLayout.Tab tab) {
//                        switch (tab.getPosition()) {
//                            case 0:
//                                loadImages(imageView, "https://raw.githubusercontent.com/hugeterry/CoordinatorTabLayout/master/sample/src/main/res/mipmap-hdpi/bg_android.jpg");
//                                break;
//                            case 1:
//                                loadImages(imageView, "https://raw.githubusercontent.com/hugeterry/CoordinatorTabLayout/master/sample/src/main/res/mipmap-hdpi/bg_js.jpg");
//                                break;
//                            case 2:
//                                loadImages(imageView, "https://raw.githubusercontent.com/hugeterry/CoordinatorTabLayout/master/sample/src/main/res/mipmap-hdpi/bg_ios.jpg");
//                                break;
//                            case 3:
//                                loadImages(imageView, "https://raw.githubusercontent.com/hugeterry/CoordinatorTabLayout/master/sample/src/main/res/mipmap-hdpi/bg_other.jpg");
//                                break;
//                            default:
//                                break;
//                        }
//                    }
//                })
//                .setupWithViewPager(mViewPager);
//    }


}
