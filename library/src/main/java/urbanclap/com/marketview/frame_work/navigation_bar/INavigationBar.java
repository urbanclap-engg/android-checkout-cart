package urbanclap.com.marketview.frame_work.navigation_bar;

import android.support.annotation.NonNull;

/**
 * @author : Adnaan 'Zohran' Ahmed <adnaanahmed@urbanclap.com>
 * @version : 1.0.0
 * @since : 12 Mar 2018 2:13 PM
 */


public interface INavigationBar {
    void addView(@NonNull NavigationItemView navigationItemView);

    void addView(@NonNull NavigationItemView navigationItemView, int pos);

    void updateView(@NonNull NavigationItemView navigationItemView, int pos);

    void removeView(int pos);
}
