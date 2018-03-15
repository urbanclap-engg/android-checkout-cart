package urbanclap.com.marketview;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
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

    public NavigationHorizontalScroll(Context context) {
        super(context);
        init();
    }

    private void init() {
        // TODO: 15/Mar/18 @adnaan: remove the below coloring...
        setBackgroundColor(Color.parseColor("#FFAAAA"));
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
    public ViewGroup getView() {
        return this;
    }
}
