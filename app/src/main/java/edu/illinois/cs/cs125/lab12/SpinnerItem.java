package edu.illinois.cs.cs125.lab12;

public class SpinnerItem {

    private String itemText;
    private Integer itemId;

    public String getItemText() {
        return itemText;
    }

    public void setItemText(String itemText) {
        this.itemText = itemText;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    SpinnerItem(String setItemName) {
        this.itemText = setItemName;
//        System.out.println(setItemName);
        this.itemId = null;
    }

    SpinnerItem(String setItemName, Integer setItemIconId) {
        this.itemText = setItemName;
        this.itemId = setItemIconId;
    }

    @Override
    public String toString() {
        return this.getItemText();
    }
}
