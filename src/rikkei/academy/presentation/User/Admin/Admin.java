package rikkei.academy.presentation.User.Admin;

import rikkei.academy.business.until.IOFile;
import rikkei.academy.business.until.InputMethods;
import rikkei.academy.presentation.run.Main;

import static rikkei.academy.presentation.run.Main.*;

public class Admin {
    CategoryManagement categoryManagement = new CategoryManagement();
    ProductManagement productManagement = new ProductManagement();
    UsersManagement usersManagement = new UsersManagement();
    OrderManagement orderManagement = new OrderManagement();
   public void menuAdmin(){

       System.out.print("\u001B[H\u001B[2J");
       while (true){
           System.out.println(ANSI_BLUE + "╔══════════════════════════════════════════════════════╗" + ANSI_RESET);
           System.out.println(ANSI_PURPLE + "   Chào mừng Admin "                                      + ANSI_RESET);
           System.out.println(ANSI_BLUE + "╠══════════════════════════════════════════════════════╣" + ANSI_RESET);
           System.out.println(ANSI_YELLOW + "║ 1. Quản lý danh mục                     ║" + ANSI_RESET);
           System.out.println(ANSI_BLUE + "╠══════════════════════════════════════════════════════╣" + ANSI_RESET);
           System.out.println(ANSI_YELLOW + "║ 2. Quản lý sản phẩm                              ║" + ANSI_RESET);
           System.out.println(ANSI_BLUE + "╠══════════════════════════════════════════════════════╣" + ANSI_RESET);
           System.out.println(ANSI_YELLOW + "║ 3. Quản lý người dùng                   ║" + ANSI_RESET);
           System.out.println(ANSI_BLUE + "╠══════════════════════════════════════════════════════╣" + ANSI_RESET);
           System.out.println(ANSI_YELLOW + "║ 4. Quản lý đơn hàng                        ║" + ANSI_RESET);
           System.out.println(ANSI_BLUE + "╠══════════════════════════════════════════════════════╣" + ANSI_RESET);
           System.out.println(ANSI_RED + "║ 0. Đăng xuất                                         ║" + ANSI_RESET);
           System.out.println(ANSI_BLUE + "╚══════════════════════════════════════════════════════╝" + ANSI_RESET);
           System.out.print("Nhập số cần chọn tại đây:  ");
           int choice = InputMethods.getInteger();
           switch (choice){
               case 1:
                   categoryManagement.categoryManagement();
                   break;
                case 2:
                    productManagement.productManagement();
                    break;
               case 3:
                   usersManagement.userManagement();
                   break;
               case 4:
                   orderManagement.orderManagement();
                   break;
               case 0 :
                   IOFile.deleteFile();
                   System.out.println("Đăng xuất thành công");
                   Main.phone();

                   return;
               default:
                   System.out.println("vui long chon lai");
           }
       }
    }
}
