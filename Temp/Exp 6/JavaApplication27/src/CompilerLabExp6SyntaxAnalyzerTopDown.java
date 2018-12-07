
import java.io.*;
import java.util.*;

public class CompilerLabExp6SyntaxAnalyzerTopDown {

    public static BufferedReader brip, brip2;
    public static String ts = "", tsa[];
    public static int ti = 0;
    public static char ct, cc;
    public static ArrayList ipfrom = new ArrayList(), ipto = new ArrayList(), states = new ArrayList(), first = new ArrayList(), follow = new ArrayList();

    public static void main(String[] args) throws FileNotFoundException, IOException {
        System.out.println("\nUR12CS135 - Syntax Analyzer (Top Down)");
        readip();
        inititalize();
        findfirst();
        //findfollow();
        showfirstfollow();
    }

    public static void readip() throws FileNotFoundException, IOException {
        System.out.println("\n---------Input File---------");
        brip = new BufferedReader(new FileReader("..\\4.txt"));
        while ((ts = brip.readLine()) != null) {
            System.out.println(ts);
            tsa = ts.split(" ");
            ipfrom.add(tsa[0]);
            ipto.add(tsa[1]);
        }
    }

    public static void inititalize() {
        for (int i = 0; i < 20; i++) {
            first.add("");
            follow.add("");
        }
    }

    public static void findfirst() {
        System.out.println("");
        char ct;
        for (int i = 0; i < ipfrom.size(); i++) {
            ct = ipfrom.get(i).toString().charAt(0);
            System.out.println("\n" + ct);
            addfirst(ct, ipto.get(i).toString(), 0, 0);
        }
    }

    public static void addfirst(char a, String b, int i, int t) {
        for (int j = t; j < ipfrom.size(); j++) {
            if (ipfrom.get(j).toString().contains(a + "")) {
                System.out.println(" a:" + a + " b:" + b + " i:" + i + " t:" + t);
                if (ipto.get(j).toString().length() > i) {
                    ct = ipto.get(j).toString().charAt(i++);
                    System.out.println("ct: " + ct);
                }
                //addfirst(ct, ipto.get(i).toString(), 0, j);
            }
        }
    }

    public static void showfirstfollow() {
        System.out.println("\n----------First & Follow---------");
        System.out.println("State\tFirst\tFollow");
        for (int i = 0; i < states.size(); i++) {
            System.out.println("  " + states.get(i) + "\t " + first.get(i) + "\t " + follow.get(i));
        }
    }

}
