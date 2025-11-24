package driver;

import java.util.Scanner;

public abstract class Driver {
    protected Scanner scanner;

    public Driver(Scanner scanner) {
        this.scanner = scanner;
    }

    public abstract void menu();
}
