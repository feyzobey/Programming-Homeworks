// In this question, I wrote a program for drawing a triangular shape, iterating the previous ones
// Name and Surname : Feyzullah Asıllıoğlu
// Student ID : 150121021

#include <stdio.h>

const int ROW = 32;
const int COL = 63;

void initializeMatrix(char matrix[ROW][COL]);
void iterateMatrix(char matrix[ROW][COL], int startRow, int startCol, int heightOfTriangles, int iterationNumber);
void arrangeTriangle(char matrix[ROW][COL], int startRow, int startCol, int heightOfTriangles);
void printShape(char matrix[ROW][COL]);

int main(void)
{
    unsigned int iterationNumber;
    printf("Please enter a number of iteration: ");
    scanf("%u", &iterationNumber);
    // taking input from the user until the user enters a iteration number less than 5.
    while (iterationNumber >= 5)
    {
        printf("Number must be less than 5, enter again: ");
        scanf("%u", &iterationNumber);
    }

    char matrix[ROW][COL];

    initializeMatrix(matrix);                                // initialize phase
    iterateMatrix(matrix, 0, COL / 2, ROW, iterationNumber); // iteration phase
    printShape(matrix);                                      // print phase
    return 0;
}
// initializing our matrix with full of '_'.
void initializeMatrix(char matrix[ROW][COL])
{
    for (int i = 0; i < ROW; i++)
    {
        for (int j = 0; j < COL; j++)
        {
            matrix[i][j] = '_';
        }
    }
}
// this function iterates the matrix to construct the shape according to the instructions given.
void iterateMatrix(char matrix[ROW][COL], int startRow, int startCol, int heightOfTriangles, int iterationNumber)
{
    if (iterationNumber == 0)
    {
        arrangeTriangle(matrix, startRow, startCol, heightOfTriangles);
    }
    else
    {
        iterateMatrix(matrix, startRow, startCol, heightOfTriangles / 2, iterationNumber - 1);
        iterateMatrix(matrix, startRow + heightOfTriangles / 2, startCol - heightOfTriangles / 2, heightOfTriangles / 2, iterationNumber - 1);
        iterateMatrix(matrix, startRow + heightOfTriangles / 2, startCol + heightOfTriangles / 2, heightOfTriangles / 2, iterationNumber - 1);
    }
}
// this function prints the final arranged matrix to the console.
void printShape(char matrix[ROW][COL])
{
    for (int i = 0; i < ROW; i++)
    {
        for (int j = 0; j < COL; j++)
        {
            printf("%c", matrix[i][j]);
        }
        printf("\n");
    }
}
// this function arranges the matrix one by one according to the instructions given.
void arrangeTriangle(char matrix[ROW][COL], int startRow, int startCol, int heightOfTriangles)
{
    for (int i = 0; i < heightOfTriangles; i++)
    {
        for (int j = startCol - i; j <= startCol + i; j++)
        {
            matrix[startRow + i][j] = '1';
        }
    }
}