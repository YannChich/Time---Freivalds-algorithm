import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int n = 3;
        int[][] A = generateRandomMatrix(n);
        int[][] B = generateRandomMatrix(n);
        int[][] C = multiplyMatrices(A, B);

        /* Perturb C to test error detection
        C[0][0] += 1;*/

        /* Print matrices
        System.out.println("Matrix A:");
        printMatrix(A);
        System.out.println("Matrix B:");
        printMatrix(B);
        System.out.println("Matrix C:");
        printMatrix(C);*/

        // Measure the time taken by the Freivalds algorithm
        long startTimeFreivalds = System.nanoTime();
        boolean resultFreivalds = freivalds(A, B, C, 10);
        long endTimeFreivalds = System.nanoTime();

        // Measure the time taken by the classic verification algorithm
        long startTimeClassic = System.nanoTime();
        boolean resultClassic = classicVerification(A, B, C);
        long endTimeClassic = System.nanoTime();

        System.out.println("Freivalds' algorithm: C is the product of A and B? " + (resultFreivalds ? "Yes" : "No"));
        System.out.println("Time taken by Freivalds' algorithm: " + (endTimeFreivalds - startTimeFreivalds) + " nanoseconds");

        System.out.println("Classic verification: C is the product of A and B? " + (resultClassic ? "Yes" : "No"));
        System.out.println("Time taken by classic verification: " + (endTimeClassic - startTimeClassic) + " nanoseconds");
    }

    public static int[][] generateRandomMatrix(int n) {
        Random rand = new Random();
        int[][] matrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = rand.nextInt(10); // Generate random integers between 0 and 9
            }
        }
        return matrix;
    }

    public static int[][] multiplyMatrices(int[][] A, int[][] B) {
        int n = A.length;
        int[][] result = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    result[i][j] += A[i][k] * B[k][j];
                }
            }
        }
        return result;
    }

    public static boolean freivalds(int[][] A, int[][] B, int[][] C, int k) {
        int n = A.length;
        Random rand = new Random();

        for (int t = 0; t < k; t++) {
            // Step 1: Choose a random vector r
            int[] r = new int[n];
            for (int i = 0; i < n; i++) {
                r[i] = rand.nextInt(2); // Random integers in {0, 1}
            }

            // Step 2: Compute Br = B * r
            int[] Br = new int[n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    Br[i] += B[i][j] * r[j];
                }
            }

            // Step 3: Compute ABr = A * (B * r)
            int[] ABr = new int[n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    ABr[i] += A[i][j] * Br[j];
                }
            }

            // Step 4: Compute Cr = C * r
            int[] Cr = new int[n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    Cr[i] += C[i][j] * r[j];
                }
            }

            // Step 5: Compare ABr and Cr
            for (int i = 0; i < n; i++) {
                if (ABr[i] != Cr[i]) {
                    return false; // The matrices do not match
                }
            }
        }
        return true; // The matrices match
    }

    public static boolean classicVerification(int[][] A, int[][] B, int[][] C) {
        int n = A.length;
        int[][] product = multiplyMatrices(A, B);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (product[i][j] != C[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int element : row) {
                System.out.print(element + " ");
            }
            System.out.println();
        }
    }
}
