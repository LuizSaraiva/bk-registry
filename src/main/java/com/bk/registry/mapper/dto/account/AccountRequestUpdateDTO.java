package com.bk.registry.mapper.dto.account;

import com.bk.registry.api.enums.account.TypeAccount;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AccountRequestUpdateDTO {

    @NotBlank
    private String name;

    @NotBlank
    private String document;

    @NotNull
    private TypeAccount type;
}
