public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World");

        // Implimentation
        BinarySearchTree bst = new BinarySearchTree();
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
    }
}


// Binary Tree Class
class Node {
    int key;
    Node left, right;
    public Node(int item){
        key = item;
        left = right = null;
    }
}

class BinarySearchTree{
    private Node root;
    public BinarySearchTree(){
        root = null;
    }
    public void insert(int key){
        root = insertRec(root, key);
    }
    private Node insertRec(Node root, int key){
        if(root == null){
            root = new Node(key);
            return root;
        }
        if(key<root.key){
            root.left = insertRec(root.left, key);
        } else if(key>root.key){
            root.right = insertRec(root.right, key);
        } return root;
    }
    public void delete(int key){
        root = deleteRec(root, key);
    }
    private Node deleteRec(Node root, int key){
        if(root == null){
            return root;
        } 
        if(key<root.key){
            root.left = deleteRec(root.left, key);
        }else if(key>root.key){
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
    private int minValue(Node root){
        int minValue = root.key;
        while(root.left != null){
            minValue = root.left.key;
        } return minValue;        
    }
    public boolean search(int key){
        return searchRec(root, key);
    }
    private boolean searchRec(Node root, int key){
        if(root == null || root.key == key){
            return root!=null;
        }
        if(key < root.key){
            return searchRec(root.left, key);
        }
        return searchRec(root.right, key);
    }
    // Inorder traversal
    public void inOrderTraversal(){
        inOrderTraversalRec(root);
    }
    private void inOrderTraversalRec(Node root){
        if(root != null){
            inOrderTraversalRec(root.left);
            System.out.println(root.key + " ");
            inOrderTraversalRec(root.right);
        }
    }
    public void preOrderTraversal(){
        preOrderTraversalRec(root);
    }
    private void preOrderTraversalRec(Node root){
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
    private void postOrderTraversalRec(Node root){
        if(root!=null){
            postOrderTraversalRec(root.left);
            postOrderTraversalRec(root.right);
            System.out.println(root.key + " ");
        }
    }
    
}