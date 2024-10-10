package at.gv.fhcampuswien;

import java.util.Locale;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        lengthUnitConverter();
        incomeStatistics();
        muesliMenge();

    }

    // Length Unit Conversion Method
    public static void lengthUnitConverter() {
        Scanner milliMeterScanner = new Scanner(System.in);
        System.out.print("Enter a number of millimeters to convert: ");
        int milliMeterInput = milliMeterScanner.nextInt();

        if (milliMeterInput < 0) {
            System.out.println("Invalid value!");
        } else {
            int anzahl_meter = milliMeterInput / 1000;
            int anzahl_cm = (milliMeterInput % 1000) / 10;
            int anzahl_mm = milliMeterInput % 10;
            System.out.format("m: %d, cm: %d, mm: %d", anzahl_meter, anzahl_cm, anzahl_mm);
        }
        System.out.println();
    }

    public static void incomeStatistics() {
        Scanner incomeScanner = new Scanner(System.in);
        int income, incomeCount = 1;
        double incomeAverage, incomeSum = 0;

        do {
            System.out.format("%d. Income: "+System.lineSeparator(), incomeCount);
            income = incomeScanner.nextInt();

            if (income > 0) {
                incomeSum += income;
                incomeCount++;
            } else if (income < 0) {
                System.out.println("Invalid");
            }

        } while (income != 0);

        if (incomeSum == 0) {
            System.out.println("No valid income entered.");
        } else {
            incomeCount--;
            incomeAverage = incomeSum/incomeCount;
            System.out.format(Locale.ENGLISH,"Average income: %.2f Sum: %.2f Count: %d", incomeAverage, incomeSum, incomeCount);
        }
    }

    public static void muesliMenge() {
        Scanner muesliScanner = new Scanner(System.in);
        System.out.print("Kalorienberechnung fuer ein Muesli."+System.lineSeparator()+"Menge in g= ");
        float muesliMenge = muesliScanner.nextFloat();

        if (muesliMenge < 1 || muesliMenge > 1000) {
            System.out.println("Fehler!");
        } else {
            float nuesseMenge = muesliMenge / 21 * 3;
            float haferflockenMenge = muesliMenge / 21 * 8;
            float joghurtMenge = muesliMenge / 21 * 10;

            float nuesseKalorien = nuesseMenge * 7;
            float haferflockenKalorien = haferflockenMenge * 37 / 10;
            float joghurtKalorien = joghurtMenge * 65 / 100;

            float kalorien = nuesseKalorien + haferflockenKalorien + joghurtKalorien;

            System.out.println("Nuesse: "+(int)nuesseMenge+"g Haferflocken: "+(int)haferflockenMenge+"g Joghurt: "+(int)joghurtMenge+"g");
            System.out.println(muesliMenge+" g Muesli enthalten "+(int)kalorien+" kcal.");
        }
    }
}