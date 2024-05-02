package rikkei.academy.presentation.User.Admin;

import rikkei.academy.business.designImpl.CategoryService;
import rikkei.academy.business.model.Category;
import rikkei.academy.business.model.Product;
import rikkei.academy.business.until.InputMethods;

import static rikkei.academy.presentation.run.Main.*;

public class CategoryManagement {
    public static CategoryService categoryService = new CategoryService();
    public  static Category category = new Category();
    public void categoryManagement(){
        while (true){
            System.out.print("\u001B[H\u001B[2J");

            // In menu với màu chữ và khung khác nhau
            System.out.println(ANSI_BLUE + "╔══════════════════════════════════════════════════════╗" + ANSI_RESET);
            System.out.println(ANSI_PURPLE + "  Quản lý danh mục "                                      + ANSI_RESET);
            System.out.println(ANSI_BLUE + "╠══════════════════════════════════════════════════════╣" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "║ 1. Hiển thị tất cả danh mục                     ║" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "║ 2. Thêm danh mục sản phẩm                           ║" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "║ 3. Chỉnh sửa danh mục sản phẩm                   ║" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "║ 4. Xóa danh mục sản phẩm                        ║" + ANSI_RESET);
            System.out.println(ANSI_RED + "║ 0. Thoát                                         ║" + ANSI_RESET);
            System.out.println(ANSI_BLUE + "╚══════════════════════════════════════════════════════╝" + ANSI_RESET);
            int choice= InputMethods.getInteger();
            switch (choice){
                case 1:
                    categoryService.findAll();
                    break;
                case 2:
                    addCategory();
                    break;
                case 3:
                    categoryService.updateCategoryName();
                    break;
                case 4:
                    categoryService.deleteCategoryById();
                    break;
                case 0:
                    System.out.println("Thoát thành công");
                    return;
                default:
                    System.out.println("vui long chon lai");
            }
        }
    }
    public static void addCategory() {
        Category category1 = new Category();
        category1.inputCategory();
        categoryService.addCategory(category1);

    }


}
