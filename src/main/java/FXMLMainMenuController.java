import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;

public class FXMLMainMenuController implements Initializable {

    String[][] nazwy = {{"Dolar amerykański","1 USD"},
            {"Euro","1 EUR"},
            {"Frank szwajcarski","1 CHF"},
            {"Funt szterling","1 GBP"},
            {"Dolar australijski","1 AUD"},
            {"Dolar nowozelandzki","1 NZD"},
            {"Dolar kanadyjski","1 CAD"},
            {"Forint węgierski","1 HUF"},
            {"Jen japoński","100 JPY"},
            {"Juan chiński","1 CNY"},
            {"Korona czeska","1 CZK"},
            {"Korona duńska","1 DKK"},
            {"Korona norweska","1 NOK"},
            {"Korona szwedzka","1 SEK"},
            {"Kuna chorwacka","1 HRK"},
            {"Lej rumuński","1 RON"},
            {"Lew bułgarski","1 BGN"},
            {"Lira turecka","1 TRY"},
            {"Rubel rosyjski","1 RUB"}};


    String currIndex;
@FXML
    ComboBox<String> comboBox1 = new ComboBox();
@FXML
    ComboBox<String> comboBox2 = new ComboBox();
//

//
    @FXML
    Button buttonExit;
@FXML
    ComboBox<String> comboBox4 = new ComboBox();
@FXML
    ComboBox<String> comboBox5 = new ComboBox();
@FXML
    TextField fdCurText1;
@FXML
    TextField fdCurText2;
@FXML
    TextField fdCurText3;
@FXML
    TextField fdCurText4;
@FXML
    TextField fdCurText5;
@FXML
        boolean displayComboBox4;
@FXML
    Text dataText;
@FXML
    Text dataText2;

    SQLConnection sqlConnection = new SQLConnection();

    String methodId;

    String[] wynik=new String[5];;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {

        comboBox1.getItems().addAll(
                "Dolar amerykański",
                "Euro",
                "Frank szwajcarski",
                "Funt szterling",
                "Dolar australijski",
                "Dolar nowozelandzki",
                "Dolar kanadyjski",
                "Forint węgierski",
                "Jen japoński",
                "Juan chiński",
                "Korona czeska",
                "Korona duńska",
                "Korona norweska",
                "Korona szwedzka",
                "Kuna chorwacka",
                "Lej rumuński",
                "Lew bułgarski",
                "Lira turecka",
                "Rubel rosyjski"

        );
        comboBox2.getItems().add("Archiwalny");
        comboBox2.getItems().add("Obecny");
        comboBox2.getItems().add("Prognozowany");

        comboBox2.getSelectionModel().selectFirst();
        comboBox1.getSelectionModel().selectFirst();

        comboBox5.getItems().add("Reg. liniowa");
        comboBox5.getItems().add("Śr. krocząca");
        comboBox5.getSelectionModel().selectFirst();
        dataText2.setText("");

        comboBox5.setVisible(false);
        try {
            upDate("");
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
    @FXML
    void upDate(String cond) throws SQLException {

        try{
            ResultSet resultSet=sqlConnection.sqlResultReturn("select distinct MN_DATE from waluty" + cond);
            while(resultSet.next())
            {
                comboBox4.getItems().add(resultSet.getString("MN_DATE"));

            }
        }catch(SQLException e)
        {

        }
        comboBox4.getSelectionModel().selectFirst();

    }
    @FXML
    void comboBoxAction2() throws SQLException {
        if(comboBox2.getValue().equals("Obecny") )
        {
            comboBox4.setVisible(false);
            comboBox5.setVisible(false);
            dataText2.setText("");

        }
        else if(comboBox2.getValue().equals("Prognozowany"))
        {
            comboBox4.setVisible(false);
            comboBox5.setVisible(true);
            dataText.setText("");
            dataText2.setText("Metoda");
        }
        else if(comboBox2.getValue().equals("Archiwalny"))
        {
            comboBox4.setVisible(true);
            comboBox5.setVisible(false);
            dataText.setText("Data");
            dataText2.setText("");
        }

        valuesUpdate();

    }
    @FXML
    void comboBoxAction3()
    {

    }
    @FXML
    void chooseCur()
    {
        currIndex = comboBox1.getValue();
        try {
            valuesUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void comboBox4Action() throws SQLException {
        valuesUpdate();
    }
    @FXML
    void comboBox5Action() throws SQLException {
        methodId=comboBox5.getValue();
        getFutureValues();
    }

    void getFutureValues() throws SQLException {

        int index;
        ResultSet[] resultSet=new ResultSet[5];
        currIndex = comboBox1.getValue();
        Statistics[] st = new Statistics[5];
        for(int i=0;i<5;i++)
        {
            index=0;
            wynik[i]="";
            resultSet[i]= sqlConnection.sqlResultReturn(
                    "select value from waluty where (id_source="+i
                            +" and id_current='"+ retCurrIndex(currIndex)
                            +"') order by mn_date asc");

            System.out.println("select value from waluty where (id_source="+i
                    +" and id_current='"+ retCurrIndex(currIndex)
                    +"') order by mn_date asc");
            while (resultSet[i].next())
            {
                try {
                    st[i].addData(1, index, resultSet[i].getDouble("value"));
                    st[i].addData(2, index, resultSet[i].getDouble("value"));
                }
                catch (SQLException a)
                {

                }
            }
            if(methodId.equals("Reg. liniowa")) wynik[i]=Double.toString(st[i].finalCount(index+1, 1));
            else wynik[i]=Double.toString(st[i].finalCount(index+1, 2));

        }
    }

    @FXML
    void settingsButton(ActionEvent actionEvent) throws IOException {
        System.out.println("settings");
        Stage stageTheEventSourceNodeBelongs = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        Parent root2 = FXMLLoader.load(getClass().getResource("FXML_Settings_controller.fxml"));
        Scene scene2 = new Scene(root2, root2.prefWidth(300), root2.prefHeight(600));
        stageTheEventSourceNodeBelongs.setScene(scene2);
    }

    ResultSet currentCurrentValues()
    {
        System.out.println("dddddddddddddddddddddddddddddddddddddd");
        System.out.println("select id_source, value from waluty where mn_date=(select max(mn_date) from waluty) and" +
                "(id_current='" + retCurrIndex(currIndex)+"' or id_current='"+
                retCurrIndex(currIndex).substring(retCurrIndex(currIndex).indexOf(" ") + 1)+"'");
        return sqlConnection.sqlResultReturn(
                "select id_source, value from waluty where mn_date=(select max(mn_date) from waluty) and" +
                        "(id_current='" + retCurrIndex(currIndex)+"' or id_current='"+
                        retCurrIndex(currIndex).substring(retCurrIndex(currIndex).indexOf(" ") + 1)+"')");
    }

    void valuesUpdate() throws SQLException
    {
        List<String> pppp = new ArrayList<String>();
        String indeks = comboBox4.getValue();
        System.out.println(indeks);
        ResultSet resultSet;
        if(comboBox2.getValue().equals("Archiwalny")) {
            resultSet = sqlConnection.sqlResultReturn(
                    "select id_source, value from waluty where mn_date='"
                            + indeks + "' and id_current='" + retCurrIndex(currIndex) + "' OR id_current='" +
                            retCurrIndex(currIndex).substring(retCurrIndex(currIndex).indexOf(" ") + 1) + "'");
        }
        else
        {
            resultSet=currentCurrentValues();
        }
        System.out.println("select id_source, value from waluty where mn_date='"+indeks+"' and id_current='"+currIndex+"'");

        while(resultSet.next())
        {
            pppp.add(resultSet.getString("value"));
        }

        System.out.println(pppp.size()+"   aaaaaaaaaaaa");

        try {
            if(comboBox2.getValue().equals("Prognozowany")) fdCurText1.setText(wynik[0]);
                else fdCurText1.setText(pppp.get(0));

        }
        catch (Exception e)
        {
            fdCurText1.setText("0.0000");
        }

        try {
            fdCurText2.setText(pppp.get(1));

        }
        catch (Exception e)
        {
            fdCurText2.setText("0.0000");
        }

        try {
            fdCurText3.setText(pppp.get(2));

        }
        catch (Exception e)
        {
            fdCurText3.setText("0.0000");
        }

        try {
            fdCurText4.setText(pppp.get(3));

        }
        catch (Exception e)
        {
            fdCurText4.setText("0.0000");
        }
        try {
            fdCurText5.setText(pppp.get(4));

        }
        catch (Exception e)
        {
            fdCurText5.setText("0.0000");
        }
    }

    String retCurrIndex(String indeks)
    {
        for(int i=0; i<nazwy.length; i++)
        {
            if(nazwy[i][0].equals(indeks)) return nazwy[i][1];
        }
        return null;
    }

    @FXML
    public void logoutButton(ActionEvent actionEvent) throws IOException {

        System.out.println("logout");
        Stage stageTheEventSourceNodeBelongs = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        Parent root2 = FXMLLoader.load(getClass().getResource("fxml_example.fxml"));
        Scene scene2 = new Scene(root2, root2.prefWidth(300), root2.prefHeight(600));
        stageTheEventSourceNodeBelongs.setScene(scene2);

    }

    public void exitButton(ActionEvent actionEvent) {
        Platform.exit();
        System.exit(0);
    }
}

