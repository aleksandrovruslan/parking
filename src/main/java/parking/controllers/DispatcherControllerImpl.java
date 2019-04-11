package parking.controllers;

import parking.views.View;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiConsumer;

public class DispatcherControllerImpl implements DispatcherController {

    private Map<String, BiConsumer<String, View>> commandMap = new HashMap<>();
    private BiConsumer<String, View> defaultCommand;

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

}
