package urbanclap.com.marketview.market_impl.recycler_view_market;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
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
        setBackgroundColor(Color.parseColor("#757575"));
    }

    @Override
    public void setStickyView(@Nullable View view) {
        removeAllViews();
        addView(view);
        invalidate();
    }

    @Override
    public ViewGroup getView() {
        return this;
    }
}
