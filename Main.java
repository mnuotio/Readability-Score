package readability;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ProgramLogic core = new ProgramLogic(args[0]);
        UserInterface ui = new UserInterface(core, scanner);
        ui.start();
    }

}