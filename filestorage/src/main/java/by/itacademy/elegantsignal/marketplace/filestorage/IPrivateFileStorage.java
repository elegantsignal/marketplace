package by.itacademy.elegantsignal.marketplace.filestorage;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IBook;


public interface IPrivateFileStorage {

	void savePdf(IBook book);

}
