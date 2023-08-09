package tutorials.products_service.exceptions;

public class ProductNotFoundException extends CustomNotFoundException {
    public ProductNotFoundException() {
        super("The selected product does not exist.");
    }
}
