
import java.io.*;
import java.util.*;

//@author William Scott
public class CompilerLabExp7RelativeLoader {
    //UR12CS135 - P.William Scott - Exp 7 - Relative Loader

    public static BufferedReader br, brop;
    public static BufferedWriter bw;
    public static String line, ts[], s, startingaddress, v, ss, m, r, op;
    public static Scanner in = new Scanner(System.in);
    public static int n;

    public static void main(String[] args) throws Exception {
        System.out.println("\nUR12CS135 - Relative Loader");
        br = new BufferedReader(new FileReader("..\\Exp 7 - Loader Input File.txt"));
        bw = new BufferedWriter(new FileWriter(new File("..\\Exp 7 - Loader Output File.txt")));
        System.out.println("Enter Starting Address");
        startingaddress = in.nextLine();
        ss = startingaddress;
        System.out.println("\n------Input File-----");
        while ((line = br.readLine()) != null) {
            System.out.println(line);
            ts = line.split(" ");
            if (line.startsWith("H")) {
                s = ts[1];
            }
            if (line.startsWith("T")) {
                if (ts[1] != s) {
                    v = hexoperations(ts[1], s, '-');
                } else {
                    v = "1";
                }
                if (v != "1") {
                    ss = hexoperations(startingaddress, v, '+');
                }
                m = ts[2];
                n = 3;
                for (char c : m.toCharArray()) {
                    if (c == '1') {
                        r = hexoperations(startingaddress, ts[n], '+');
                        r = hexoperations(r, s, '-');
                    } else {
                        r = ts[n];
                    }
                    op = ss.toUpperCase() + "\t" + r;
                    bw.write(op);
                    bw.newLine();
                    ss = hexoperations(ss, "3", '+');
                    n++;
                }
            }
        }
        bw.close();
        showop();
    }

    public static void showop() throws Exception {
        brop = new BufferedReader(new FileReader("..\\Exp 7 - Loader Output File.txt"));
        System.out.println("\n-----Output File------");
        while ((line = brop.readLine()) != null) {
            System.out.println(line);
        }
    }

    public static String hexoperations(String a, String b, char op) {
        String c;
        int t = 0;
        if (op == '+') {
            t = Integer.parseInt(a, 16) + Integer.parseInt(b, 16);
        } else if (op == '-') {
            t = Integer.parseInt(a, 16) - Integer.parseInt(b, 16);
        }
        c = Integer.toHexString(t);
        return c;
    }
}
