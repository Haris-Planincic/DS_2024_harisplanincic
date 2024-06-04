package homework3;

import java.util.ArrayList;


public class Node {
    public static final boolean RED = true;
    public static final boolean BLACK = false;

    private String key;
    private ArrayList<Entry> values;
    private Node left;
    private Node right;
    private boolean color;

    public Node(String key, Entry value, boolean color) {
        this.key = key;
        this.values = new ArrayList<>();
        this.values.add(value);
        this.left = null;
        this.right = null;
        this.color = color;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public ArrayList<Entry> getValues() {
        return values;
    }

    public void setValues(ArrayList<Entry> values) {
        this.values = values;
    }

    public void addValue(Entry value) {
        this.values.add(value);
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public boolean isRed() {
        return color == RED;
    }

    public boolean isBlack() {
        return color == BLACK;
    }

    public boolean getColor() {
        return color;
    }

    public void setColor(boolean color) {
        this.color = color;
    }
}
