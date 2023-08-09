package tutorials.products_service;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import tutorials.products_service.entity.Category;
import tutorials.products_service.entity.Product;
import tutorials.products_service.repository.CategoryRepository;
import tutorials.products_service.repository.ProductRepository;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class ProductsServiceApplicationTests {

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ProductRepository productRepository;

	@Test
	void contextLoads() {
	}

	@Test
	void testSaveAndRetrieveApple() {
		Category fruits = Category.builder().detail("Fruits").build();

		Product apple = Product.builder()
				.name("Apple")
				.description("This is a red apple")
				.category(fruits)
				.price(234.00)
				.build();

		Product savedApple = productRepository.save(apple);
		Product retrievedProduct = productRepository.findById(savedApple.getId()).orElseThrow();

		assertThat(retrievedProduct).isNotNull();
		assertThat(retrievedProduct.getName()).isEqualTo("Apple");
	}
}
