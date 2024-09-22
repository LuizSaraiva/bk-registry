package com.bk.registry.api.mapper.dto;

import com.bk.registry.api.enums.StatusAccountApi;
import com.bk.registry.api.enums.TypeAccount;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AccountRequestDTO {

    @NotNull
    private Integer branch;

    @NotNull
    private Long account;

    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @NotBlank
    private String document;

    @NotNull
    private TypeAccount type;

    private StatusAccountApi status;

}
