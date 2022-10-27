package ru.netology.manager;

import ru.netology.domain.Flight;
import ru.netology.exception.NotFoundException;
import ru.netology.repository.FlightRepository;

import java.util.Arrays;

public class FlightManager {
    FlightRepository repository;

    public FlightManager(FlightRepository repository) {
        this.repository = repository;
    }

    public void add(Flight item) {
        repository.save(item);
    }

    public Flight[] searchByAirports(String from, String to) {
        Flight[] result = new Flight[0];
        for (Flight item : repository.findAll()) {
            if (item.matches(from, to)) {
                int length = result.length + 1;
                Flight[] tmp = new Flight[length];
                System.arraycopy(result, 0, tmp, 0, result.length);
                tmp[tmp.length - 1] = item;
                result = tmp;
            }
            Arrays.sort(result);
        }
        return result;
    }

    public Flight[] getAll() {
        return repository.findAll();
    }

    public Flight findByID(int id) {
        return repository.findById(id);
    }

    public void removeByID(int id) {
        try {
            repository.removeByID(id);
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
    }
}

