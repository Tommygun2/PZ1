import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class FXMLExampleController
{
    @FXML
    private Label label;

    @FXML
    public String myText;

    @FXML
    TextField pwd;

    @FXML
    TextField login;

    @FXML
    Text fdText;

    @FXML
    private URL location;

    @FXML
    ResourceBundle resources;

    SQLConnection connection;

    public FXMLExampleController() {

    }


    @FXML private void initialize(){}

    @FXML private void printOutput(ActionEvent event) throws IOException, SQLException {
        //outputText.setText(inputText.getText());
        fdText.setText("h");
        Stage stageTheEventSourceNodeBelongs = (Stage) ((Node)event.getSource()).getScene().getWindow();
        // OR
        //Stage stageTheLabelBelongs = (Stage) label.getScene().getWindow();
        // these two of them return the same stage
        // Swap screen
        Parent root2 = FXMLLoader.load(getClass().getResource("FXML_main_menu.fxml"));
        Scene scene2 = new Scene(root2, root2.prefWidth(300), root2.prefHeight(600));


        ResultSet st = connection.sqlResultReturn("select COUNT(*) from konta where nazwa='"
                +login.getText()+"' and haslo='"+pwd.getText()+"'");
        st.next();
        int ann = st.getInt(1);
        if(ann!=0) stageTheEventSourceNodeBelongs.setScene(scene2);
        else
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("Look, an Information Dialog");
            alert.setContentText("błąd logowania");

            alert.showAndWait();
        }
    }


    public void getSqlConnection(SQLConnection aaa){
        connection=aaa;
    }

    @FXML private void zalogowanie(){


    }

}
