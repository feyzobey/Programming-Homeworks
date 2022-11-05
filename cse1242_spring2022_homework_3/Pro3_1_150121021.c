// In this question, I wrote a program to find out the number of ears of rabbits standing in a line
// Name and Surname : Feyzullah Asıllıoğlu
// Student ID : 150121021

#include <stdio.h>
int bunnyEars(int);
int main(int argc, char const *argv[])
{
    unsigned int numberOfLines, numberOfEars;

    printf("Please enter the number of lines (n=): ");
    scanf("%u", &numberOfLines);

    numberOfEars = bunnyEars(numberOfLines);

    printf("%d", numberOfEars);
    return 0;
}
int bunnyEars(int numberOfLines)
{
    int counter = 0;
    if (numberOfLines == 0)
    {
        return 0;
    }
    if (numberOfLines % 2 == 0)
    {
        counter += 3;
    }
    else
    {
        counter += 2;
    }
    return counter + bunnyEars(numberOfLines - 1);
}