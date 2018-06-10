package android.c4q.com.finalretake;

/**
 * Created by C4Q on 6/9/18.
 */

public class Item {
    private String mImageUrl;
    private String cardValue;

    public Item(String mImageUrl, String cardValue) {
        this.mImageUrl = mImageUrl;
        this.cardValue = cardValue;
    }

    public String getmImageUrl() {
        return mImageUrl;
    }

    public void setmImageUrl(String mImageUrl) {
        this.mImageUrl = mImageUrl;
    }

    public String getCardValue() {
        return cardValue;
    }

    public void setCardValue(String cardValue) {
        this.cardValue = cardValue;
    }
}
