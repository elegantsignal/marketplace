package by.itacademy.elegantsignal.marketplace.dao.orm;

import org.hibernate.cfg.ImprovedNamingStrategy;


public class CustomImprovedNamingStrategy extends ImprovedNamingStrategy {

	private static final long serialVersionUID = 1011016023627571461L;

	@Override
	public String foreignKeyColumnName(final String propertyName, final String propertyEntityName,
			final String propertyTableName, final String referencedColumnName) {
		final String base = super.foreignKeyColumnName(propertyName, propertyEntityName, propertyTableName,
				referencedColumnName);
		return ((base != null) && (base.length() > 0)) ? base + "_id" : base;
	}
}