package org.example;

import java.util.List;

public class OutputManager {

    public void writeFinalErrorMessage() {
        System.out.println("К сожалению не удалось решить систему линейных алгебраических уравнений методом Гаусса");
    }

    public void  writeFinalSuccessMessage() {
        System.out.println("Система линейных алгебраических уравнений успешно решена методом Гаусса");
    }

    public void writeSwapError() {
        System.out.println("Не удалось поменять местами строки матрицы так, чтобы на главной диагонали матрицы не было нулей");
    }

    public void writeNeedForSwap() {
        System.out.println("Необходимо поменять местами строки таблицы для продолжения решения методом Гаусса");
    }

    public void writeCurrentMatrix(double[][] a, double[] b) {
        System.out.println("Сейчас матрица выглядит таким образом:");
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length; j++) {
                int howManySpaces = 10 - String.format("%.2f", a[i][j]).length();
                System.out.format("%.2f", a[i][j]);
                for (int l = 0; l < howManySpaces; l++) {
                    System.out.print(" ");
                }
            }
            System.out.format("%.2f", b[i]);
            System.out.println();
        }
    }

    public void writeSwapSuccess() {
        System.out.println("Две строки успешно поменялись местами");
    }

    public void writeNeedToSubFromLinesBelow(int index)  {
        System.out.println("Необходимо вычесть строку с индексом " + index + "из всех строк ниже так, чтобы все элементы в "
                + index + " столбце в них обернулись в ноль");
    }

    public void writeTriangleMatrixSuccess() {
        System.out.println("Матрица успешна приведена к треугольному виду");
    }

    public void writeFinalResult(List<Double> results) {
        System.out.println("Решение:");
        for (int i = results.size() - 1; i >= 0; i--) {
            System.out.format("x" + (results.size() - i) + " = %.3f\n", results.get(i));
        }
    }

    public void writeResiduals(double[] discrepancy) {
        System.out.println("Вектор невязок:");
        for (int i = 0; i < discrepancy.length; i++) {
            System.out.format("r" + i + " = %.18f\n",discrepancy[i]);
        }
    }

    public void writeDeterminant(double detA) {
        System.out.format("Детерминант матрицы = %.6f\n", detA);
    }

    public void writeInterceptor() {
        System.out.println("|---------------------------|");
    }


}
