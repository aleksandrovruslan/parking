package parking.controllers;

import java.util.Optional;
import java.util.function.Consumer;

public interface DispatcherController {

    Consumer<String> putCommand(String command, Consumer<String> consumer);

    void runCommand(Optional<String> command);

}
