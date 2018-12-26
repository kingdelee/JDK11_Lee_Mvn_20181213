package datastructure.u_11_UnionFind.t_04_Optimized_by_Size.src;

import org.junit.jupiter.api.Test;

public class TestClient {

    @Test
    public void t1() {
        UnionFind2 unionFind2 = new UnionFind2(10);

        unionFind2.unionElements(5, 4);
        unionFind2.unionElements(4, 3);

        System.out.println(unionFind2.isConnected(3, 4));
        System.out.println(unionFind2);
        // UnionFind2[parent=[0, 1, 2, 3, 3, 4, 6, 7, 8, 9]]
    }


    @Test
    public void t2() {
        UnionFind2 unionFind2 = new UnionFind2(10);

        unionFind2.unionElements(4, 3);
        unionFind2.unionElements(5, 4);

        System.out.println(unionFind2.isConnected(3, 4));
        System.out.println(unionFind2);
        // UnionFind2[parent=[0, 1, 2, 3, 3, 3, 6, 7, 8, 9]]
    }
}
