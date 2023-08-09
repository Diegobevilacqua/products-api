package tutorials.products_service.dto;

import lombok.Builder;
import lombok.Data;

import tutorials.products_service.enums.Role;

@Data
@Builder
public class RegisterRequest {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String country;
    private Role role;
}
