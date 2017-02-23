package com.jv.widget.view;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.jv.widget.R;
import com.jv.widget.listener.LoadHeaderImagesListener;

/**
 * Created by Administrator on 2017/2/16.
 */

public class CoordinatorTabLayout extends CoordinatorLayout {

    private int[] mImageArray, mColorArray;

    private Context mContext;
    private Toolbar mToolbar;
    private ActionBar mActionbar;
    private TabLayout mTabLayout;
    private ImageView mImageView;
    private CollapsingToolbarLayout mCollapsingToolbarLayout;
    private LoadHeaderImagesListener mLoadHeaderImagesListener;

    public CoordinatorTabLayout(Context context) {
        super(context);
        mContext = context;
    }

    public CoordinatorTabLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        if (!isInEditMode()) {
            initView(context);
            initWidget(context, attrs);
        }
    }

    public CoordinatorTabLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        if (!isInEditMode()) {
            initView(context);
            initWidget(context, attrs);
        }
    }

    /**
     * 初始化View控件实例
     *
     * @param context
     */
    private void initView(Context context) {
        //设置当前类为 当前布局父容器 获取findViewById权限
        LayoutInflater.from(context).inflate(R.layout.view_coordinatortablayout, this, true);
        initToolbar();
        mCollapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsingtoolbarlayout);
        mTabLayout = (TabLayout) findViewById(R.id.tabLayout);
        mImageView = (ImageView) findViewById(R.id.imageview);
    }

    /**
     * 实例化控件属性
     *
     * @param context
     * @param attrs
     */
    private void initWidget(Context context, AttributeSet attrs) {
        //获取当前控件定义的属性
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CoordinatorTabLayout);

        //获取主题主色调 作为Toolbar未设置颜色的默认颜色
        TypedValue typedValue = new TypedValue();
        mContext.getTheme().resolveAttribute(R.attr.colorPrimary, typedValue, true);

        //设置Toolbar颜色
        int contentScrimColor = typedArray.getColor(R.styleable.CoordinatorTabLayout_contentScrim, typedValue.data);
        mCollapsingToolbarLayout.setContentScrimColor(contentScrimColor);

        //设置TabLayout指示器颜色
        int tabIndicatorColor = typedArray.getColor(R.styleable.CoordinatorTabLayout_tabIndicatorColor, Color.WHITE);
        mTabLayout.setSelectedTabIndicatorColor(tabIndicatorColor);

        //设置TabLayout文字颜色
        int tabTextColor = typedArray.getColor(R.styleable.CoordinatorTabLayout_tabTextColor, Color.WHITE);
        mTabLayout.setTabTextColors(ColorStateList.valueOf(tabTextColor));
        typedArray.recycle();

    }

    /**
     * 当前控件只能在AppCompatActivity中使用
     */
    private void initToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        ((AppCompatActivity) mContext).setSupportActionBar(mToolbar);
        mActionbar = ((AppCompatActivity) mContext).getSupportActionBar();
    }

    /**
     * 设置Toolbar的标题
     *
     * @param title
     * @return
     */
    public CoordinatorTabLayout setTitle(String title) {
        if (mActionbar != null) {
            mActionbar.setTitle(title);
        }
        return this;
    }

    /**
     * 设置Toolbar是否显示 Back按钮 及标题
     *
     * @param canBack
     * @return
     */
    public CoordinatorTabLayout setBackEnable(Boolean canBack) {
        if (canBack && mActionbar != null) {
            mActionbar.setDisplayShowHomeEnabled(true);
//            mActionbar.setHomeAsUpIndicator(R.drawable.ic_arrow_white_24dp);
            mToolbar.setNavigationIcon(R.drawable.ic_arrow_white_24dp);
        }
        return this;

    }


    /**
     * 设置每个tab对应的头部图片
     *
     * @param imageArray 图片数组
     * @return
     */
    public CoordinatorTabLayout setImageArray(@NonNull int[] imageArray) {
        mImageArray = imageArray;
        setupTabLayout();
        return this;
    }

    /**
     * 设置每个tab对应的头部图片 和背景颜色
     *
     * @param imageArray 图片数组
     * @param colorArray ContentScrimColor数组
     * @return
     */
    public CoordinatorTabLayout setImageAndScrimColorArray(@NonNull int[] imageArray, @NonNull int[] colorArray) {
        mImageArray = imageArray;
        mColorArray = colorArray;
        setupTabLayout();
        return this;
    }

    /**
     * 设置每个tab对应的ContentScrimColor
     *
     * @param colorArray ContentScrimColor数组
     * @return
     */
    public CoordinatorTabLayout setScrimColorArray(@NonNull int[] colorArray) {
        mColorArray = colorArray;
        setupTabLayout();
        return this;
    }

    /**
     * 显示隐藏 当前选中tab 图片 和 背景颜色 使用渐入 渐出动画过度
     */
    private void setupTabLayout() {
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mImageView.setAnimation(AnimationUtils.loadAnimation(mContext, R.anim.anim_dismiss));
                if (mLoadHeaderImagesListener == null) {
                    if (mImageArray != null) {
                        mImageView.setImageResource(mImageArray[tab.getPosition()]);
                    }
                } else {
                    mLoadHeaderImagesListener.loadHeaderImages(mImageView, tab);
                }

                if (mColorArray != null) {
                    mCollapsingToolbarLayout.setContentScrimColor(ContextCompat.getColor(mContext, mColorArray[tab.getPosition()]));
                }
                mImageView.setAnimation(AnimationUtils.loadAnimation(mContext, R.anim.anim_show));
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }


    /**
     * 设置 与tabLayout 相关的 viewPager
     *
     * @param viewPager
     * @return
     */
    public CoordinatorTabLayout setWithViewPager(ViewPager viewPager) {
        mTabLayout.setupWithViewPager(viewPager);
        return this;
    }

    /**
     * 获取该组件中的ActionBar
     */
    public ActionBar getActionBar() {
        return mActionbar;
    }

    /**
     * 获取该组件中的TabLayout
     */
    public TabLayout getTabLayout() {
        return mTabLayout;
    }

    /**
     * 获取该组件中的ImageView
     */
    public ImageView getImageView() {
        return mImageView;
    }

    /**
     * 设置LoadHeaderImagesListener
     *
     * @param loadHeaderImagesListener 设置LoadHeaderImagesListener
     * @return
     */
    public CoordinatorTabLayout setLoadHeaderImagesListener(LoadHeaderImagesListener loadHeaderImagesListener) {
        mLoadHeaderImagesListener = loadHeaderImagesListener;
        setupTabLayout();
        return this;
    }


}
