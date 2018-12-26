package datastructure.u_11_UnionFind.t_01_What_is_UnionFind.src;

public interface UF {

    int getSize();

    boolean isConnected(int p, int q);

    void unionElements(int p, int q);
}