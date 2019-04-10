package parking.models;

public class Car {

    private String number;
    private Ticket ticket;
    private char[] chars = "qwertyuiopasdfghjklzxcvbnm0123456789".toCharArray();

    public Car() {
        number = numberGenerator(6);
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public Ticket getTicket() {
        return ticket;
    }

    private String numberGenerator(int sizeNumber) {
        StringBuilder builder = new StringBuilder(sizeNumber);
        for (int i = 0; i < sizeNumber; i++) {
            builder.append(chars[(int) (Math.random() * chars.length)]);
        }
        return builder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Car car = (Car) o;

        return number.equals(car.number);

    }

    @Override
    public int hashCode() {
        return number.hashCode();
    }

    @Override
    public String toString() {
        return "Car{" +
                "number='" + number + '\'' +
                ", ticket=" + ticket +
                '}';
    }

}
