package by.itacademy.elegantsignal.marketplace.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.itacademy.elegantsignal.marketplace.daoapi.ILanguageDao;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.ILanguage;
import by.itacademy.elegantsignal.marketplace.service.ILanguageService;


@Service
public class LanguageServiceImpl implements ILanguageService {
	private ILanguageDao languageDao;

	@Autowired
	public LanguageServiceImpl(ILanguageDao languageDao) {
		this.languageDao = languageDao;
	}

	@Override
	public ILanguage createEntity() {
		return languageDao.createEntity();
	}

	@Override
	public void save(final ILanguage entity) {
		if (entity.getId() == null) {
			languageDao.insert(entity);
		} else {
			languageDao.update(entity);
		}
	}

	@Override
	public ILanguage get(final Integer id) {
		return languageDao.get(id);
	}

	@Override
	public void delete(final Integer id) {
		languageDao.delete(id);
	}

	@Override
	public void deleteAll() {
		languageDao.deleteAll();
	}

	@Override
	public List<ILanguage> getAll() {
		return languageDao.selectAll();
	}

}
