package ADT_HASH;

public interface IHASH {
    boolean insert(long key);
    boolean search(long key);
    boolean delete(long key);
    int batchInsert(Long[] list);
    int batchDelete(Long[] list);
    int getNumOfCollisions();
}
