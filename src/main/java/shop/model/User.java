package shop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    private String name;
    private String surname;
    private int age;
    private String gander;
    private String email;
    private  String password;
    private String login;
    private double balance;



}

