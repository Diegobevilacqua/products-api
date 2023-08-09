package tutorials.products_service.service;

import tutorials.products_service.dto.ProductDTO;
import tutorials.products_service.entity.Category;
import tutorials.products_service.entity.Product;

import java.util.List;

public interface ProductService {
    List<ProductDTO> getProducts();

    ProductDTO getProduct(Long id);

    ProductDTO saveProduct(ProductDTO productDTO);

    ProductDTO updateProduct(Long id, ProductDTO productDTO);

    void deleteProduct(Long id);
}
