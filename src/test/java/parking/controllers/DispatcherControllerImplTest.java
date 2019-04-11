package parking.controllers;

import org.junit.Before;
import org.junit.Test;
import parking.views.ConsoleView;
import parking.views.View;

import java.util.Optional;
import java.util.function.BiConsumer;

import static org.mockito.Mockito.*;

public class DispatcherControllerImplTest {

    private BiConsumer<String, View> defConsumer;
    private String defMessage = "default";
    private DispatcherController dispatcher;
    private View view;
    private String command;
    private String argument;
    private String colon = ":";

    @Before
    public void setUp() throws Exception {
        view = mock(ConsoleView.class);
        defConsumer = (s, view) -> view.showMessage(defMessage);
        doNothing().when(view).showMessage(command);
        dispatcher = new DispatcherControllerImpl(defConsumer);
    }

    @Test
    public void command_with_argument() {
        command = "command";
        argument = "argument";
        dispatcher.putCommand(command, (s, v) -> v.showMessage(s));
        dispatcher.runCommand(Optional.ofNullable(command + colon + argument), view);
        verify(view).showMessage(argument);
    }

    @Test
    public void command_without_argument() {
        command = "command";
        dispatcher.putCommand(command, (s, v) -> v.showMessage(s));
        dispatcher.runCommand(Optional.ofNullable(command ), view);
        verify(view).showMessage("");
    }

    @Test
    public void def_command() {
        dispatcher.runCommand(Optional.ofNullable(""), view);
        dispatcher.runCommand(Optional.ofNullable("test"), view);
    }

}