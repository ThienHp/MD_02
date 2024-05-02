package rikkei.academy.business.design;

import rikkei.academy.business.model.Product;

public interface IProductService extends IGenericServive<Product ,String> {
    Product findById(String id);

    void updateProductStatus(String id, boolean newStatus);

}
