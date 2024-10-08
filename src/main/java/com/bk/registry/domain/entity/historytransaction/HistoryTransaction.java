package com.bk.registry.domain.entity.historytransaction;

import com.bk.registry.api.enums.account.TypeAccount;
import com.bk.registry.api.enums.historytransaction.TypeHistoryTransaction;
import com.bk.registry.domain.enums.account.StatusAccount;
import jakarta.persistence.*;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Data
@Table(name = "tb_history")
public class HistoryTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String description;
    @Enumerated(EnumType.STRING)
    private TypeHistoryTransaction type;
    private OffsetDateTime create_date;
}
