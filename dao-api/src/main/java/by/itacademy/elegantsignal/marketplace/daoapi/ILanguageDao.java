package by.itacademy.elegantsignal.marketplace.daoapi;

import java.util.List;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.ILanguage;
import by.itacademy.elegantsignal.marketplace.daoapi.filter.LanguageFilter;


public interface ILanguageDao extends IDao<ILanguage, Integer> {

	List<ILanguage> find(LanguageFilter filter);

	long getCount(LanguageFilter filter);
}
