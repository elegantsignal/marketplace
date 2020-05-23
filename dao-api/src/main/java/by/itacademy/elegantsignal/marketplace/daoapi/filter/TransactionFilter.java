package by.itacademy.elegantsignal.marketplace.daoapi.filter;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.enums.TransactionStatus;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.enums.TransactionType;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Arrays;
import java.util.List;


@Getter @Setter @Accessors(chain = true)
public class TransactionFilter extends AbstractFilter {

	Integer id;
	Integer userId;
	TransactionType type;
	TransactionStatus status;
	List<TransactionStatus> excludedStatusList;

	public TransactionFilter setExcludedStatus(final TransactionStatus... excludedStatus) {
		this.excludedStatusList = Arrays.asList(excludedStatus);
		return this;
	}
}
