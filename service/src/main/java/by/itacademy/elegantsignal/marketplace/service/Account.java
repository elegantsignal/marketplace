package by.itacademy.elegantsignal.marketplace.service;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IUser;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;


@Component @Getter @Setter @Accessors(chain = true)
public class Account {

	IUser user;
	BigDecimal balance;
	BigDecimal withdrawalAmount;
}
