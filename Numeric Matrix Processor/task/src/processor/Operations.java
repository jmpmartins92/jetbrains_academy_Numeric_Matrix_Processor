package processor;

public class Operations {

    public boolean checkSizesSum(Matrix matrixA, Matrix matrixB) {
        int numRowsA = matrixA.getNumRows();
        int numColsA = matrixA.getNumCols();

        int numRowsB = matrixB.getNumRows();
        int numColsB = matrixB.getNumCols();

        if (numRowsA == numRowsB && numColsA == numColsB) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkSizesMult(Matrix matrixA, Matrix matrixB) {
        int numColsA = matrixA.getNumCols();
        int numRowsB = matrixB.getNumRows();


        if (numColsA == numRowsB) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkSizeSquare(Matrix matrix) {
        int numCols = matrix.getNumCols();
        int numRows = matrix.getNumRows();


        if (numCols == numRows) {
            return true;
        } else {
            return false;
        }
    }

    public Matrix sumMatrix(Matrix matrixA, Matrix matrixB) {

        int numRows = matrixA.getNumRows();
        int numCols = matrixA.getNumCols();

        Matrix matrixSum = new Matrix(numRows, numCols);

        for (int rowIndex = 0; rowIndex < numRows; rowIndex++) {
            for (int colIndex = 0; colIndex < numCols; colIndex++) {
                matrixSum.setValue(rowIndex, colIndex, matrixA.getValue(rowIndex, colIndex) + matrixB.getValue(rowIndex, colIndex));
            }
        }
        return matrixSum;
    }

    public Matrix multiplyConst(Matrix matrix, double constant) {

        int numRows = matrix.getNumRows();
        int numCols = matrix.getNumCols();

        Matrix matrixMultConst = new Matrix(numRows, numCols);

        for (int rowIndex = 0; rowIndex < numRows; rowIndex++) {
            for (int colIndex = 0; colIndex < numCols; colIndex++) {
                matrixMultConst.setValue(rowIndex, colIndex, matrix.getValue(rowIndex, colIndex) * constant);
            }
        }
        return matrixMultConst;
    }

    public Matrix multiplyMatrix(Matrix matrixA, Matrix matrixB) {

        int numRows = matrixA.getNumRows();
        int numCols = matrixB.getNumCols();

        Matrix matrixMultiply = new Matrix(numRows, numCols);

        for (int rowIndex = 0; rowIndex < numRows; rowIndex++) {
            for (int colIndex = 0; colIndex < numCols; colIndex++) {
                matrixMultiply.setValue(rowIndex, colIndex, dotMultiplication(matrixA, matrixB, rowIndex, colIndex));
            }
        }
        return matrixMultiply;
    }

    public double dotMultiplication(Matrix matrixA, Matrix matrixB, int rowIndex, int colIndex) {
        double result = 0;
        double[] rowA = new double[matrixA.getNumCols()];
        double[] colB = new double[matrixB.getNumRows()];

        for (int indexArray = 0; indexArray < rowA.length; indexArray++) {
            rowA[indexArray] = matrixA.getValue(rowIndex, indexArray);
            colB[indexArray] = matrixB.getValue(indexArray, colIndex);
            result += rowA[indexArray] * colB[indexArray];
        }
        return result;
    }

    public Matrix transposeMainDiagonal(Matrix matrix) {
        int numRows = matrix.getNumCols();
        int numCols = matrix.getNumRows();

        Matrix matrixTransposed = new Matrix(numRows, numCols);

        for (int rowIndex = 0; rowIndex < numRows; rowIndex++) {
            for (int colIndex = 0; colIndex < numCols; colIndex++) {
                matrixTransposed.setValue(rowIndex, colIndex, matrix.getValue(colIndex, rowIndex));
            }
        }
        return matrixTransposed;
    }

    public Matrix transposeSideDiagonal(Matrix matrix) {
        int numRows = matrix.getNumCols();
        int numCols = matrix.getNumRows();

        Matrix matrixTransposed = new Matrix(numRows, numCols);

        for (int rowIndex = 0; rowIndex < numRows; rowIndex++) {
            for (int colIndex = 0; colIndex < numCols; colIndex++) {
                matrixTransposed.setValue(rowIndex, colIndex, matrix.getValue(numRows - colIndex - 1, numCols - rowIndex - 1));
            }
        }
        return matrixTransposed;
    }

    public Matrix transposeVerticalLine(Matrix matrix) {
        int numRows = matrix.getNumRows();
        int numCols = matrix.getNumCols();

        Matrix matrixTransposed = new Matrix(numRows, numCols);

        for (int rowIndex = 0; rowIndex < numRows; rowIndex++) {
            for (int colIndex = 0; colIndex < numCols; colIndex++) {
                matrixTransposed.setValue(rowIndex, colIndex, matrix.getValue(rowIndex, numCols - colIndex - 1));
            }
        }
        return matrixTransposed;
    }

    public Matrix transposeHorizontalLine(Matrix matrix) {
        int numRows = matrix.getNumRows();
        int numCols = matrix.getNumCols();

        Matrix matrixTransposed = new Matrix(numRows, numCols);

        for (int rowIndex = 0; rowIndex < numRows; rowIndex++) {
            for (int colIndex = 0; colIndex < numCols; colIndex++) {
                matrixTransposed.setValue(rowIndex, colIndex, matrix.getValue(numRows - rowIndex - 1, colIndex));
            }
        }
        return matrixTransposed;
    }

    public double calculateDet(Matrix matrix) {
        double det = 0;

        if (matrix.getNumRows() == 1) {
            return matrix.getValue(0, 0);
        }

        for (int IndexFirstRow = 0; IndexFirstRow < matrix.getNumRows(); IndexFirstRow++) {

            det += matrix.getValue(0, IndexFirstRow) * calculateDet(getCofactor(matrix,0, IndexFirstRow));
        }
        return det;
    }

    public Matrix getCofactor(Matrix matrix, int rowIndex, int colIndex) {
        Matrix tempMatrix = new Matrix(matrix.getNumRows() - 1, matrix.getNumCols() - 1);
        int tempMatrixRow = 0;
        int tempMatrixCol = 0;

        for (int tempRowIndex = 0; tempRowIndex < matrix.getNumRows(); tempRowIndex++) {
            for (int tempColIndex = 0; tempColIndex < matrix.getNumCols(); tempColIndex++) {
                if (tempRowIndex != rowIndex && tempColIndex != colIndex) {
                    tempMatrix.setValue(tempMatrixRow, tempMatrixCol, matrix.getValue(tempRowIndex, tempColIndex) * getCofactorSign(tempRowIndex, tempColIndex));
                    tempMatrixCol++;
                    if (tempMatrixCol == matrix.getNumCols() - 1) {
                        tempMatrixCol = 0;
                        tempMatrixRow++;
                    }
                }
            }
        }
        return tempMatrix;
    }

    public int getCofactorSign(int rowIndex, int colIndex) {
        return (int) Math.pow(-1, 2 + rowIndex + colIndex);
    }

    public Matrix inverseMatrix(Matrix matrix) {
        Matrix matrixCofactors = new Matrix(matrix.getNumRows(), matrix.getNumCols());

        for (int adjugateRowIndex = 0; adjugateRowIndex < matrix.getNumRows(); adjugateRowIndex++) {
            for (int adjugateColIndex = 0; adjugateColIndex < matrix.getNumCols(); adjugateColIndex++) {
                matrixCofactors.setValue(adjugateRowIndex, adjugateColIndex, calculateDet(getCofactor(matrix, adjugateRowIndex, adjugateColIndex)));
            }
        }
        Matrix inverseMatrix = multiplyConst(transposeMainDiagonal(matrixCofactors), 1 / calculateDet(matrix));
        return inverseMatrix;
    }
}
