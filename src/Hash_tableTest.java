import java.util.ArrayDeque;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class Hash_tableTest {

    @org.junit.jupiter.api.Test
    void testToString() {
    }

    @org.junit.jupiter.api.Test
    void size() {
        Hash_table<String> testTable = new Hash_table<>();
        assertEquals(testTable.size(), 0);
        testTable.add("hi");
        testTable.add(" , hello");
        assertEquals(testTable.size(), 2);
    }

    @org.junit.jupiter.api.Test
    void isEmpty() {
    }

    @org.junit.jupiter.api.Test
    void contains() {
        Hash_table<String> testTable = new Hash_table<>();
        testTable.add("hi");
        testTable.add(" , hello");
        assertTrue(testTable.contains("hi"));
        assertFalse(testTable.contains("bye"));
    }

    @org.junit.jupiter.api.Test
    void iterator() {
        Hash_table<String> testTable = new Hash_table<>();
        testTable.add("hi");
        testTable.add(" , hello");
        testTable.add(" nice to meet you!");
        testTable.add(" 1");
        testTable.add(" 23");
        testTable.add(" 456");
        assertTrue(testTable.iterator().hasNext());

        Iterator<String> testTable2 = testTable.iterator();
        while(testTable2.hasNext()){
            testTable.iterator().remove();
        }
        assertEquals(0, testTable.size());
    }

    @org.junit.jupiter.api.Test
    void toArray() {
        Hash_table<String> testTable = new Hash_table<>();
        testTable.add("hi");
        testTable.add(" , hello");
        testTable.add(" , how are you");
        Object[] newAr = testTable.toArray();
        assertEquals(newAr[2], "hi");
        assertEquals(newAr[1], " , hello");
        assertEquals(newAr[0], " , how are you");
    }

    @org.junit.jupiter.api.Test
    void testToArray() {
        Hash_table<String> testTable = new Hash_table<>();
        testTable.add("hi");
        testTable.add(" , hello");
        testTable.add(" , how are you");
        Object[] newAr = testTable.toArray();
        assertEquals(newAr[2], "hi");
        assertEquals(newAr[1], " , hello");
        assertEquals(newAr[0], " , how are you");
    }

    @org.junit.jupiter.api.Test
    void add() {
        Hash_table<String> testTable = new Hash_table<>();
        testTable.add("hi");
        testTable.add(" , hello");
        assertTrue(testTable.size() == 2 && testTable.contains("hi") && testTable.contains(" , hello"));
        testTable.add("hi");
        assertTrue(testTable.size() == 2 && testTable.contains("hi") && testTable.contains(" , hello"));
    }

    @org.junit.jupiter.api.Test
    void remove() {
        Hash_table<String> testTable = new Hash_table<>();
        testTable.add("hi");
        testTable.add(" , hello");
        testTable.remove("hi");
        assertEquals(testTable.size(), 1);
        assertFalse(testTable.contains("hi"));
    }

    @org.junit.jupiter.api.Test
    void containsAll() {
        Hash_table<String> testTable = new Hash_table<>();
        testTable.add("hi");
        testTable.add(" , hello");
        testTable.add(" , how are you");
        ArrayDeque<String> testDeque = new ArrayDeque<>();
        testDeque.add("hi");
        testDeque.add(" , how are you");
        assertTrue(testTable.containsAll(testDeque));
    }

    @org.junit.jupiter.api.Test
    void addAll() {
        Hash_table<String> testTable = new Hash_table<>();
        ArrayDeque<String> testDeque = new ArrayDeque<>();
        testDeque.add("hi");
        testDeque.add(" , how are you");
        testTable.addAll(testDeque);
        assertEquals(testTable.size(), 2);
        assertTrue(testTable.contains("hi") && testTable.contains(" , how are you"));
    }

    @org.junit.jupiter.api.Test
    void retainAll() {
        Hash_table<String> testTable = new Hash_table<>();
        testTable.add("hi");
        testTable.add(" , hello");
        testTable.add(" , how are you");
        ArrayDeque<String> testDeque = new ArrayDeque<>();
        testDeque.add("hi");
        testDeque.add(" , how are you");
        testTable.retainAll(testDeque);
        assertEquals(testTable.size(), 2);
        assertTrue(testTable.contains("hi") && testTable.contains(" , how are you"));
    }

    @org.junit.jupiter.api.Test
    void removeAll() {
        Hash_table<String> testTable = new Hash_table<>();
        testTable.add("hi");
        testTable.add(" , hello");
        testTable.add(" , how are you");
        ArrayDeque<String> testDeque = new ArrayDeque<>();
        testDeque.add("hi");
        testDeque.add(" , how are you");
        testTable.removeAll(testDeque);
        assertEquals(testTable.size(), 1);
        assertTrue(testTable.contains(" , hello"));
    }

    @org.junit.jupiter.api.Test
    void clear() {
        Hash_table<String> testTable = new Hash_table<>();
        testTable.add("hi");
        testTable.add(" , hello");
        testTable.add(" , how are you");
        testTable.clear();
        assertEquals(testTable.size(), 0);
    }
}