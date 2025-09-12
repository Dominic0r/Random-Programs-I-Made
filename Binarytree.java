
class Node{
    int key;
    Node left, right;
    
    public Node(int value){
        this.key = value;
        this.left = right = null;
    }
}

public class Binarytree
{
    
    static Node root;
    
    public Binarytree(){
        root = null;
    }
    
    public static void insert(int key){
        root = insertRec(root, key);
    }
    
    private static Node insertRec(Node root, int key){
        if(root == null){
            root = new Node(key);
            return root;
        }
        
        if(key < root.key){
            root.left = insertRec(root.left, key);
        } else if(key > root.key){
            root.right = insertRec(root.right, key);
        }
        return root;
    }
    
    public static void inorder(){
        inorderRec(root);
    }
    
    private static void inorderRec(Node root){
        if(root != null){
            inorderRec(root.left);
            System.out.print(root.key+ " ");
            inorderRec(root.right);
        }
        
    }
    
    public static void preorder(){
        preorderRec(root);
        
    }
    
    public static void preorderRec(Node root){
        if(root != null){
        System.out.print(root.key+ " ");
        preorderRec(root.left);
        preorderRec(root.right);
        }
    }
    
    public static void postorder(){
        postorderRec(root);
    }
    
    public static void postorderRec(Node root){
        if(root != null){
            postorderRec(root.left);
        postorderRec(root.right);
        System.out.print(root.key+ " ");
        }
    }
    
	public static void main(String[] args) {
	    Binarytree tree = new Binarytree();
	    
		insert(25);
		insert(20);
		insert(36);
		insert(10);
		insert(22);
		insert(30);
		insert(40);
		insert(5);
		insert(12);
		insert(28);
		insert(38);
		insert(48);
		insert(1);
		insert(8);
		insert(15);
		insert(45);
		insert(50);
		
		System.out.print("Inorder: ");
		inorder();
		
		System.out.print("\nPreorder: ");
		preorder();
		System.out.print("\nPostorder: ");
		postorder();
	}
}


