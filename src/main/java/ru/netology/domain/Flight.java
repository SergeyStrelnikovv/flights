package ru.netology.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class Flight implements Comparable<Flight> {
    private int id;
    private int price;
    private String departure;
    private String arrive;
    private int flightTime;

    @Override
    public int compareTo(Flight o) {
        return this.price - o.price;
    }

    public boolean matches(String from, String to) {
        return (this.getDeparture().equalsIgnoreCase(from)) && (getArrive().equalsIgnoreCase(to));
    }
}

