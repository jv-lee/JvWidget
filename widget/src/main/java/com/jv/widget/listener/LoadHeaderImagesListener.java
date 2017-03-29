package com.jv.widget.listener;

import android.support.design.widget.TabLayout;
import android.widget.ImageView;

/**
 * CoordinatorTabLayout 头部图片加载监听
 */

public interface LoadHeaderImagesListener {
    void loadHeaderImages(ImageView imageView, TabLayout.Tab tab);
}
