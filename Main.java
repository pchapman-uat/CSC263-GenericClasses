public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World");

        System.out.println("Integer Binary Search Tree");
        // Implimentation
        BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
        // Insert keys
        bst.insert(30);
        bst.insert(70);
        bst.insert(100);
        bst.insert(10);
        bst.insert(90);
        bst.insert(20);

        System.out.println("In-Order:");
        bst.inOrderTraversal();
        System.out.println();

        System.out.println("Pre-Order:");
        bst.preOrderTraversal();
        System.out.println();

        System.out.println("Post-Order:");
        bst.postOrderTraversal();
        System.out.println();

        int keyToSearch = 70;
        System.out.println("Search for "+ keyToSearch);
        boolean result = bst.search(keyToSearch);
        if(result) System.out.println(keyToSearch+" is in the tree");
        else System.out.println(keyToSearch + " is not in the tree");
        System.out.println();

        int keyToDelete = 30;
        System.out.println("Deleteting "+ keyToDelete);
        bst.delete(keyToDelete);
        System.out.println("In-Order traversal after deleting " + keyToDelete);
        bst.inOrderTraversal();
        System.out.println();

        System.out.println("String Binary Search Tree");
        BinarySearchTree<String> bst2 = new BinarySearchTree<String>();
        // Insert keys
        bst2.insert("apple");
        bst2.insert("banana");
        bst2.insert("cherry");
        bst2.insert("date");
        bst2.insert("elderberry");
        bst2.insert("fig");

        System.out.println("In Order");
        bst2.inOrderTraversal();
        System.out.println();

        System.out.println("Pre Order");
        bst2.preOrderTraversal();
        System.out.println();

        System.out.println("Post Order");
        bst2.postOrderTraversal();
        System.out.println();

        System.out.println("Search for 'banana'");
        boolean result2 = bst2.search("banana");
        if(result2) System.out.println("banana is in the tree");
        else System.out.println("banana is not in the tree");
        System.out.println();

        System.out.println("Deleting 'cherry'");
        bst2.delete("cherry");
        System.out.println("In Order traversal after deleting 'cherry'");
        bst2.inOrderTraversal();
        System.out.println();


    }
}


// Binary Tree Class
class Node<T extends Comparable<T>> implements Comparable<Node<T>> {
    T key;
    Node<T> left, right;

    public Node(T item) {
        this.key = item;
        this.left = null;
        this.right = null;
    }

    @Override
    public int compareTo(Node<T> other) {
        if (other == null) {
            throw new NullPointerException("Cannot compare with null");
        }
        // Delegate comparison to the key's compareTo method
        return this.key.compareTo(other.key);
    }
}


class BinarySearchTree<T extends Comparable<T>> {
    private Node<T> root;
    public BinarySearchTree(){
        root = null;
    }
    public void insert(T key){
        root = insertRec(root, key);
    }
    private Node<T> insertRec(Node<T> root, T key) {
        if (root == null) {
            root = new Node<T>(key);
            return root;
        }
    
        if (key.compareTo(root.key) < 0) {
            root.left = insertRec(root.left, key);
        } else if (key.compareTo(root.key) > 0) {
            root.right = insertRec(root.right, key);
        }
    
        return root;
    }
    
    public void delete(T key){
        root = deleteRec(root, key);
    }
    private Node<T> deleteRec(Node<T> root, T key){
        if(root == null){
            return root;
        } 
        if(key.compareTo(root.key) < 0){
            root.left = deleteRec(root.left, key);
        }else if(key.compareTo(key) > 0){
            root.right = deleteRec(root.right, key);
        } else {
            // If node with only 1 or less children
            if(root.left == null){
                return root.right;
            } else if(root.right == null){
                return root.left;
            } 
            // Node with two children get the inorder sucesssor (smallest in right sub tree)
            root.key = minValue(root.right);
            // Delete the in order successor
            root.right = deleteRec(root.right, root.key);
        } return root;
    }
    private T minValue(Node<T> root){
        T minValue = root.key;
        while(root.left != null){
            minValue = root.left.key;
        } return minValue;        
    }
    public boolean search(T key){
        return searchRec(root, key);
    }
    private boolean searchRec(Node<T> root, T key){
        if(root == null || root.key.compareTo(key) == 0){
            return root!=null;
        }
        if(root.key.compareTo(key) < 0){
            return searchRec(root.left, key);
        }
        return searchRec(root.right, key);
    }
    // Inorder traversal
    public void inOrderTraversal(){
        inOrderTraversalRec(root);
    }
    private void inOrderTraversalRec(Node<T> root){
        if(root != null){
            inOrderTraversalRec(root.left);
            System.out.println(root.key + " ");
            inOrderTraversalRec(root.right);
        }
    }
    public void preOrderTraversal(){
        preOrderTraversalRec(root);
    }
    private void preOrderTraversalRec(Node<T> root){
        if(root!=null){
            System.out.println(root.key + " ");
            preOrderTraversalRec(root.left);
            preOrderTraversalRec(root.right);
        }
    }
    // Post Order Traversal
    public void postOrderTraversal(){
        postOrderTraversalRec(root);
    }
    private void postOrderTraversalRec(Node<T> root){
        if(root!=null){
            postOrderTraversalRec(root.left);
            postOrderTraversalRec(root.right);
            System.out.println(root.key + " ");
        }
    }
    
}