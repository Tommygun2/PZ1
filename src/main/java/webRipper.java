import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings({"all"})
 public  class webRipper {

    WebClient webClient = new WebClient(BrowserVersion.BEST_SUPPORTED);

    HtmlPage[] page =  new HtmlPage[2];


    webRipper()
    {

        try {
            page[0] = webClient.getPage("https://www.nbp.pl/home.aspx?f=/kursy/kursya.html");
            page[1]= webClient.getPage("https://kursy-na-zywo.mybank.pl/");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    String[][] prepare(String webp, String begin, String end, String regexDelimiter)
    {
        HtmlPage webpage = null;
        try {
            webpage = webClient.getPage(webp);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String pageContext = webpage.asText();
        int[] ends = {pageContext.indexOf(begin), pageContext.indexOf(end)};
        String trimmedString=pageContext.substring(ends[0], ends[1]);
        //System.out.println(trimmedString);
        String[] pageLines=trimmedString.split("\n");
        String[][] finalString=new String[pageLines.length][pageLines[0].split(regexDelimiter).length] ;
            for(int i=0; i<pageLines.length; i++)
            {
                finalString[i]=pageLines[i].split(regexDelimiter);
            }

        return finalString;
    }
    /*
    void say()
    {
        String pageContext1= page[0].asText();
        String pageContext2= page[1].asText();

        String trimmedString = pageContext1.substring(pageContext1.indexOf("bat"),pageContext1.indexOf("Powyższa tabela"));
        String trimmedString2 = pageContext2.substring(pageContext2.indexOf("Dolar amerykański"),pageContext2.indexOf("Darmowe kursy walut na stronę www"));

        String[] pageLines=trimmedString.split("\n");
        String[] pageLines2=trimmedString2.split("\n");

        for(String a: pageLines)
        System.out.println(a);
        for(String a: pageLines2)
            System.out.println(a);

        Pattern pat = Pattern.compile("/^[a-z\\s]+|[1A-Z\\s]+|[0-9,0-9]+/g");
        Matcher mat = pat.matcher(pageLines[1]);
        while(mat.find()) System.out.println(mat.group());
        String[] large = pageLines[5].split("\\t");
        String[] large2 = pageLines2[5].split("\\t");

        for(String a: large)
            System.out.println(a);
        for(String a: large2)
            System.out.println(a);
        System.out.println(large.length); //^[a-z\s]+|[1A-Z\s]+|[0-9,0-9]+ regex do tego
    }*/

}
