package com.example;

public class MatrixOperations {

    private static boolean checkWidth(double[][] a, int width) {
        for (int i = 0; i < a.length; i++) {
            if (a[i].length != width) {
                return false;
            }
        }
        return true;
    }

    private static boolean checkHeight(double[][] a, int height) {
        return a.length == height;
    }

    private static boolean checkSize(double[][] a, int height, int width) {
        return checkHeight(a, height) && checkWidth(a, width);
    }

    public static double[][] add(double[][] a, double[][] b) {
        int height = a.length;
        int width = a[0].length;
        if (!checkSize(a, height, width) || !checkSize(b, height, width)) {
            throw new IllegalArgumentException();
        }
        double[][] result = new double[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                result[i][j] = a[i][j] + b[i][j];
            }
        }
        return result;
    }

    public static double[][] subtract(double[][] a, double[][] b) {
        int height = a.length;
        int width = a[0].length;
        double[][] result = new double[height][width];
        if (!checkSize(a, height, width) || !checkSize(b, height, width)) {
            throw new IllegalArgumentException();
        }
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                result[i][j] = a[i][j] - b[i][j];
            }
        }
        return result;
    }

    public static double[][] multiply(double[][] a, double[][] b) {
        int aWidth = a[0].length;
        int aHeight = a.length;
        int bWidth = b[0].length;
        int bHeight = b.length;
        if (aWidth != bHeight || !checkSize(a, aHeight, aWidth) || !checkSize(b, bHeight, bWidth)) {
            throw new IllegalArgumentException();
        }
        int height = aHeight;
        int width = bWidth;
        double[][] result = new double[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                result[i][j] = 0.0;
                for (int k = 0; k < bHeight; k++) {
                    result[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        // for (int i = 0; i < result.length; i++) {
        //     for (int j = 0; j < result[0].length; j++) {
        //         System.out.print(result[i][j] + ' ');
        //     }
        //     System.out.println();
        // }
        return result;
    }

    public static void main(String[] args) {
        System.out.println("Hello World!");
    }
}
