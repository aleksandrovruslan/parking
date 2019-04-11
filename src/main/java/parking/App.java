package parking;

import parking.controllers.Controller;
import parking.controllers.DispatcherController;
import parking.controllers.DispatcherControllerImpl;
import parking.services.Model;
import parking.services.ModelImpl;
import parking.views.ConsoleView;

public class App {

    public static void main(String[] args) {
        int defParkingSize = 10;
        int defParkTime = 5000;
        if (args.length > 0) {
            try {
                defParkingSize = Integer.parseInt(args[0]);
                if (args.length > 1) {
                    defParkTime = Integer.parseInt(args[1]);
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        ConsoleView view = new ConsoleView();
        DispatcherController dispatcher = new DispatcherControllerImpl(
                (s, v) -> v.showMessage("Command not found!"));
        Model model = new ModelImpl(defParkingSize, defParkTime);
        new Controller(dispatcher, model);
        view.setDispatcher(dispatcher);
        view.startApplication();
    }

}
