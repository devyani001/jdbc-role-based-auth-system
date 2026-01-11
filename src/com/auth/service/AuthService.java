package com.auth.service;

import com.auth.dao.UserDAO;
import com.auth.model.User;

public class AuthService {

    private static final int MAX_ATTEMPTS = 3;
    private UserDAO dao = new UserDAO();

    public void login(String username, String password) throws Exception {

        User user = dao.findByUsername(username);

        if (user == null) {
            System.out.println("User not found");
            return;
        }

        if (user.isAccountLocked()) {
            System.out.println("Account is locked");
            return;
        }

        if (password.equals(user.getPasswordHash())) {
            dao.resetAttempts(user.getId());
            System.out.println("Login successful");
            System.out.println("Role: " + user.getRole());
        } else {
            int attempts = user.getFailedAttempts() + 1;
            boolean lock = attempts >= MAX_ATTEMPTS;

            dao.updateFailedAttempts(user.getId(), attempts, lock);

            if (lock) {
                System.out.println("Account locked due to multiple failures");
            } else {
                System.out.println(
                    "Invalid password. Attempts left: " + (MAX_ATTEMPTS - attempts)
                );
            }
        }
    }
}
