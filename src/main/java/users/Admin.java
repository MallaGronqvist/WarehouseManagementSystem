package users;


public class Admin extends User{

    @Override
    public void processMenuOption(int selectedOption) throws IndexOutOfBoundsException {
        switch (selectedOption) {
            case 1 -> super.viewProductPool();
            case 2 -> navigateToMainMenu();
            //     case 2 -> // user.doSomething()
            //     case 3 -> // user.findYourShoe()
            default -> throw new IndexOutOfBoundsException();
        }
    }
}
