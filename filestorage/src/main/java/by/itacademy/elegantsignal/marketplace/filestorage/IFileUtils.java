package by.itacademy.elegantsignal.marketplace.filestorage;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public interface IFileUtils {

	File saveTmpFile(InputStream inputStream);

	String getFileExtension(File image) throws IllegalArgumentException;

}
