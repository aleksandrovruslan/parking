package parking.services;

import parking.models.Car;

import java.util.Collection;

public interface Model {

    String park();

    String unpark(Integer ticket);

    Collection<Car> list();

    int countCars();

}
