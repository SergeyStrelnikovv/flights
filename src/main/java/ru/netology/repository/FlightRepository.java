package ru.netology.repository;

import ru.netology.domain.Flight;
import ru.netology.exception.NotFoundException;

public class FlightRepository {

    private Flight[] flights = new Flight[0];

    public void save(Flight item) {
        int length = flights.length + 1;
        Flight[] tmp = new Flight[length];
        System.arraycopy(flights, 0, tmp, 0, flights.length);
        int lastIndex = tmp.length - 1;
        tmp[lastIndex] = item;
        flights = tmp;
    }

    public Flight[] findAll() {
        return flights;
    }

    public Flight findById(int id) {
        for (Flight item : flights) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }

    public void removeByID(int id) throws NotFoundException {
        if (findById(id) != null) {
            int length = flights.length - 1;
            Flight[] tmp = new Flight[length];
            int index = 0;
            for (Flight item : flights) {
                if (item.getId() != id) {
                    tmp[index] = item;
                    index++;
                }
                flights = tmp;
            }
        } else {
            throw new NotFoundException("Element with ID " + id + " not found");
        }
    }


}
