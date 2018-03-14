package urbanclap.com.marketview.market_impl.recycler_view_market;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import urbanclap.com.marketview.R;
import urbanclap.com.marketview.frame_work.market.interfaces.IMarketSectionView;
import urbanclap.com.marketview.frame_work.market.interfaces.IMarketView;
import urbanclap.com.marketview.frame_work.market.interfaces.IStickyView;
import urbanclap.com.marketview.frame_work.market.interfaces.MarketManagerBinder;
import urbanclap.com.marketview.frame_work.navigation_bar.INavigationBar;

/**
 * @author : Adnaan 'Zohran' Ahmed <adnaanahmed@urbanclap.com>
 * @version : 1.0.0
 * @since : 14 Mar 2018 1:45 PM
 */


public class RecyclerMarketView extends LinearLayout implements IMarketView {
    // TODO: 14/Mar/18 @adnaan: add support for ordering..


    @Nullable
    private View navigationBar;
    @Nullable
    private View stickyView;
    @Nullable
    private View marketSectionView;

    private static final int NAVIGATION_BAR_POSITION = 0;
    private static final int STICKY_VIEW_POSITION = 1;
    private static final int SECTION_VIEW_POSITION = 2;


    public RecyclerMarketView(Context context) {
        super(context);
        init();
    }

    public RecyclerMarketView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RecyclerMarketView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.market_layout, this);
    }

    public void bindMarketManger(@NonNull MarketManagerBinder binder) {
        binder.bindMarketManager(this);
    }

    @Override
    public void addNavigationBar(@Nullable View navigationBar) {

        if (!(navigationBar instanceof INavigationBar))
            throw new IllegalArgumentException("navigationBar has to implement INavigationBar");

        if (this.navigationBar != null)
            removeViewAt(NAVIGATION_BAR_POSITION);
        this.navigationBar = navigationBar;
        addView(this.navigationBar, NAVIGATION_BAR_POSITION);
    }

    @Override
    public void addStickyViewHolder(@Nullable View stickyView) {

        if (!(stickyView instanceof IStickyView))
            throw new IllegalArgumentException("stickyView has to implement IStickyView");

        if (this.stickyView != null)
            removeViewAt(STICKY_VIEW_POSITION);
        this.stickyView = stickyView;
        addView(this.stickyView, STICKY_VIEW_POSITION);

    }

    @Override
    public void addIMarketSectionView(@Nullable View marketSectionView) {

        if (!(marketSectionView instanceof IMarketSectionView))
            throw new IllegalArgumentException("marketSectionView has to implement IMarketSectionView");

        if (this.marketSectionView != null)
            removeViewAt(SECTION_VIEW_POSITION);
        this.marketSectionView = marketSectionView;
        addView(this.marketSectionView, SECTION_VIEW_POSITION);
    }
}
