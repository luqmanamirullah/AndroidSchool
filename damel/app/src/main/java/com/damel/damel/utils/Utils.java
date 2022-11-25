package com.damel.damel.utils;

import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class Utils {
    public static void setMargins (View view, int left, int top, int right, int bottom) {
        if (view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) view.getLayoutParams();

            final float scale = view.getContext().getResources().getDisplayMetrics().density;
            // convert the DP into pixel
            int l =  (int)(left * scale + 0.5f);
            int r =  (int)(right * scale + 0.5f);
            int t =  (int)(top * scale + 0.5f);
            int b =  (int)(bottom * scale + 0.5f);

            p.setMargins(l, t, r, b);
            view.requestLayout();
        }
    }

    public static boolean required(EditText editText) {
        if (editText.getText().toString().isEmpty()) {
            editText.setError("Wajib diisi");
            editText.requestFocus();
            return false;
        }
        return true;
    }
}
