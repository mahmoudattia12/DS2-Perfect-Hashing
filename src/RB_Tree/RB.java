package RB_Tree;

import AVL_Tree.ITree;

public class RB<T extends Comparable<T>> implements ITree<T> {
    private RBNode<T> root;
    private int size;
    private complementary function;
    private int treeHeight;

    public RB(){
        RBNode<T>Tnull=new RBNode<T>(null);
        Tnull.setColor(Color.BLACK);
        root =Tnull;
        size = 0;
        treeHeight = -1;
        function = complementary.getInstance();
    }

    public void treeTraversal(RBNode<T> currentNode, String direction){
        // base case
        if( currentNode.getKey() == null ){
            return;
        }

        treeTraversal(currentNode.getLeftChild(), "l" );

        // work
        System.out.println( currentNode.getKey() + ", my parent is: " + ((currentNode.getParent() != null)?  currentNode.getParent().getKey(): null ) + ", " + direction + ", " + currentNode.getColor() );

        treeTraversal(currentNode.getRightChild(), "r");

    }

    public boolean search(T valueNeeded){
        return function.search(valueNeeded, root);
    }


    public RBNode<T> getRoot() {
        return this.root;
    }

    public boolean insert(T valueInserted){
        // need return type(boolean) ... implementation.
        int oldSize = size;
        root = function.insert(valueInserted, root, null, this);
        function.checkBalance(root);
        if( oldSize == size ){
            return false;
        } else {
            return true;
        }
    }

    public boolean delete(T val){
        boolean isDeleted = function.deleteNode(val,root,this);
        if( !isDeleted ){
            return false;
        } else {
            decrementSize();
            return true;
        }
    }

    public int getSize(){
        return this.size;
    }
    public void incrementSize() {
        size++;
    }
    public void decrementSize() {
        size--;
    }

    private int updateHeight(){
        if( size == 0 )
            return treeHeight = -1;
        else {
            return treeHeight = function.updateHeight(root);
        }
    }
    public int getHeight(){
        return this.updateHeight();
    }
    public void transplant(RBNode<T> nodeTobeDeleted,RBNode<T> replacement){
        if(nodeTobeDeleted.getParent()== null)
            root = replacement;
        else if (nodeTobeDeleted == nodeTobeDeleted.getParent().getLeftChild())
            nodeTobeDeleted.getParent().setLeftChild(replacement);
        else
            nodeTobeDeleted.getParent().setRightChild(replacement);

        replacement.setParent(nodeTobeDeleted.getParent());
    }
    public void setRoot(RBNode<T>val){
        this.root=val;
    }
}
