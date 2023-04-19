import java.util.Scanner;

public class mainMenu {
    public void RunMainMenu() {
        Scanner input = new Scanner(System.in);

        System.out.println("1. Enter game" +
                "\n2. Help" +
                "\n3. Exit");
        //Displays the options for main menu.

        boolean exit = false;
        //Allows for safe exit of the program.

        while (!exit) {
            System.out.print("\033[h\033[2J");
            System.out.flush();
            //Clears screen

            int choice = input.nextInt();
            //Will store the next integer from the user for a menu option.

            switch (choice) {
                case 1:
                    NewGame();
                    break;
                    //Calls the new game method.

                case 2:
                    Help();
                    break;
                    //Calls the help method.

                case 3:
                    System.out.println("Thanks for playing!");
                    exit = true;
                    System.exit(0);
                    break;
                    //Exits the programme safely.

                default:
                    System.out.println("Invalid choice. Please choose a number between 1 & 3.");
                    //Appears when a user enters something outside the given options.
            }
        }
    }

    private void NewGame() {
        NewGame exp = new NewGame();
        exp.runNewGame();
    }
    //When option 1 is chosen the NewGame class will be run.

    private void Help() {
        Help hlp = new Help();
        hlp.runHelp();
    }
    //when option 2 is chosen the Help  class will be run.
}