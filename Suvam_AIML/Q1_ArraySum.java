/*
1. Given square matrix of dimension nxn where n>1, write a program to find the sum of values of even and odd values of each row of the matrix.
Input: array [4][4] = {{1, 3, 4, 11},
{2, 4, 4, 6},
{3, 13, 23, 3},
{1, 4, 3, 4}};
 */
package Suvam_AIML;

public class Q1_ArraySum {
    public static void main(String[] args) {
        int[][] matrix = {
                { 1, 3, 4, 11 },
                { 2, 4, 4, 6 },
                { 3, 13, 23, 3 },
                { 1, 4, 3, 4 }
        };

        for (int i = 0; i < matrix.length; i++) {
            int evenSum = 0;
            int oddSum = 0;
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] % 2 == 0) {
                    evenSum += matrix[i][j];
                } else {
                    oddSum += matrix[i][j];
                }
            }
            System.out.println("Row " + (i + 1) + ": Even sum = " + evenSum + ", Odd sum = " + oddSum);
        }
    }
}

/*
 * Answer:
 * Row 1: Even sum = 4, Odd sum = 15
 * Row 2: Even sum = 16, Odd sum = 0
 * Row 3: Even sum = 0, Odd sum = 42
 * Row 4: Even sum = 8, Odd sum = 4
 */