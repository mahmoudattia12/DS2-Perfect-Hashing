package AVL_Tree;


import RB_Tree.complementary;

public class AVL<T extends Comparable<T>> implements ITree<T> {
    private Node<T> root;
    private int size;
    public AVL(){
        root = null;
        size = 0;
    }

    public Node<T> getRoot() {
        return root;
    }
    private boolean checkInsert = true;

    public boolean insert(T key){
        checkInsert = true;
        root = insertRec(key,root);
        return checkInsert;
    }
    private Node<T> insertRec(T val,Node<T> root){
        //size will be updated during insertion
        //normal BST insert
        if(root==null){
            size++;
            return new Node<>(val);
        }
        else if(root.getKey().compareTo(val) > 0)
            root.setLeft(insertRec(val,root.getLeft()));
        else if(root.getKey().compareTo(val) < 0)
            root.setRight(insertRec(val,root.getRight()));
        else{
            //already exists
            checkInsert = false;
            return root;
        }
        //update height
        root.setHeight(Math.max(height(root.getLeft()), height(root.getRight())) + 1);
        //get balance
        int balance = balanceFactor(root);
        //fix balance only once
        //right
        if(balance < -1){
            //RR case --> val > root.Right.Key
            if(root.getRight().getKey().compareTo(val) < 0){
                return leftRotate(root);
            }
            //RL case
            root.setRight(rightRotate(root.getRight()));
            return leftRotate(root);
        }
        //left
        if(balance > 1){
            //LL case --> val < root.Left.key
            if(root.getLeft().getKey().compareTo(val) > 0){
                return rightRotate(root);
            }
            //LR case
            root.setLeft(leftRotate(root.getLeft()));
            return rightRotate(root);
        }
        return root;
    }

    //function to get the minimum value of the subtree rooted at root
    private T minRight(Node<T> root){
        T min = root.getKey();
        while (root.getLeft() != null){
            root = root.getLeft();
            min = root.getKey();
        }
        return min;
    }
    private boolean checkDelete = true;
    public boolean delete(T val){
        checkDelete = true;
        root = deleteRec(val, root);
        return checkDelete;
    }
    private Node<T> deleteRec(T val, Node<T> root){
        //normal BST delete
        if(root == null){
            checkDelete = false;
            return root;
        }

        if(root.getKey().compareTo(val) > 0){
            root.setLeft(deleteRec(val, root.getLeft()));
        }else if(root.getKey().compareTo(val) < 0){
            root.setRight(deleteRec(val, root.getRight()));
        }else{
            if(root.getLeft() == null){
                size--;
                root = root.getRight();
            }else if(root.getRight() == null){
                size--;
                root = root.getLeft();
            }else{
                T toDelete = minRight(root.getRight());
                root.setKey(toDelete);
                root.setRight(deleteRec(toDelete, root.getRight()));
            }
        }
        //delete a single node
        if(root == null){
            return root;
        }
        //update height
        root.setHeight(Math.max(height(root.getLeft()), height(root.getRight())) + 1);
        //get balance
        int balance = balanceFactor(root);
        //fix balance up till root
        //right
        if(balance < -1){
            //RR case
            if(balanceFactor(root.getRight()) <= 0){
                return leftRotate(root);
            }
            //RL case
            root.setRight(rightRotate(root.getRight()));
            return leftRotate(root);
        }
        //left
        if(balance > 1){
            //LL case
            if(balanceFactor(root.getLeft()) >= 0){
                return rightRotate(root);
            }
            //LR case
            root.setLeft(leftRotate(root.getLeft()));
            return rightRotate(root);
        }
        return root;
    }
    private Node<T> search(T val, Node<T> tempRoot){
        if(tempRoot == null || tempRoot.getKey().compareTo(val) == 0)
            return tempRoot;
        if(tempRoot.getKey().compareTo(val) > 0){
            return search(val, tempRoot.getLeft());
        }
        return search(val, tempRoot.getRight());
    }

    public boolean search(T val){
        Node<T> node = search(val, root);
        if(node == null){
            return false;
        }
        return true;
    }

    public int getSize(){
        return size;
    }

    public int getHeight(){
        return height(root) - 1;
    }
    private int height(Node<T> node){
        if(node == null)
            return 0;
        return node.getHeight();
    }
    private int balanceFactor(Node<T> node){
        if(node == null)
            return 0;
        return height(node.getLeft()) - height(node.getRight());
    }
    private Node<T> leftRotate(Node<T> node){
        //the new root
        Node<T> r = node.getRight();
        node.setRight(r.getLeft());
        r.setLeft(node);
        //update heights
        node.setHeight(Math.max(height(node.getLeft()),height(node.getRight())) + 1);
        r.setHeight(Math.max(height(r.getLeft()),height(r.getRight())) + 1);
        return r;
    }
    private Node<T> rightRotate(Node<T> node){
        //the new root
        Node<T> r = node.getLeft();
        node.setLeft(r.getRight());
        r.setRight(node);
        //update heights
        node.setHeight(Math.max(height(node.getLeft()), height(node.getRight())) + 1);
        r.setHeight(Math.max(height(r.getLeft()),height(r.getRight())) + 1);
        return r;
    }
}