package rikkei.academy.presentation.User.Admin;

import rikkei.academy.business.designImpl.ProductService;
import rikkei.academy.business.designImpl.UserService;
import rikkei.academy.business.until.InputMethods;

import static rikkei.academy.presentation.run.Main.*;
import static rikkei.academy.presentation.run.Main.ANSI_RESET;

public class UsersManagement {
    static UserService userService = new UserService();
    public void userManagement(){
        while (true){
            System.out.print("\u001B[H\u001B[2J");

            // In menu với màu chữ và khung khác nhau
            System.out.println(ANSI_BLUE + "╔══════════════════════════════════════════════════════╗" + ANSI_RESET);
            System.out.println(ANSI_PURPLE + "  Quản lý người dùng "                                      + ANSI_RESET);
            System.out.println(ANSI_BLUE + "╠══════════════════════════════════════════════════════╣" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "║ 1. Hiển thị tất cả người dùng                     ║" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "║ 2. Khóa / mở người dùng                             ║" + ANSI_RESET);
            System.out.println(ANSI_RED + "║ 0. Thoát                                        ║" + ANSI_RESET);
            System.out.println(ANSI_BLUE + "╚══════════════════════════════════════════════════════╝" + ANSI_RESET);
            int choice= InputMethods.getInteger();
            switch (choice){
                case 1:
                    userService.findAll();
                    break;
                case 2:
                    userService.changeUserStatus();
                    break;
                case 0:
                    System.out.println("Thoát thành công");
                    return;
                default:
                    System.out.println("vui long chon lai");

            }
        }
    }

}
