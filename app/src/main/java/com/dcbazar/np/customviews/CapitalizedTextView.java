package com.dcbazar.np.customviews;

import android.content.Context;
import android.util.AttributeSet;

/**
 * Created by Amir on 16/06/2017.
 */

public class CapitalizedTextView extends android.support.v7.widget.AppCompatTextView {

    public CapitalizedTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        if (text.length() > 0) {
            text = String.valueOf(text.charAt(0)).toUpperCase() + text.subSequence(1, text.length());
        }
        super.setText(text, type);
    }
}