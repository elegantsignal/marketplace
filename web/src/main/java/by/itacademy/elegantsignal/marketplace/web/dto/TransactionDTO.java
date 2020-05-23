package by.itacademy.elegantsignal.marketplace.web.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.Date;


@Getter @Setter @Accessors(chain = true)
public class TransactionDTO {

	BigDecimal amount;
	String type;
	String status;
	Date created;
}
