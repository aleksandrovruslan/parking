package parking.controllers;

import org.junit.Before;
import org.junit.Test;
import parking.services.Model;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ControllerTest {
    private DispatcherController dispatcher;
    private Model model;
    private Controller controller;

    @Before
    public void setUp() throws Exception {
        dispatcher = mock(DispatcherController.class);
        model = mock(Model.class);
        controller = new Controller(dispatcher, model);
        doNothing().when(dispatcher).putCommand("p", controller::park);
        doNothing().when(dispatcher).putCommand("p", controller::unpark);
        doNothing().when(dispatcher).putCommand("p", controller::list);
        doNothing().when(dispatcher).putCommand("p", controller::count);
    }

    @Test
    public void when_create_count_command4() {
        controller = new Controller(dispatcher, model);
        verify(dispatcher, times(4));
    }


}