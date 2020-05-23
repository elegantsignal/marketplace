package by.itacademy.elegantsignal.marketplace.web.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;


@Getter @Setter @Accessors(chain = true)
public class AccountDTO {

	private Integer userId;
	private BigDecimal balance;
	private BigDecimal withdrawalAmount;
}
