package urbanclap.com.marketview.market_impl.recycler_view_market;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import urbanclap.com.marketview.frame_work.market.MarketManager;
import urbanclap.com.marketview.frame_work.market.interfaces.IMarketView;

/**
 * @author : Adnaan 'Zohran' Ahmed <adnaanahmed@urbanclap.com>
 * @version : 1.0.0
 * @since : 14 Mar 2018 1:45 PM
 */


public class RecyclerMarketView extends LinearLayout implements IMarketView {

    @Nullable
    private View navigationBar;
    @Nullable
    private View stickyView;
    @Nullable
    private View marketSectionView;

    private int navigationBarPos;
    private int stickyViewPos;
    private int marketSectionViewPos;


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

        navigationBar = null;
        stickyView = null;
        marketSectionView = null;

        navigationBarPos = -1;
        stickyViewPos = -1;
        marketSectionViewPos = -1;

        setLayoutParams(
                new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                ));
        setOrientation(VERTICAL);
    }

    @Override
    public void addNavigationBar(@Nullable View navigationBar, @NonNull ViewGroup.LayoutParams layoutParams) {

        if (this.navigationBar != null && navigationBarPos >= 0)
            removeViewAt(navigationBarPos);
        this.navigationBar = navigationBar;
        if (this.navigationBar == null) {
            navigationBarPos = -1;
            return;
        }
        navigationBarPos = 0;
        addView(this.navigationBar, navigationBarPos, layoutParams);
    }

    @Override
    public void addStickyViewHolder(@Nullable View stickyView, @NonNull ViewGroup.LayoutParams layoutParams) {

        if (this.stickyView != null && stickyViewPos >= 0)
            removeViewAt(stickyViewPos);
        this.stickyView = stickyView;
        if (this.stickyView == null) {
            stickyViewPos = -1;
            return;
        }
        stickyViewPos = navigationBarPos + 1;
        addView(this.stickyView, stickyViewPos, layoutParams);
    }

    @Override
    public void addIMarketSectionView(@Nullable View marketSectionView, @NonNull ViewGroup.LayoutParams layoutParams) {

        if (this.marketSectionView != null && marketSectionViewPos >= 0)
            removeViewAt(marketSectionViewPos);
        this.marketSectionView = marketSectionView;
        if (this.marketSectionView == null) {
            marketSectionViewPos = -1;
            return;
        }
        marketSectionViewPos = Math.max(navigationBarPos, stickyViewPos) + 1;
        addView(this.marketSectionView, marketSectionViewPos, layoutParams);
    }

    @Override
    public void bindMarketManager(@NonNull MarketManager<?, ?, ?> marketManager) {
        marketManager.bindMarketManager(this);
    }
}
