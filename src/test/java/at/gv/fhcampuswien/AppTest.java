package at.gv.fhcampuswien;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AppTest
{

    private PrintStream originalOut;
    private InputStream originalIn;
    private ByteArrayOutputStream bos;
    private PrintStream ps;

    @BeforeEach
    public void setupStreams() throws IOException {
        originalOut = System.out;
        originalIn = System.in;

        bos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bos));

        PipedOutputStream pos = new PipedOutputStream();
        PipedInputStream pis = new PipedInputStream(pos);
        System.setIn(pis);
        ps = new PrintStream(pos, true);
    }

    @AfterEach
    public void tearDownStreams() {
        // undo the binding in System
        System.setOut(originalOut);
        System.setIn(originalIn);
    }


    @Test
    public void lenghtUnit001() {
        ps.println(1);
        App.lengthUnitConverter();
        assertTrue(true);
    }

    @Test
    public void lengthUnit002() {
        int val=-1;
        ps.println(val);
        App.lengthUnitConverter();
        assertTrue(bos.toString().contains("Invalid value"));
    }

    @Test
    public void lengthUnit003() {
        int val=0;
        ps.println(val);
        App.lengthUnitConverter();
        assertTrue(bos.toString().contains("m: 0, cm: 0, mm: 0"));
    }

    @Test
    public void lengthUnit005() {
        int val=3;
        ps.println(val);
        App.lengthUnitConverter();
        assertTrue(bos.toString().contains("m: 0, cm: 0, mm: 3"));
    }

    @Test
    public void lengthUnit006() {
        int val=32;
        ps.println(val);
        App.lengthUnitConverter();
        assertTrue(bos.toString().contains("m: 0, cm: 3, mm: 2"));
    }

    @Test
    public void lengthUnit007() {
        int val=11132;
        ps.println(val);
        App.lengthUnitConverter();
        assertTrue(bos.toString().contains("m: 11, cm: 13, mm: 2"));
    }

    @Test
    public void incomeStat001() {
        int val=0;
        ps.println(val);
        App.incomeStatistics();
        assertTrue(true);
    }

    @Test
    public void incomeStat002() {
        int val=0;
        ps.println(val);
        App.incomeStatistics();
        assertTrue(bos.toString().contains("No valid income"));
    }

    @Test
    public void incomeStat003() {
        ps.println(100);
        ps.println(200);
        ps.println(0);
        App.incomeStatistics();
        assertTrue(bos.toString().contains("Average income: 150.00 Sum: 300.00 Count: 2"));
    }

    @Test
    public void incomeStat004() {
        int val=-10;
        ps.println(val);
        ps.println(20);
        ps.println(0);
        App.incomeStatistics();
        assertTrue(bos.toString().contains("Average income: 20.00 Sum: 20.00 Count: 1"));
        assertTrue(bos.toString().contains("Invalid"));
    }

}
