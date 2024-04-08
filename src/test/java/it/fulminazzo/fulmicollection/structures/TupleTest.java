package it.fulminazzo.fulmicollection.structures;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TupleTest {

    @Test
    void testToString() {
        String key = "Hello";
        String value = "World";
        String toString = new Tuple<>(key, value).toString();
        assertTrue(toString.contains(key), "toString() should contain key");
        assertTrue(toString.contains(value), "toString() should contain value");
    }

    @Test
    void testIsEmpty() {
        Tuple<String, Integer> tuple = new Tuple<>();
        assertTrue(tuple.isEmpty(), "Tuple should be empty when initialized empty");
        tuple.set("Hello", 10);
        assertFalse(tuple.isEmpty(), "Tuple should not be empty after set");
    }

    @Test
    void testKeyMethods() {
        String expected = "Hello";
        Tuple<String, Integer> tuple = new Tuple<>();
        assertFalse(tuple.hasKey(), "Tuple should not have key when initialized empty");
        assertFalse(tuple.containsKey(expected), String.format("Tuple should not contain key '%s'", expected));
        tuple.setKey(expected);
        assertTrue(tuple.hasKey(), "Tuple should have key after set");
        assertTrue(tuple.containsKey(expected), String.format("Tuple should contain key '%s'", expected));
    }

    @Test
    void testValueMethods() {
        int expected = 10;
        Tuple<String, Integer> tuple = new Tuple<>();
        assertFalse(tuple.hasValue(), "Tuple should not have value when initialized empty");
        assertFalse(tuple.containsValue(expected), String.format("Tuple should not contain value '%s'", expected));
        tuple.setValue(expected);
        assertTrue(tuple.hasValue(), "Tuple should have value after set");
        assertTrue(tuple.containsValue(expected), String.format("Tuple should contain value '%s'", expected));
    }

    @Test
    void testEquality() {
        Tuple<String, Integer> t1 = new Tuple<>("Hello", 1);
        Tuple<String, Integer> t2 = new Tuple<>("Hello", 1);
        assertEquals(t1, t2);
    }

    @Test
    void testInequality() {
        Tuple<String, Integer> t1 = new Tuple<>("Hello", 1);
        Tuple<String, Integer> t2 = new Tuple<>("Hello", 2);
        Tuple<String, Integer> t3 = new Tuple<>("Hello!", 1);
        assertNotEquals(t1, t2);
        assertNotEquals(t1, t3);
    }

    @Test
    void testCopy() {
        Tuple<String, Integer> t1 = new Tuple<>("Hello", 1);
        Tuple<String, Integer> t2 = t1.copy();
        assertEquals(t1, t2);
    }
}