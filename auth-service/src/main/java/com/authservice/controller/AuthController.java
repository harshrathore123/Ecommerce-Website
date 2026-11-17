    package com.authservice.controller;

    import com.authservice.dto.RegisterRequest;
    import com.authservice.dto.UserResponse;
    import com.authservice.service.AuthService;
    import jakarta.validation.Valid;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.validation.BindingResult;
    import org.springframework.web.bind.annotation.PostMapping;
    import org.springframework.web.bind.annotation.RequestBody;
    import org.springframework.web.bind.annotation.RequestMapping;
    import org.springframework.web.bind.annotation.RestController;

    @RestController
    @RequestMapping("/api/auth")
    public class AuthController {

        private final AuthService authService;

        public AuthController(AuthService authService) {
            this.authService = authService;
        }

        @PostMapping("/register")
        public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequest registerRequest, BindingResult result){

            if(result.hasErrors()){
                String errorMessage = result.getFieldErrors()
                        .stream()
                        .map(error -> error.getField() + ": " + error.getDefaultMessage())
                        .findFirst()
                        .orElse("Invalid input");

                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
            }

            UserResponse user = authService.registerUser(registerRequest);

            return ResponseEntity.status(HttpStatus.CREATED).body(user);
        }
    }
