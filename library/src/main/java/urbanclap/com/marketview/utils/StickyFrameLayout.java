package urbanclap.com.marketview.utils;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import urbanclap.com.marketview.frame_work.sticky.IStickyView;


/**
 * @author : Adnaan 'Zohran' Ahmed <adnaanahmed@urbanclap.com>
 * @version : 1.0.0
 * @since : 15 Mar 2018 5:28 PM
 */


public class StickyFrameLayout extends FrameLayout implements IStickyView {

    StickyFrameLayout(@NonNull Context context) {
        super(context);
        init();
    }

    private void init() {
        ViewGroup.LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        setLayoutParams(layoutParams);
        setBackgroundColor(Color.parseColor("#F0f0f0"));
    }

    @Override
    public void setStickyView(@Nullable View view) {
        removeAllViews();
        if (view != null)
            addView(view,
                    new ViewGroup.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT)
            );
    }

    @Override
    public ViewGroup getView() {
        return this;
    }
}
