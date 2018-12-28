package C4_composing_objects.Ch4_16_Implementing_put_if_absent_using_composition;

import org.junit.Test;
import support.annotations.NotThreadSafe;
import support.sleep.Sleep;
import testUtils.ConcurrentTestExecutor;
import testUtils.ConcurrentTestExecutor.ConcurrentTestCallback;

import java.util.*;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

/**
 * @author Jin Lei Stormborn, the Unburnt, King of of Meereen, King of the
 * Andals and the Rhoynar and the First Men, Lord of the Seven Kingdoms,
 * Protector of the Realm, Caho of the Great Grass Sea, Breaker of
 * Shackles, Father of Dragons.
 */
public class ImprovedListTest {

    @Test
    public void test_notThreadSafe() {

        assertTrue(ConcurrentTestExecutor.repeatedExecute(new ConcurrentTestCallback() {

            @Override
            public boolean doConcurrentTest() throws Exception {
                List<Person> list = Collections.synchronizedList(new ArrayList<Person>());
                final ImprovedList<Person> improvedList = new ImprovedList<Person>(list);
                final Person tom = new Person("tom");

                Thread t = new Thread() {
                    @Override
                    public void run() {

                        Sleep.sleepUninterruptibly(2, TimeUnit.SECONDS);

                        improvedList.add(0, tom); // 该语句能插入到putIfAbsent执行过程中从而破坏putIfAbsent的原子性
                    }
                };
                t.start();

                try {
                    improvedList.putIfAbsent(tom);
                    return false;
                } catch (IllegalStateException illegalState) {
                    illegalState.printStackTrace();
                    return true;
                }

            }

        }));

    }

    @NotThreadSafe
    public class ImprovedList<T> implements List<T> {
        private final List<T> list;

        /**
         * PRE: list argument is thread-safe.
         */
        public ImprovedList(List<T> list) {
            this.list = list;
        }

        public synchronized boolean putIfAbsent(T x) {
            boolean contains = list.contains(x);

            // 注意： 如果putIfAbsent具有原子性，那么增加了如下代码它也仍然具有原子性
            // 为了测试效果，补充代码>>>>

            Sleep.sleepUninterruptibly(5, TimeUnit.SECONDS);

            boolean contains2 = list.contains(x);
            if (contains != contains2) {
                // 前后两次值不相等则说明原子性被破坏了，有其他操作修改了list
                throw new IllegalStateException();
            }

            // <<<<为测试效果，补充的代码

            if (!contains)
                list.add(x);
            return !contains;
        }

        // Plain vanilla delegation for List methods.
        // Mutative methods must be synchronized to ensure atomicity of
        // putIfAbsent.

        public int size() {
            return list.size();
        }

        public boolean isEmpty() {
            return list.isEmpty();
        }

        public boolean contains(Object o) {
            return list.contains(o);
        }

        public Iterator<T> iterator() {
            return list.iterator();
        }

        public Object[] toArray() {
            return list.toArray();
        }

        public <T> T[] toArray(T[] a) {
            return list.toArray(a);
        }

        public synchronized boolean add(T e) {
            return list.add(e);
        }

        public synchronized boolean remove(Object o) {
            return list.remove(o);
        }

        public boolean containsAll(Collection<?> c) {
            return list.containsAll(c);
        }

        public synchronized boolean addAll(Collection<? extends T> c) {
            return list.addAll(c);
        }

        public synchronized boolean addAll(int index, Collection<? extends T> c) {
            return list.addAll(index, c);
        }

        public synchronized boolean removeAll(Collection<?> c) {
            return list.removeAll(c);
        }

        public synchronized boolean retainAll(Collection<?> c) {
            return list.retainAll(c);
        }

        public boolean equals(Object o) {
            return list.equals(o);
        }

        public int hashCode() {
            return list.hashCode();
        }

        public T get(int index) {
            return list.get(index);
        }

        public T set(int index, T element) {
            return list.set(index, element);
        }

        public void add(int index, T element) {
            list.add(index, element);
        }

        public T remove(int index) {
            return list.remove(index);
        }

        public int indexOf(Object o) {
            return list.indexOf(o);
        }

        public int lastIndexOf(Object o) {
            return list.lastIndexOf(o);
        }

        public ListIterator<T> listIterator() {
            return list.listIterator();
        }

        public ListIterator<T> listIterator(int index) {
            return list.listIterator(index);
        }

        public List<T> subList(int fromIndex, int toIndex) {
            return list.subList(fromIndex, toIndex);
        }

        public synchronized void clear() {
            list.clear();
        }
    }

    class Person {
        private final String name;

        Person(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
}
