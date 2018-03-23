# Checkout Cart

This library will help developers create fully customised checkout cart screen for their own use case.

## Settings

For maven :
```xml
<dependency>
  <groupId>com.urbanclap</groupId>
  <artifactId>checkout-cart</artifactId>
  <version>0.1.0</version>
  <type>pom</type>
</dependency>
```

For gradle:
```groovy
compile 'com.urbanclap:checkout-cart:0.1.0'
```

For Ivy:
```xml
<dependency org='com.urbanclap' name='checkout-cart' rev='0.1.0'>
  <artifact name='checkout-cart' ext='pom' ></artifact>
</dependency>
```

## Components

### Section

Checkout - cart accepts a list of section. Section itself contains an id for its unique identification, list of items and an optional sticky view. If a sticky view container is passed in the config this view will be rendered in the sticky view container when this section is visible.

`Section(@NonNull String id, @NonNull List<ItemData<T>> itemDataList)`

### Market Manager

Library provides the above basic components and interaction between them is handled by `MarketManager`.
At this point of time library only supports one variant of market manager : `RecyclerMarketManager` which shows the item list in a recycler view. 
`MarketManager` receives a set of configs (explained below) and according to which it will create and manage all the components.

### Config

This handles the configuration data for market manager. It receives list of sections in a constructor and has following configurations:

* `setNavigator(@Nullable INavigationBar navigationBar, @Nullable INavigationFactory<NT> navigationFactory)`  - It sets the navigation bar component of the checkout cart. When null is passed that view won't be attached and rendered.
* `setSticky(@Nullable IStickyView stickyView)` - It sets the sticy component of the checkout cart. When null is passed that view won't be attached and rendered.
* `setCart(@Nullable ICart<CT> cart)` - It sets the cart for  the checkout cart.
* `setNavigationCallback(@Nullable OnNavigationItemSelectCallback<NT> navigationItemSelectCallback)` - client can register callbacks for the navigation select for internal uses.
* `enableAutoNavBarScroll(boolean enable)` - when enabled auto nav bar automatically focusses on the current visible section.

### UI/Functional Components

This library accepts list of sections which in turn are the list of items that client wants to show along with config details. Client have to create view holders for the different kinds of views that client want to display and provide them via a factory. 

This library in total has 4 ui/functional components.
* Navigation Bar
* Sticky Section
* Cart
* Item List

#### Navigation Bar

This is a component where shortcut to a particular item in the item-list.
To enable this component client need to do 3 things.
* Provide a container view which must implement `INavigationBar`.
* Provide a Navigation item factory generator which must implement `INavigationFactory`.
* Mark Items which needs to be routable by making them implement `Routable`.


Internally the library manages everything. It will iterate through all the items passed and filter out the routable models and then create their views via the factory passed and populate them in the navigation bar. On clicking these items screen window moves to the particular item.

For easier usage library provides a default navigation bar container view : `NavigationHorizontalScroll` which can be accessed via `MarketUtils` class.

Library also provides `OnNavigationItemSelectCallback` interface to give client callbacks when a particular routable item was clicked via a function - `void onItemSelect(@NonNull String routeId, @NonNull List<Routable<T>> routable)`

#### Sticky Section

This is a component where if client wants, client can have a sticky header for a specific section. Here again client can define the container for their sticky section container which must implement `IStickyView`. All those sections which has a sticky view will then have that view displayed in the Sticky section by the library.

For easier usage library provides a default sticky section container view : `StickyFrameLayout` which can be accessed via `MarketUtils` class.


#### Cart

This components manages functional part of the checkout cart. Client can create their own implementation of the cart by implementing `ICart`.  

For easier usage library provides default cart `DefaultCart` which can be accessed via `MarketUtils` class.


#### Item List

This is the component where all the sections along with its items are visible. To display this client needs to pass a container view which must implement `IMarketView`. 

### Generics

This entire library is generic. This library needs three models :
1. view model for the item to be displayed as the list.
2. view model for the items to be displayed in the navigation bar.
3. data model for the cart.

In the library client needs to define this generic while defining `Config` and `MarketManager`. 

`MarketManager.Config<IT, NT, CT> config` where IT is the (1 - item template), NT is (2 - navigation template) and CT is (3 - cart template).

If any of the model is not needed we can use `Void`.


## Usage


### Creating Navigation Bar UI Component

Client will have to create view holder class(es) for the navigation. It is generic and client can have different kinds of view holders for different types. The view holder must extend `NavigationItemViewHolder<NT>`. Here `<NT>` is the navigation template generic model. In the example `String` will be used for `<NT>` .

```java
public class PokemonNavigationViewHolder extends NavigationItemViewHolder<String> {

    PokemonNavigationViewHolder(int layoutId, @NonNull Context context) {
        super(layoutId, context);
    }

    @Override
    public void onBind(String viewModel) {
        NavigationItemView itemView = getNavigationItemView();
        TextView tvTitle = itemView.findViewById(R.id.tv_nav);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "you clicked and it still scrolls.... wow", Toast.LENGTH_SHORT).show();
            }
        });
        tvTitle.setText("" + viewModel.charAt(0));
    }
}
```

Then client needs to create a view holder factory which the library will use to create view holders.

```java
public class NavigationItemFactory implements INavigationFactory<String> {

    public NavigationItemFactory() {
    }

    @Override
    public NavigationItemViewHolder<String> createViewHolder(int viewType, @NonNull Context context) {
        return new PokemonNavigationViewHolder(R.layout.navigation_bar_layout, context);
    }
}
```

Here item factory will receive view types and using that client can define different kinds of view holders that must be returned.
Here in the example only one kind is used.


Now if client wants client can create custom navigation bar container view which must implement `INavigationBar` or can use default provided by the library. To set it up just add them to the config :
```java
config.setNavigator(MarketUtils.getDefaultHorizontalNavigationBar(this),new NavigationItemFactory());
```

### Creating Sticky Section UI Component

To create sticky component client need to pass sticky view container to the config. The client can implement its own custom sticky view by implementing `IStickyView` or using the default provided by the library. To set it up just add in to the config:
```java
config.setSticky(MarketUtils.getDefaultStickyView(this))
```

Sticky view are related to each section. When creating a section client needs to pass its related sticky view which the library will show in the container. It can be null if section has no sticky header.
```java
section = new Section<>(itr.getUuid(), itemDataList, new IStickyViewItem() {
                @Override
                public View createView(@NonNull ViewGroup parent) {
                    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_section_layout, parent, false);
                    TextView textView = view.findViewById(R.id.tv_itemSection_title);
                    textView.setText(itr.getType());
                    return view;
                }
            });
```

### Creating Cart Functional Component

Client can create its own custom cart by implementing the interface `ICart<CT>`. Here `<CT>` is the cart template generic model. Client can also use the default cart provided by the library. In the case if client uses default client then client template model will be of type `CartItem<T>` where cart item encapsulates the cart data model along with fields like quantity and price. Here `<T>` is the data model to be stored inside. To set it up client needs to pass the cart to the config.
```java
config.setCart(new DefaultCart<DefaultCartItem<PokemonItem>>());
```

To increment or decrement the quantity of an item inside the cart, client needs to call `increment(@NonNull String uuid, CT item);` or `decrement(@NonNull String uuid, CT item);` respectively. It returns if the transaction was successful or not.

### Creating List Item UI Components

For list item components first client needs to create view holders for them. Client needs to implement `IItemViewHolder<IT,CT>` in the view holders. Here `<IT>` is the item template view model and `<CT>` is the cart template data model. Then client needs to create factory which the library will use to create these view model. Clients needs to create a factory by implementing `IItemFactory<IT, CT, K extends IItemViewHolder<IT, CT>>`. 

This is the abstract part of the library which client can use to create its own market manager in case `RecyclerMarketManager` doesn't solve their use case. For majority of use case client can use `RecyclerMarketManager`.

#### Setting up `RecyclerMarketManager`

As mentioned above client need to create a view holder for each items which will extend `RecyclerItemViewHolder<IT, CT>`. Here client will have to give definition to the function `public void onBindView(final IT vm)`. Here client can call `increment` or `decrement` function in case client have set up the cart.

```java
public class CartItemViewHolder extends RecyclerItemViewHolder<PokemonCartBaseItem, DefaultCartItem<PokemonItem>> {

    private TextView tvId;
    private TextView tvName;
    private TextView tvDesc;

    CartItemViewHolder(final View itemView) {
        super(itemView);
        tvId = itemView.findViewById(R.id.tv_item_id);
        tvName = itemView.findViewById(R.id.tv_item_name);
        tvDesc = itemView.findViewById(R.id.tv_item_desc);
    }

    @Override
    public void onBindView(final PokemonCartBaseItem vm) {
        final PokemonCartItem viewModel = (PokemonCartItem) vm;
        tvId.setText("# " + viewModel.getUuid());
        tvName.setText(viewModel.getName());
        tvDesc.setText(viewModel.getDescription());
        PokemonItem pokemonItem = new PokemonItem(viewModel.getUuid(), viewModel.getName(), viewModel.getDescription());
        final DefaultCartItem<PokemonItem> cartItem = new DefaultCartItem<>(1, 5.0, pokemonItem);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(itemView.getContext(), "added to cart", Toast.LENGTH_SHORT).show();
                increment(vm.id(), cartItem);
            }
        });
    }

}
```
After creating the view holder client needs to create factory to create these view holders which library will use to create the view holders and manage them. The factory must extend `RecyclerItemFactory<IT, CT>`.  It will receive viewType which can be used to create different kinds of view holders. 

```java
public class ItemFactory extends RecyclerItemFactory<PokemonCartBaseItem, DefaultCartItem<PokemonItem>> {
    @Override
    public RecyclerItemViewHolder<PokemonCartBaseItem, DefaultCartItem<PokemonItem>> createViewHolder(@NonNull Context context,
                                                                                                      @NonNull ViewGroup parent,
                                                                                                      int viewType) {
        switch (viewType) {
            case PokemonCartBaseItem.TYPE_CART:
                return new CartItemViewHolder(LayoutInflater
                        .from(context)
                        .inflate(R.layout.item_layout, parent, false));

            case PokemonCartBaseItem.TYPE_HEADER:
                return new SectionItemViewHolder(LayoutInflater
                        .from(context)
                        .inflate(R.layout.item_section_layout, parent, false));
        }
        return null;
    }
}
```

For the list to be visible client needs to create list of sections. A section in turn has three fields: id, list of items and sticky view associated with it. If no sticky view is passed there won't be any sticky header for that section.


```java
section = new Section<>(itr.getUuid(), itemDataList, new IStickyViewItem() {
                @Override
                public View createView(@NonNull ViewGroup parent) {
                    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_section_layout, parent, false);
                    TextView textView = view.findViewById(R.id.tv_itemSection_title);
                    textView.setText(itr.getType());
                    return view;
                }
            });
```

Then client needs to create a config for the market. It contains all the configs related to the market.

```java
config.setCart(new DefaultCart<DefaultCartItem<PokemonItem>>())
                .setSticky(MarketUtils.getDefaultStickyView(this))
                .setNavigator(
                        MarketUtils.getDefaultHorizontalNavigationBar(this),
                        new NavigationItemFactory()
                );
```

and finally we need to create a market manager with these configs and bind it to market view declared in the xml.

```java
marketView.bindMarketManager(new RecyclerMarketManager<>(this, config, new ItemFactory()));
```

In the xml :
```xml
<?xml version="1.0" encoding="utf-8"?>
<FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context="com.urbanclap.checkoutcartsample.main_screen.MainActivity">

    <com.urbanclap.checkoutcart.market_impl.recycler_view_market.RecyclerMarketView
        android:id="@+id/market"
        android:layout_width="match_parent"
        android:layout_height="match_parent" />


</FrameLayout>
```

## License
MIT License

Copyright (c) 2018 adnaan1703

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
