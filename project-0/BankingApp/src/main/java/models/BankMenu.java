package models;

import java.util.LinkedList;
import java.util.List;

public class BankMenu {
    private String menuText;
    private boolean complete;

    //use constructor to instantiate an object, then use this keyword refers to that object reference
    public BankMenu() {

    }

    //single argument constructor that constructs the field
    public BankMenu(String menuText) {
        complete = false;
        this.menuText = menuText;
    }

    //get value
    public String getMenuText() {
        return menuText;
    }

    //set value
    public void setMenuText(String menuText) {
        this.menuText = menuText;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }


}
