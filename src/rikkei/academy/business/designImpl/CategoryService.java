package rikkei.academy.business.designImpl;

import rikkei.academy.business.design.IGenericServive;
import rikkei.academy.business.model.Category;
import rikkei.academy.business.model.Product;
import rikkei.academy.business.until.IOFile;
import rikkei.academy.business.until.InputMethods;

import java.util.ArrayList;
import java.util.List;

public class CategoryService implements IGenericServive<Category, Integer> {
    private static java.util.List<Category> categories = new ArrayList<>();
    public CategoryService(){
        categories= IOFile.readFromFile(IOFile.CATEGORY_PATH);
    }

    @Override
    public List<Category> findAll() {
        if (categories.isEmpty()) {
            System.out.println("Không có danh mục hiện tại");
        } else {
            for (Category category : categories) {
                System.out.println(category.toString());
            }
        }
        return categories;
    }

    @Override
    public Category findById(Integer id) {
        for (Category category : categories) {
            if (category.getCategoryId() == id) {
                return category;
            }
        }
        return null;
    }

    @Override
    public void save(Category category) {
        categories.add(category);
    }

    @Override
    public void deleteById(Integer id) {
        categories.removeIf(category -> category.getCategoryId() == id);
    }

    public void addCategory(Category category) {
        categories.add(category);
        System.out.println("Thêm sản phẩm thành công");
        IOFile.writeToFile(IOFile.CATEGORY_PATH, categories);
    }
    public void updateCategoryName() {
        System.out.println("Nhập ID danh mục cần chỉnh sửa:");
        int id = InputMethods.getInteger();

        Category category = findById(id);
        if (category != null) {
            System.out.println("Nhập tên mới cho danh mục:");
            String newName = InputMethods.getString();
            category.setCategoryName(newName);
            System.out.println("Cập nhật tên danh mục thành công");
            IOFile.writeToFile(IOFile.CATEGORY_PATH, categories);
        } else {
            System.out.println("Không tìm thấy danh mục với ID đã nhập.");
        }
    }
    public void deleteCategoryById() {
        System.out.println("Nhập ID danh mục cần xóa:");
        int id = InputMethods.getInteger();

        Category category = findById(id);
        if (category != null) {
            categories.remove(category);
            System.out.println("Đã xóa danh mục có ID: " + id);
            IOFile.writeToFile(IOFile.CATEGORY_PATH, categories);
        } else {
            System.out.println("Không tìm thấy danh mục với ID đã nhập.");
        }
    }
}
