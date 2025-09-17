package com.apartment.management.service.mapper;

import com.apartment.management.entity.Transactions;
import com.apartment.management.service.dto.MiscellaneousTransactionDTO;
import com.apartment.management.service.dto.TransactionsDTO;
import com.apartment.management.service.mapper.config.BaseMapperConfig;
import com.apartment.management.service.mapper.config.GenericMapper;
import org.mapstruct.Mapper;

/**
 * A TransactionsMapper
 */
@Mapper(config = BaseMapperConfig.class, uses = {ApartmentsMapper.class, FlatsMaintenancesMapper.class})
public interface TransactionsMapper extends GenericMapper<Transactions, MiscellaneousTransactionDTO> {
    TransactionsDTO toTransactionsDTO(Transactions entity);
}
