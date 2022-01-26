package shop;


import shop.model.Deal;
import shop.model.Message;
import shop.model.Product;
import shop.model.User;
import shop.storage.DealStorage;
import shop.storage.MessageStorage;
import shop.storage.ProductStorage;
import shop.storage.UserStorage;

import java.io.IOException;
import java.util.Date;
import java.util.Scanner;

public class ShopDemo implements Commands {


    private static Scanner scanner = new Scanner(System.in);
    private static ProductStorage productStorage = new ProductStorage();
    private static UserStorage userStorage = new UserStorage();
    private static MessageStorage messageStorage = new MessageStorage();
    private static DealStorage dealStorage = new DealStorage();


    public static void main(String[] args) throws IOException, ClassNotFoundException {

        boolean isRun = true;
        while (isRun) {
            initData();
            Commands.printCommand();
            String command = scanner.nextLine();
            switch (command) {
                case EXIT:
                    isRun = false;
                    break;
                case LOGIN:
                    loginInterface();
                    break;
                case REGISTRATION:
                    registration();
                    break;
                default:
                    System.out.println("Invalid command");
            }
        }


    }

    private static void registration() {
        try {
            System.out.println("please input your name");
            String name = scanner.nextLine();

            System.out.println("please input your surname");
            String surname = scanner.nextLine();

            System.out.println("please input your email");
            String email = scanner.nextLine();

            System.out.println("please input your gender");
            String gender = scanner.nextLine();

            System.out.println("please input your age");
            int age = Integer.parseInt(scanner.nextLine());

            System.out.println("please input your password");
            String password = scanner.nextLine();

            System.out.println("please input your login");
            String login = scanner.nextLine();


            User user = new User(name, surname, age, gender, email, login, password, 0);
            if (userStorage.getByEmail(user.getEmail()) != null) {
                System.err.println("Invalid email. Author with this email already exists");
            } else {
                userStorage.add(user);
                System.out.println("Congratulations ! You are registered in our shop");
                userStorage.serial();
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }


    }


    private static void loginInterface() {
        System.out.println("please input your login");
        String login = scanner.nextLine();

        System.out.println("please input your password");
        String password = scanner.nextLine();

        if (login.equalsIgnoreCase("admin") && password.equals("admin")) {
            adminInterface();
        } else {
            userInterface(login, password);
        }
    }


    private static void userInterface(String login, String password) {
        if (userStorage.check(login, password) != null) {

            boolean isRun = true;
            User currentUser = userStorage.check(login, password);
            System.out.println("Welcome back " + currentUser.getName() + " " + currentUser.getSurname());
            while (isRun) {
                Commands.userCommands();
                String userCommand = scanner.nextLine();
                switch (userCommand) {
                    case BACK:
                        isRun = false;
                        break;
                    case EDIT_USER:
                        editUser(userStorage.check(login, password));
                        break;
                    case ADD_BALANCE:
                        addBalance(userStorage.check(login, password));
                        break;
                    case BUY_PRODUCT:
                        buyProduct(currentUser);
                        break;
                    case INBOX:
                        writeAndReadMessages(currentUser);
                        break;
                    default:
                        System.out.println("Invalid Command");
                }
            }
        } else {
            System.out.println("Wrong email or password !!!");
            loginInterface();
        }

    }

    private static void writeAndReadMessages(User currentUser) {
        messageStorage.printMessageByUser(currentUser);
        userStorage.print();

        System.out.println("Type user email to communicate");
        String email = scanner.nextLine();
        User toUser = userStorage.getByEmail(email);
        if (toUser == null) {
            System.out.println("User with this email doesn't exist!");
        } else {
            System.out.println("Write a text!");
            String text = scanner.nextLine();
            Message newMessage = new Message(currentUser, toUser, text, new Date());
            messageStorage.add(newMessage);
        }
    }

    private static void buyProduct(User user) {
        productStorage.print();
        System.out.println("choose which product you want to buy");
        String productName = scanner.nextLine();

        Product product = productStorage.getProductByName(productName);
        System.out.println("How many " + productName + " you want to buy?");

        int count = Integer.parseInt(scanner.nextLine());
        if (product != null) {
            if (user.getBalance() >= count * product.getPrice() && product.getCount() >= count) {
                double newBalance = user.getBalance() - count * product.getPrice();
                int newCount = product.getCount() - count;
                user.setBalance(newBalance);
                product.setCount(newCount);
                System.out.println("product sold");
                Deal deal = new Deal(new Date(), count * product.getPrice(), product.getName());
                dealStorage.add(deal);

            } else {
                System.out.println("You don't have enough money or We don't have that much " + productName);
            }
        } else {
            System.out.println("We don't have that product!!!");
        }

    }

    private static void addBalance(User check) {
        System.out.println("Type your money");
        double payment = Double.parseDouble(scanner.nextLine());
        userStorage.payment(check, payment);
        System.out.println("Balance updated");

    }

    private static void editUser(User check) {
        System.out.println(check.toString());
        System.out.println("please input your name");
        String name = scanner.nextLine();

        System.out.println("please input your surname");
        String surname = scanner.nextLine();

        System.out.println("please input your gender");
        String gender = scanner.nextLine();
        System.out.println("please input your age");
        int age = 0;
        try {
            age = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        System.out.println("please input your password");
        String password = scanner.nextLine();

        System.out.println("please input your login");
        String login = scanner.nextLine();

        check.setName(name);
        check.setSurname(surname);
        check.setGander(gender);
        check.setAge(age);
        check.setPassword(password);
        check.setLogin(login);
        System.out.println("Thank you " + name + " " + surname + " your account has updated");
    }


    private static void adminInterface() {
        boolean isRun = true;

        while (isRun) {
            Commands.adminCommands();
            String adminCommand = scanner.nextLine();
            switch (adminCommand) {
                case BACK:
                    isRun = false;
                    break;
                case DELETE_USER:
                    deleteUser();
                    break;
                case ADD_PRODUCT:
                    addProduct();
                    break;
                case UP_DATE_SHOP:
                    productStorage.print();
                    productStorage.upDateStorage();
                    productStorage.print();
                    break;
                case MANAGE_SHOP:
                    manageShop();
                    break;
                default:
                    System.out.println("Invalid Command");
            }
        }


    }

    private static void manageShop() {
        dealStorage.printAlldeals();
        dealStorage.printWithTaxes();

    }


    private static void addProduct() {
        System.out.println("please input product name");
        String name = scanner.nextLine();

        System.out.println("please input product price");
        double price = Double.parseDouble(scanner.nextLine());

        System.out.println("please product count");
        int count = Integer.parseInt(scanner.nextLine());

        if (productStorage.getProductByName(name) != null) {
            System.out.println("ProductStorage has this product");
        } else {
            Product product = new Product(name, count, price);
            productStorage.add(product);
        }


    }

    private static void deleteUser() {
        userStorage.print();
        System.out.println("Please input user's email to remove");
        String deletedUser = scanner.nextLine();
        if (userStorage.getByEmail(deletedUser) != null) {
            userStorage.deleteUser(userStorage.getByEmail(deletedUser));
            System.out.println("User was deleted!");
        } else {
            System.out.println("User with this email doesn't exist! ");
        }


    }

    private static void initData() {
        dealStorage.initData();
        messageStorage.initData();
        productStorage.initData();
        userStorage.initData();
    }


}
