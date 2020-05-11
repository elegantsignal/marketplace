package by.itacademy.elegantsignal.marketplace.filestorage;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IBook;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;


@Repository
@Slf4j
public class PrivateFileStorageImpl implements IPrivateFileStorage {

	IFileUtils fileUtils = new FileUtils();

	@Override public void savePdf(final IBook book) {

		if (book.getCover() == null) {
			return;
		}

		log.info(String.format("Will try to save file: %s", book.getPdf()));

		final String fileExtension = fileUtils.getFileExtension(book.getPdf());

		if (!"pdf".equals(fileExtension)) {
			throw new IllegalArgumentException("Bad file type: " + fileExtension);
		}

		final String fileName = book.getTitle().replace(" ", "_").toLowerCase() + "." + fileExtension;
		final Path relativeDir = Paths.get("private");
		final Path rootDir = Paths.get(System.getenv("ASSETS_ROOT"));
		final Path absoluteDir = rootDir.resolve(relativeDir);

		try {
			Files.move(
				book.getPdf().toPath(),
				absoluteDir.resolve(fileName),
				StandardCopyOption.REPLACE_EXISTING);
		} catch (final IOException e) {
			log.warn(String.format("Can't copy file from tmp %s", absoluteDir.resolve(fileName)));
		}

		book.setPdf(new File(relativeDir.resolve(fileName).toString()));
	}
}
