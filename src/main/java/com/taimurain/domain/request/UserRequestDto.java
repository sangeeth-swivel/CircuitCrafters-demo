package com.taimurain.domain.request;

import com.taimurain.enums.UserType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestDto extends LoginRequestDto {

    private String name;
    private UserType userType;

    @Override
    public boolean isRequiredFieldsAvailable() {
        return isNonEmpty(name) && super.isRequiredFieldsAvailable();
    }
}
