package org.example;

public class Main {
    private static InputManager inputManager = new InputManager();
    private static OutputManager outputManager = new OutputManager();
    private static SLAUSolver slauSolver = new SLAUSolver();
    private static Matrix matrix;
    public static void main(String[] args) {
        matrix = inputManager.getMatrixFromUser();
        if (matrix != null) {
            if (slauSolver.solveSLAU(matrix)) {
                outputManager.writeFinalSuccessMessage();
            } else {
                outputManager.writeFinalErrorMessage();
            }
        }
    }
}