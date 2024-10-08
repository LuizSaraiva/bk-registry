package com.bk.registry.domain.entity.account;

import com.bk.registry.domain.enums.TypeEvent;
import com.bk.registry.domain.enums.TypeOutbox;
import jakarta.persistence.*;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "tb_outbox_registry")
@Data
public class OutboxRegistry {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private UUID idRef;
    @Column(columnDefinition = "TEXT")
    private String message;
    private OffsetDateTime create_date;
    private OffsetDateTime update_date;
    private boolean sent;
    @Enumerated(EnumType.STRING)
    private TypeOutbox type;
    @Enumerated(EnumType.STRING)
    private TypeEvent typeEvent;
}
