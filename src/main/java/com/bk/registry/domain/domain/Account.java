package com.bk.registry.domain.domain;

import com.bk.registry.api.enums.TypeAccount;
import com.bk.registry.domain.enums.StatusAccount;
import jakarta.persistence.*;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Data
@Table(name = "tb_account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;
    private Integer branch;
    private Long account;
    private String document;
    @Enumerated(EnumType.STRING)
    private TypeAccount type;
    private OffsetDateTime create_date;
    @Enumerated(EnumType.STRING)
    private StatusAccount status = StatusAccount.ACTIVE;
}
