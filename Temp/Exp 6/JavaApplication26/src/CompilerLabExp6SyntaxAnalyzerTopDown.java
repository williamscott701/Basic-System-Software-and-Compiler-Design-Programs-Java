
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
        findfollow();
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
        for (Object fo : ipfrom) {
            if (states.contains(fo)) {
                ti = states.indexOf(fo);
            } else {
                states.add(fo);
                ti = states.size() - 1;
            }
            addfirst(fo.toString(), 0, "");
        }
    }

    public static void findfollow() {
        System.out.println("\n\n--------------------");
        for (Object state : states) {
            cc = state.toString().charAt(0);
            System.out.println("\n\nStates: " + cc);
            if (states.get(0).toString().contains(cc + "")) {
                addfollowsym('$');
            }
            addfollow();
        }
    }

    public static void addfollow() {
        for (int i = 0; i < ipto.size(); i++) {
            ts = ipto.get(i).toString();
            if (ts.contains(cc + "")) {
                unnamed(' ', i);
            }
        }
    }

    public static void unnamed(char a, int i) {
        char p;
        ti = ts.lastIndexOf(cc);
        System.out.print("\n  *" + cc + "  " + ts);
        ti = ts.indexOf(cc);
        System.out.println("\t" + ti + " " + (ts.length() - 1));
        if (ti == ts.length() - 1) {
            System.out.println("       A: last");
            System.out.println("        " + ipfrom.get(i).toString().charAt(0));
            getvaluesforfollow(ipfrom.get(i).toString().charAt(0), "follow");
        } else if (!states.contains(ts.charAt(ti + 1) + "")) {
            System.out.println("       B: " + ts.charAt(ti + 1));
            addfollowsym(ts.charAt(ti + 1));
        } else {
            System.out.println("       C: " + ts.charAt(ti + 1));
            addfirsttofollow(ts.charAt(ti + 1));
        }
    }

    public static void addfirsttofollow(char a) {
        System.out.println("\n\n-------------");
        System.out.println(a);
        int y=states.indexOf(a+"");
        System.out.println(y);
        for (int i = 0; i < first.get(y).toString().length(); i++) {
            addfollowsym(first.get(y).toString().charAt(i));
        }
    }

    public static void getvaluesforfollow(char a, String f) {
        int t, r;
        String g, p = "";
        t = states.indexOf(cc + "");
        r = states.indexOf(a + "");
        if (f.equals("follow")) {
            p = follow.get(r).toString();
            System.out.println("          adding follow: " + p);
        } else {
            p = first.get(r).toString();
            System.out.println("          adding first: " + p);
        }
        for (char c : p.toCharArray()) {
            addfollowsym(c);
        }
    }

    public static void addfollowsym(char a) {
        int t;
        String g;
        t = states.indexOf(cc + "");
        if (a == '~') {
        } else if (!follow.get(t).toString().contains(a + "")) {
            g = follow.get(t).toString() + a;
            follow.set(t, g);
        }
    }

    public static void addfirst(String a, int b, Object c) {
        char h = ' ';
        for (int i = b; i < ipfrom.size(); i++) {
            if (ipfrom.get(i).toString().contains(a)) {
                ct = ipto.get(i).toString().charAt(0);
                if (ipto.get(i).toString().length() > 1) {
                    h = ipto.get(i).toString().charAt(1);
                }
                if (ipfrom.contains(ct + "")) {
                    addfirst(ct + "", ++i, h);
                } else if (!first.get(ti).toString().contains(ct + "")) {
                    ts = first.get(ti).toString() + ct;
                    first.set(ti, ts);

                }
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
