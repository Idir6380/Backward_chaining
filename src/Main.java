import java.util.ArrayList;
import java.util.Arrays;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
public class Main {
    public static ArrayList<String[]> readRulesFromFile(String filename) {
        ArrayList<String[]> rules = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {

                String[] parts = line.split(" THEN ");
                if (parts.length == 2) {
                    String pi = parts[0].replace("IF ", "");
                    String ci = parts[1];
                    rules.add(new String[]{pi, ci});
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rules;
    }
    public static void readFactsFromFile(String filename, ArrayList<String> FP, ArrayList<String> FB) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Vérifier si la ligne commence par "FP =" ou "FB ="
                if (line.startsWith("FP =")) {
                    String[] parts = line.substring(5).trim().split(",");
                    FP.addAll(Arrays.asList(parts));
                } else if (line.startsWith("FB =")) {
                    String[] parts = line.substring(5).trim().split(",");
                    FB.addAll(Arrays.asList(parts));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        Rule[] base = new Rule[9];
        /*ArrayList<String> p1 = new ArrayList<>(Arrays.asList("A", "B"));
        ArrayList<String> c1 = new ArrayList<>(Arrays.asList("F"));

        base[0] = new Rule(p1, c1, 1);

        ArrayList<String> p2 = new ArrayList<>(Arrays.asList("F", "H"));
        ArrayList<String> c2 = new ArrayList<>(Arrays.asList("I"));

        base[1] = new Rule(p2, c2, 2);

        ArrayList<String> p3 = new ArrayList<>(Arrays.asList("D", "H", "G"));
        ArrayList<String> c3 = new ArrayList<>(Arrays.asList("A"));

        base[2] = new Rule(p3, c3, 3);

        ArrayList<String> p4 = new ArrayList<>(Arrays.asList("O", "G"));
        ArrayList<String> c4 = new ArrayList<>(Arrays.asList("H"));

        base[3] = new Rule(p4, c4, 4);

        ArrayList<String> p5 = new ArrayList<>(Arrays.asList("E", "H"));
        ArrayList<String> c5 = new ArrayList<>(Arrays.asList("B"));

        base[4] = new Rule(p5, c5, 5);

        ArrayList<String> p6 = new ArrayList<>(Arrays.asList("G", "A"));
        ArrayList<String> c6 = new ArrayList<>(Arrays.asList("B"));

        base[5] = new Rule(p6, c6, 6);

        ArrayList<String> p7 = new ArrayList<>(Arrays.asList("G", "H"));
        ArrayList<String> c7 = new ArrayList<>(Arrays.asList("P"));

        base[6] = new Rule(p7, c7, 7);

        ArrayList<String> p8 = new ArrayList<>(Arrays.asList("G", "H"));
        ArrayList<String> c8 = new ArrayList<>(Arrays.asList("O"));

        base[7] = new Rule(p8, c8, 8);

        ArrayList<String> p9 = new ArrayList<>(Arrays.asList("D", "O", "G"));
        ArrayList<String> c9 = new ArrayList<>(Arrays.asList("J"));

        base[8] = new Rule(p9, c9, 9);
*/
        ArrayList<String[]> rulesFromFile = readRulesFromFile("src/rules.txt");


        for (int i = 0; i < rulesFromFile.size(); i++) {
            String[] ruleParts = rulesFromFile.get(i);
            ArrayList<String> pi = new ArrayList<>(Arrays.asList(ruleParts[0].split(",")));
            String[] ciArray = ruleParts[1].split(",");
            ArrayList<String> ci = new ArrayList<>(Arrays.asList(ciArray));

            base[i] = new Rule(pi, ci, i + 1);
        }

        ArrayList<String> FP = new ArrayList<>();
        ArrayList<String> FB = new ArrayList<>();

        readFactsFromFile("src/facts.txt", FP, FB);

        BackWardChaining bach_chaining = new BackWardChaining();
        System.out.println(FP);
        System.out.println(FB);
        if (bach_chaining.back_ward_chaining(base, FP, FB)) {
            System.out.println("Fait I prouve avec backward chaining");

        } else {
            System.out.println("Fait I non prouve avec backward chaining");
        }

    }
}