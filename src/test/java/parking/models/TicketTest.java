package parking.models;

import org.junit.Test;

import static org.junit.Assert.*;

public class TicketTest {

    @Test
    public void multithreading_create100000_count100000() {
        for (int i = 0; i < 99998; i++) {
            new Thread(Ticket::new).start();
        }
        Thread thread = new Thread(Ticket::new);
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Ticket ticket = new Ticket();
        assertEquals(ticket.getNumber(), 100000);
    }

}