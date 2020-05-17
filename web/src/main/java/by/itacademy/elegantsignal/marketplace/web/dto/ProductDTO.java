package by.itacademy.elegantsignal.marketplace.web.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;


@Getter
@Setter
public class ProductDTO {

	private Integer id;
	private String type;
	private BigDecimal price;
	private BookDTO book;

}
