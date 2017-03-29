package com.jv.widget.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jv.widget.R;
import com.jv.widget.utils.SizeUtils;

/**
 * Created by Administrator on 2017/3/1.
 */

public class SubjectView extends LinearLayout {

    private Context mContext;
    private LinearLayout llLayout1, llLayout2;
    private ImageView ivIcon;
    private TextView tvTitle, tvContent;
    private TextView mLine;

    public SubjectView(Context context) {
        super(context);
        mContext = context;
    }

    public SubjectView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView(context);
        initWidget(context, attrs);
    }

    private void initView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.view_subject, this, true);
        llLayout1 = (LinearLayout) findViewById(R.id.ll_layout1);
        llLayout2 = (LinearLayout) findViewById(R.id.ll_layout2);
        ivIcon = (ImageView) findViewById(R.id.iv_icon);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvContent = (TextView) findViewById(R.id.tv_content);
        mLine = (TextView) findViewById(R.id.v_line);
    }

    private void initWidget(Context context, AttributeSet attrs) {
        //获取当前控件定义的属性
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SubjectView);

        Drawable icon = typedArray.getDrawable(R.styleable.SubjectView_icon);
        int iconHeight = (int) typedArray.getDimension(R.styleable.SubjectView_iconHeight, LinearLayout.LayoutParams.WRAP_CONTENT);
        int iconWight = (int) typedArray.getDimension(R.styleable.SubjectView_iconWight, LinearLayout.LayoutParams.WRAP_CONTENT);
        ivIcon.setImageDrawable(icon);
        ivIcon.setLayoutParams(new LinearLayout.LayoutParams(iconHeight, iconWight));

        String title = typedArray.getString(R.styleable.SubjectView_title);
        int titleColor = typedArray.getColor(R.styleable.SubjectView_titleColor, Color.BLACK);
        int titleSize = (int) typedArray.getDimension(R.styleable.SubjectView_titleSize, 16);
        tvTitle.setText(title);
        tvTitle.setTextSize(titleSize);
        tvTitle.setTextColor(titleColor);

        String content = typedArray.getString(R.styleable.SubjectView_content);
        int contentColor = typedArray.getColor(R.styleable.SubjectView_contentColor, Color.BLACK);
        int contentSize = (int) typedArray.getDimension(R.styleable.SubjectView_contentSize, 16);
        tvContent.setText(content);
        tvContent.setTextSize(contentSize);
        tvContent.setTextColor(contentColor);

        //设置 自适应线View 高度
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(SizeUtils.dp2px(mContext, 3), SizeUtils.getMeasuredHeight(llLayout2));
        params.setMargins(0, SizeUtils.dp2px(mContext, 5), 0, 0);
        mLine.setLayoutParams(params);

        //回收资源
        typedArray.recycle();
    }

}
