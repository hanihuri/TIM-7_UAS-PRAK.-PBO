package driver;

import java.util.Scanner;

/**
 * Kelas Driver adalah kelas abstrak yang menjadi induk
 * bagi semua driver (AdminDriver dan CustomerDriver) 
 * pada sistem.
 */

public abstract class Driver {
    protected Scanner scanner;

    public Driver(Scanner scanner) {
        this.scanner = scanner;
    }

    public abstract void menu();
}
