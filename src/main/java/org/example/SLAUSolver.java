package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SLAUSolver {

    public OutputManager outputManager = new OutputManager();
    private int detMul = 1;

    public boolean solveSLAU(Matrix matrix) {
        int n = matrix.getN();
        double[][] a = matrix.getA();
        double[] b = matrix.getB();
        outputManager.writeCurrentMatrix(a, b);
        for (int i = 0; i < n - 1; i++) {
            outputManager.writeInterceptor();
            if (a[i][i] == 0) {
                outputManager.writeNeedForSwap();
                boolean swapRes = swapTwoLines(i, a, b);
                if (!swapRes) {
                    outputManager.writeSwapError();
                    outputManager.writeDeterminant(0);
                    return false;
                }
                detMul *= -1;
                outputManager.writeSwapSuccess();
                outputManager.writeCurrentMatrix(a, b);
                outputManager.writeInterceptor();
            }
            outputManager.writeNeedToSubFromLinesBelow(i);
            for (int j = i + 1; j < n; j++) {
                double multiplier = a[j][i] / a[i][i];
                for (int l = 0; l < n; l++) {
                    a[j][l] -= a[i][l] * multiplier;
                }
                b[j] -= b[i] * multiplier;
            }
            outputManager.writeCurrentMatrix(a, b);
        }
        outputManager.writeInterceptor();
        outputManager.writeTriangleMatrixSuccess();
        double detA = calcDeterminant(a);
        outputManager.writeDeterminant(detA);
        if (detA != 0) {
            ArrayList<Double> reversedResultList = new ArrayList<>();
            for (int i = a.length - 1; i >= 0; i--) {
                double currentResult = b[i];
                for (int j = 0; j < reversedResultList.size(); j++) {
                    currentResult -= reversedResultList.get(j) * a[i][a.length - j - 1];
                }
                currentResult = currentResult / a[i][i];
                reversedResultList.add(currentResult);
            }
            outputManager.writeFinalResult(reversedResultList);
            Collections.reverse(reversedResultList);
            outputManager.writeResiduals(getResiduals(matrix.getA(), matrix.getB(),reversedResultList));
        }
        return true;
    }

    private boolean swapTwoLines(int line, double[][] a, double[] b) {
        int cur_line = line;
        while (cur_line < a.length && a[cur_line][line] == 0) {
            cur_line++;
        }
        if (cur_line == a.length) {
            return false;
        }
        double[] y = a[cur_line];
        a[cur_line] = a[line];
        a[line] = y;

        double x = b[cur_line];
        b[cur_line] = b[line];
        b[line] = x;
        return true;
    }

    private double calcDeterminant(double[][] a) {
        double det = 1;
        for (int i = 0; i < a.length; i++) {
            det *= a[i][i];
        }
        return det * detMul;
    }

    public double[] getResiduals(double[][] a, double[] b, List<Double> roots) {
        double[] results = new double[a.length];
        for (int i = 0; i < a.length; i++) {
            double curVal = b[i];
            for (int j = 0; j < a.length; j++) {
                curVal -= a[i][j] * roots.get(j);
            }
            results[i] = Math.abs(curVal);
        }
        return results;
    }
}
