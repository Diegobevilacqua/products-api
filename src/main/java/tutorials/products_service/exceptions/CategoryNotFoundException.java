package tutorials.products_service.exceptions;

public class CategoryNotFoundException extends CustomNotFoundException {
    public CategoryNotFoundException() {
        super("The selected category does not exist.");
    }
}
