package by.itacademy.elegantsignal.marketplace.dao.orm.converter;

import java.io.File;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;


@Converter
public class FileAtributeConverter implements AttributeConverter<File, String> {

	@Override
	public String convertToDatabaseColumn(final File attribute) {
		return attribute.getPath();
	}

	@Override
	public File convertToEntityAttribute(final String dbData) {
		return new File(dbData);
	}

}