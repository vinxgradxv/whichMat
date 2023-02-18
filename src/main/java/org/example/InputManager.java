package org.example;

import java.io.File;
import java.util.Scanner;

public class InputManager {

    Scanner scanner = new Scanner(System.in);
    Scanner fileReader;
    public InputManager() {
    }

    private boolean askIfFromFile() {
        while (true) {
            System.out.println("Вы хотите ввести данные через консоль или прочитать из файла ? (c/f)");
            String answer = scanner.nextLine();
            if (!answer.equals("f") && !answer.equals("c")) {
                System.out.println("Пожалуйста, введите один из предложенных вариантов");
            } else return answer.equals("f");
        }
    }

    private Matrix getMatrixFromConsole() {
            try {
                System.out.println("Введите размерность матрицы (n):");
                Integer n = scanner.nextInt();
                if (n > 20) throw new Exception();
                double[][] a = new double[n][n];
                double[] b = new double[n];
                System.out.println("Введите матрицу, размерностью " + n);
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        a[i][j] = scanner.nextDouble();
                    }
                    b[i] = scanner.nextDouble();
                }
                return new Matrix(n, a, b);
            }
            catch (Exception e) {
                System.out.println("Произошла ошибка, попробуйте запустить программу заново");
                return null;
            }
    }

    private Matrix getMatrixFromFile() {
        while (true) {
            System.out.println("Введите имя файла");
            String fileName = scanner.nextLine();
            File inputFile = new File(fileName);
            Integer n;
            double[][] a;
            double[] b;
            try {
                fileReader = new Scanner(inputFile);
                n = fileReader.nextInt();
                if (n > 20) throw new Exception();
                System.out.println("Размерность матрицы (n): " + n);
                a = new double[n][n];
                b = new double[n];
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        a[i][j] = fileReader.nextDouble();
                    }
                    b[i] = fileReader.nextDouble();
                }
                return new Matrix(n, a, b);
            } catch (Exception e) {
                System.out.println("Не получается прочитать данные из файла, попробуйте еще раз");
            }
        }
    }

    public Matrix getMatrixFromUser() {
        if (!askIfFromFile()) {
            return getMatrixFromConsole();
        }
        else {
            return getMatrixFromFile();
        }
    }
}
