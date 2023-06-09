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
                    "\n1. Explorer" +
                    "\n2. Science Mode" +
                    "\n3. Inventory" +
                    "\n4. Save Game" +
                    "\n5. Load Game" +
                    "\n6. Return to Menu.");
            //main game has begun for the user to begin playing they can choose various menu options to begin playing.

            int choice = input.nextInt();

            switch (choice) {
                case 1:
                    Explorer();
                    break;
                    //Start of the game, were the user chooses different locations find materials.

                case 2:
                    ScienceMode();
                    break;
                    //Displays a recipe list of the items the user can craft at the cost of the collected ones.

                case 3:
                    System.out.println("This is " + Main.getPlayerName() + "'s inventory");
                    for (String item : inventory) {
                        if (item != null) {
                            System.out.println("- " + item);
                        }
                    }
                    break;
                    //Displays the users inventory. Will be empty if the user is yet to collect anything.

                case 4:
                    saveGame();
                    break;
                //calls the save game method

                case 5:
                    loadGame();
                    break;
                //calls the load game method

                case 6:
                    MainMenu();
                    return;
                //calls the main menu to exit out of the game.

                default:
                    System.out.println("Invalid choice. Please enter a number between 1 & 6");
                    //safeguarding for the user to enter a correct input.
            }

            if (xp >= level * 100) {
                xp -= level * 100;
                level++;
                //totals the amount of xp a user has. once total xp reaches 100 they will go up a level.
                //xp will also be reset to zero.
                System.out.println("Congratulations! " + Main.getPlayerName() + " has leveled up!" +
                        "\nThey are now level " + level + "!");
                //when the user reaches a new level the game will send a message congratulating them.
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

            for (String item : inventory) {
                writer.write(item + "\n");
            }
            //writes the information found in the inventory array.
            System.out.println("Game Saved successfully");
            //alerts the user the game state was saved.

            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving game.");
        }
        //will notify the user for any problems saving the game.
    }

    public void loadGame() {
        try {
            File file = new File("saveGame.txt");
            //will find the .txt file in the same place it was saved.
            Scanner scanner = new Scanner(file);

            inventoryIndex = scanner.nextInt();
            level = scanner.nextInt();
            xp = scanner.nextInt();
            //individually reads from the .txt files and stores to the corresponding integers.


            inventory = new String[inventoryIndex];
            for (int i = 0; i < inventoryIndex; i++) {
                inventory[i] = scanner.nextLine();
            }
            //does the same for the inventory array.

            scanner.close();
            System.out.println("\nGame loaded.");
            //message display when successful.
        } catch (IOException e) {
            System.out.println("\nNo save game found.");
        }
        //message display when a .txt file doesn't exist or exists but is empty.
    }

    public void MainMenu() {
        mainMenu mm = new mainMenu();
        mm.RunMainMenu();
    }
    //send the user back to main menu where they can enter the help or exit the game safely.

    public void Explorer() {
        System.out.println("Where would you like to go?" +
                "\n1. Forest" +
                "\n2. Beach" +
                "\n3. Mine" +
                "\n4. Return");
        //beginning of the exploring mechanic, user will find crafting materials here.

        Scanner input = new Scanner(System.in);
        int choice = input.nextInt();
        //waits for user input to correspond with location option.

        boolean exit = false;
        while (!exit) {
            System.out.println("\033[h\033[2j");
            System.out.flush();

            switch (choice) {
                case 1:
                    forest();
                    break;
                    //calls the forest method.

                case 2:
                    beach();
                    break;
                    //calls the beach method.

                case 3:
                    mine();
                    break;
                    //calls the mine method.

                case 4:
                    runNewGame();
                    //returns the user to the previous menu option.
            }
        }
    }

    public void forest() {
        //Option 1 forest start.
        System.out.println("You enter the forest and find WOOD." +
                "\nWould you ike to take the WOOD(1) or leave it(2)?");
        Scanner input = new Scanner(System.in);
        int wood = input.nextInt();
        //asks the user if they would like to take the item or not.

        if (wood == 1) {
            System.out.println("You have acquired WOOD!" +
                    "\nYou return to your homestead.");
            inventory[inventoryIndex++] = "WOOD";
            xp += 10;
            //if they take the WOOD it will add 'WOOD' to the array and award the user 10 experience points(xp).

        } else {
            System.out.println("You leave the WOOD and return to your homestead.");
        }
        Explorer();
        //if they choose to leave the item, it will bring them back to the new game menu without any change.
    }

    public void beach() {
        //Option 2 beach start.
        System.out.println("You go to the beach and find STONE." +
                "\nWould you like to take the STONE(1) or leave it(2)?");
        Scanner input = new Scanner(System.in);
        int stone = input.nextInt();
        //asks the user if they would like to take the item or not.

        if (stone == 1) {
            System.out.println("You have acquired STONE." +
                    "\nYou return to your homestead.");
            inventory[inventoryIndex++] = "STONE";
            xp += 10;
            //if they take the STONE it will add 'STONE' to the array and award the user 10 experience(xp) points.

        } else {
            System.out.println("You leave the STONE and return to your homestead.");
        }
        Explorer();
        //if they choose to leave the item, it will bring them back to the new game menu without any change.
    }

    public void mine() {
        //Option 3 mine start.
        System.out.println("You go to the mine and find COAL near the entrance." +
                "\nDo you take the COAL(1) or leave it(2)?");
        Scanner input = new Scanner(System.in);
        int coal = input.nextInt();
        //asks the user if they would like to take the item or not.

        if (coal == 1) {
            System.out.println("You have acquired COAL");
            inventory[inventoryIndex++] = "COAL";
            xp += 10;
            //if they take the COAL it will add 'COAL' to the array and award the user 10 experience(xp) points.

            System.out.println("would you like to go deeper into the mine?" +
                    "\n1. Enter Mineshaft" +
                    "\n2. Leave");
            //the user has the option to further search a location (in this case the mine) for richer materials.

            int choice = input.nextInt();
            switch (choice) {
                case 1:
                    mineshaft();
                    break;
                    //starts the mineshaft method if the user has chosen option 1.

                case 2:
                    Explorer();
                    break;
                    //else they will be sent back to the previous menu.
            }
        } else {
            System.out.println("You leave the STONE.");
            //the user opts to not take a material.
            System.out.println("Would you like to explore the Mine further(1) or leave(2)?");
            //but it still presented with the option to go to the mine.
            input = new Scanner(System.in);

            int further = input.nextInt();
            if (further == 1) {
                mineshaft();
                //starts the mineshaft method, same as if they had chosen to take materials.
            } else {
                System.out.println("You leave and return to your homestead.");
            }
            //if they reject all the options they return to the exploration menu without any new items.
        }
        Explorer();
        //if they choose to leave the item, it will bring them back to the new game menu without any change.
    }

    public void mineshaft() {
        //only starts when the user searches the mine but will still be presented with the option to explore here no matter their decision-making.
        System.out.println("You venture deeper into the Mineshaft and find IRON." +
                "\nDo you dig out the IRON(1) or leave it(2)?");
        Scanner input = new Scanner(System.in);
        int iron = input.nextInt();
        //asks the user if they would like to take the item or not.


        if (iron == 1) {
            System.out.println("You attempt to dig out the IRON.");
            if (inventoryContains(inventory, "PICK")) {
                System.out.println("You successfully dig out the IRON at the expense of your PICK.");
                removeFromInventory(inventory, "PICK");
                inventory[inventoryIndex++] = "IRON";
                xp += 60;
            /*
            Here when a user selects 'take iron'. The program will search the inventory array for the required items.
            If a PICK has been made (obtainable from the 'Science Mode') it will remove the item from the array and add IRON,
            presenting a message saying the user has been successful.
            Lastly adding 60 experience points(xp) to their level.
            */

            } else {
                System.out.println("A PICK is required to dig out the IRON." +
                        "\nReturn and look for these components.");
                //If the user hasn't yet made a PICK it will notify the user of this and reject any further progress until the requirement is met.
            }

        } else {
            System.out.println("You leave the iron and return to your homestead.");
            //Displayed when the user opts to not take IRON.
        }

        Explorer();
        //if they choose to leave the item, it will bring them back to the new game menu without any change.
    }

    public void ScienceMode() {
        System.out.println("This is your workshop!" +
                "\nHere you can combine different items to create new ones" +
                "\nand help you on your journey!" +
                "\nwhat would you like to make?" +
                "\n1. Fire" +
                "\n2. Pick" +
                "\n3. Return");
        //Here a user can combine items from using 'Explorers mode' to construct a new item.

        Scanner input = new Scanner(System.in);
        int choice = input.nextInt();

        boolean exit = false;
        while (!exit) {
            System.out.println("\033[h\033[2j");
            System.out.flush();

            switch (choice) {
                case 1:
                    Fire();
                    break;
                    //Calls the FIRE method.

                case 2:
                    Pick();
                    break;
                    //Calls the PICK method.

                case 3:
                    runNewGame();
                    //Otherwise the user can return to the previous menu if they don't want to or are not ready for this step.
            }
        }
    }

    public void Fire() {
        //Option 2 combine start.
        System.out.println("You attempt to make a FIRE.");
        if (inventoryContains(inventory, "WOOD") && inventoryContains(inventory, "COAL")) {
            System.out.println("You successfully get a fire going.");
            removeFromInventory(inventory, "WOOD");
            removeFromInventory(inventory, "COAL");
            inventory[inventoryIndex++] = "FIRE";
            xp += 60;
        /*
        here when a user selects 'make FIRE' the program will search the inventory array for the required items.
        if WOOD and COAL have both been acquired it will remove these items from the array and add FIRE presenting
        a message to the user they are successful.
        Lastly adding 60 experience points(xp) to their level.
        If the xp has met 100 the user will go up a level.
        */

        } else {
            System.out.println("WOOD and COAL are required to make FIRE." +
                    "\nReturn and look for these components.");
        }
        ScienceMode();
        /*
        should the user not have the required materials it will declare what the user needs and return them to the menu
        options in search of these.
        */
    }

    public void Pick() {
        //Option 4 combine start.
        System.out.println("You attempt to make a PICK.");
        if (inventoryContains(inventory, "WOOD") && inventoryContains(inventory, "STONE")) {
            System.out.println("You successfully make a PICK!");
            removeFromInventory(inventory, "WOOD");
            removeFromInventory(inventory, "STONE");
            inventory[inventoryIndex++] = "PICK";
            xp += 60;
        /*
        When a user selects 'Make PICK' the program will search the inventory array for the required items.
        If WOOD and STONE have both been acquired it will remove these items from the array and add 'PICK'
        presenting a message saying the user has been successful.
        Lastly adding 60 experience points(xp) to their level.
        If 'xp' goes above 100 the level will go up by 1.
        */
        } else {
            System.out.println("WOOD and STONE are required to make a PICK." +
                    "\nReturn and look for these components.");
        }
        ScienceMode();
        /*
        should the user not have the required materials it will declare what the user needs and return them to the menu
        options in search of these.
        */
    }
}