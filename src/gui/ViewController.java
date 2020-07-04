package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import gui.utils.Alerts;
import gui.utils.Constraints;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.util.Callback;
import model.entities.User;

public class ViewController implements Initializable {

	@FXML
	private ComboBox<User> comboBoxUser;

	private ObservableList<User> obsList;

	@FXML
	private TextField txtN1;

	@FXML
	private TextField txtN2;

	@FXML
	private Button btnSum;

	@FXML
	private Label labelResult;

	@FXML
	public void onBtnSumAction() {
		Locale.setDefault(Locale.US);
		var user = onComboBoxUserAction();
		try {
			double n1 = Double.parseDouble(txtN1.getText());
			double n2 = Double.parseDouble(txtN2.getText());

			double result = n1 + n2;
			labelResult.setText(String.format("%.2f", result));
			
			Alerts.showAlert("OK", null, "Concluído " + user, AlertType.CONFIRMATION);

		} catch (NumberFormatException e) {
			Alerts.showAlert("Error", null, e.getMessage(), AlertType.ERROR);

		}
	}
	
	@FXML
	public User onComboBoxUserAction() {
		var user = comboBoxUser.getSelectionModel().getSelectedItem();
		return user;
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		Constraints.setTextFieldDouble(txtN1);
		Constraints.setTextFieldDouble(txtN2);

		List<User> list = new ArrayList<>();
		list.add(new User(1, "Maria", "maria@gmail.com"));
		list.add(new User(2, "Bob", "bob@gmail.com"));
		list.add(new User(3, "Carlos", "carlos@gmail.com"));

		obsList = FXCollections.observableArrayList(list);
		comboBoxUser.setItems(obsList);

		Callback<ListView<User>, ListCell<User>> factory = lv -> new ListCell<User>() {
			@Override
			protected void updateItem(User item, boolean empty) {
				super.updateItem(item, empty);
				setText(empty ? "" : item.getName());
			}
		};
		comboBoxUser.setCellFactory(factory);
		comboBoxUser.setButtonCell(factory.call(null));

	}
}
