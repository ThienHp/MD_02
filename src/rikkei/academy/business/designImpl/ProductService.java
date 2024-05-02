package rikkei.academy.business.designImpl;

import rikkei.academy.business.design.IProductService;
import rikkei.academy.business.model.Category;
import rikkei.academy.business.model.Product;
import rikkei.academy.business.model.User;
import rikkei.academy.business.until.IOFile;

import java.util.ArrayList;
import java.util.List;

public class ProductService implements IProductService {
    public static List<Product> productList=new ArrayList<>();
    public ProductService(){
        productList= IOFile.readFromFile(IOFile.PRODUCT_PATH);
    }

    @Override
    public List<Product> findAll() {
        if (productList.isEmpty()) {
            System.out.println("Không có sản phẩm hiện tại");
        } else {
            for (Product product : productList) {
                System.out.println(product.toString());
            }
        }
        return productList;
    }
    public Product findByName(String name) {
        // Giả sử bạn có một danh sách các sản phẩm
        for (Product product : productList) {
            if (product.getProductName().equals(name)) {
                return product;
            }
        }
        return null;
    }

    @Override
    public Product findById(String id) {
        return productList.stream().filter(e -> e.getProductId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public void save(Product product) {
        productList.add(product);
        IOFile.writeToFile(IOFile.PRODUCT_PATH,productList);
    }

    @Override
    public void deleteById(String id) {
        productList.remove(findById(id));
        IOFile.writeToFile(IOFile.PRODUCT_PATH,productList);

    }
    @Override
    public void updateProductStatus(String id, boolean newStatus) {
        Product product = findById(id);
        if (product != null) {
            product.setStatus(newStatus);
        }
    }

    public void addProduct(Product product) {
        productList.add(product);
        System.out.println("Thêm sản phẩm thành công");
        IOFile.writeToFile(IOFile.PRODUCT_PATH, productList);
    }
    public boolean isProductIdExist(String productId) {
        for (Product product : productList) {
            if (product.getProductId().equals(productId)) {
                return true;
            }
        }
        return false;
    }
    public static void updateProduct(Product product) {
        // Tìm sản phẩm cần cập nhật
        for (Product products : productList) {
            if (products.getProductId().equals(product.getProductId())) {
                // Cập nhật thông tin sản phẩm
                products.setProductName(product.getProductName());
                products.setPrice(product.getPrice());
                products.setQuantity(product.getQuantity());
                products.setCategory(product.getCategory());
                products.setProductId(product.getProductId());
                products.setStatus(product.isStatus());
                break;
            }
        }
        // Lưu lại danh sách sản phẩm
        IOFile.writeToFile(IOFile.PRODUCT_PATH, productList);
    }

}
