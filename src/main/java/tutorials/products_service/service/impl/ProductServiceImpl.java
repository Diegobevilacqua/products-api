package tutorials.products_service.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tutorials.products_service.dto.ProductDTO;
import tutorials.products_service.entity.Product;
import tutorials.products_service.exceptions.ProductNotFoundException;
import tutorials.products_service.mapper.ProductMapper;
import tutorials.products_service.repository.ProductRepository;
import tutorials.products_service.service.ProductService;

import java.util.List;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<ProductDTO> getProducts() {
        log.info("Retrieving products...");
        List<Product> products = productRepository.findAll();
        log.info("Products were retrieved successfully");
        return products.stream().map(product -> productMapper.mapToProductDTO(product)).toList();
    }

    @Override
    public ProductDTO getProduct(Long id) {
        log.info(String.format("Retrieving product %d...", id));
        Product product = productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
        log.info(String.format("Product %d was retrieved successfully", product.getId()));
        return productMapper.mapToProductDTO(product);
    }

    @Override
    public ProductDTO saveProduct(ProductDTO productDTO) {
        log.info(String.format("Saving product named \"%s\"...", productDTO.getName()));
        Product product = productMapper.mapToProduct(productDTO);
        productRepository.save(product);
        log.info(String.format("Product named \"%s\" has been successfully saved.", productDTO.getName()));
        return productDTO;
    }

    @Override
    public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
        log.info(String.format("Retrieving product %d...", id));
        Product existingProduct = productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
        log.info(String.format("Product named \"%s\" was retrieved.", existingProduct.getName()));
        Product product = productMapper.mapToProduct(productDTO);
        existingProduct.setCategory(product.getCategory());
        existingProduct.setName(productDTO.getName());
        existingProduct.setDescription(productDTO.getDescription());
        existingProduct.setPrice(product.getPrice());
        log.info(String.format("Saving product named \"%s\"...", existingProduct.getName()));
        productRepository.save(existingProduct);
        log.info(String.format("Product named \"%s\" has been successfully updated and saved.", existingProduct.getName()));
        return productMapper.mapToProductDTO(existingProduct);
    }

    @Override
    public void deleteProduct(Long id) {
        log.info(String.format("Deleting product with id: %d...", id));
        productRepository.deleteById(id);
        log.info(String.format("Product with id: %d has been successfully deleted.", id));
    }
}
