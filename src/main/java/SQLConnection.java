
import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;
import javafx.scene.control.Alert;

import java.sql.*;


public class SQLConnection {

    //to będzie w pliku parametrycznym
    String driverName = "oracle.jdbc.driver.OracleDriver";
    String url = "jdbc:oracle:thin:@soil-festivities.wsisiz.edu.pl:1521:orcl";
    String uid = "1521";
    String pwd = "wcy_03";
    String user = "wcy_03";
    public Connection con;
    Statement st;

    SQLConnection()
    {

        try {
            Class.forName(driverName);
            con = DriverManager.getConnection(url, user, pwd);

        } catch (ClassNotFoundException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("Look, an Information Dialog");
            alert.setContentText("błąd klasy");

            alert.showAndWait();
            e.printStackTrace();
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Błąd bazy danych: "+ e.getMessage());

            alert.showAndWait();
            e.printStackTrace();
        }

    }

    ResultSet sqlResultReturn(String sqlQuery)
    {
        try {
            st = con.createStatement();
            ResultSet result = st.executeQuery(sqlQuery);
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    void sqlUpdateExecute(String sqlQuery)
    {
        try {
            st = con.createStatement();
            st.executeUpdate(sqlQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




}
