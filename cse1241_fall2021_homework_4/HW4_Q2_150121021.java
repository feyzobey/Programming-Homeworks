
/**
 * This program will determine whether or not it is valid per the Luhn formula.
 * The Luhn algorithm is a simple checksum formula used to validate a variety of
 * identification numbers,
 * such as credit card numbers. The task is to check if a given string is valid.
 * 
 * Name and Surname : Feyzullah Asıllıoğlu
 * Student ID : 150121021
 */
import java.util.Scanner;

public class HW4_Q2_150121021 {
    public static void main(String[] aInrgs) {
        var input = new Scanner(System.in);
        var cardNo = input.nextLine();
        input.close();
        var DNumber = "";
        for (int i = 0; i < cardNo.length(); i++) {
            if ((cardNo.charAt(i) < ' ')
                    || (cardNo.charAt(i) > ' ' && cardNo.charAt(i) < '0')
                    || (cardNo.charAt(i) > '9')) {
                System.out.println("Invalid Input !");
                return;
            }
            if (cardNo.charAt(i) != ' ') {
                DNumber += cardNo.charAt(i);
            }
        }
        var lastDigit = DNumber.length() - 1;
        var masked = "";
        var LNumber = "";
        if (DNumber.length() % 2 == 0) {
            for (int i = lastDigit; i >= 0; i--) {
                if (i % 2 == 0) {
                    masked += '_';
                    LNumber += DNumber.charAt(lastDigit - i);

                } else {
                    masked += DNumber.charAt(lastDigit - i);
                    LNumber += (2 * (DNumber.charAt(lastDigit - i) - '0')) >= 9
                            ? (2 * (DNumber.charAt(lastDigit - i) - '0')) - 9
                            : 2 * (DNumber.charAt(lastDigit - i) - '0');
                }
            }
        } else {
            for (int j = 0; j < DNumber.length(); j++) {
                if (j % 2 == 0) {
                    masked += '_';
                    LNumber += DNumber.charAt(j);
                } else {
                    masked += DNumber.charAt(j);
                    LNumber += (2 * (DNumber.charAt(j) - '0')) >= 9
                            ? (2 * (DNumber.charAt(j) - '0')) - 9
                            : 2 * (DNumber.charAt(j) - '0');
                }
            }
        }
        System.out.println("DNumber: " + masked);
        System.out.println("LNumber: " + LNumber);
        if (validateNumber(LNumber))
            System.out.println("Number is Valid");
        else
            System.out.println("Number is Invalid");
    }

    public static boolean validateNumber(String number) {
        var ValueOfNumbers = new int[number.length()];
        var totalOfNumbers = 0;
        for (int i = 0; i < ValueOfNumbers.length; i++) {
            ValueOfNumbers[i] = number.charAt(i) - '0';
            totalOfNumbers += ValueOfNumbers[i];
        }
        if (totalOfNumbers % 20 == 0)
            return true;
        else
            return false;
    }
}
