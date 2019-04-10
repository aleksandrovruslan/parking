package parking.models;

import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Parking {

    private Map<Integer, Car> cars;
    private Queue<Ticket> tickets = new ConcurrentLinkedQueue<>();

    public Parking(int parkingSize) {
        cars = new ConcurrentHashMap<>(parkingSize);
    }

    public Map<Integer, Car> getCars() {
        return cars;
    }

    public Queue<Ticket> getTickets() {
        return tickets;
    }

}
