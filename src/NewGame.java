import java.io.*;
import java.util.Scanner;


public class NewGame {

    int inventoryIndex = 0;
    int level = 1;
    int xp = 0;
    String[] inventory = new String[10];
    //stores a variety of variables.

    public void runNewGame() {
        Scanner input = new Scanner(System.in);

        boolean exit = false;

        while (!exit) {
            System.out.println("\033[h\033[2J");
            System.out.flush();
            //clears the compiler ready for the next input.

            System.out.println("You are level " + level + " with " + xp + " xp" +
                    "\nPlease choose an option:" +
                    "\n1. Forest" +
                    "\n2. Beach" +
                    "\n3. Mine" +
                    "\n4. Make a Fire" +
                    "\n5. Inventory" +
                    "\n6. Save Game" +
                    "\n7. Load Game" +
                    "\n8. Return to Menu.");
            //main game has begun for the user to begin playing they can choose various menu options to begin playing.

            int choice = input.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("You enter the forest and find WOOD." +
                            "\nWould you ike to take the WOOD(1) or leave it(2)?");
                    int wood = input.nextInt();
                    //asks the user if they would like to take the item or not.

                    if (wood == 1) {
                        System.out.println("You have acquired WOOD!" +
                                "\nYou return to your homestead.");
                        inventory[inventoryIndex++] = "WOOD";
                        xp += 10;
                        //if they take the WOOD it will add 'WOOD' to the array and award the user 10 experience(xp) points.

                    } else {
                        System.out.println("You leave the WOOD and return to your homestead.");
                    }
                    break;
                    //if they choose to leave the item, it will bring them back to the new game menu without any change.

                case 2:
                    System.out.println("You go to the beach and find STONE" +
                            "\nWould you like to take the STONE(1) or leave it(2)?");
                    int stone = input.nextInt();
                    //asks the user if they would like to take the item or not.

                    if (stone == 1) {
                        System.out.println("You have acquired STONE" +
                                "\nYou return to your homestead.");
                        inventory[inventoryIndex++] = "STONE";
                        xp += 10;
                        //if they take the STONE it will add 'STONE' to the array and award the user 10 experience(xp) points.

                    } else {
                        System.out.println("You leave the STONE and return to your homestead.");
                    }
                    break;
                //if they choose to leave the item, it will bring them back to the new game menu without any change.

                case 3:
                    System.out.println("You go to the mine and find COAL near the entrance." +
                            "\nDo you take the COAL(1) or leave it(2)?");
                    int coal = input.nextInt();
                    //asks the user if they would like to take the item or not.

                    if (coal == 1) {
                        System.out.println("You have acquired COAL" +
                                "\nYou return to your homestead.");
                        inventory[inventoryIndex++] = "COAL";
                        xp += 10;
                        //if they take the COAL it will add 'COAL' to the array and award the user 10 experience(xp) points.

                    } else {
                        System.out.println("You leave the STONE and return to your homestead.");
                    }
                    break;
                //if they choose to leave the item, it will bring them back to the new game menu without any change.

                case 4:
                    System.out.println("You attempt to make a FIRE.");
                    if (inventoryContains(inventory, "WOOD") && inventoryContains(inventory, "COAL")) {
                        System.out.println("You successfully get a fire going.");
                        removeFromInventory(inventory, "WOOD");
                        removeFromInventory(inventory, "COAL");
                        inventory[inventoryIndex++] = "FIRE";
                        xp += 60;
                    /*
                    here when a user selects 'make fire' the programme will search the inventory array for the required items.
                    if WOOD and COAL have both been acquired it will remove these items from the array and add FIRE.
                    and present a message saying the user has been successful.
                    lastly adding 60 experience points(xp) to their level.
                    if collected all the items and made this combination the user will go up in level.
                     */

                    } else {
                        System.out.println("WOOD and COAL are required to make fire." +
                                "\nReturn and look for these components.");
                    }
                    break;
                    /*
                    should the user not have the required materials it will declare what the user needs and return them to the menu
                    options in search of these.
                    */

                case 5:
                    System.out.println("This is " + Main.getPlayerName() + "'s inventory");
                    for (String item : inventory) {
                        if (item != null) {
                            System.out.println("- " + item);
                        }
                    }
                    break;
                    //if the user requests it they can see what is already in their inventory.

                case 6:
                    saveGame();
                    break;
                    //calls the save game method

                case 7:
                    loadGame();
                    break;
                    //calls the load game method

                case 8:
                    MainMenu();
                    return;
                    //calls the main menu to exit out of the game.

                default:
                    System.out.println("Invalid choice. Please enter a number between 1 & 8");
                    //safeguarding for the user to enter a correct input.
            }

            if (xp >= level * 100) {
                xp -= level * 100;
                level++;
                //totals the amount of xp a user has. once total xp reaches 100 they will go up a level.
                //xp will also be reset to zero.
                System.out.println("Congratulations! " + Main.getPlayerName() + " has leveled up!" +
                        "\nThey are now level" + level + "!");
                //when the user reaches a new level the game will send a message declaring their new level.
            }
        }
    }

    public static boolean inventoryContains(String[] inventory, String item) {
        for (String i : inventory) {
            if (i != null && i.equals(item)) {
                return true;
            }
            //used in case 4 line 97 to check if the user has collected the correct materials in the array.
        }
        return false;
    }

    public static void removeFromInventory(String[] inventory, String item) {
        for (int i = 0; i < inventory.length; i++) {
            if (inventory[i] != null && inventory[i].equals(item)) {
                inventory[i] = null;
                return;
            }
            //a method that will remove the item when a combination has been successful.
        }
    }

    public void saveGame() {
        try {
            FileWriter writer = new FileWriter("saveGame.txt");
            //should save a .txt file in the same location the program is being stored.

            writer.write(inventoryIndex + "\n");
            writer.write(level + "\n");
            writer.write(xp + "\n");
            //takes the information stored and writes them to a .txt file saved under a name "saveGame.txt".


            for (String item: inventory) {
                writer.write(item + "\n");
            }
            //writes the information found in the inventory array.

            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving game.");
        }
        //will notify the user for any problems saving the game.
    }

    public void loadGame() {
        try {
            File file = new File ("saveGame.txt");
            //will find the .txt file in the same place it was saved.
            Scanner scanner = new Scanner(file);

            inventoryIndex = scanner.nextInt();
            level = scanner.nextInt();
            xp = scanner.nextInt();
            //individually reads from the .txt files and stores to the corresponding integers.


            inventory = new String[inventoryIndex];
            for (int i=0; i < inventoryIndex; i++) {
                inventory[i] = scanner.nextLine();
            }
            //does the same for the inventory array.

            scanner.close();
            System.out.println("\nGame loaded.");
            //message display when successful.
        } catch (IOException e) {
            System.out.println("\nNo save game found.");
        }
        //message display when no .txt file exists or exists but is empty.
    }

    public void MainMenu() {
        mainMenu mm = new mainMenu();
        mm.RunMainMenu();
    }
    //send the user back to main menu where they can enter the help or exit the game safely.
}