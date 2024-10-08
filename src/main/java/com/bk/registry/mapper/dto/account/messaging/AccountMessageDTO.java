package com.bk.registry.mapper.dto.account.messaging;

import com.bk.registry.api.enums.account.TypeAccount;
import com.bk.registry.domain.enums.account.StatusAccount;
import lombok.Data;

import java.util.UUID;

@Data
public class AccountMessageDTO {
    private UUID id;
    private Integer branch;
    private Long account;
    private TypeAccount type;
    private StatusAccount status = StatusAccount.ACTIVE;
}
