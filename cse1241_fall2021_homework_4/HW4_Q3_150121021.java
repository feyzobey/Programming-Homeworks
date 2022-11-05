
/**
 * This Program takes an input letter and outputs it in a diamond shape. 
 * Given a letter, it prints a diamond starting with 'A', with the supplied letter at the widest point.
 * 
 * Name and Surname : Feyzullah Asıllıoğlu
 * Student ID : 150121021
 */
import java.util.Scanner;

public class HW4_Q3_150121021 {
    public static void main(String[] args) {
        var input = new Scanner(System.in);
        System.out.print("Enter a letter: ");
        var phrase = input.next().toUpperCase();
        input.close();
        if (phrase.length() > 1) {
            System.out.println("Invalid input");
            return;
        }
        var letter = phrase.charAt(0);
        // Check [A-Z]
        if ((int) letter < 65 || (int) letter > 90) {
            System.out.println("Invalid input");
            return;
        }
        var diamondShape = constructDiamond(letter);
        printDiamond(diamondShape);
    }

    public static char[][] constructDiamond(char letter) {
        final char startChar = 'A', indentChar = '.';
        var size = (letter - startChar + 1) * 2;
        var middleOfShape = size / 2 - 1;
        var diamondShape = new char[size - 1][size - 1];
        var printLetter = startChar;
        for (int i = 0, indent = middleOfShape; i < diamondShape.length; i++, indent--) {
            // if (i > middleOfShape) {
            // printLetter -= 2;
            // }

            // var printLetter = (char)(i <= middleOfShape
            // ? startChar + i
            // : startChar + middleOfShape + indent);
            for (int j = 0; j < diamondShape[i].length; j++) {
                if (j == Math.abs(indent) || Math.abs(indent) == diamondShape[i].length - 1 - j) {
                    diamondShape[i][j] = printLetter;
                } else {
                    diamondShape[i][j] = indentChar;
                }
            }
            if (i < middleOfShape)
                printLetter++;
            else
                printLetter--;
        }
        return diamondShape;
    }

    public static void printDiamond(char[][] diamond) {
        for (var x : diamond) {
            for (var y : x) {
                System.out.print(y);
            }
            System.out.println("");
        }
    }
}
