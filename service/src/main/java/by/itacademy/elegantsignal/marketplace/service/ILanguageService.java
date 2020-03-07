package by.itacademy.elegantsignal.marketplace.service;

import java.util.List;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.ILanguage;


public interface ILanguageService {

	ILanguage get(Integer id);

	List<ILanguage> getAll();

	void save(ILanguage entity);

	void delete(Integer id);

	void deleteAll();

	ILanguage createEntity();

}
