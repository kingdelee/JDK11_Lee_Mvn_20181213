package datastructure.u_10_Trie.t_04_Prefix_in_Trie.src;

import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

public class TrieTest {

    @Test
    public void t1() {
        Trie trie = new Trie();

        String[] arr = {
                "a1",
                "a2",
                "a22",
                "ab",
                "ba",
                "ba2",
                "aed",
                "see"
        };

        Stream.of(arr).forEach(i -> trie.add(i));
        trie.add("see");
        System.out.println(trie);
    }

}
