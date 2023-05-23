package AVL_Tree;

public class Node<T extends Comparable<T>>{
    private T key;
    private Node<T> left,right;
    private int height;
    public Node(T val){
        key = val;
        left=right=null;
        height = 1;
    }

    public T getKey() {
        return key;
    }

    public void setKey(T key) {
        this.key = key;
    }

    public Node<T> getLeft() {
        return left;
    }

    public void setLeft(Node<T> left) {
        this.left = left;
    }

    public Node<T> getRight() {
        return right;
    }

    public void setRight(Node<T> right) {
        this.right = right;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void print(){
        if(this.left!=null)
            this.left.print();
        System.out.print("val: " +this.key);
        if(this.left == null){
            System.out.print("  left: null");
        }else{
            System.out.print("  left: " + this.left.key);
        }
        if(this.right == null){
            System.out.println("  right: null");
        }else{
            System.out.println("  right: " + this.right.key);
        }
        if(this.right!=null)
            this.right.print();
    }


}