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
// Compareable allows to compare two values of the same type
// To make generic we need to add <T extends Comparable<T>>
// extends is to make sure the object is comparable
class Node<T extends Comparable<T>> implements Comparable<Node<T>> {
    // Generic type T for the key
    T key;
    // Left and right child nodes
    Node<T> left, right;

    // Constructor to initialize the key and set left and right children to null
    public Node(T item) {
        this.key = item;
        this.left = null;
        this.right = null;
    }

    // An override must be called when implementing Comparable
    // This will return 3 different values
    // -1 if this object is less than the other object
    // 0 if this object is equal to the other object
    // 1 if this object is greater than the other object
    @Override
    public int compareTo(Node<T> other) {
        // Check for null
        if (other == null) {
            throw new NullPointerException("Cannot compare with null");
        }
        // Delegate comparison to the key's compareTo method
        return this.key.compareTo(other.key);
    }
}


// Binary Search Tree Class
// T is the type of the key
class BinarySearchTree<T extends Comparable<T>> {
    // Root of the BST, using the generic key type T
    private Node<T> root;
    // Constructor to initialize the root to null
    public BinarySearchTree(){
        root = null;
    }
    // Insert a key into the BST
    public void insert(T key){
        root = insertRec(root, key);
    }
    // Recursive helper method to insert a key into the BST
    private Node<T> insertRec(Node<T> root, T key) {
        // If the tree is empty, create a new node and return it
        if (root == null) {
            root = new Node<T>(key);
            return root;
        }
    
        // Compare the key with the root's key
        // If the key is less than the root's key, insert in the left subtree
        // If the key is greater than the root's key, insert in the right subtree
        if (key.compareTo(root.key) < 0) {
            root.left = insertRec(root.left, key);
        } else if (key.compareTo(root.key) > 0) {
            root.right = insertRec(root.right, key);
        }
        // Return the (unchanged) node pointer
        return root;
    }
    
    // Delete a key from the BST
    public void delete(T key){
        // Call the recursive helper method to delete the key
        root = deleteRec(root, key);
    }

    // Recursive helper method to delete a key from the BST
    private Node<T> deleteRec(Node<T> root, T key){
        // If the root is null, return null
        if(root == null){
            return root;
        } 
        // Recursively call the delete method on the left subtree
        // If the key is less than the root's key, delete from the left subtree
        // If the key is greater than the root's key, delete from the right subtree
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

    // Find the minimum value in the BST
    private T minValue(Node<T> root){
        // Set the minimum value to the root's key
        T minValue = root.key;
        // While the left child is not null, set the minimum value to the left child's key
        // Then return the minimum value
        while(root.left != null){
            minValue = root.left.key;
        } return minValue;        
    }

    // Search the BST for a key
    public boolean search(T key){
        // Call the recursive helper method to search for the key
        return searchRec(root, key);
    }

    // Recursive helper method to search for a key in the BST
    private boolean searchRec(Node<T> root, T key){
        // If the root is null return false
        // If the root's key is equal to the key, return true
        if(root == null || root.key.compareTo(key) == 0){
            return root!=null;
        }
        // If the key is less than the root's key, search in the left subtree
        if(root.key.compareTo(key) < 0){
            return searchRec(root.left, key);
        }
        // If the key is greater than the root's key, search in the right subtree
        return searchRec(root.right, key);
    }

    // Inorder traversal
    public void inOrderTraversal(){
        // Call the recursive helper method to perform inorder traversal
        inOrderTraversalRec(root);
    }

    // Recursive helper method to perform inorder traversal
    private void inOrderTraversalRec(Node<T> root){
        // If the root is not null, 
        // traverse the left subtree, 
        // print the root's key, 
        // and traverse the right subtree
        if(root != null){
            inOrderTraversalRec(root.left);
            System.out.println(root.key + " ");
            inOrderTraversalRec(root.right);
        }
    }

    // Pre Order Traversal
    public void preOrderTraversal(){
        // Call the recursive helper method to perform preorder traversal
        preOrderTraversalRec(root);
    }

    // Recursive helper method to perform preorder traversal
    private void preOrderTraversalRec(Node<T> root){
        // If the root is not null,
        // print the root's key,
        // traverse the left subtree,
        // and traverse the right subtree
        if(root!=null){
            System.out.println(root.key + " ");
            preOrderTraversalRec(root.left);
            preOrderTraversalRec(root.right);
        }
    }

    // Post Order Traversal
    public void postOrderTraversal(){
        // Call the recursive helper method to perform postorder traversal
        postOrderTraversalRec(root);
    }

    // Recursive helper method to perform postorder traversal
    private void postOrderTraversalRec(Node<T> root){
        // If the root is not null,
        // traverse the left subtree,
        // traverse the right subtree,
        // and print the root's key
        if(root!=null){
            postOrderTraversalRec(root.left);
            postOrderTraversalRec(root.right);
            System.out.println(root.key + " ");
        }
    }
    
}