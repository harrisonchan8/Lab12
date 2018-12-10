package edu.illinois.cs.cs125.lab12;

public class SpinnerItem {

    private String itemText;
    private int itemId;

    public String getItemText() {
        return itemText;
    }

    public void setItemText(String itemText) {
        this.itemText = itemText;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = (int) itemId;
    }

    SpinnerItem(String setItemName) {
        this.itemText = setItemName;
//        System.out.println(setItemName);
        this.itemId = 0;
    }

    SpinnerItem(String setItemName, long setItemIconId) {
        this.itemText = setItemName;
        this.itemId = (int) setItemIconId;
    }

    @Override
    public String toString() {
        return this.getItemText();
    }
}
