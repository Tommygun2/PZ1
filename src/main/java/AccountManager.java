
import javafx.scene.control.Alert;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountManager
{
    List<Account> listaKont = new ArrayList<Account>();
    SQLConnection sqlConnection;
    Account currentAccount;

    AccountManager(SQLConnection sqlConnection)
    {
        this.sqlConnection=sqlConnection;
    }
    boolean chooseAccount()
    {
        //na razie nic tu nie ma
        //zrobić tu szukanie konta w bazie
        return true;
    }


    public boolean isAccountAlreadyExists(String name)
    {
        try
        {
            ResultSet resultset = sqlConnection.sqlResultReturn("select * from konta");
            while(resultset.next())
            {
                if(resultset.getString("nazwa").equals(name)) return true;
            }
        }
        catch(SQLException we)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Błąd bazy danych: "+ we.getMessage());

            alert.showAndWait();
        }

        return false;
    }

    public void addAccount(String name, String haslo)
    {
        if(isAccountAlreadyExists(name)==false)
        {
            Account a =new Account(name, haslo, sqlConnection);
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Konto o nazwie "+name+" już istnieje");
            alert.showAndWait();
        }
    }

}
