
import java.io.*;

public class CompilerLabExp10CodeGeneration {

    public static BufferedReader br;
    public static String line, lhs = "m", rhs1, rhs2;
    public static int eq, sym, i = 1;

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new FileReader("..\\inputpfile.txt"));

        while ((line = br.readLine()) != null) {
            eq = line.indexOf("=");
            if (line.contains("+")) {
                sym = line.indexOf("+");
            }
            if (line.contains("-")) {
                sym = line.indexOf("-");
            }
            rhs1 = line.substring(eq + 1, sym);
            rhs2 = line.substring(sym + 1, line.length() - 1);
            if (line.contains(lhs)) {
                System.out.println("mov " + rhs2 + ", R" + i);
                if (line.contains("+")) {
                    System.out.println("add R1, R" + i);
                } else if (line.contains("-")) {
                    System.out.println("sub R1, R" + i);
                } else if (line.contains("*")) {
                    System.out.println("mul R1, R" + i);
                } else if (line.contains("/")) {
                    System.out.println("div R1, R" + i);
                }
                i++;
            } else {
                System.out.println("mov " + rhs1 + ", R" + i++);
                System.out.println("mov " + rhs2 + ", R" + i++);
                if (line.contains("+")) {
                    System.out.println("add R" + (i - 2) + ", R" + (i - 1));
                } else if (line.contains("-")) {
                    System.out.println("sub R" + i++ + ", R" + i++);
                } else if (line.contains("*")) {
                    System.out.println("mul R" + i++ + ", R" + i++);
                } else if (line.contains("/")) {
                    System.out.println("div R" + i++ + ", R" + i++);
                }
            }
            lhs = line.substring(0, eq);
        }
    }
}
