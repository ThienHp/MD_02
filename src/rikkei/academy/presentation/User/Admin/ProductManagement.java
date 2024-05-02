package rikkei.academy.presentation.User.Admin;

import rikkei.academy.business.designImpl.CategoryService;
import rikkei.academy.business.designImpl.ProductService;
import rikkei.academy.business.model.Category;
import rikkei.academy.business.model.Product;
import rikkei.academy.business.until.IOFile;
import rikkei.academy.business.until.InputMethods;

import static rikkei.academy.presentation.run.Main.*;
import static rikkei.academy.presentation.run.Main.ANSI_RESET;

public class ProductManagement {
    public  static CategoryService categoryService = new CategoryService();
    public static ProductService products=new ProductService();
    public void productManagement() {
        while (true) {

            System.out.print("\u001B[H\u001B[2J");

            // In menu với màu chữ và khung khác nhau
            System.out.println(ANSI_BLUE + "╔══════════════════════════════════════════════════════╗" + ANSI_RESET);
            System.out.println(ANSI_PURPLE + "  Quản lý sản phẩm " + ANSI_RESET);
            System.out.println(ANSI_BLUE + "╠══════════════════════════════════════════════════════╣" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "║ 1. Hiển thị tất cả sản phẩm                    ║" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "║ 2. Thêm sản phẩm                           ║" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "║ 3. Chỉnh sửa sản phẩm                   ║" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "║ 4. Xóa sản phẩm                        ║" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "║ 5. Hiển thị danh mục theo danh mục                      ║" + ANSI_RESET);
            System.out.println(ANSI_RED + "║ 0. Thoát                                         ║" + ANSI_RESET);
            System.out.println(ANSI_BLUE + "╚══════════════════════════════════════════════════════╝" + ANSI_RESET);
            int choice = InputMethods.getInteger();
            switch (choice) {
                case 1:
                    products.findAll();
                    break;
                case 2:
                    addProduct();
                    break;
                case 3:
                    editProduct();
                    break;
                case 4:
                    deleteProduct();
                    break;
                case 5:

                case 0:
                    System.out.println("Thoát thành công");
                    return;
                default:
                    System.out.println("vui long chon lai");
            }
        }
    }
    public static void addProduct() {

        Product product = new Product();
        product.inputProduct();
        products.addProduct(product);

    }
    public void editProduct() {
        System.out.println("Nhập ID sản phẩm cần chỉnh sửa:");
        String id = InputMethods.getString();

        Product product = products.findById(id);
        if (product != null) {
            System.out.println("Chọn thuộc tính cần chỉnh sửa:");
            System.out.println("1. Tên sản phẩm");
            System.out.println("2. Mô tả sản phẩm");
            System.out.println("3. Giá sản phẩm");
            System.out.println("4. Giảm giá sản phẩm");
            System.out.println("5. Danh mục sản phẩm");
            System.out.println("6. Số lượng sản phẩm");
            System.out.println("7. Nguồn gốc sản phẩm");

            int choice = InputMethods.getInteger();
            switch (choice) {
                case 1:
                    System.out.println("Nhập tên sản phẩm mới:");
                    String newName = InputMethods.getString();
                    product.setProductName(newName);
                    break;
                case 2:
                    System.out.println("Nhập mô tả sản phẩm mới:");
                    String newDescription = InputMethods.getString();
                    product.setDescription(newDescription);
                    break;
                case 3:
                    System.out.println("Nhập giá sản phẩm mới:");
                    double newPrice = InputMethods.getDouble();
                    product.setPrice(newPrice);
                    break;
                case 4:
                    System.out.println("Nhập giảm giá sản phẩm mới:");
                    double newDiscount = InputMethods.getDouble();
                    product.setDiscount(newDiscount);
                    break;
                case 5:
                    System.out.println("Nhập ID danh mục sản phẩm mới:");
                    int newCategoryId = InputMethods.getInteger();
                    Category newCategory = categoryService.findById(newCategoryId);
                    if (newCategory != null) {
                        product.setCategory(newCategory);
                    } else {
                        System.out.println("Không tìm thấy danh mục với ID đã nhập.");
                    }
                    break;
                case 6:
                    System.out.println("Nhập số lượng sản phẩm mới:");
                    int newQuantity = InputMethods.getInteger();
                    product.setQuantity(newQuantity);
                    break;
                case 7:
                    System.out.println("Nhập nguồn gốc sản phẩm mới:");
                    String newManufacturer = InputMethods.getString();
                    product.setManufacturer(newManufacturer);
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ.");
            }

            System.out.println("Cập nhật thông tin sản phẩm thành công");
            products.updateProduct(product);
        } else {
            System.out.println("Không tìm thấy sản phẩm với ID đã nhập.");
        }
    }
    public void deleteProduct() {
        System.out.println("Nhập ID sản phẩm cần xóa:");
        String id = InputMethods.getString();

        Product product = products.findById(id);
        if (product != null) {
            products.deleteById(id);
            System.out.println("Đã xóa sản phẩm có ID: " + id);
        } else {
            System.out.println("Không tìm thấy sản phẩm với ID đã nhập.");
        }
    }
}
