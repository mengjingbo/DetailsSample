package com.details.sample;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 时间         ：2018/9/10
 * 作者         ：秦川小将
 * CSDN博客地址 ：https://blog.csdn.net/mjb00000
 */
public class DetailsActivity extends AppCompatActivity implements View.OnClickListener, NestedScrollView.OnScrollChangeListener{

    private LinearLayout mToolbarContainer;
    private ImageView mToolbarImg;
    private TextView mToolbarTitle;

    /**
     * 偏移量阈值，大于300后Toolbar由全透明变成不透明
     */
    protected float mGradualChange;

    private int mToolbarTopPadding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        findViewById(R.id.details_like).setOnClickListener(this);
        findViewById(R.id.details_buy).setOnClickListener(this);
        findViewById(R.id.details_add_cart).setOnClickListener(this);
        initToolbar();
        initWebView();
    }

    private void initToolbar() {
        NestedScrollView scrollView = findViewById(R.id.details_scroll_view);
        scrollView.setOnScrollChangeListener(this);
        mToolbarContainer = findViewById(R.id.details_toolbar_container);
        mToolbarTopPadding = getResources().getDimensionPixelSize(R.dimen.margin_size_24);
        mToolbarContainer.setPadding(0, mToolbarTopPadding, 0, 0);
        mToolbarContainer.getBackground().setAlpha(0);
        mGradualChange = getResources().getDimensionPixelSize(R.dimen.margin_size_300);
        mToolbarImg = findViewById(R.id.details_back_image);
        mToolbarTitle = findViewById(R.id.details_toolbar_title);
        mToolbarTitle.setAlpha(0);
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void initWebView() {
        WebView mWebView = findViewById(R.id.details_web_view);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebChromeClient(new WebChromeClient());
        mWebView.setWebViewClient(new WebViewClient());
        mWebView.loadUrl("https://github.com/mengjingbo/AndroidWindowTheme");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.details_like:
                Toast.makeText(this, "收藏", Toast.LENGTH_SHORT).show();
                break;
            case R.id.details_buy:
                new ChoicePatternDialog().showDialog(getSupportFragmentManager(), "ChoicePatternDialog");
                break;
            case R.id.details_add_cart:
                new ChoicePatternDialog().showDialog(getSupportFragmentManager(), "ChoicePatternDialog");
                break;
        }
    }

    @Override
    public void onScrollChange(NestedScrollView nestedScrollView, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
        int mMoveDistance = oldScrollY - mToolbarTopPadding;
        int startOffset = 0;
        if (mMoveDistance <= startOffset) {
            mToolbarContainer.getBackground().setAlpha(0);
            mToolbarImg.setBackgroundResource(R.drawable.draw_gray_circle_shape);
            mToolbarTitle.setAlpha(0);
        } else if (mMoveDistance < mGradualChange) {
            int alpha = Math.round(((mMoveDistance - startOffset) / mGradualChange) * 255);
            mToolbarContainer.getBackground().setAlpha(alpha);
            if (mToolbarImg.getBackground() != null) {
                mToolbarImg.getBackground().setAlpha(255 - alpha);
            }
            mToolbarTitle.setAlpha(255 - alpha);
        } else if (mMoveDistance >= mGradualChange) {
            mToolbarContainer.getBackground().setAlpha(255);
            mToolbarImg.setBackgroundResource(0);
            mToolbarTitle.setAlpha(1);
        }
    }
}
