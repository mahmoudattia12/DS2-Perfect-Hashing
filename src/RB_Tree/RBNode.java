package RB_Tree;

public class RBNode<T extends Comparable<T>> {

    // variables
    private T key;
    private RBNode<T> leftChild, rightChild;
    private Color color;
    private RBNode<T> parent;
    private int height;



    // constructor
    public RBNode(T key){
        this.key = key;
        this.leftChild = null;
        this.rightChild = null;
        this.color = Color.RED;
        this.parent = null;
        height = 0;
    }

    // setters and getters
    public RBNode<T> getLeftChild() {
        return leftChild;
    }
    public void setLeftChild(RBNode<T> leftChild) {
        this.leftChild = leftChild;
    }
    public RBNode<T> getRightChild() {
        return rightChild;
    }
    public void setRightChild(RBNode<T> rightChild) {
        this.rightChild = rightChild;
    }
    public Color getColor() {
        return color;
    }
    public void setColor(Color color) {
        this.color = color;
    }
    public RBNode<T> getParent() {
        return parent;
    }
    public void setParent(RBNode<T> parent) {
        this.parent = parent;
    }
    public T getKey() {

        return key;
    }

    public void setKey(T val) {
         key=val;
    }
    public int getHeight() {
        return height;
    }
    public void setHeight(int height) {
        this.height = height;
    }
    public boolean isLeaf(){
        return ( this.leftChild == null && this.rightChild == null );
    }
}
