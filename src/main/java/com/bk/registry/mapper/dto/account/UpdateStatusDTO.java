package com.bk.registry.mapper.dto.account;

import com.bk.registry.api.enums.account.StatusAccountApi;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateStatusDTO {

    @NotNull
    private StatusAccountApi status;
}
