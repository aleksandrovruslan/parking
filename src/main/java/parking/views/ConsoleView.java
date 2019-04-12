package parking.views;

import parking.controllers.DispatcherController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Optional;

public class ConsoleView implements View {

    private DispatcherController dispatcher;
    private String[] prompts = {"p:N - parking N cars."
            , "u:N - leave the parking. N - parking ticket number"
            , "u:[1..n] - drive out of the parking to several cars, where in " +
            "square brackets, numbers of parking tickets are transmitted, separated by commas."
            , "l - list of cars in the parking.", "c - number of remaining parking spaces"
            , "e - exit application."};
    private String exitCommand = "e";

    public void startApplication() {
        for (String prompt : prompts) {
            showMessage(prompt);
        }
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String command = reader.readLine();
            while (command != null && !exitCommand.equals(command)) {
                dispatcher.runCommand(Optional.ofNullable(command), this);
                command = reader.readLine();
            }
            dispatcher.finalizeControllers();
            showMessage("Bye!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setDispatcher(DispatcherController dispatcher) {
        this.dispatcher = dispatcher;
    }

    @Override
    public void showMessage(String message) {
        System.out.println(message);
    }

}
