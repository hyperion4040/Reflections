package com.akozlowski;

public class App {

    public static void main(String[] args) {
        final User user = new User(0L, "Adrian");

        final DAO<User> userDAO = new DAO<>();
        userDAO.save(user);
    }
}
