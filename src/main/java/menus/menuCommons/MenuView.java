package menuCommons;

public abstract class MenuView {
    public void printInvalidOption() {
        System.out.println("You entered an invalid option.");
    }

    public void requestUserInput() {
        System.out.print("Enter your choice and press enter: ");
    }
}
