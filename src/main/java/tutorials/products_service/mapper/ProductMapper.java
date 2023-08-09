package tutorials.products_service.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tutorials.products_service.dto.ProductDTO;
import tutorials.products_service.entity.Category;
import tutorials.products_service.entity.Product;
import tutorials.products_service.exceptions.CategoryNotFoundException;
import tutorials.products_service.repository.CategoryRepository;

@Component
public class ProductMapper {
    @Autowired
    private CategoryRepository categoryRepository;

    public ProductDTO mapToProductDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setName(product.getName());
        productDTO.setDescription(product.getDescription());
        productDTO.setPrice(product.getPrice());
        productDTO.setCreatedAt(product.getCreatedAt());
        productDTO.setCategoryId(product.getCategory() != null ? product.getCategory().getId() : null);
        return productDTO;
    }

    public Product mapToProduct(ProductDTO productDTO) {
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setCreatedAt(productDTO.getCreatedAt());
        Category category = categoryRepository.findById(productDTO.getCategoryId())
                .orElseThrow(CategoryNotFoundException::new);
        product.setCategory(category);
        return product;
    }
}
