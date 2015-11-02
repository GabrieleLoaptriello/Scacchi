package view;

import controller.Controller;
import model.Model;

public interface View {
	Model getModel();
	void setController(Controller controller);
	void onConfigurationChange();
	void showEndPanel();
}
