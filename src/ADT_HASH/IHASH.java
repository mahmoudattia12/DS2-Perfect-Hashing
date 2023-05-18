package ADT_HASH;

public interface IHASH {
    boolean insert(int key);
    boolean search(int key);
    boolean delete(int key);
    int batchInsert(int[] list);
    int batchDelete(int[] list);
    int getNumberOfRebuild();
}
