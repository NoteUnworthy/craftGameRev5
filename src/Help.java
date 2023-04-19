public class Help {
    public void runHelp() {
        System.out.println("Welcome to ByProduct!" +
                "\nThis is a small, simple, crafting game with an exploration element, in a text based format." +
                "\nNavigate through the menus with the prompted inputs and see what you can find and combine." +
                "\n\nYou can use '!exit' to leave the game at any time but," +
                "\nit is recommend saving your game and exiting through the main menu." +
                "\tThank you for your time." +
                "\n\n");
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
