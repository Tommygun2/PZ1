
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class HelloWorld extends Application {
    public static void main(String[] args) {
        java.util.logging.Logger.getLogger("com.gargoylesoftware").setLevel(Level.OFF);
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException, SQLException {



        SQLConnection sqlConnection = new SQLConnection();
        AccountManager accountManager = new AccountManager(sqlConnection);
        currenciesManager currenciesManager = new currenciesManager(sqlConnection);

        //ResultSet resultSet = sqlConnection.sqlResultReturn("select MN_DATE from waluty");



        //currenciesManager.ripFromWeb();
        //AccountManager aab = new AccountManager(sqlConnection);
        //Account a = new Account("ccc","qqq", sqlConnection);
        //aab.addAccount("aaa","bbb");

        //webRipper aaa = new webRipper();
        //aaa.say();
        FXMLLoader loader1 = new FXMLLoader( getClass().getResource("fxml_example.fxml"));
        FXMLLoader loader2 = new FXMLLoader( getClass().getResource("FXML_main_menu.fxml"));
        FXMLLoader loader3 = new FXMLLoader( getClass().getResource("FXML_Settings_controller.fxml"));
        Parent root = loader1.load();
        FXMLExampleController a = loader1.getController();
        FXMLMainMenuController b =loader2.getController();
        FXML_Settings_controller c = loader3.getController();


        a.connection=sqlConnection;




        /*if(accountManager.isAccountAlreadyExists("aaa")==true)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Konto już istnieje ");

            alert.showAndWait();
        }*/
        Scene scene = new Scene(root, root.prefWidth(300), root.prefHeight(60));
        String pawel="aaaa";


        stage.setTitle("FXML Welcome");
        stage.setScene(scene);
        stage.show();

    }
}