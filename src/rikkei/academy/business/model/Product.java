package rikkei.academy.business.model;

import rikkei.academy.business.designImpl.CategoryService;
import rikkei.academy.business.designImpl.ProductService;
import rikkei.academy.business.until.IOFile;
import rikkei.academy.business.until.InputMethods;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Product implements Serializable{
    private String productId;
    private String productName;
    private String description;
    private double price;
    private double discount;
    private Category category;
    private int quantity;
    private String manufacturer;
    private boolean status = true;

    public Product() {

    }

    public Product(String productId, String productName, String description
            , double price, double discount, Category category, int quantity, String manufacturer) {
        this.productId = productId;
        this.productName = productName;
        this.description = description;
        this.price = price;
        this.discount = discount;
        this.category = category;
        this.quantity = quantity;
        this.manufacturer = manufacturer;


    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Product inputProduct() {
        CategoryService categoryService =new CategoryService();
        ProductService productService = new ProductService();
        List<Category> categories = IOFile.readFromFile(IOFile.CATEGORY_PATH);
        System.out.println("Nhập ID sản phẩm:");
        this.productId = InputMethods.getString();
        while (!productId.matches("^[a-zA-Z]\\d{1,3}$") || productService.isProductIdExist(productId)) {
            if (!productId.matches("^[a-zA-Z]\\d{1,3}$")) {
                System.out.println("ID phải bắt đầu bằng một chữ cái và theo sau là tối đa 3 chữ số. Vui lòng nhập lại:");
            } else {
                System.out.println("ID sản phẩm đã tồn tại. Vui lòng nhập lại:");
            }
            productId = InputMethods.getString();
        }

        System.out.println("Nhập tên sản phẩm :");
        this.productName = InputMethods.getString();
        while (productName.length() < 4) {
            System.out.println("Tên sản phẩm phải có ít nhất 4 ký tự. Vui lòng nhập lại:");
            productName = InputMethods.getString();
        }

        System.out.println("Nhập mô tả sản phẩm:");
        this.description = InputMethods.getString();
        while (description.length() < 10) {
            System.out.println("Mô tả sản phẩm phải có ít nhất 10 ký tự. Vui lòng nhập lại:");
            description = InputMethods.getString();
        }

        System.out.println("Nhập giá sản phẩm:");
        this.price = InputMethods.getDouble();
        while (price < 0) {
            System.out.println("Giá sản phẩm phải là lớn hơn 0.");
            price = InputMethods.getDouble();
        }

        System.out.println("Nhập giảm giá của sản phẩm:");
        this.discount = InputMethods.getDouble();
        while (discount < 20 || discount > 100) {
            System.out.println("Giảm giá sản phẩm phải là một số từ 20% đến 100%");
            discount = InputMethods.getDouble();
        }

        System.out.println("Danh mục hiện có:");

        categoryService.findAll();
        System.out.println("Nhập ID danh mục sản phẩm:");
        int categoryId = InputMethods.getInteger();

        Category chosenCategory = null;
        for (Category category : categories) {
            if (category.getCategoryId() == categoryId) {
                chosenCategory = category;
                break;
            }
        }
        if (chosenCategory == null) {
            System.out.println("Không tìm thấy danh mục với ID đã nhập.");
            return null;
        }
        setCategory(chosenCategory);


        System.out.println("Nhập số lượng sản phẩm:");
        this.quantity = InputMethods.getInteger();
        while (quantity < 10) {
            System.out.println("Số lượng phải lớn hơn 10 sản phẩm.");
            quantity = InputMethods.getInteger();
        }

        System.out.println("Nhập nguồn gốc sản phẩm:");
        this.manufacturer = InputMethods.getString();

        // Tạo sản phẩm mới với danh mục đã chọn
        Product newProduct = new Product(productId, productName, description, price, discount, chosenCategory, quantity, manufacturer);

        // Print the product details
        System.out.println(newProduct.toString());

        return newProduct;
    }

    @Override
    public String toString() {
        return String.format("ID :" + productId + '\'' +
                ", Name : '" + productName + '\'' +
                ", Mô tả : '" + description + '\'' +
                ",\n Giá : " + discount +
                ", danh mục : " + category.getCategoryName() +
                ", Số lượng : " + getQuantity() +
                ", Sản xuất : '" + manufacturer + '\'' +
                ", Trạng thái :" + (status ? "Đang bán" : "Ngừng bán") ) +
                "\n ======================================";


    }
}
