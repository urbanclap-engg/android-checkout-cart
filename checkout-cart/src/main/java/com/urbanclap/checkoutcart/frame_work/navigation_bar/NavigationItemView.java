package com.urbanclap.checkoutcart.frame_work.navigation_bar;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;


/**
 * @author : Adnaan 'Zohran' Ahmed <adnaanahmed@com.urbanclap>
 * @version : 1.0.0
 * @since : 12 Mar 2018 2:00 PM
 */


public class NavigationItemView extends FrameLayout {

    @Nullable
    String id;

    @Nullable
    private OnNavigateCallback callback;

    public NavigationItemView(@NonNull Context context) {
        super(context);
    }

    private NavigationItemView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        throw new IllegalStateException("This needs to be initialised with context only constructor");
    }

    private NavigationItemView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        throw new IllegalStateException("This needs to be initialised with context only constructor");
    }

    public void setContentView(@LayoutRes int id) {
        LayoutInflater.from(getContext()).inflate(id, this, true);
        setOnClickListener(new NavigationItemClickListener(null));
    }

    public void setCallback(@Nullable OnNavigateCallback callback, @Nullable String id) {
        this.id = id;
        this.callback = callback;
    }

    @Override
    public void setOnClickListener(@Nullable OnClickListener l) {
        NavigationItemClickListener clickListener = new NavigationItemClickListener(l);
        super.setOnClickListener(clickListener);
    }

    private class NavigationItemClickListener implements View.OnClickListener {

        @Nullable
        private OnClickListener onClickListener;

        NavigationItemClickListener(@Nullable OnClickListener onClickListener) {
            this.onClickListener = onClickListener;
        }

        @Override
        public void onClick(View view) {

            if (callback != null && id != null)
                callback.navigateTo(id);

            if (onClickListener != null)
                onClickListener.onClick(view);
        }
    }

    public interface OnNavigateCallback {
        void navigateTo(@NonNull String id);
    }
}
