package datastructure.u_11_UnionFind.t_06_Path_Compression.src;

public interface UF {

    int getSize();

    boolean isConnected(int p, int q);

    void unionElements(int p, int q);
}
