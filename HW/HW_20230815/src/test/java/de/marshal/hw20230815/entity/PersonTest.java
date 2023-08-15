package de.marshal.hw20230815.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    @Test
    void testIsAdult() {
        Person p1 = new Person("p1", Person.LEGAL_AGE - 1, "a1", "e1");
        Person p2 = new Person("p2", Person.LEGAL_AGE, "a2", "e2");
        Person p3 = new Person("p3", Person.LEGAL_AGE + 1, "a3", "e3");

        assertFalse(p1.isAdult());
        assertTrue(p2.isAdult());
        assertTrue(p3.isAdult());
    }

    @Test
    void testCompareTo() {
        Person p1 = new Person("p1", 1, "a1", "e1");
        Person p2 = new Person("p1", 2, "a2", "e2");
        Person p3 = new Person("p3", 3, "a3", "e3");

        assertTrue(p1.compareTo(p3) < 0);
        assertEquals(0, p1.compareTo(p2));
        assertTrue(p3.compareTo(p1) > 0);
    }
}
