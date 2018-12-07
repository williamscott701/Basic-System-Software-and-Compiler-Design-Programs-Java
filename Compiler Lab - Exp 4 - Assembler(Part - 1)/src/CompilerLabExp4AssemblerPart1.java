
import java.io.*;
import java.util.*;

//@author William Scott
public class CompilerLabExp4AssemblerPart1 {
    //UR12CS135 - P.William Scott - Exp 4 - Assembler
    
    public static BufferedReader broptable, brsiccode, brsiccode2, broutputaddress, broutputaddress2, broutputaddress3, broutputsymboltable, broutputobjectcode;
    public static BufferedWriter bwaddress, bwsymboltable, bwobjectcode;
    public static Scanner in = new Scanner(System.in);
    public static String line = "", ts = "", s[] = new String[3];
    public static ArrayList optableoperand = new ArrayList(), optablevalue = new ArrayList(), operands = new ArrayList(), operandaddress = new ArrayList(), operandnames = new ArrayList();

    public static void main(String[] args) throws Exception {
        System.out.println("UR12CS135 - Assembler (Part - 1)");
        showinputfile();
        storeoptable();
        showoptable();
        generateaddress();
        showoperands();
        showoutputaddress();
    }

    public static void showoutputaddress() throws Exception {
        broutputaddress = new BufferedReader(new FileReader("..\\Exp 4 - Assembler Output Address File.txt"));
        System.out.println("\n--------Output Address File----------");
        String a;
        while ((a = broutputaddress.readLine()) != null) {
            System.out.println(a);
        }
    }

    public static void showinputfile() throws Exception {
        brsiccode2 = new BufferedReader(new FileReader("..\\Exp 4 - Assembler Input File - SIC Code.txt"));
        System.out.println("\n--------Input File----------");
        String a;
        while ((a = brsiccode2.readLine()) != null) {
            System.out.println(a);
        }
    }

    public static void showsymboltable() throws Exception {
        broutputsymboltable = new BufferedReader(new FileReader("..\\Exp 4 - Assembler Output SymbolTable File.txt"));
        System.out.println("\n---Generated SymbolTable File----");
        String a;
        while ((a = broutputsymboltable.readLine()) != null) {
            System.out.println(a);
        }
    }

    public static void showoptable() {
        System.out.println("\n------Stored Optable------");
        optableoperand.stream().forEach(System.out::println);
    }

    public static void showoperands() {
        System.out.println("\n------Stored Operands------");
        operands.stream().forEach(System.out::println);
    }

    public static void showobjectcode() throws Exception {
        broutputobjectcode = new BufferedReader(new FileReader("..\\Exp 4 - Assembler Output ObjectCode File.txt"));
        System.out.println("\n---Generated ObjectCode File----");
        String a;
        while ((a = broutputobjectcode.readLine()) != null) {
            System.out.println(a);
        }

    }

    public static void generateaddress() throws Exception {
        brsiccode = new BufferedReader(new FileReader("..\\Exp 4 - Assembler Input File - SIC Code.txt"));
        bwaddress = new BufferedWriter(new FileWriter(new File("..\\Exp 4 - Assembler Output Address File.txt")));
        bwaddress.flush();
        String d;
        System.out.println("\n----Generating Address----");
        while (!(line = brsiccode.readLine()).contains("START")) {
            System.out.println(line);
            bwaddress.write("Address" + "\t" + line);
            bwaddress.newLine();
        }
        do {
            s = line.split("\t", 3);
            System.out.println(line);
            if (line.contains("START")) {
                ts = s[2];
                d = "";
                line = s[0] + "\t" + s[1] + "\t" + "0";
            } else {
                d = ts;
                ts = addhex(ts, getadditionvalue());
            }
            bwaddress.write(d + "\t" + line);
            bwaddress.newLine();
        } while (!(line = brsiccode.readLine()).contains("END"));
        bwaddress.write(addhex(ts, "1") + "\t" + line);
        bwaddress.newLine();

        bwaddress.close();
    }

    public static void generatesymboltable() throws Exception {
        broutputaddress2 = new BufferedReader(new FileReader("..\\Exp 4 - Assembler Output Address File.txt"));
        bwsymboltable = new BufferedWriter(new FileWriter(new File("..\\Exp 4 - Assembler Output SymbolTable File.txt")));
        bwsymboltable.flush();
        String a, t[];
        bwsymboltable.write("Name\tValue");
        bwsymboltable.newLine();
        while ((a = broutputaddress2.readLine()) != null) {
            t = a.split("\t", 3);
            if (operands.contains(t[1])) {
                operandaddress.add(t[0]);
                operandnames.add(t[1]);
                bwsymboltable.write(t[0] + "\t" + t[1]);
                bwsymboltable.newLine();
            }
        }
        bwsymboltable.close();
    }

    public static void generateobjectcode() throws Exception {
        broutputaddress3 = new BufferedReader(new FileReader("..\\Exp 4 - Assembler Output Address File.txt"));
        bwaddress = new BufferedWriter(new FileWriter(new File("..\\Exp 4 - Assembler Output ObjectCode File.txt")));
        bwaddress.flush();
        String a, t[], r;
        while ((a = broutputaddress3.readLine()) != null) {
            t = a.split("\t", 4);
            if (optableoperand.contains(t[2])) {
                a += "\t" + getopvalue(t[2]) + getoperandaddress(t[3]);
            } else if (t[2].contains("BYTE")) {
                r = t[3].substring(2, 3);
                a += "\t" + Integer.toHexString((int) r.charAt(0)).toUpperCase();
            } else if (t[2].contains("WORD")) {
                a += "\t" + Integer.parseInt(t[3], 16);
            } else if (a.contains("operand")) {
                a += "\tObjectCode";
            }
            bwaddress.write(a);
            bwaddress.newLine();
        }
        bwaddress.close();
    }

    public static String getopvalue(String a) {
        int v;
        v = optableoperand.indexOf(a);
        return (String) optablevalue.get(v);
    }

    public static String getoperandaddress(String a) {
        int v;
        v = operandnames.indexOf(a);
        return (String) operandaddress.get(v);
    }

    public static void storeoptable() throws Exception {
        broptable = new BufferedReader(new FileReader("..\\Exp 4 - Assembler Input File - OpTable.txt"));
        String a, t[];
        while ((a = broptable.readLine()) != null) {
            t = a.split("\t", 2);
            optableoperand.add(t[0]);
            optablevalue.add(t[1]);
        }
    }

    public static String getadditionvalue() {
        String val = "0";
        int p;
        if (optableoperand.contains(s[1])) {
            val = "3";
            operands.add(s[2]);
        } else if (s[1].contains("WORD")) {
            val = "3";
        } else if (s[1].contains("RESW")) {
            p = 3 * Integer.parseInt(s[2]);
            val = String.valueOf(p);
        } else if (s[1].contains("RESB")) {
            p = 1 * Integer.parseInt(s[2]);
            val = String.valueOf(p);
        } else if (s[1].contains("BYTE")) {
            val = "1";
        }
        return val;
    }

    public static String addhex(String a, String b) {
        String c;
        int t;
        t = Integer.parseInt(a, 16) + Integer.parseInt(b, 16);
        c = Integer.toHexString(t);
        return c;
    }
}
