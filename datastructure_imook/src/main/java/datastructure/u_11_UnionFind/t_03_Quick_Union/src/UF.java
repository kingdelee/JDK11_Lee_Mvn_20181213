package datastructure.u_11_UnionFind.t_03_Quick_Union.src;

public interface UF {

    int getSize();

    boolean isConnected(int p, int q);

    void unionElements(int p, int q);
}
