public class Handbook {
    public void runHandbook() {
        System.out.println("""
                Collectable items;
                WOOD
                STONE
                COAL
                IRON

                Craft-able items;
                FIRE
                PICK""");

        System.out.println("More to come.");
        MainMenu();
        /*I wanted to include a handbook for the user to see what can be available to them without giving too much away
        to ruin the experience of experimenting and exploring.*/
    }

    public void MainMenu() {
        mainMenu mm = new mainMenu();
        mm.RunMainMenu();
    }
    //sends the user back to the Main Menu.

}