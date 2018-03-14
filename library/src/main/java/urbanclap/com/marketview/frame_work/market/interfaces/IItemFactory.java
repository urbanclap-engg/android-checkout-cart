package urbanclap.com.marketview.frame_work.market.interfaces;

/**
 * @author : Adnaan 'Zohran' Ahmed <adnaanahmed@urbanclap.com>
 * @version : 1.0.0
 * @since : 12 Mar 2018 6:12 PM
 */


public interface IItemFactory<T, K extends IItemViewHolder<T>> {
    K createViewHolder(int viewType);
}
