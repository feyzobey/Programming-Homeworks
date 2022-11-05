
/*
This program draws the coordinate system and some geometric shapes on the console screen as described below:
1. Line
2. Rectangle
3. Triangle
4. Parabola
5. Circle

Name and Surname : Feyzullah Asıllıoğlu
Student ID : 150121021 
*/
import java.util.Scanner;

public class HW3_150121021 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        do {
            int selectedMenu = SelectionMenu(input);
            switch (selectedMenu) {
                case 1:
                    System.out.println("Line formula is y=ax+b");
                    System.out.print("Please enter the coefficents a and b: ");
                    int a1 = input.nextInt();
                    int b1 = input.nextInt();
                    Line(a1, b1);
                    break;
                case 2:
                    System.out.println("For triangle, we need the coordinates of the points for three vertices.");
                    System.out.print("Please enter the coordinates of 3 vertices a, b, c, d, e, f: ");
                    double a = input.nextDouble();
                    double b = input.nextDouble();
                    double c = input.nextDouble();
                    double d = input.nextDouble();
                    double e = input.nextDouble();
                    double f = input.nextDouble();
                    Triangle(a, b, c, d, e, f);
                    break;
                case 3:
                    System.out.println("For rectangle, we need the coordinates of the points for three vertices.");
                    System.out.print("Please enter the coordinates of 3 vertices a, b, c, d, e, f: ");
                    int i = input.nextInt();
                    int j = input.nextInt();
                    int k = input.nextInt();
                    int l = input.nextInt();
                    int m = input.nextInt();
                    int n = input.nextInt();
                    Rectangle(i, j, k, l, m, n);
                    break;
                case 4:
                    System.out.println("Parabola formula is y = ax^2 + bx + c");
                    System.out.print("Please enter the coefficients a, b and c: ");
                    int a2 = input.nextInt();
                    int b2 = input.nextInt();
                    int c2 = input.nextInt();
                    Parabola(a2, b2, c2);
                    break;
                case 5:
                    System.out.println("Circle formula is (x-a)^2 + (y-b)^2 = r^2");
                    System.out.print("Please enter the coordinates of the center (a,b) and the radius: ");
                    double center_a = input.nextDouble();
                    double center_b = input.nextDouble();
                    double r = input.nextDouble();
                    Circle(center_a, center_b, r);
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Invalid input");
                    return;
            }
            System.out.print("Do you wanna continue (y/n): ");
            char ans = input.next().charAt(0);
            if (ans == 'n')
                break;
            else if (ans == 'y')
                continue;
        } while (true);
    }

    public static int SelectionMenu(Scanner input) {
        System.out.println("Which shape would you like to draw");
        System.out.println("1-Line");
        System.out.println("2-Triangle");
        System.out.println("3-Rectangle");
        System.out.println("4-Parabola");
        System.out.println("5-Circle");
        System.out.println("6-Exit");
        int ans = input.nextInt();
        return ans;
    }

    public static void Line(int a, int b) {
        for (int y = 15; y >= -14; y--) {
            for (int x = -14; x <= 15; x++) {
                if ((a * x + b) == y)
                    System.out.print("*");
                else if (y == 0) {
                    if (x == 0)
                        System.out.print("|");
                    else if (x == 15)
                        System.out.print("x");
                    else
                        System.out.print("-");
                } else if (x == 0) {
                    if (y == 15)
                        System.out.print("y");
                    else
                        System.out.print("|");
                } else
                    System.out.print(" ");
            }
            System.out.println("");
        }
        System.out.println("\n");
    }

    public static void Triangle(double a, double b, double c, double d, double e, double f) {
        for (int y = 15; y >= -14; y--) {
            for (int x = -14; x <= 15; x++) {
                if (x >= a && x <= c && y >= b && y <= d
                        && (Math.round(((d - b) * x)) == Math.round((y - d) * (c - a))))
                    System.out.print("*");
                else if (x >= c && x <= a && y >= b && y <= d
                        && (Math.round(((d - b) * x)) == -Math.round((y - d) * (a - c))))
                    System.out.print("*");
                else if (x >= a && x <= c && y >= d && y <= b
                        && (Math.round(((b - d) * x)) == Math.round((y - d) * (c - a))))
                    System.out.print("*");
                else if (x >= c && x <= a && y >= d && y <= b
                        && (Math.round(((b - d) * x)) == -Math.round((y - d) * (a - c))))
                    System.out.print("*");

                else if (x <= e && x >= c && y >= f && y <= d
                        && (Math.round((d - f) * x) == -Math.round((y - d) * (e - c))))
                    System.out.print("*");
                else if (x <= c && x >= e && y >= f && y <= d
                        && (Math.round((d - f) * x) == Math.round((y - d) * (c - e))))
                    System.out.print("*");
                else if (x <= e && x >= c && y >= d && y <= f
                        && (Math.round((f - d) * x) == -Math.round((y - d) * (e - c))))
                    System.out.print("*");
                else if (x <= c && x >= e && y >= d && y <= f
                        && (Math.round((f - d) * x) == Math.round((y - d) * (c - e))))
                    System.out.print("*");

                else if (x >= a && x <= e && y >= f && y <= b
                        && ((Math.round((b - f) / (e - a)) * x + Math.round(b - ((b - f) / (e - a) * a))) == y))
                    System.out.print("*");
                else if (x >= e && x <= a && y >= f && y <= b
                        && ((Math.round((b - f) / (a - e)) * x + Math.round(b - ((b - f) / (a - e) * e))) == y))
                    System.out.print("*");
                else if (x >= a && x <= e && y >= b && y <= f
                        && ((Math.round((f - b) / (e - a)) * x + Math.round(f - ((f - b) / (e - a) * a))) == y))
                    System.out.print("*");
                else if (x >= e && x <= a && y >= b && y <= f
                        && ((Math.round((f - b) / (a - e)) * x + Math.round(f - ((f - b) / (a - e) * e))) == y))
                    System.out.print("*");

                else if (y == 0) {
                    if (x == 0)
                        System.out.print("|");
                    else if (x == 15)
                        System.out.print("x");
                    else
                        System.out.print("-");
                } else if (x == 0) {
                    if (y == 15)
                        System.out.print("y");
                    else
                        System.out.print("|");
                } else
                    System.out.print(" ");
            }
            System.out.println("");
        }
        System.out.println("\n");
    }

    public static void Rectangle(int a, int b, int c, int d, int e, int f) {
        int g, h;
        if (e == c)
            g = a;
        else if (a == c)
            g = e;
        else
            g = c;
        if (b == f)
            h = d;
        else if (d == f)
            h = b;
        else
            h = f;
        if ((-a == c) && (b == f)) {
            int temp = e;
            e = c;
            c = temp;
            temp = f;
            f = d;
            d = temp;
        } else if ((-c == e) && (d == f)) {
            int temp = e;
            e = a;
            a = temp;
            temp = f;
            f = b;
            b = temp;
        }
        if ((Math.abs(h - d) != Math.abs(f - b)) || Math.abs(c - a) != Math.abs(g - e)) {
            System.out.println("Not rectangle");
            return;
        }
        for (int y = 15; y >= -14; y--) {
            for (int x = -14; x <= 15; x++) {
                if (((y == b) && (x <= c) && (x >= a))
                        || ((y == f) && (x <= (-Math.abs(e) + Math.abs(c - a))) && (x >= -Math.abs(e))))
                    System.out.print("*");
                else if (((y == b) && (x <= a) && (x >= c))
                        || ((y == f) && (x <= (-Math.abs(e) + Math.abs(c - a))) && (x >= -Math.abs(e))))
                    System.out.print("*");
                else if (((x == a) && (y <= b) && (y >= f)) || ((x == c) && (y >= f) && (y <= b)))
                    System.out.print("*");
                else if (((x == a) && (y <= f) && (y >= b)) || ((x == c) && (y <= f) && (y >= b)))
                    System.out.print("*");
                else if (y == 0) {
                    if (x == 0)
                        System.out.print("|");
                    else if (x == 15)
                        System.out.print("x");
                    else
                        System.out.print("-");
                } else if (x == 0) {
                    if (y == 15)
                        System.out.print("y");
                    else
                        System.out.print("|");
                } else
                    System.out.print(" ");
            }
            System.out.println("");
        }
        System.out.println("\n");
    }

    public static void Parabola(int a, int b, int c) {
        for (int y = 15; y >= -14; y--) {
            for (int x = -14; x <= 15; x++) {
                if ((x * x * a + b * x + c) == y)
                    System.out.print("*");
                else if (y == 0) {
                    if (x == 0)
                        System.out.print("|");
                    else if (x == 15)
                        System.out.print("x");
                    else
                        System.out.print("-");
                } else if (x == 0) {
                    if (y == 15)
                        System.out.print("y");
                    else
                        System.out.print("|");
                } else
                    System.out.print(" ");
            }
            System.out.println("");
        }
        System.out.println("\n");
    }

    public static void Circle(double a, double b, double r) {
        for (int y = 15; y >= -14; y--) {
            for (int x = -14; x <= 15; x++) {
                if (Math.pow((x - a), 2) + Math.pow((y - b), 2) == Math.pow(r, 2))
                    System.out.print("*");
                else if (y == 0) {
                    if (x == 0)
                        System.out.print("|");
                    else if (x == 15)
                        System.out.print("x");
                    else
                        System.out.print("-");
                } else if (x == 0) {
                    if (y == 15)
                        System.out.print("y");
                    else
                        System.out.print("|");
                } else
                    System.out.print(" ");
            }
            System.out.println("");
        }
        System.out.println("\n");
    }
}
