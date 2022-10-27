package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Flight;
import ru.netology.repository.FlightRepository;

import static org.junit.jupiter.api.Assertions.*;

class FlightManagerTest {
    FlightRepository repository = new FlightRepository();
    FlightManager manager = new FlightManager(repository);
    Flight first = new Flight(1, 3330, "GOJ", "DME", 90);
    Flight second = new Flight(2, 2000, "GOJ", "DME", 90);
    Flight third = new Flight(3, 10000, "GOJ", "SIP", 150);
    Flight fourth = new Flight(4, 3000, "DME", "GOJ", 125);
    Flight fifth = new Flight(5, 8000, "SIP", "DME", 500);

    @BeforeEach
    void setUp() {
        manager.add(first);
        manager.add(second);
        manager.add(third);
        manager.add(fourth);
        manager.add(fifth);
    }

    @Test
    public void shouldSortCorrectly() {
        Flight[] expected = new Flight[]{second, first};
        Flight[] actual = manager.searchByAirports("GOJ", "DME");
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindByID() {
        Flight actual = manager.findByID(4);
        Flight expected = fourth;
        assertEquals(expected, actual);
    }

    @Test
    public void shouldNotFindByID() {
        assertNull(manager.findByID(8));
    }

    @Test
    public void shouldRemoveByID() {
        manager.removeByID(3);
        Flight[] expected = new Flight[]{first, second, fourth, fifth};
        Flight[] actual = manager.getAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldShowNotFoundException() {
        manager.removeByID(9);
        Flight[] expected = new Flight[]{first, second, third, fourth, fifth};
        Flight[] actual = manager.getAll();
        assertArrayEquals(expected, actual);
    }

}