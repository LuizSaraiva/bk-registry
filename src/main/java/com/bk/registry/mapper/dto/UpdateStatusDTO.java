package com.bk.registry.api.mapper.dto;

import com.bk.registry.api.enums.StatusAccountApi;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateStatusDTO {

    @NotNull
    private StatusAccountApi status;
}
