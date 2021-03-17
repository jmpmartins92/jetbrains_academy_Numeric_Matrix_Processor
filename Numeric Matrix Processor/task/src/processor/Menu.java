package processor;

import java.util.Scanner;

public class Menu {

    Scanner scanner = new Scanner(System.in);

    public int printIniMenu() {
        System.out.println("1. Add matrices");
        System.out.println("2. Multiply matrix by a constant");
        System.out.println("3. Multiply matrices");
        System.out.println("4. Transpose matrix");
        System.out.println("5. Calculate a determinant");
        System.out.println("6. Inverse matrix");
        System.out.println("0. Exit");
        System.out.println("Your choice: ");
        int chosenOption = -1;
        chosenOption = scanner.nextInt();
        while (chosenOption < 0 || chosenOption > 6) {
            System.out.println("Option not valid, please choose between available options");
            chosenOption = scanner.nextInt();
        }
        return chosenOption;
    }

    public Matrix requestMatrix(String matrixNum) {
        System.out.printf("\nEnter size of %smatrix:\n", matrixNum);
        int numRows = scanner.nextInt();
        int numCols = scanner.nextInt();
        Matrix matrix = new Matrix(numRows, numCols);
        System.out.printf("\nEnter %s matrix:\n", matrixNum);
        matrix.readMatrix();
        return matrix;
    }

    public double requestConstant() {
        System.out.println("Enter constant:");
        return scanner.nextDouble();
    }

    public void exitOption() {
        System.exit(0);
    }

    public void additionOption() {
        Operations operations = new Operations();
        Matrix matrixA = requestMatrix("first ");
        Matrix matrixB = requestMatrix("second ");
        if (operations.checkSizesSum(matrixA, matrixB)) {
            System.out.println();
            System.out.println("The result is:");
            Matrix matrixC = operations.sumMatrix(matrixA, matrixB);
            matrixC.printMatrix();
            System.out.println();
        } else {
            System.out.println("The operation cannot be performed");
        }
    }

    public void multConstOption() {
        Operations operations = new Operations();
        Matrix matrix = requestMatrix("");
        Double constant = requestConstant();
        System.out.println();
        System.out.println("The result is:");
        Matrix matrixResult = operations.multiplyConst(matrix, constant);
        matrixResult.printMatrix();
        System.out.println();
    }

    public void multiplicationOption() {
        Operations operations = new Operations();
        Matrix matrixA = requestMatrix("first ");
        Matrix matrixB = requestMatrix("second ");
        System.out.println();
        if (operations.checkSizesMult(matrixA, matrixB)) {
            System.out.println("The result is:");
            Matrix matrixC = operations.multiplyMatrix(matrixA, matrixB);
            matrixC.printMatrix();
            System.out.println();
        } else {
            System.out.println("The operation cannot be performed");
        }
    }

    public void transposeOption() {
        int transposeType = transposeTypeRequest();
        Matrix matrix = requestMatrix("");
        System.out.println();
        System.out.println("The result is:");
        Matrix matrixResult = transposeTypeSelection(matrix, transposeType);
        matrixResult.printMatrix();
        System.out.println();
    }

    public int transposeTypeRequest() {
        System.out.println();
        System.out.println("1. Main diagonal");
        System.out.println("2. Side diagonal");
        System.out.println("3. Vertical line");
        System.out.println("4. Horizontal line");
        System.out.println("Your choice");
        int chosenOption = -1;
        chosenOption = scanner.nextInt();
        while (chosenOption < 0 || chosenOption > 4) {
            System.out.println("Option not valid, please choose between available options");
            chosenOption = scanner.nextInt();
        }
        return chosenOption;
    }

    public Matrix transposeTypeSelection(Matrix matrix, int option) {
        Operations operations = new Operations();
        switch (option) {
            case 0:
                exitOption();
            case 1:
                return operations.transposeMainDiagonal(matrix);
            case 2:
                return operations.transposeSideDiagonal(matrix);
            case 3:
                return operations.transposeVerticalLine(matrix);
            case 4:
                return operations.transposeHorizontalLine(matrix);
            default:
                System.out.println("Input Error");
                return matrix;
        }
    }

    public void calculateDetOption() {
        Operations operations = new Operations();
        Matrix matrix = requestMatrix("");
        if (operations.checkSizeSquare(matrix)) {
            System.out.println();
            System.out.println("The result is:");
            double det = operations.calculateDet(matrix);
            System.out.println(det);
            System.out.println();
        } else {
            System.out.println("Error: matrix has to be square, try again");
        }
    }

    public void inverseMatrixOption() {
        Operations operations = new Operations();
        Matrix matrix = requestMatrix("");

        System.out.println();
        if (operations.checkSizeSquare(matrix)) {
            System.out.println();
            System.out.println("The result is:");
            Matrix matrixResult = operations.inverseMatrix(matrix);
            matrixResult.printMatrix();
            System.out.println();
        } else {
            System.out.println("Error: matrix has to be square, try again");
        }
    }


    public void optionSelection(int option) {
        switch (option) {
            case 0:
                exitOption();
            case 1:
                additionOption();
                break;
            case 2:
                multConstOption();
                break;
            case 3:
                multiplicationOption();
                break;
            case 4:
                transposeOption();
                break;
            case 5:
                calculateDetOption();
                break;
            case 6: inverseMatrixOption();
                break;
            default:
                System.out.println("Input Error");
                break;

        }
    }

}
