package edu.neu.madcourse.numad22sp_azimtamboli;
/*
    I have borrowed this code from professors sample code which I found extremely helpful.
     */

public class ItemCard implements ItemClickListener {

    private final String itemName;
    private final String itemUrl;
    private boolean isChecked;

    public ItemCard(String itemName, String itemUrl) {
        this.itemName = itemName;
        this.itemUrl = itemUrl;
    }


    @Override
    public void onItemClick(int position) {
        isChecked = !isChecked;
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemUrl() {
        return itemUrl;
    }
}
