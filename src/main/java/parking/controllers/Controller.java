package parking.controllers;

import parking.services.Model;
import parking.views.View;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Controller {
    private DispatcherController dispatcher;
    private final Model model;
    private final ExecutorService service = Executors.newFixedThreadPool(2);

    public Controller(DispatcherController dispatcher, Model model) {
        this.dispatcher = dispatcher;
        this.model = model;
        dispatcher.putCommand("p", this::park);
        dispatcher.putCommand("u", this::unpark);
        dispatcher.putCommand("l", this::list);
        dispatcher.putCommand("c", this::count);
        dispatcher.putCommand("shutdown_controller", this::shutdown);
    }

    public void park(String carCount, View view) {
        try {
            int count = Integer.parseInt(carCount);
            for (int i = 0; i < count; i++) {
                service.submit(() -> view.showMessage(model.park()));
            }
        } catch (NumberFormatException e) {
            view.showMessage("Incorrect number format cars count!");
        }
    }

    public void unpark(String ticket, View view) {
        if (ticket.matches("^\\[+\\d+(,\\d+)*\\]+$|\\d+")) {
            String[] tickets = ticket.replaceAll("[^0-9,]", "").split(",");
            Arrays.stream(tickets).map(Integer::parseInt)
                    .forEach((i) -> service.submit(() -> view.showMessage(model.unpark(i))));
        } else {
            view.showMessage("Incorrect tickets state.");
        }
    }

    public void list(String empty, View view) {
        model.list().forEach((car -> view.showMessage(car.toString())));
    }

    public void count(String empty, View view) {
        view.showMessage("In the parking of " + model.countCars() + " cars.");
    }

    public void shutdown(String empty, View view) {
        service.shutdown();
    }

}
