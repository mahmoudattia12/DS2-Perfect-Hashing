package ADT_HASH;

public interface IHASH <T extends Comparable<T>> {
    boolean insert(T key);
    boolean search(T key);
    boolean delete(T key);
    int batchInsert(T[] list);
    int batchDelete(T[] list);
    int getNumOfCollisions();
}
