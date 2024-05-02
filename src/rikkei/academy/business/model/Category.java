package rikkei.academy.business.model;

import rikkei.academy.business.designImpl.CategoryService;
import rikkei.academy.business.until.InputMethods;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;

public class Category implements Serializable {
    private static int count = 1;
    private final int categoryId;
    private String categoryName;

    public Category(){
        this.categoryId = count;
    }

    public Category(int categoryId, String categoryName){
        this.categoryId = ++count;
        this.categoryName = categoryName;
    }


    public int getCategoryId() {
        return categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
    public Category inputCategory(){
        System.out.println("Nhập tên danh mục:");
        String name = InputMethods.getString();
        this.categoryName= name;
        Category newCategory = new Category(categoryId, categoryName);

        // Save the new category


        return newCategory;
    }

    @Override
    public String toString() {
        return String.format("Category ID: " + getCategoryId() +
                ", Category Name: " + getCategoryName());

    }
}
