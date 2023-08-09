package tutorials.products_service.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ProductDTO {
    private String name;
    private String description;
    private Double price;
    private Date createdAt = new Date();
    private Long categoryId;
}
