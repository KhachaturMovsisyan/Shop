package shop.storage;


import shop.data.DataClass;
import shop.model.User;

import java.util.HashMap;
import java.util.Map;

public class UserStorage {
    private Map<User, String> userMap = new HashMap<>();


    public void add(User user) {
        userMap.put(user, user.getEmail());
        serial();
    }

    public void print() {
        for (Map.Entry<User, String> userStringEntry : userMap.entrySet()) {
            System.out.println((userStringEntry.getKey().getName() + " " + userStringEntry.getKey().getSurname()));
        }

    }

    public User getByEmail(String email) {
        for (Map.Entry<User, String> userStringEntry : userMap.entrySet()) {
            if (userStringEntry.getKey().getEmail().equals(email)) {
                return userStringEntry.getKey();
            }
        }
        return null;
    }

    public User check(String login, String password) {
        for (Map.Entry<User, String> userStringEntry : userMap.entrySet()) {
            if (userStringEntry.getKey().getLogin().equals(login) && userStringEntry.getKey().getPassword().equals(password)) {
                return userStringEntry.getKey();
            }
        }
        return null;
    }

    public void payment(User check, double payment) {
        for (Map.Entry<User, String> userStringEntry : userMap.entrySet()) {
            if (userStringEntry.equals(check)) {
                double newBalance = userStringEntry.getKey().getBalance() + payment;
                userStringEntry.getKey().setBalance(newBalance);
                serial();
            }
        }
    }

    public void deleteUser(User byEmail) {
        userMap.remove(byEmail);

    }


    public void serial() {
        DataClass.serializeUser(userMap);
    }

    public void initData() {
        Map<User, String> map = DataClass.deSerializeUsers();
        if (map != null) {
            userMap = map;
        }
    }


}
