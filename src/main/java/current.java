import java.util.ArrayList;
import java.util.List;

public class current {
    public String name;
    public String code;
    public List<currentValue>  listOfValues = new ArrayList<currentValue>();

    current(String name, String code)
    {
        this.name=name;
        this.code=code;
    }
    void add(int idSource, double value)
    {
        listOfValues.add(new currentValue(value, idSource));
    }
}
