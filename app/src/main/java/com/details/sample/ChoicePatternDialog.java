package com.details.sample;

import android.os.Bundle;
import android.view.View;

/**
 * 时间         ：2018/9/10
 * 作者         ：秦川小将
 * CSDN博客地址 ：https://blog.csdn.net/mjb00000
 */
public class ChoicePatternDialog extends BaseBottomSheetDialog {

    @Override
    protected int layoutResId() {
        return R.layout.dialog_choice_pattern;
    }

    @Override
    protected void initView(View view) {
        view.findViewById(R.id.details_select_confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    @Override
    protected void loadData(Bundle arguments) {

    }
}
