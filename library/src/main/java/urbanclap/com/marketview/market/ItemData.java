package urbanclap.com.marketview.market;

/**
 * @author : Adnaan 'Zohran' Ahmed <adnaanahmed@urbanclap.com>
 * @version : 1.0.0
 * @since : 12 Mar 2018 6:09 PM
 */


public interface ItemData<T> {
    T getViewModel();

    String getUUID();

    int getViewType();
}
