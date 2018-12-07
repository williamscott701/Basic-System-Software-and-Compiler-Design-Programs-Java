
import java.io.*;
import java.util.*;

//@author William Scott

public class CompilerLabExp3MacroProcessor {

    //UR12CS135 - William Scott - Exp 3 - Macro Processor 
    public static BufferedReader br, broutput, brinput;
    public static BufferedWriter bw;
    public static Scanner in = new Scanner(System.in);
    public static String line = "", t = "";
    public static ArrayList definitions = new ArrayList(), values = new ArrayList();
    public static int count = 0;

    public static void main(String[] args) throws FileNotFoundException, IOException {

        System.out.println("UR12CS135 - Macro Processor");

        br = new BufferedReader(new FileReader("..\\Exp 3 - MacroProcessor Input File.txt"));
        brinput = new BufferedReader(new FileReader("..\\Exp 3 - MacroProcessor Input File.txt"));
        broutput = new BufferedReader(new FileReader("..\\Exp 3 - MacroProcessor Output File.txt"));
        bw = new BufferedWriter(new FileWriter(new File("..\\Exp 3 - MacroProcessor Output File.txt")));
        bw.flush();

        showinputfile(); 

        while (!(line = br.readLine()).contains("main")) {
            if (line.contains("#define")) {
                store(line);
            } else {
                bw.newLine();
                bw.write(line);
            }
        }

        bw.write(line);
        bw.newLine();

        while ((line = br.readLine()) != null) {
            replacevalues(line);
        }

        bw.close();
        showvalues();
        showoutput();
    }

    public static void store(String a) {
        t = a;
        String[] s;
        s = t.split(" ", 3);
        if (s[2].startsWith("{")) {
            s[2] = s[2].substring(1, s[2].length() - 1);
            s[1] += ';';
        } else {
            s[2] = s[2].substring(0, s[2].length() - 1);
        }
        definitions.add(s[1]);
        values.add(s[2]);
        count++;
    }

    public static void showvalues() {
        System.out.println("\n\n-------Stored Values----------");
        for (int i = 0; i < count; i++) {
            System.out.println(definitions.get(i) + " " + values.get(i));
        }
    }

    public static void replacevalues(String a) throws IOException {
        for (int i = 0; i < count; i++) {
            if (a.contains((String) definitions.get(i))) {
                a = a.replace((String) definitions.get(i), (String) values.get(i));
            }
        }
        bw.write(a);
        bw.newLine();
    }

    public static void showoutput() throws IOException {
        System.out.println("\n\n--------Output File----------");
        String a;
        while ((a = broutput.readLine()) != null) {
            System.out.println(a);
        }
    }

    public static void showinputfile() throws IOException {
        System.out.println("\n--------Input File----------");
        String a;
        while ((a = brinput.readLine()) != null) {
            System.out.println(a);
        }
    }

}
