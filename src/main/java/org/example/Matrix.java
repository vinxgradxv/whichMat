package org.example;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Matrix {
    private double[][] a;
    private double[] b;
    private int n;
    public Matrix(int n, double[][] a, double[] b) {
        this.n = n;
        this.a = a;
        this.b = b;
    }
}
