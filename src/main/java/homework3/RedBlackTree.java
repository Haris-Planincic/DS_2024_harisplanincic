package homework3;


import java.util.ArrayList;

public class RedBlackTree<T> {
    private Node root;

    public ArrayList<Entry> get(String searchableName) {
        int[] edgeCount = new int[2];
        Node x = root;
        while (x != null) {
            int cmp = searchableName.compareTo(x.getKey());
            if (cmp < 0) {
                if (!x.isBlack()) edgeCount[1]++;
                else edgeCount[0]++;
                x = x.getLeft();
            } else if (cmp > 0) {
                if (!x.isBlack()) edgeCount[1]++;
                else edgeCount[0]++;
                x = x.getRight();
            } else {
                System.out.println("Black edges: " + edgeCount[0] + ", Red edges: " + edgeCount[1]);
                return x.getValues();
            }
        }
        System.out.println("Black edges: " + edgeCount[0] + ", Red edges: " + edgeCount[1]);
        return null;
    }

    public void put(String searchableName, Entry entry) {
        root = put(root, searchableName, entry);
        root.setColor(Node.BLACK);
    }

    private Node put(Node h, String searchableName, Entry entry) {
        if (h == null) {
            return new Node(searchableName, entry, Node.RED);
        }

        int cmp = searchableName.compareTo(h.getKey());
        if (cmp < 0) {
            h.setLeft(put(h.getLeft(), searchableName, entry));
        } else if (cmp > 0) {
            h.setRight(put(h.getRight(), searchableName, entry));
        } else {
            h.addValue(entry);
        }
        if (isRed(h.getRight()) && !isRed(h.getLeft())) h = rotateLeft(h);
        if (isRed(h.getLeft()) && isRed(h.getLeft().getLeft())) h = rotateRight(h);
        if (isRed(h.getLeft()) && isRed(h.getRight())) flipColors(h);

        return h;
    }

    private boolean isRed(Node x) {
        if (x == null) return false;
        return x.getColor() == Node.RED;
    }

    private Node rotateLeft(Node h) {
        Node x = h.getRight();
        h.setRight(x.getLeft());
        x.setLeft(h);
        x.setColor(h.getColor());
        h.setColor(Node.RED);
        return x;
    }

    private Node rotateRight(Node h) {
        Node x = h.getLeft();
        h.setLeft(x.getRight());
        x.setRight(h);
        x.setColor(h.getColor());
        h.setColor(Node.RED);
        return x;
    }

    private void flipColors(Node h) {
        h.setColor(Node.RED);
        h.getLeft().setColor(Node.BLACK);
        h.getRight().setColor(Node.BLACK);
    }

    public int[] countRedAndBlackEdges() {
        int[] count = new int[2];
        countEdges(root, count);
        return count;
    }

    private void countEdges(Node node, int[] count) {
        if (node == null) return;

        if (node.getLeft() != null) {
            if (node.getLeft().isRed()) count[1]++;
            else count[0]++;
            countEdges(node.getLeft(), count);
        }

        if (node.getRight() != null) {
            if (node.getRight().isRed()) count[1]++;
            else count[0]++;
            countEdges(node.getRight(), count);
        }
    }
}

