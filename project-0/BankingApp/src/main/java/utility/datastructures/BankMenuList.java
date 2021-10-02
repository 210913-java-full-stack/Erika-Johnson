package utility.datastructures;

public class BankMenuList {
    //object array to store all the elements
    Object obj[];
    int s;
    int capacity;
    private String menuText;
    private boolean complete;

    public BankMenuList() {
        capacity = 10;
        s = 0;
        obj = new Object[capacity];
    }

    public void add(Object item) {
        //increment item by one
        obj[s++] = item;
    }

    public Object get(int i) {
        return obj[i];
    }

    public int size() {
        return s;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }
}



