package by.itacademy.elegantsignal.marketplace.daoapi.filter;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;


@Getter
@Setter
@Accessors(chain = true)
public class DownloadFilter extends AbstractFilter {
	private Integer userId;
}
