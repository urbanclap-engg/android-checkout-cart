package urbanclap.com.marketview.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import urbanclap.com.marketview.frame_work.navigation_bar.INavigationBar;
import urbanclap.com.marketview.frame_work.navigation_bar.NavigationItemView;

/**
 * @author : Adnaan 'Zohran' Ahmed <adnaanahmed@urbanclap.com>
 * @version : 1.0.0
 * @since : 15 Mar 2018 7:37 PM
 */


public class NavigationHorizontalScroll extends HorizontalScrollView implements INavigationBar {

    private LinearLayout linearLayout;
    private int screenWidth;


    public NavigationHorizontalScroll(Context context) {
        super(context);
        init();
    }

    private void init() {
        screenWidth = getContext().getResources().getDisplayMetrics().widthPixels;
        linearLayout = new LinearLayout(getContext());
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        addView(linearLayout, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
    }

    @Override
    public void addView(@NonNull NavigationItemView navigationItemView) {
        linearLayout.addView(navigationItemView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
    }

    @Override
    public void addView(@NonNull NavigationItemView navigationItemView, int pos) {
        linearLayout.addView(navigationItemView, pos, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
    }

    @Override
    public void updateView(@NonNull NavigationItemView navigationItemView, int pos) {
        removeView(pos);
        addView(navigationItemView, pos);
    }

    @Override
    public void removeView(int pos) {
        linearLayout.removeViewAt(pos);
    }

    @Override
    public void clear() {
        linearLayout.removeAllViews();
    }

    @Override
    public ViewGroup getView() {
        return this;
    }

    @Override
    public void selectViewAt(int pos) {
        scrollTo(pos);
    }

    public void scrollTo(final int pos) {
        postDelayed(new Runnable() {
            @Override
            public void run() {
                View view = linearLayout.getChildAt(pos);
                int scrollX = view.getLeft() - (screenWidth >> 1) + (view.getWidth() >> 1);
                smoothScrollTo(scrollX, 0);
            }
        }, 100);
    }
}
