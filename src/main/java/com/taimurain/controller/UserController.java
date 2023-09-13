package com.taimurain.controller;

import com.taimurain.domain.request.UserRequestDto;
import com.taimurain.domain.response.ResponseWrapper;
import com.taimurain.enums.ErrorMessage;
import com.taimurain.enums.SuccessMessage;
import com.taimurain.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/api/v1/user")
public class UserController extends BaseController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("")
    public ResponseEntity<ResponseWrapper> registerUser(@RequestBody UserRequestDto userRequestDto) {
        if (!userRequestDto.isRequiredFieldsAvailable()) {
            log.debug("Required fields missing. data: {}", userRequestDto.toLogJson());
            return getBadRequestErrorResponse(ErrorMessage.MISSING_REQUIRED_FIELDS, HttpStatus.BAD_REQUEST);
        }
        if (!userRequestDto.isValidMobileNo())
            return getBadRequestErrorResponse(ErrorMessage.INVALID_MOBILE_NO, HttpStatus.BAD_REQUEST);
        userService.addUser(userRequestDto);
        return getSuccessResponse(SuccessMessage.SUCCESSFULLY_ADDED, null, HttpStatus.CREATED);

    }
}
