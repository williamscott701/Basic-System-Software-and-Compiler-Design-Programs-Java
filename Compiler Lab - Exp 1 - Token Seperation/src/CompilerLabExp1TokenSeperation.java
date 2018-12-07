
import java.io.*;
import java.util.*;

//@author William Scott
public class CompilerLabExp1TokenSeperation {

    public static Scanner in = new Scanner(System.in);
    public static BufferedReader filedata;
    public static String line = "", temp;
    public static StringTokenizer st;
    
    public static ArrayList pkeywords = new ArrayList();
    public static ArrayList psymbols = new ArrayList();
    public static ArrayList poperators = new ArrayList();
    public static ArrayList pdatatypes = new ArrayList();
    
    public static ArrayList keywords = new ArrayList();
    public static ArrayList symbols = new ArrayList();
    public static ArrayList operators = new ArrayList();
    public static ArrayList variables = new ArrayList();
    public static ArrayList constants = new ArrayList();
    public static ArrayList declarations = new ArrayList();

    public static void main(String[] args) throws FileNotFoundException, IOException {

        addpredata();

        filedata = new BufferedReader(new FileReader("..\\Exp 1 - Token Seperation Input File.txt"));
        while (!(temp = filedata.readLine()).contains("class")) {
            if (temp.contains("import")) {
                declarations.add(temp);
            }
        }

        while ((line = filedata.readLine()) != null) {
            st = new StringTokenizer(line, " +-/*%^,=;{}( )", true);
            while (st.hasMoreTokens()) {

                temp = st.nextToken();
                System.out.println(temp);

                if (poperators.contains(temp) && !operators.contains(temp)) {
                    operators.add(temp);
                } else if (pkeywords.contains(temp) && !keywords.contains(temp)) {
                    keywords.add(temp);
                } else if (psymbols.contains(temp) && !symbols.contains(temp)) {
                    symbols.add(temp);
                }

                if (pdatatypes.contains(temp)) {
                    while (!(temp = st.nextToken()).contains(";")) {
                        if (!temp.contains(" ") && !temp.contains(",") && !temp.contains("=")) {
                            if ((int) temp.charAt(0) >= 48 && (int) temp.charAt(0) <= 57) {
                                constants.add(temp);
                            } else {
                                variables.add(temp);
                            }
                        }
                    }
                }
            }
        }

        display();
    }

    public static void display() {
        System.out.println("\n\n--------All Tokens------");

        System.out.println("\nDeclarations: ");
        declarations.stream().forEach(System.out::println);

        System.out.println("\nKeywords: ");
        keywords.stream().forEach(System.out::println);

        System.out.println("\nSymbols: ");
        symbols.stream().forEach(System.out::println);

        System.out.println("\nOperators: ");
        operators.stream().forEach(System.out::println);

        System.out.println("\nVariables: ");
        variables.stream().forEach(System.out::println);

        System.out.println("\nConstants: ");
        constants.stream().forEach(System.out::println);
    }

    public static void addpredata() {
        pkeywords.add("abstract");
        pkeywords.add("break");
        pkeywords.add("case");
        pkeywords.add("catch");
        pkeywords.add("class");
        pkeywords.add("const");
        pkeywords.add("continue");
        pkeywords.add("default");
        pkeywords.add("do");
        pkeywords.add("else");
        pkeywords.add("enum");
        pkeywords.add("extends");
        pkeywords.add("finally");
        pkeywords.add("for");
        pkeywords.add("goto");
        pkeywords.add("if");
        pkeywords.add("implements");
        pkeywords.add("import");
        pkeywords.add("instanceof");
        pkeywords.add("interface");
        pkeywords.add("native");
        pkeywords.add("new");
        pkeywords.add("package");
        pkeywords.add("private");
        pkeywords.add("protected");
        pkeywords.add("public");
        pkeywords.add("return");
        pkeywords.add("static");
        pkeywords.add("strictfp");
        pkeywords.add("super");
        pkeywords.add("switch");
        pkeywords.add("synchronized");
        pkeywords.add("this");
        pkeywords.add("throw");
        pkeywords.add("throws");
        pkeywords.add("transient");
        pkeywords.add("try");
        pkeywords.add("void");
        pkeywords.add("volatile");
        pkeywords.add("while");
        pkeywords.add("false");
        pkeywords.add("null");
        pkeywords.add("true");
        pkeywords.add("System.out.println");
        pkeywords.add("int");
        pkeywords.add("char");
        pkeywords.add("float");
        pkeywords.add("String");
        pkeywords.add("double");
        pkeywords.add("short");
        pkeywords.add("long");
        pdatatypes.add("int");
        pdatatypes.add("char");
        pdatatypes.add("float");
        pdatatypes.add("String");
        pdatatypes.add("double");
        pdatatypes.add("short");
        pdatatypes.add("long");
        psymbols.add("#");
        psymbols.add("/");
        psymbols.add("{");
        psymbols.add("}");
        psymbols.add("|");
        psymbols.add("!");
        psymbols.add("`");
        psymbols.add("~");
        psymbols.add("$");
        psymbols.add("(");
        psymbols.add(")");
        poperators.add("+");
        poperators.add("-");
        poperators.add("*");
        poperators.add("+");
        poperators.add("/");
        poperators.add("%");
        poperators.add(">");
        poperators.add("<");
        poperators.add("=");
    }
}
