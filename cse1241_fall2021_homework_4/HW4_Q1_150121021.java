
/**
 * This program calculates the invoice of each flat in an apartment building with given instructions. 
 * 
 * Name and Surname : Feyzullah Asıllıoğlu
 * Student ID : 150121021
 */
import java.util.Scanner;

public class HW4_Q1_150121021 {
    public static void main(String[] args) {
        var input = new Scanner(System.in);
        var flatCount = input.nextInt();
        var consumptionOfFlats = new double[flatCount];
        for (int i = 0; i < consumptionOfFlats.length; i++)
            consumptionOfFlats[i] = input.nextDouble();
        var totalBill = input.nextDouble();
        input.close();
        var invoice = calculateTheInvoice(consumptionOfFlats, totalBill);
        printBills(invoice);
    }

    public static double[] calculateTheInvoice(double[] flats, double totalBill) {
        double totalConsumption = 0;
        double[] invoceValues = new double[flats.length];
        for (int i = 0; i < flats.length; i++)
            totalConsumption += flats[i];
        for (int i = 0; i < flats.length; i++)
            invoceValues[i] = (int) (100
                    * ((totalBill * 0.7 / totalConsumption * flats[i]) + ((totalBill * 0.3) / (flats.length)))) / 100.0;
        return invoceValues;
    }

    public static void printBills(double[] bills) {
        for (int i = 0; i < bills.length; i++)
            System.out.println("Flat #" + (i + 1) + ": " + bills[i]);
    }
}