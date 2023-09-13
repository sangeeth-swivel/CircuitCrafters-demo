package com.taimurain.controller;

import com.taimurain.domain.request.LoginRequestDto;
import com.taimurain.domain.response.LoginResponseDto;
import com.taimurain.domain.response.ResponseWrapper;
import com.taimurain.enums.ErrorMessage;
import com.taimurain.enums.SuccessMessage;
import com.taimurain.service.JwtTokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@Slf4j
public class AuthController extends BaseController {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenService jwtTokenService;

    public AuthController(AuthenticationManager authenticationManager, JwtTokenService jwtTokenService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenService = jwtTokenService;
    }

    @PostMapping("/sign-in")
    public ResponseEntity<ResponseWrapper> loginUser(@RequestBody LoginRequestDto loginRequestDto) {
        if (!loginRequestDto.isRequiredFieldsAvailable()) {
            log.debug("Required fields missing. data: {}", loginRequestDto.toLogJson());
            return getBadRequestErrorResponse(ErrorMessage.MISSING_REQUIRED_FIELDS, HttpStatus.BAD_REQUEST);
        }
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequestDto.getMobileNo(), loginRequestDto.getPassword()));
        String token = jwtTokenService.generateToken(loginRequestDto.getMobileNo());
        return getSuccessResponse(SuccessMessage.SUCCESSFULLY_LOGGED_IN, new LoginResponseDto(token), HttpStatus.OK);

    }

    @GetMapping("/validate-token")
    public ResponseEntity<ResponseWrapper> validateToken(@RequestParam String token) {
        jwtTokenService.validateToken(token);
        return getSuccessResponse(SuccessMessage.VALID_TOKEN, null, HttpStatus.OK);
    }
}
