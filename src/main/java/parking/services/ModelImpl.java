package parking.services;

import parking.models.Car;
import parking.models.Parking;
import parking.models.Ticket;

import java.util.Collection;
import java.util.concurrent.atomic.AtomicInteger;

public class ModelImpl implements Model {

    private final int size;
    private final int timeParking;
    private AtomicInteger countCars = new AtomicInteger();
    private AtomicInteger ticketCount = new AtomicInteger();
    private Parking parking;

    public ModelImpl(int size, int timeParking) {
        this.size = size;
        this.timeParking = timeParking;
        parking = new Parking(size);
    }

    @Override
    public String park() {
        if (countCars.get() >= size) {
            return "Parking is busy.";
        }
        Car car = new Car();
        Ticket ticket = parking.getTickets().poll();
        if (ticket == null) {
            ticket = new Ticket(ticketCount.incrementAndGet());
        }
        car.setTicket(ticket);
        parking.getCars().put(ticket.getNumber(), car);
        countCars.incrementAndGet();
        try {
            Thread.sleep(timeParking);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return car + " was parked.";
    }

    @Override
    public String unpark(Integer ticket) {
        Car car = parking.getCars().remove(ticket);
        if (car == null) {
            return "No such ticket number " + ticket;
        }
        countCars.decrementAndGet();
        parking.getTickets().offer(car.getTicket());
        return "Car with ticket " + ticket + " left the parking";
    }

    @Override
    public Collection<Car> list() {
        return parking.getCars().values();
    }

    @Override
    public int countCars() {
        return countCars.get();
    }

}
