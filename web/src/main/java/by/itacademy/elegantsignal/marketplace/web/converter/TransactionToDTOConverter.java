package by.itacademy.elegantsignal.marketplace.web.converter;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.ITransaction;
import by.itacademy.elegantsignal.marketplace.web.dto.TransactionDTO;
import org.springframework.stereotype.Component;

import java.util.function.Function;


@Component
public class TransactionToDTOConverter implements Function<ITransaction, TransactionDTO> {

	@Override
	public TransactionDTO apply(final ITransaction transaction) {
		final TransactionDTO transactionDTO = new TransactionDTO();
		transactionDTO.setAmount(transaction.getAmount());
		transactionDTO.setType(transaction.getType().toString());
		transactionDTO.setStatus(transaction.getStatus().toString());
		transactionDTO.setCreated(transaction.getCreated());
		return transactionDTO;
	}

}
