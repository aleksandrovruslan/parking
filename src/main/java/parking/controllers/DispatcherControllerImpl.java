package parking.controllers;

import parking.views.View;

import java.util.*;
import java.util.function.BiConsumer;

public class DispatcherControllerImpl implements DispatcherController {

    private Map<String, BiConsumer<String, View>> commandMap = new HashMap<>();
    private BiConsumer<String, View> defaultCommand;
    private Set<Runnable> finalizeControllers = new HashSet<>();

    public DispatcherControllerImpl(BiConsumer<String, View> defaultCommand) {
        this.defaultCommand = defaultCommand;
    }


    @Override
    public void putCommand(String command, BiConsumer<String, View> consumer) {
        commandMap.put(command, consumer);
    }

    @Override
    public void runCommand(Optional<String> commandColonArguments, View view) {
        String str = commandColonArguments.orElse(" : ");
        String command = str.contains(":") ? str.substring(0, str.indexOf(":")) : str;
        String argument = str.contains(":") ? str.substring(str.indexOf(":") + 1) : "";
        commandMap.getOrDefault(command, defaultCommand).accept(argument, view);
    }

    @Override
    public void putFinalizeController(Runnable finalizeController) {
        finalizeControllers.add(finalizeController);
    }

    @Override
    public void finalizeControllers(Runnable finalMessage) {
        finalizeControllers.forEach(Runnable::run);
        finalMessage.run();
    }

}
