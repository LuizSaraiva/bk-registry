package com.bk.registry.mapper;

import com.bk.registry.domain.entity.historytransaction.HistoryTransaction;
import com.bk.registry.mapper.dto.historytransaction.HistoryTransactionRequestDto;
import com.bk.registry.mapper.dto.historytransaction.HistoryTransactionRequestUpdateDto;
import com.bk.registry.mapper.dto.historytransaction.HistoryTransactionResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface HistoryTransactionMapper {

    HistoryTransaction historyTransactionDtoToDomain(HistoryTransactionRequestDto historyTransactionRequestDto);
    HistoryTransactionResponseDto historyTransactionDomainToDto(HistoryTransaction historyTransaction);
    List<HistoryTransactionResponseDto> listHistoryTransactionDomainToDto(List<HistoryTransaction> historyTransactionList);

    void copyHistoryTransactionDtoUpdateToDomain(HistoryTransactionRequestUpdateDto historyTransactionRequestUpdateDto,
                                             @MappingTarget HistoryTransaction historyTransaction);
}
