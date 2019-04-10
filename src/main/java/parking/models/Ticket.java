package parking.models;

public class Ticket {

    private static volatile int count;
    private int number;

    public Ticket() {
        number = countIncrement();
    }

    public int getNumber() {
        return number;
    }

    private static synchronized int countIncrement() {
        count = ++count;
        return count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ticket ticket = (Ticket) o;

        return number == ticket.number;

    }

    @Override
    public int hashCode() {
        return number;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "number=" + number +
                '}';
    }
}
