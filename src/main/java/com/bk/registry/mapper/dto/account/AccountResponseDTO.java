package com.bk.registry.mapper.dto.account;

import com.bk.registry.api.enums.account.StatusAccountApi;
import com.bk.registry.api.enums.account.TypeAccount;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.UUID;

@Data
public class AccountResponseDTO {

    private UUID id;
    private Integer branch;
    private Long account;
    private String name;
    private String document;
    private TypeAccount type;
    private OffsetDateTime create_date;
    private StatusAccountApi status;
}
