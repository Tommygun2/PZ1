import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;

public class currenciesManager {

    SimpleDateFormat format1 = new SimpleDateFormat("yy-MM-dd  hh:m");
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

    List<current> currents = new ArrayList<current>();
    webRipper webRipper = new webRipper();
    SQLConnection connection;
    currenciesManager(SQLConnection connection){

        this.connection=connection;
        for(int i=0; i<19;i++)
        {
            currents.add(new current(nazwy[i][0], nazwy[i][1]));
        }


    }

    void ripFromWeb() {
        int[] tabLenghts = new int[5];
        String[][] tab = webRipper.prepare("https://www.nbp.pl/home.aspx?f=/kursy/kursya.html",
                "bat", "Powyższa tabela", "\\t");
        //[nazwa pelna][kod][cena]
        tabLenghts[0]=tab.length;

        String[][] tab2 = webRipper.prepare("https://kursy-na-zywo.mybank.pl/",
                "Dolar amerykański", "Darmowe kursy walut na stronę www", "\t");

        String[][] tab3 = webRipper.prepare("http://www.kantor.rybnik.pl/",
                "Ceny detaliczne", "Ceny bilonów (USD dolar : banknoty 1 $)", "\t");

        String[][] tab4 = webRipper.prepare("http://www.dilerwalut.pl/kursy-walut/",
                "Sprzedaż", "Kursy wymiany hurtowej", "\t");

        String[][] tab5 = webRipper.prepare("https://www.baksy.pl/kurs-walut",
                "SPRZEDAŻ", "* Funty: szkockie kupujemy taniej z dyskontem 15 %", "\t");

        //Nazwa waluty 	kod 	Czas 	Kurs 	Bid 	Ask 	Zmiana 	Zmiana (zł)
        tabLenghts[1]=tab2.length;

        for(int i=0; i<tab5[5].length; i++)
        {
            System.out.println(i+" "+tab5[5][i]);
        }

        Arrays.sort(tabLenghts);
        addCurrencyLoop(tab, 1, 2, 1);
        addCurrencyLoop(tab2, 1, 3, 2);
        addCurrencyLoop(tab3, 2, 3, 3);
        addCurrencyLoop(tab4, 1, 3, 4);
        addCurrencyLoop(tab5, 0, 3, 5);
        /*
        Arrays.sort(tabLenghts);

        for(int i=0; i<tabLenghts[0];i++)
        {
            this.addValueToCurrent(tab[i][1],Double.parseDouble(tab[i][2]),1);
            this.addValueToCurrent(tab2[i][1],Double.parseDouble(tab[i][3]),2);
        }
        */

        /*
        int[][] aa12 = {{1, 2, 3}, {4, 5}, {4, 5}};
        System.out.println(aa12.length + " " + aa12[1].length);


            for (int i = 0; i < tab2.length; i++) {
                for (int j = 0; j < tab2[0].length; j++) {
                    try {
                        System.out.print(tab2[i][j] + " ");
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("wyjebało poza skalę");
                    }
                }
                System.out.println();
            }

            */
        //for(int i=0; i<tab.length)
        //System.out.println(tab[0][0]);



        //showCurrents();
    }

    void addCurrencyLoop (String[][] tab, int currencyCodeIndex, int currencyValueIndex, int sourceNumber)
    {
        for(int i=0; i<tab.length; i++)
        {
            try {
                String gottenDate =format1.format(DateFormat.getDateInstance().getCalendar().getTime())+"0";
                connection.sqlUpdateExecute(
                        "INSERT INTO WALUTY VALUES(CURR_SEQ.NEXTVAL,'"
                                + tab[i][currencyCodeIndex] + "',"+sourceNumber+",'"
                                + tab[i][currencyValueIndex] + "','" + gottenDate + "')");
                connection.con.commit();
            }
            catch (ArrayIndexOutOfBoundsException e)
            {

            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }








    /*void addValueToCurrent(String kod, double value, int idSource)
    {
        System.out.println("rozpoczeto dodwania");
        for(current c: currents)
        {
            if(c.code.equals(kod))
            {
                c.add(idSource, value);
                System.out.println("dodano wartosc");
                break;
            }
        }
    }

    void showCurrents()
    {
        for(current c: currents)
        {
            System.out.println(c.code+" "+c.name+" "+c.listOfValues.size());
            for(currentValue d: c.listOfValues)
            {
                System.out.println(d.sourceId+" "+d.time+" "+d.value+" aa");
            }
        }

    }*/





}
