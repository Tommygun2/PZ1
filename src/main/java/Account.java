import com.sun.xml.internal.bind.v2.TODO;
import javafx.scene.control.Alert;

import java.sql.SQLException;

public class Account {

    private static int indeksKonta=0;
    private int indeksTegoKonta;
    private String nazwa;
    private String hasło;
    SQLConnection sqlConnection;

    Account(String nazwa, String hasło, SQLConnection sqlConnection)
    {
        this.hasło=hasło;
        this.nazwa=nazwa;
        this.sqlConnection=sqlConnection;

         try {
            indeksTegoKonta=sqlConnection.sqlResultReturn(
                    "select ACCOUNT_SEQ_1.nextval from dual").getInt("NEXTVAL");
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Błąd bazy danych: "+ e.getMessage());

            alert.showAndWait();
            e.printStackTrace();
        }

        sqlConnection.sqlUpdateExecute("insert into KONTA (ID_KONTA, NAZWA, HASLO) VALUES(" +
                indeksTegoKonta
                +nazwa+"','"
                + hasło +"')");


        indeksKonta++;
    }

    String zwrNazwe()
    {
        return nazwa;
    }
    void updateDB()
    {
        sqlConnection.sqlUpdateExecute("update konta set nazwa='"+nazwa+"'," +
                "haslo ='"+hasło+"'," +
                "where id_konta="+indeksTegoKonta);
    }
    boolean zmienNazwe(String nowaNazwa)
    {
        if(nowaNazwa.equals(nazwa))
        {
            return false;
        }
        else
        {
            nazwa=nowaNazwa;
            updateDB();
            return true;
        }
    }
    //alerty


}
