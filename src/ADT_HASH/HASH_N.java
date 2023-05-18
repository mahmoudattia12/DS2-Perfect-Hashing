package ADT_HASH;

public class HASH_N implements IHASH {
    @Override
    public boolean insert(int key) {
        return false;
    }

    @Override
    public boolean delete(int key) {
        return false;
    }

    @Override
    public boolean search(int key) {
        return false;
    }

    @Override
    public int batchInsert(int[] list) {
        return 0;
    }

    @Override
    public int batchDelete(int[] list) {
        return 0;
    }

    @Override
    public int getNumberOfRebuild() {
        return 0;
    }
}
