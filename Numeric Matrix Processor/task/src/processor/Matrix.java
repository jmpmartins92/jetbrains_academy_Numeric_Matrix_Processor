package processor;

import java.util.Scanner;

public class Matrix {

    Scanner scanner = new Scanner(System.in);

    int numRows;
    int numCols;
    double[][] matrix;

    public Matrix(int numRows, int numCols) {
        this.numRows = numRows;
        this.numCols = numCols;
        this.matrix = new double[numRows][numCols];
    }


    public void readMatrix() {
        this.numRows = getNumRows();
        this.numCols = getNumCols();

        for (int rowIndex = 0; rowIndex < numRows; rowIndex++) {
                for (int colIndex = 0; colIndex < numCols; colIndex++) {
                    double read = scanner.nextDouble();
                    setValue(rowIndex, colIndex, read);
                }
        }
    }

    public void printMatrix() {

        this.numRows = getNumRows();
        this.numCols = getNumCols();

        for (int rowIndex = 0; rowIndex < numRows; rowIndex++) {
            for (int colIndex = 0; colIndex < numCols; colIndex++) {
                System.out.print(getValue(rowIndex, colIndex) + " ");
            }
            System.out.println();
        }
    }


    public int getNumRows() {
        return this.numRows;
    }

    public int getNumCols() {
        return this.numCols;
    }

    public double getValue(int rowIndex, int colIndex) {
        return this.matrix[rowIndex][colIndex];
    }

    public void setValue(int rowIndex, int colIndex, double value) {
        this.matrix[rowIndex][colIndex] = value;
    }

}
