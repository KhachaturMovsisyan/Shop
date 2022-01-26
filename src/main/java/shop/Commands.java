package shop;

public interface Commands {

    String EXIT = "0";
    String LOGIN = "1";
    String REGISTRATION = "2";

    String BACK = "0";
    String EDIT_USER = "1";
    String ADD_BALANCE = "2";
    String BUY_PRODUCT = "3";

    String DELETE_USER = "1";
    String ADD_PRODUCT = "2";
    String UP_DATE_SHOP = "3";
    String INBOX = "4";
    String MANAGE_SHOP="4";


     static void printCommand() {
        System.out.println("Please input " + EXIT + " for EXIT");
        System.out.println("Please input " + LOGIN + " for LOGIN");
        System.out.println("Please input " + REGISTRATION + " for REGISTRATION");


    }

     static void userCommands() {
        System.out.println("Please Input " + BACK + " for BACK");
        System.out.println("Please Input " + EDIT_USER + " for edit your account");
        System.out.println("Please Input " + ADD_BALANCE + " for ADD BALANCE your account");
        System.out.println("Please Input " + BUY_PRODUCT + " for BUY PRODUCT");
        System.out.println("Please Input " + INBOX + " for chat");

    }

    static void adminCommands() {
        System.out.println("Please Input " + BACK + " for BACK");
        System.out.println("Please Input " + DELETE_USER + " for delete user");
        System.out.println("Please Input " + ADD_PRODUCT + " for ADD Product");
        System.out.println("Please Input " + UP_DATE_SHOP + " for UPDATE");
        System.out.println("Please Input " + MANAGE_SHOP + " for Management");
    }
}
