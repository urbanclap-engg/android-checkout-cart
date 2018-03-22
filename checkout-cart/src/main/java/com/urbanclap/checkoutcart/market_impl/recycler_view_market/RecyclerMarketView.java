package com.urbanclap.checkoutcart.market_impl.recycler_view_market;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.urbanclap.checkoutcart.R;
import com.urbanclap.checkoutcart.frame_work.market.MarketManager;
import com.urbanclap.checkoutcart.frame_work.market.interfaces.IMarketView;

/**
 * @author : Adnaan 'Zohran' Ahmed <adnaanahmed@com.urbanclap>
 * @version : 1.0.0
 * @since : 14 Mar 2018 1:45 PM
 */


public class RecyclerMarketView extends RelativeLayout implements IMarketView {

    @Nullable
    private View navigationBar;
    @Nullable
    private View stickyView;
    @Nullable
    private View marketSectionView;

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

        setLayoutParams(
                new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                ));
    }

    @Override
    public void addNavigationBar(@Nullable View navigationBar, @NonNull ViewGroup.LayoutParams lp) {

        if (this.navigationBar != null)
            removeView(this.navigationBar);

        this.navigationBar = navigationBar;
        if (this.navigationBar != null) {
            this.navigationBar.setId(R.id.recycler_market_view_navigation_container);
            this.navigationBar.setLayoutParams(lp);
        }
        updateUI(true, true);
    }

    @Override
    public void addStickyViewHolder(@Nullable View stickyView, @NonNull ViewGroup.LayoutParams lp) {

        if (this.stickyView != null)
            removeView(this.stickyView);

        this.stickyView = stickyView;
        if (this.stickyView != null) {
            this.stickyView.setId(R.id.recycler_market_view_frame_container);
            this.stickyView.setLayoutParams(lp);
        }
        updateUI(false, false);
    }

    @Override
    public void addIMarketSectionView(@Nullable View marketSectionView, @NonNull ViewGroup.LayoutParams lp) {

        if (this.marketSectionView != null)
            removeView(this.marketSectionView);

        this.marketSectionView = marketSectionView;
        if (this.marketSectionView != null) {
            this.marketSectionView.setId(R.id.recycler_market_view_recycler_container);
            this.marketSectionView.setLayoutParams(lp);
        }
        updateUI(false, true);
    }

    @Override
    public void bindMarketManager(@NonNull MarketManager<?, ?, ?> marketManager) {
        marketManager.bindMarketManager(this);
    }

    private void updateUI(boolean updateNavBar, boolean updateSectionView) {
        if (navigationBar == null)
            updateNoNavBarUI(updateSectionView);
        else updateNavBarUI(updateNavBar, updateSectionView);
    }

    private void updateNoNavBarUI(boolean updateSectionView) {
        RelativeLayout.LayoutParams layoutParams;
        if (marketSectionView != null && updateSectionView) {
            layoutParams = new RelativeLayout.LayoutParams(marketSectionView.getLayoutParams());
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                layoutParams.addRule(RelativeLayout.ALIGN_PARENT_START);
            }
            replaceView(marketSectionView, layoutParams);
        }

        if (stickyView != null) {
            layoutParams = new RelativeLayout.LayoutParams(stickyView.getLayoutParams());
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                layoutParams.addRule(RelativeLayout.ALIGN_PARENT_START);
            }
            replaceView(stickyView, layoutParams);
        }
    }

    private void updateNavBarUI(boolean updateNavBar, boolean updateSectionView) {

        RelativeLayout.LayoutParams layoutParams;

        if (updateNavBar) {
            assert navigationBar != null;
            layoutParams = new RelativeLayout.LayoutParams(navigationBar.getLayoutParams());
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                layoutParams.addRule(RelativeLayout.ALIGN_PARENT_START);
            }
            replaceView(navigationBar, layoutParams);
        }

        if (marketSectionView != null && updateSectionView) {
            layoutParams = new RelativeLayout.LayoutParams(marketSectionView.getLayoutParams());
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
            layoutParams.addRule(RelativeLayout.BELOW, R.id.recycler_market_view_navigation_container);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                layoutParams.addRule(RelativeLayout.ALIGN_START);
            }
            replaceView(marketSectionView, layoutParams);
        }

        if (stickyView != null) {
            layoutParams = new RelativeLayout.LayoutParams(stickyView.getLayoutParams());
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
            layoutParams.addRule(RelativeLayout.BELOW, R.id.recycler_market_view_navigation_container);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                layoutParams.addRule(RelativeLayout.ALIGN_START);
            }
            replaceView(stickyView, layoutParams);
        }
    }

    private void replaceView(@NonNull View view, @NonNull LayoutParams layoutParams) {
        removeView(view);
        addView(view, layoutParams);
    }

}
