package parking.views;

import parking.controllers.DispatcherController;

public interface View {

    void startApplication();

    void showMessage(String message);

    void setDispatcher(DispatcherController dispatcher);

}
