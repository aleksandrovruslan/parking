package parking.services;

import org.junit.Test;
import parking.models.Car;

import java.util.Iterator;

import static org.junit.Assert.*;

public class ModelImplTest {
    private String failMessagePark = "Parking is busy.";
    private String failTicketMessageUnpark = "No such ticket number ";
    private String suffixUnparkSeccess = " left the parking";

    @Test
    public void when_crowded_park_fail() {
        Model model = new ModelImpl(1, 0);
        model.park();
        String message = model.park();
        assertEquals(message, failMessagePark);
    }

    @Test
    public void when_empty_fill_park10_parked10() {
        Model model = new ModelImpl(10, 0);
        for (int i = 0; i < 10; i++) {
            model.park();
        }
        assertEquals(10, model.countCars());
        String message = model.park();
        assertEquals(message, failMessagePark);
    }

    @Test
    public void when_ticket_wrong_unpark_fail() {
        Model model = new ModelImpl(1, 0);
        String message = model.unpark(1);
        assertEquals(message, failTicketMessageUnpark + 1);
    }

    @Test
    public void when_ticket_right_success() {
        Model model = new ModelImpl(1, 0);
        model.park();
        Iterator<Car> iterator = model.list().iterator();
        Car car;
        int ticket = 0;
        if (iterator.hasNext()) {
            car = iterator.next();
            ticket = car.getTicket().getNumber();
        }
        String message = model.unpark(ticket);
        assertTrue(message.endsWith(suffixUnparkSeccess));
    }
}