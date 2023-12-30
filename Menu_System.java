package ASM2_DSA_NDT;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Menu_System {
    public static void main(String[] args) {
        long startTime = System.nanoTime();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        boolean exit = false;
        Main_Menu PC1 = new Main_Menu("PC_1");
        Main_Menu PC2 = new Main_Menu("PC_2");
        Main_Menu PC3 = new Main_Menu("PC_3");

        while (!exit) {
            System.out.println("____________________________________");
            System.out.println("|          Choose System           |");
            System.out.println("|-------------- Menu --------------|");
            System.out.println("|             1. PC_1              |");
            System.out.println("|             2. PC_2              |");
            System.out.println("|             3. PC_3              |");
            System.out.println("|             4. Exit              |");
            System.out.println("|__________________________________|");
            System.out.print("Enter your choice: ");

            int choice;
            try {
                // Read the user's choice from the console
                choice = Integer.parseInt(reader.readLine());
            } catch (IOException | NumberFormatException ex) {
                System.out.println("Invalid input. Please enter a valid choice.");
                continue;
            }

            switch (choice) {
                case 1:
                    PC1.main_Menu(PC2, PC3);
                    break;
                case 2:
                    PC2.main_Menu(PC1, PC3);
                    break;
                case 3:
                    PC3.main_Menu(PC1, PC2);
                    break;
                case 4:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
        System.out.println("Exiting the program.");
        long endTime = System.nanoTime();
        long elapsedTime = endTime - startTime;
        System.out.println("Elapsed time: " + elapsedTime + " nanoseconds.");
    }
}
