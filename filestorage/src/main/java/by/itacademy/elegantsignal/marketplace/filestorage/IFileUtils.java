package by.itacademy.elegantsignal.marketplace.filestorage;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IBook;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Path;


public interface IFileUtils {

	File saveTmpFile(InputStream inputStream);

	String getFileExtension(File image) throws IllegalArgumentException;

	File getAbsolutePath(String path);

	File getAbsolutePath(Path path);

	File getAbsolutePath(File path);

	String getFileNameFromEntity(IBook book, String extension);
}
