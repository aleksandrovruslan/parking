package parking.controllers;

import parking.views.View;

import java.util.Optional;
import java.util.function.BiConsumer;

public interface DispatcherController {

    void putCommand(String command, BiConsumer<String, View> consumer);

    void runCommand(Optional<String> commandColonArguments, View view);

    void putFinalizeController(Runnable finalizeController);

    void finalizeControllers();

}
