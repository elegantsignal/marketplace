package by.itacademy.elegantsignal.marketplace.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IBook;


public interface IFileService {

	File saveTmpImage(InputStream inputStream) throws IOException;

	void saveCover(IBook book);

}
