package by.itacademy.elegantsignal.marketplace.filestorage;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;


public interface IFileUtils {

	File saveTmpFile(InputStream inputStream);

	String getFileExtension(File image) throws IllegalArgumentException;

	File getAbsolutePath(String path);

	File getAbsolutePath(Path path);

	File getAbsolutePath(File path);
}
