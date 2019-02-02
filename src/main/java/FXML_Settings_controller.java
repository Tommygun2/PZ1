import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FXML_Settings_controller implements Initializable {

    SQLConnection sqlConnection=new SQLConnection();

    String[] currCodes ={"1 USD","1 EUR","1 CHF","1 GBP","1 AUD","1 NZD","1 CAD","1 HUF","100 JPY","1 CNY","1 CZK","1 DKK","1 NOK","1 SEK","1 HRK","1 RON","1 BGN","1 TRY","1 RUB"};
    String[] currCodesShort={"USD","EUR","CHF","GBP","AUD","NZD","CAD","HUF","JPY","CNY","CZK","DKK","NOK","SEK","HRK","RON","BGN","TRY","RUB"};
    @FXML
    ComboBox currConvertInputComboBox = new ComboBox();
    @FXML
    ComboBox currConvertOutputComboBox = new ComboBox();
    @FXML
    RadioButton showPswd = new RadioButton();
    @FXML
    PasswordField passExp;
    @FXML
    TextField passwd;
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {

    }
    @FXML
    public void showPswd() {
        if(showPswd.isSelected())
        {
            System.out.println("aaa");
            passwd.setText("aaa");
        }

    }



    public void exit(ActionEvent actionEvent) {
        Platform.exit();
        System.exit(0);
    }

    public void logOut(ActionEvent actionEvent) throws IOException {
        Stage stageTheEventSourceNodeBelongs = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        Parent root2 = FXMLLoader.load(getClass().getResource("fxml_example.fxml"));
        Scene scene2 = new Scene(root2, root2.prefWidth(300), root2.prefHeight(600));
        stageTheEventSourceNodeBelongs.setScene(scene2);
    }

    public void goToMenu(ActionEvent actionEvent) throws IOException {
        Stage stageTheEventSourceNodeBelongs = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        Parent root2 = FXMLLoader.load(getClass().getResource("FXML_main_menu.fxml"));
        Scene scene2 = new Scene(root2, root2.prefWidth(300), root2.prefHeight(600));
        stageTheEventSourceNodeBelongs.setScene(scene2);
    }
}
