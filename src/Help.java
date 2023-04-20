public class Help {
    public void runHelp() {
        System.out.println("""
                Welcome to ByProduct!
                This is a small, simple, crafting game with an exploration element, in a text based format.
                Navigate through the menus with the prompted inputs and see what you can find and combine.

                You can use '!exit' to leave the game at any time but, it is recommend saving your game
                and exiting through the main menu.
                Combining items will consume your inventory.
                Lastly please refer to the handbook to see more information regarding the items that are
                discoverable in the game.""");
        //Prints out some information about the game.
        MainMenu();
        //Calls the MainMenu function to return out of the help page.
    }
    public void MainMenu() {
        mainMenu mm = new mainMenu();
        mm.RunMainMenu();
    }
    // Runs the main menu class and returns the user back to the start.
}
