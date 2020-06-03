package by.itacademy.elegantsignal.marketplace.filestorage;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IBook;

import java.io.IOException;


public interface IFileStorage {

	@Deprecated void saveCover(IBook book);

	void autoMoveAllFiles(IBook book) throws IOException, WrongFileTypeException;

	void deleteFiles(IBook book);
}
