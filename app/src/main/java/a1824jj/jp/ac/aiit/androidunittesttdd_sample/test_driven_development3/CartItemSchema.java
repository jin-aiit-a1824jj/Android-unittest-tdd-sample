package a1824jj.jp.ac.aiit.androidunittesttdd_sample.test_driven_development3;

public class CartItemSchema {

    private final String mId;
    private final String mTitle;
    private final String mDescription;
    private final int mPrice;

    public CartItemSchema(String id, String title, String description, int price) {
        mId = id;
        mTitle = title;
        mDescription = description;
        mPrice = price;
    }

    public String getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getDescription() {
        return mDescription;
    }

    public int getPrice() {
        return mPrice;
    }

}
