/*2130231 - Group A
MOD003484 - Software Principles*/

import java.util.Scanner;


    public class Main {
        //This is the beginning of the game and will start here everytime the code is complied.
        public static String playerName;
        //begins with storing a string the user enters for their chosen name.

        public static void main(String[] args) {
            Scanner input = new Scanner(System.in);

            System.out.println("Welcome to ByProduct." +
                    "\nPlease enter your characters name:   ");

            setPlayerName(input.nextLine());
            //user enters their name and is stored in the 'main.playerName' function.
            //this allows for the name to be used throughout the program when required.

            System.out.println("Hello," + getPlayerName() + "!" +
                    "\nWhere would you like to start?");

            mainMenu mm = new mainMenu();
            mm.RunMainMenu();
            //send the user to the mainMenu class.
        }
        public static String getPlayerName(){
            return playerName;
        }
        //allows a code to refer to the user when called.
        public static void setPlayerName(String playerName) {
            Main.playerName = playerName;
        }
        //sets and stores the player name when entered at the start.
    }
