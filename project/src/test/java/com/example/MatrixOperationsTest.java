package com.example;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class MatrixOperationsTest {

    @Test
    public void testAddThrowsIfDifferentSizes() {
        int height = 10;
        int width = 15;
        double[][] a = new double[height + 1][width];
        double[][] b = new double[height][width];
        assertThrows(IllegalArgumentException.class, () -> {
            MatrixOperations.add(a, b);
        });
    }

    @Test
    public void testSubtractThrowsIfDifferentSizes() {
        int height = 10;
        int width = 15;
        double[][] a = new double[height + 1][width];
        double[][] b = new double[height][width];
        assertThrows(IllegalArgumentException.class, () -> {
            MatrixOperations.subtract(a, b);
        });
    }

    @Test
    public void testAddThrowsIfMatrixIsntRectangular() {
        int height = 10;
        int width = 15;
        double[][] a = new double[height][width];
        double[][] b = new double[height][width];
        a[height - 1] = new double[width + 1];
        assertThrows(IllegalArgumentException.class, () -> {
            MatrixOperations.add(a, b);
        });
    }

    @Test
    public void testSubtractThrowsIfMatrixIsntRectangular() {
        int height = 10;
        int width = 15;
        double[][] a = new double[height][width];
        double[][] b = new double[height][width];
        a[height - 1] = new double[width + 1];
        assertThrows(IllegalArgumentException.class, () -> {
            MatrixOperations.subtract(a, b);
        });
    }

    @Test
    public void testMultiplyThrowsIfMatrixIsntRectangular() {
        int height = 10;
        int width = height;
        double[][] a = new double[height][width];
        double[][] b = new double[height][width];
        a[height - 1] = new double[width + 1];
        assertThrows(IllegalArgumentException.class, () -> {
            MatrixOperations.multiply(a, b);
        });
    }

    @Test
    public void testMultiplyThrowsIfUnableToMultiply() {
        int aWidth = 10;
        int aHeight = 15;
        int bHeight = aWidth + 1;
        int bWidth = 20;
        double[][] a = new double[aHeight][aWidth];
        double[][] b = new double[bHeight][bWidth];
        assertThrows(IllegalArgumentException.class, () -> {
            MatrixOperations.multiply(a, b);
        });
    }

    @ParameterizedTest
    @ValueSource(ints = { 4, 32, 256, 2048 })
    public void testAnySquareMatricesCanBeAddedCorrectly(int size) {
        double[][] a = new double[size][size];
        double[][] b = new double[size][size];
        double[][] expected = new double[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                a[i][j] = i * size + j;
                b[i][j] = size * size - 1 - a[i][j];
            }
        }
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                expected[i][j] = a[i][j] + b[i][j];
            }
        }

        double[][] actual = MatrixOperations.add(a, b);

        for (int i = 0; i < size; i++) {
            assertArrayEquals(expected[i], actual[i], 0.00001);
        }
    }

    @ParameterizedTest
    @ValueSource(strings = { "/mult/bigMatrix.txt", "/mult/commonMatrix.txt", "/mult/tinyMatrix.txt" })
    public void testMultiplyMatricesAnySize(String file) {
        double[][] a = null;
        double[][] b = null;
        double[][] expected = null;
        int size = 0;
        try (InputStream inputStream = getClass().getResourceAsStream(file);
                Scanner scanner = new Scanner(inputStream)) {
            size = scanner.nextInt();
            a = new double[size][size];
            b = new double[size][size];
            expected = new double[size][size];
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    a[i][j] = scanner.nextDouble();
                }
            }
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    b[i][j] = scanner.nextDouble();
                }
            }
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    expected[i][j] = scanner.nextDouble();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        double[][] actual = MatrixOperations.multiply(a, b);
        for (int i = 0; i < size; i++) {
            assertArrayEquals(expected[i], actual[i], 0.000000000001);
        }
    }

    @ParameterizedTest
    @ValueSource(strings = { "/sum/largeMatrix.txt", "/sum/mediumMatrix.txt", "/sum/tinyMatrix.txt" })
    public void testSumMatricesAnySize(String file) {
        double[][] a = null;
        double[][] b = null;
        double[][] expected = null;
        int size = 0;
        try (InputStream inputStream = getClass().getResourceAsStream(file);
                Scanner scanner = new Scanner(inputStream)) {
            size = scanner.nextInt();
            a = new double[size][size];
            b = new double[size][size];
            expected = new double[size][size];
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    a[i][j] = scanner.nextDouble();
                }
            }
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    b[i][j] = scanner.nextDouble();
                }
            }
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    expected[i][j] = scanner.nextDouble();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        double[][] actual = MatrixOperations.add(a, b);
        for (int i = 0; i < size; i++) {
            assertArrayEquals(expected[i], actual[i], 0.000000000001);
        }
    }

    @ParameterizedTest
    @ValueSource(strings = { "/diff/largeMatrix.txt", "/diff/mediumMatrix.txt", "/diff/tinyMatrix.txt" })
    public void testDiffMatricesAnySize(String file) {
        double[][] a = null;
        double[][] b = null;
        double[][] expected = null;
        int size = 0;
        try (InputStream inputStream = getClass().getResourceAsStream(file);
                Scanner scanner = new Scanner(inputStream)) {
            size = scanner.nextInt();
            a = new double[size][size];
            b = new double[size][size];
            expected = new double[size][size];
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    a[i][j] = scanner.nextDouble();
                }
            }
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    b[i][j] = scanner.nextDouble();
                }
            }
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    expected[i][j] = scanner.nextDouble();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        double[][] actual = MatrixOperations.subtract(a, b);
        for (int i = 0; i < size; i++) {
            assertArrayEquals(expected[i], actual[i], 0.000000000001);
        }
    }
}
