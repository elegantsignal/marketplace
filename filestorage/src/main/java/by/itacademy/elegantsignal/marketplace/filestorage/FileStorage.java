package by.itacademy.elegantsignal.marketplace.filestorage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IBook;
import org.springframework.stereotype.Repository;


@Repository
public class FileStorage implements IFileStorage {

	IFileUtils fileUtils = new FileUtils();

	final Logger LOGGER = LoggerFactory.getLogger(FileStorage.class);

	@Override
	public void saveCover(final IBook book) {
		String logMsg;

		if (book.getCover() == null) {
			return;
		}

		logMsg = String.format("Will try to save file: %s", book.getCover());
		LOGGER.info(logMsg);

		final String fileExtension = fileUtils.getFileExtension(book.getCover());
		if (!"jpeg".equals(fileExtension) && !"png".equals(fileExtension)) {
			throw new IllegalArgumentException("Bad file type");
		}

		final String fileName = book.getTitle().replace(" ", "_").toLowerCase() + "." + fileExtension;
		final Path relativeDir = Paths.get("media");
		final Path rootDir = Paths.get("/home/binbrayer/projects/elegantsignal/marketplace/");
		final Path absoluteDir = rootDir.resolve(relativeDir);

		try {
			Files.move(
					book.getCover().toPath(),
					absoluteDir.resolve(fileName),
					StandardCopyOption.REPLACE_EXISTING);
		} catch (final IOException e) {
			logMsg = String.format("Can't copy file from tmp %s", absoluteDir.resolve(fileName));
			LOGGER.warn(logMsg);
		}

		book.setCover(new File(relativeDir.resolve(fileName).toString()));
	}

}
