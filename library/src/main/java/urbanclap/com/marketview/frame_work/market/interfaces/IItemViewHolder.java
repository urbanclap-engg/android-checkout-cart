package urbanclap.com.marketview.frame_work.market.interfaces;

/**
 * @author : Adnaan 'Zohran' Ahmed <adnaanahmed@urbanclap.com>
 * @version : 1.0.0
 * @since : 12 Mar 2018 6:11 PM
 */


public interface IItemViewHolder<T> {
    void onBindView(T viewModel);
}
