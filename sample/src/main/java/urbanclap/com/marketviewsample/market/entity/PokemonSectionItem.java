package urbanclap.com.marketviewsample.market.entity;

/**
 * @author : Adnaan 'Zohran' Ahmed <adnaanahmed@urbanclap.com>
 * @version : 1.0.0
 * @since : 15 Mar 2018 4:08 PM
 */


public class PokemonSectionItem implements PokemonCartBaseItem {

    private String title;

    public PokemonSectionItem(String title) {
        this.title = title;
    }

    @Override
    public int getCartItemType() {
        return TYPE_SECTION;
    }

    @Override
    public String id() {
        return title.toLowerCase();
    }

    public String getTitle() {
        return title;
    }
}
