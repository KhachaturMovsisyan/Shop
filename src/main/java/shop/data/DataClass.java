package shop.data;

import shop.model.Deal;
import shop.model.Message;
import shop.model.Product;
import shop.model.User;

import java.io.*;
import java.util.List;
import java.util.Map;

public class DataClass {
    private static String path = "E:\\java 2021\\java.2021\\onlineShop\\src\\main\\resources";

    public static void serializeDeal(List<Deal> deals) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(path + "\\" + "deals.txt"))) {
            out.writeObject(deals);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void serializeMessage(List<Message> messages) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(path + "\\" + "messages.txt"))) {
            out.writeObject(messages);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void serializeProduct(List<Product> products) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(path + "\\" + "products.txt"))) {
            out.writeObject(products);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void serializeUser(Map<User,String> users) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(path + "\\" + "users.txt"))) {
            out.writeObject(users);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Deal> deSerializeDeals() {
        File dealFile = new File(path + "\\" + "deals.txt");
        if (!dealFile.exists()) {
            try {
                dealFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(dealFile))) {
                Object obj = in.readObject();
                return (List<Deal>) obj;
            } catch (EOFException e) {
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static List<Message> deSerializeMessages() {
        File messageFile = new File(path + "\\" + "messages.txt");
        if (!messageFile.exists()) {
            try {
                messageFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(messageFile))) {
                Object obj = in.readObject();
                return (List<Message>) obj;
            } catch (EOFException e) {

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static List<Product> deSerializeProducts() {
        File productFile = new File(path + "\\" + "products.txt");
        if (!productFile.exists()) {
            try {
                productFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(productFile))) {
                Object obj = in.readObject();
                return (List<Product>) obj;
            } catch (EOFException e) {

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static Map<User,String> deSerializeUsers() {
        File userFile = new File(path + "\\" + "users.txt");
        if (!userFile.exists()) {
            try {
                userFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(userFile))) {
                Object obj = in.readObject();
                return (Map<User, String>) obj;
            } catch (EOFException e) {

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
