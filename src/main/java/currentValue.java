import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class currentValue {
    public double value;
    DateFormat time;
    int sourceId;

    currentValue(double val, int sid)
    {
        value=val;
        sourceId=sid;
        time=DateFormat.getDateInstance();
    }
}
