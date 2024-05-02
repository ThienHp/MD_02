package rikkei.academy.presentation.run;

import rikkei.academy.business.designImpl.UserService;
import rikkei.academy.business.model.Category;
import rikkei.academy.business.model.Product;
import rikkei.academy.business.model.User;
import rikkei.academy.business.until.IOFile;
import rikkei.academy.business.until.InputMethods;
import rikkei.academy.presentation.User.Admin.Admin;
import rikkei.academy.presentation.User.ProductControl;


import java.util.Scanner;

public class Main {
    public static  UserService userService = new UserService();

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BRIGHT_BLACK = "\u001B[90m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";



    public static void main(String[] args) {
        while (true){
            // In khung đăng nhập với màu
            // In khung đăng nhập với màu chữ và khung khác nhau
            System.out.println(ANSI_BRIGHT_BLACK + "╔════════════════════════╗" + ANSI_RESET);
            System.out.println(ANSI_BRIGHT_BLACK + "║" + ANSI_PURPLE + "       Của Hàng  Điện Thoại       " + ANSI_BRIGHT_BLACK + "║" + ANSI_RESET);
            System.out.println(ANSI_BRIGHT_BLACK + "╠════════════════════════╣" + ANSI_RESET);
            System.out.println(ANSI_BRIGHT_BLACK + "║     " + ANSI_YELLOW + "          1. Đăng nhập            " + ANSI_BRIGHT_BLACK + "║" + ANSI_RESET);
            System.out.println(ANSI_BRIGHT_BLACK + "╠════════════════════════╣" + ANSI_RESET);
            System.out.println(ANSI_BRIGHT_BLACK + "║     " + ANSI_YELLOW + "         2. Đăng kí                  " + ANSI_BRIGHT_BLACK + "║" + ANSI_RESET);
            System.out.println(ANSI_BRIGHT_BLACK + "╠════════════════════════╣" + ANSI_RESET);
            System.out.println(ANSI_BRIGHT_BLACK + "║     " + ANSI_YELLOW + "        3. Quên mật khẩu        " + ANSI_BRIGHT_BLACK + "║" + ANSI_RESET);
            System.out.println(ANSI_BRIGHT_BLACK + "╠════════════════════════╣" + ANSI_RESET);
            System.out.println(ANSI_BRIGHT_BLACK + "║     " + ANSI_RED + "     0. Thoát chương trình    " + ANSI_BRIGHT_BLACK + "║" + ANSI_RESET);
            System.out.println(ANSI_BRIGHT_BLACK + "╚════════════════════════╝" + ANSI_RESET);
            System.out.print("Nhập số cần chọn tại đây:  ");

            int choice = InputMethods.getInteger();
            switch (choice){
                case 1:
                    login();
                    break;
                case 2:
                    registerUser();
                    break;
                case 3:
                    userService.forgotPassword();
                default:
                    System.out.println("vui long chon lai");
            }
        }
    }
    public static void login( ) {
        Admin adminUser = new Admin();
        System.out.println(ANSI_BLUE+"Nhập Email đăng nhập");
        String email = InputMethods.getString();
        System.out.println(ANSI_BLUE+"nhập mật khẩu");
        String mk = InputMethods.getString();
        User userLogin = UserService.loginUser(email, mk);
        User admin = new User(1234, "Admin Name", "admin@gmail.com", "admin");
        if (email.equals(admin.getEmail()) && mk.equals(admin.getPassword())) {
            userLogin = admin;
            System.out.println("Đăng nhập Admin thành công");
        }
        //lưu thông tin người đăng nhập
        if (userLogin == null) {
            System.err.println("Tên đăng nhập hoặc mật khẩu không chính xác.");
            return;
        }
        IOFile.writeToFile(IOFile.USERLOGIN_PATH, userLogin);
        if (userLogin.isStatus()) {
            switch (userLogin.getRoles()) {
                case USER:
                    ProductControl.userMenu();
                    break;
                case ADMIN:
                    adminUser.menuAdmin();
                    break;
            }
        }
    }
    public static void registerUser() {
        System.out.println("nhập thông tin người dùng");
        User newUser = new User();
        newUser.inputUser(true);
        //            tạo tài khoản admin
//            newUser.setRole(UserRoles.ADMIN);

        userService.registerUser(newUser);
    }
}
