class Node {
    int key;
    Node left, right;

    public Node(int item) {
        key = item;
        left = right = null;
    }
}

public class BST {
    private Node root;

    public BST() {
        root = null;
    }

    public void insert(int key) {
        root = insertRecursive(root, key);
    }

    private Node insertRecursive(Node root, int key) {
        if (root == null) {
            root = new Node(key);
            return root;
        }

        if (key < root.key)
            root.left = insertRecursive(root.left, key);
        else if (key > root.key)
            root.right = insertRecursive(root.right, key);

        return root;
    }

    public boolean search(int key) {
        return searchRecursive(root, key);
    }

    private boolean searchRecursive(Node root, int key) {
        if (root == null || root.key == key)
            return root != null;

        if (root.key < key)
            return searchRecursive(root.right, key);

        return searchRecursive(root.left, key);
    }

    public void delete(int key) {
        root = deleteRecursive(root, key);
    }

    private Node deleteRecursive(Node root, int key) {
        if (root == null) return root;

        if (key < root.key)
            root.left = deleteRecursive(root.left, key);
        else if (key > root.key)
            root.right = deleteRecursive(root.right, key);
        else {
            if (root.left == null)
                return root.right;
            else if (root.right == null)
                return root.left;

            root.key = minValue(root.right);

            root.right = deleteRecursive(root.right, root.key);
        }

        return root;
    }

    private int minValue(Node root) {
        int minValue = root.key;
        while (root.left != null) {
            minValue = root.left.key;
            root = root.left;
        }
        return minValue;
    }

    public void inorder() {
        inorderRecursive(root);
        System.out.println();
    }

    private void inorderRecursive(Node root) {
        if (root != null) {
            inorderRecursive(root.left);
            System.out.print(root.key + " ");
            inorderRecursive(root.right);
        }
    }

    public void preorder() {
        preorderRecursive(root);
        System.out.println();
    }

    private void preorderRecursive(Node root) {
        if (root != null) {
            System.out.print(root.key + " ");
            preorderRecursive(root.left);
            preorderRecursive(root.right);
        }
    }

    public void postorder() {
        postorderRecursive(root);
        System.out.println();
    }

    private void postorderRecursive(Node root) {
        if (root != null) {
            postorderRecursive(root.left);
            postorderRecursive(root.right);
            System.out.print(root.key + " ");
        }
    }

    public static void main(String[] args) {
        BST bst = new BST();

        bst.insert(50);
        bst.insert(30);
        bst.insert(20);
        bst.insert(40);
        bst.insert(70);
        bst.insert(60);
        bst.insert(80);

        System.out.println("Inorder traversal:");
        bst.inorder();

        System.out.println("Preorder traversal:");
        bst.preorder();

        System.out.println("Postorder traversal:");
        bst.postorder();

        int key = 50;
        System.out.println("Searching for key " + key + ": " + (bst.search(key) ? "Found" : "Not found"));

        key = 90;
        System.out.println("Searching for key " + key + ": " + (bst.search(key) ? "Found" : "Not found"));

        bst.delete(50);
        System.out.println("Inorder traversal after deleting 50:");
        bst.inorder();
    }
}
