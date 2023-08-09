package tutorials.products_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tutorials.products_service.constants.Constants;
import tutorials.products_service.dto.AuthResponse;
import tutorials.products_service.dto.LoginRequest;
import tutorials.products_service.dto.RegisterRequest;
import tutorials.products_service.service.AuthService;

@RestController
@RequestMapping(Constants.AUTH_BASE_PATH)
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping(Constants.LOGIN_ENDPOINT_URL)
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(authService.login(loginRequest));
    }

    @PostMapping(Constants.REGISTER_ENDPOINT_URL)
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest registerRequest) {
        return ResponseEntity.ok(authService.register(registerRequest));
    }
}
