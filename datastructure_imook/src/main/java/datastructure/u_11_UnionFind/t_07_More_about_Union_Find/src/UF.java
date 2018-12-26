package datastructure.u_11_UnionFind.t_07_More_about_Union_Find.src;

public interface UF {

    int getSize();

    boolean isConnected(int p, int q);

    void unionElements(int p, int q);
}
