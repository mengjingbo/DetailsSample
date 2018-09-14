package com.details.sample;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * 时间         ：2018/9/10
 * 作者         ：秦川小将
 * CSDN博客地址 ：https://blog.csdn.net/mjb00000
 */
public abstract class BaseBottomSheetDialog extends BottomSheetDialogFragment {

    protected View mView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getStyle() == 0) {
            setStyle(BottomSheetDialogFragment.STYLE_NORMAL, R.style.BottomSheetDialogStyle);
        } else {
            setStyle(BottomSheetDialogFragment.STYLE_NORMAL, getStyle());
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (null != mView) {
            ViewGroup mViewGroup = (ViewGroup) mView.getParent();
            if (mViewGroup != null) {
                mViewGroup.removeView(mView);
            }
        } else {
            mView = inflater.inflate(layoutResId(), container, false);
        }
        return mView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loadData(getArguments());
    }

    /**
     * 初始化Layout
     */
    @LayoutRes
    protected abstract int layoutResId();

    /**
     * 初始化View
     */
    protected abstract void initView(View view);

    /**
     * 加载数据
     */
    protected abstract void loadData(Bundle arguments);

    /**
     * 设置样式
     */
    protected int getStyle() {
        return 0;
    }

    /**
     * 初始化View
     */
    protected final <T extends View> T findViewById(@IdRes int id) {
        return mView.findViewById(id);
    }

    /**
     * 消息提示
     */
    protected void showToast(int resId) {
        if (resId == 0) return;
        showToast(getString(resId));
    }

    /**
     * 消息提示
     */
    protected void showToast(String msg) {
        if (getActivity() == null || msg.isEmpty()) return;
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * showDialog
     */
    public void showDialog(FragmentManager manager, String tag) {
        showDialog(manager, null, tag);
    }

    /**
     * showDialog
     */
    public void showDialog(FragmentManager manager, Bundle bundle, String tag) {
        FragmentTransaction mTransaction = manager.beginTransaction();
        if (manager.findFragmentByTag(tag) != null) {
            mTransaction.remove(manager.findFragmentByTag(tag));
        }
        if (bundle != null) {
            setArguments(bundle);
        }
        show(manager, tag);
    }
}
