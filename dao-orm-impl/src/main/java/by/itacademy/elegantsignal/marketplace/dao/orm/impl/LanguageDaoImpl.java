package by.itacademy.elegantsignal.marketplace.dao.orm.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import by.itacademy.elegantsignal.marketplace.dao.orm.impl.entity.Language;
import by.itacademy.elegantsignal.marketplace.daoapi.ILanguageDao;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.ILanguage;
import by.itacademy.elegantsignal.marketplace.daoapi.filter.LanguageFilter;


@Repository
public class LanguageDaoImpl extends AbstractDaoImpl<ILanguage, Integer> implements ILanguageDao {

	protected LanguageDaoImpl() {
		super(Language.class);
	}

	@Override
	public ILanguage createEntity() {
		return new Language();
	}

	@Override
	public List<ILanguage> find(final LanguageFilter filter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getCount(final LanguageFilter filter) {
		// TODO Auto-generated method stub
		return 0;
	}
}
