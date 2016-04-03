package UserInterface;

import BackEnd.ATMManager;
import BackEnd.Receipt;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PINController implements Initializable {

    @FXML
    private Label lblError;

    @FXML
    private TextField txtInput;

    public void ProceedButton() throws IOException {
        String pin = txtInput.getText();
        if(pin.length() != 4){
            lblError.setText("invalid input");
            return;
        }
        if (ATMManager.db.verifyAccountPIN(pin)) {
            Stage stage = (Stage) txtInput.getScene().getWindow();
            stage.setTitle("Main Menu");
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("MainMenuScreen.fxml"))));
            ATMManager.receipt = new Receipt(ATMManager.db.getActiveAccount().getBalance(), ATMManager.db.getActiveAccount().getUserName());

        } else {
            lblError.setText((3 - ATMManager.db.getAttempts()) + " attempts remaining.");
            txtInput.setText("");
            if (ATMManager.db.doneThreeAttempts()) {
                Stage stage = (Stage) txtInput.getScene().getWindow();
                stage.setTitle("Login");
                stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("LoginScreen.fxml"))));
                ATMManager.db.setThreeAttempts(false);
                ATMManager.db.setAttempts(0);
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        txtInput.lengthProperty().addListener((observable, oldValue, newValue) -> {
            if (txtInput.getText().length() > 4)
                txtInput.setText(txtInput.getText().substring(0, 4));
        });
    }
}
