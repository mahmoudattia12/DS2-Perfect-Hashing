package AVL_Tree;

public interface ITree<T extends Comparable<T>>{
    boolean insert(T key);
    boolean delete(T val);
    boolean search(T val);
    int getSize();
    int getHeight();

}