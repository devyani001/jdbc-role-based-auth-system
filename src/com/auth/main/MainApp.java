package com.auth.main;

import com.auth.service.AuthService;

import java.util.Scanner;

public class MainApp {

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
        AuthService auth = new AuthService();

        System.out.print("Username: ");
        String username = sc.nextLine();

        System.out.print("Password: ");
        String password = sc.nextLine();

        auth.login(username, password);
    }
}
