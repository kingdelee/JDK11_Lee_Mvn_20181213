package datastructure.u_11_UnionFind.t_05_Optimized_by_Rank.src;

public interface UF {

    int getSize();

    boolean isConnected(int p, int q);

    void unionElements(int p, int q);
}
