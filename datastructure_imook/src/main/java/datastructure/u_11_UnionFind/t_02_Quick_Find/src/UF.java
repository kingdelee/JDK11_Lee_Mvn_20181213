package datastructure.u_11_UnionFind.t_02_Quick_Find.src;

public interface UF {

    int getSize();

    boolean isConnected(int p, int q);

    void unionElements(int p, int q);
}
