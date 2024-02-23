import java.util.ArrayList;


public class Rule {
    ArrayList<String> premisses = new ArrayList<>();
    ArrayList<String> consequences = new ArrayList<>();
    int index;

    public Rule( ArrayList<String>premisses, ArrayList<String>consequences, int index){
        this.index = index;
        this.premisses = premisses;
        this.consequences = consequences;
    }

}
