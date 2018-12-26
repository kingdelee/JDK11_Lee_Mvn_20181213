package datastructure.u_11_UnionFind.t_04_Optimized_by_Size.src;

public interface UF {

    int getSize();

    boolean isConnected(int p, int q);

    void unionElements(int p, int q);
}
