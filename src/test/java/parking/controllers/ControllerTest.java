package parking.controllers;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import parking.models.Car;
import parking.services.Model;
import parking.views.View;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ControllerTest {
    private DispatcherController dispatcher;
    private Model model;
    private View view;
    private Controller controller;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        dispatcher = mock(DispatcherController.class);
        model = mock(Model.class);
        view = mock(View.class);
        controller = new Controller(dispatcher, model);
    }

    @Test
    public void when_create_count_command4() {
        doNothing().when(dispatcher).putCommand(any(), any());
        verify(dispatcher, times(4)).putCommand(any(), any());
    }

    @Test
    public void when_park_2car_show_2message() {
        when(model.park()).thenReturn("");
        doNothing().when(view).showMessage("");
        controller.park("2", view);
        verify(view, timeout(1000).times(2)).showMessage("");
    }

    @Test
    public void when_park_car_count_wrong_error_message() {
        String errorMessage = "Incorrect number format cars count!";
        when(model.park()).thenReturn("");
        doNothing().when(view).showMessage("");
        controller.park("qwerty", view);
        verify(view).showMessage(errorMessage);
    }

    @Test
    public void when_unpark_right_single_ticket_success_message() {
        when(model.unpark(1)).thenReturn("");
        doNothing().when(view).showMessage("");
        controller.unpark("1", view);
        verify(view, timeout(15000)).showMessage("");
    }

    @Test
    public void when_unpark_wrong_single_ticket_error_message() {
        String error_message = "Incorrect tickets state.";
        when(model.unpark(any())).thenReturn("");
        doNothing().when(view).showMessage("");
        controller.unpark("qwerty", view);
        verify(view, atLeast(1)).showMessage(error_message);
        controller.unpark("1,2", view);
        verify(view, atLeast(2)).showMessage(error_message);
    }

    @Test
    public void when_unpark_right_3ticket_3success_message() {
        when(model.unpark(any())).thenReturn("");
        doNothing().when(view).showMessage("");
        controller.unpark("[1,2,3]", view);
        verify(view, timeout(25000).times(3)).showMessage("");
    }

    @Test
    public void when_unpark_wrong_3ticket_error_message() {
        String errorMessage ="Incorrect tickets state.";
        when(model.unpark(any())).thenReturn("");
        doNothing().when(view).showMessage("");
        controller.unpark("1,2,3", view);
        verify(view, atLeast(1)).showMessage(errorMessage);
        controller.unpark("[1 ,2,3]", view);
        verify(view, atLeast(1)).showMessage(errorMessage);
        controller.unpark("[1, 2,3]", view);
        verify(view, atLeast(1)).showMessage(errorMessage);
        controller.unpark("[1,2,q]", view);
        verify(view, atLeast(1)).showMessage(errorMessage);
        controller.unpark("[q,2,3]", view);
        verify(view, atLeast(1)).showMessage(errorMessage);;
    }

    @Test
    public void when_list_view_show_list() {
        Collection<Car> cars = new ArrayList<>();
        Car car1 = mock(Car.class);
        when(car1.toString()).thenReturn("car");
        Car car2 = mock(Car.class);
        when(car2.toString()).thenReturn("car");
        cars.add(car1);
        cars.add(car2);
        when(model.list()).thenReturn(cars);
        doNothing().when(view).showMessage("car");
        controller.list("", view);
        verify(view, times(2)).showMessage("car");
    }

    @Test
    public void when_count_view_show_count() {
        String message = "In the parking of " + 1 + " cars.";
        when(model.countCars()).thenReturn(1);
        doNothing().when(view).showMessage(message);
        controller.count("", view);
        verify(view).showMessage(message);
    }

}
