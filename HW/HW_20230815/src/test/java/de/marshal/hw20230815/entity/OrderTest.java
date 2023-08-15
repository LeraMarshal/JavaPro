package de.marshal.hw20230815.entity;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    @Test
    void testCalculateTotalPrice() {
        Product p1 = Mockito.mock(Product.class);
        Product p2 = Mockito.mock(Product.class);

        Mockito.when(p1.getPrice()).thenReturn(1.0);
        Mockito.when(p2.getPrice()).thenReturn(2.0);

        Order order = new Order(1, null, List.of(p1, p2), null);

        assertEquals(3.0, order.calculateTotalPrice());
    }

    @Test
    void testIsOrderExpired() {
        Order o1 = new Order(1, null, null, LocalDate.now());
        Order o2 = new Order(2, null, null, LocalDate.now().minusDays(Order.DAYS_BEFORE_EXPIRATION + 1));

        assertFalse(o1.isOrderExpired());
        assertTrue(o2.isOrderExpired());
    }
}
