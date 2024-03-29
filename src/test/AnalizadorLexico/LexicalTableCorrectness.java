package AnalizadorLexico;


import java.io.*;
import java.util.Scanner;

import Analizadorlexico.AnalizadorLexico;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.*;


public class LexicalTableCorrectness {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    public void out() {
        System.out.print("100\n-5140\n-51");
        assertEquals("100\n-5140\n-51", outContent.toString());
    }

    @Test
    public void err() {
        System.err.print("100\n-5140\n-51");
        assertEquals("100\n-5140\n-51", errContent.toString());
    }

    @Test
    public void lexicalTableCorrectness() throws FileNotFoundException {

        File file = new File("prueba1.txt");
        Scanner scanner = new Scanner(file);
        String codigo = " ";
        while (scanner.hasNext()) {
            codigo += scanner.nextLine() + "\n";
        }
        AnalizadorLexico analizadorLexico = new AnalizadorLexico(codigo);
        analizadorLexico.imprimirTokens();

        assertEquals("    1      1 Identificador   x\r\n" +
                "    1      3 Asignacion     \r\n" +
                "    1      5 Digito            100\r\n" +
                "    1      9 PuntoComa      \r\n" +
                "    2      1 Identificador   z\r\n" +
                "    2      3 Asignacion     \r\n" +
                "    2      5 Digito            300\r\n" +
                "    2      9 ExprecionSum   \r\n" +
                "    2     11 ParentesisIzquierdo\r\n" +
                "    2     13 Digito             10\r\n" +
                "    2     16 ExpresionRes   \r\n" +
                "    2     18 ParentesisIzquierdo\r\n" +
                "    2     19 Identificador   x\r\n" +
                "    2     21 ExprecionSum   \r\n" +
                "    2     23 Digito              9\r\n" +
                "    2     24 ParentesisDerecho\r\n" +
                "    2     26 TerminoMult    \r\n" +
                "    2     28 Digito             50\r\n" +
                "    2     31 ParentesisDerecho\r\n" +
                "    2     33 PuntoComa      \r\n" +
                "    3      1 Identificador   w\r\n" +
                "    3      3 Asignacion     \r\n" +
                "    3      5 Identificador   z\r\n" +
                "    3      7 TerminoDiv     \r\n" +
                "    3      9 Identificador   x\r\n" +
                "    3     11 PuntoComa      \r\n" +
                "    4      1 Imprime        \r\n" +
                "    4      9 Identificador   x\r\n" +
                "    4     10 PuntoComa      \r\n" +
                "    5      1 Imprime        \r\n" +
                "    5      9 Identificador   z\r\n" +
                "    5     10 PuntoComa      \r\n" +
                "    6      1 Imprime        \r\n" +
                "    6      9 Identificador   w\r\n" +
                "    6     10 PuntoComa      \r\n" +
                "    7      1 EOI            \r\n", outContent.toString());



    }
}