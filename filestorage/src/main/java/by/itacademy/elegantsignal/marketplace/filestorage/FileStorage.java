package by.itacademy.elegantsignal.marketplace.filestorage;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IBook;
import lombok.extern.slf4j.Slf4j;
import org.apache.tika.Tika;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;


@Repository @Slf4j
public class FileStorage implements IFileStorage {

	@Autowired Tika tika;

	IFileUtils fileUtils = new FileUtils();
	Path rootDir = Paths.get(System.getenv("ASSETS_ROOT"));

	@Override @Deprecated
	public void saveCover(final IBook book) {
		String logMsg;

		if (book.getCover() == null) {
			return;
		}

		logMsg = String.format("Will try to save file: %s", book.getCover());
		log.info(logMsg);

		final String fileExtension = fileUtils.getFileExtension(book.getCover());
		if (!"jpeg".equals(fileExtension) && !"png".equals(fileExtension)) {
			throw new IllegalArgumentException("Bad file type");
		}

		final String fileName = book.getTitle().replace(" ", "_").toLowerCase() + "." + fileExtension;
		final Path relativeDir = Paths.get("media");

		final Path absoluteDir = rootDir.resolve(relativeDir);

		try {
			Files.move(
				book.getCover().toPath(),
				absoluteDir.resolve(fileName),
				StandardCopyOption.REPLACE_EXISTING);
		} catch (final IOException e) {
			logMsg = String.format("Can't copy file from tmp %s", absoluteDir.resolve(fileName));
			log.warn(logMsg);
		}

		book.setCover(new File(relativeDir.resolve(fileName).toString()));
	}

	@Override public void autoMoveAllFiles(final IBook book) throws IOException, WrongFileTypeException {
		moveCover(book);
		movePdf(book);
	}

	private void moveCover(final IBook book) throws IOException, WrongFileTypeException {
		final File file = book.getCover();
		if (file == null || !file.exists() || !file.isFile()) {
			throw new WrongFileTypeException("empty file");
		}

		final String extension = getFileExtension(file, "image/jpeg", "image/png");
		final String fileName = book.getTitle().replace(" ", "_").toLowerCase() + "." + extension;
		final Path destination = rootDir.resolve("media").resolve(fileName);
		try {
			rename(file.toPath(), destination);
		} catch (final IOException e) {
			return;
		}
		book.setCover(Paths.get("media", fileName).toFile());
	}

	@Override public void deleteFiles(final IBook book) {
		if (book.getCover() != null) {
			fileUtils.getAbsolutePath(book.getCover()).delete();
		}

		if (book.getPdf() != null) {
			fileUtils.getAbsolutePath(book.getPdf()).delete();
		}

	}

	private void movePdf(final IBook book) throws IOException, WrongFileTypeException {
		final File file = book.getPdf();
		if (file == null || !file.exists() || !file.isFile()) {
			throw new WrongFileTypeException("empty file");
		}

		final String extension = getFileExtension(file, "application/pdf");

		final String fileName = book.getTitle().replace(" ", "_").toLowerCase() + "." + extension;
		final Path destination = rootDir.resolve("private").resolve(fileName);
		try {
			rename(file.toPath(), destination);
		} catch (final IOException e) {
			return;
		}
		book.setPdf(Paths.get("private", fileName).toFile());
	}

	private void rename(final Path source, final Path destination) throws IOException {
		try {
			Files.move(source, destination, StandardCopyOption.REPLACE_EXISTING);
		} catch (final IOException e) {
			log.error("Can't rename file from {} to {}", source, destination);
			throw new IOException(e);
		}
		log.info("File renamed from {} to {}", source, destination);
	}

	private String getFileExtension(final File file, final String... mimeTypes) throws IOException, WrongFileTypeException {
		log.warn("Try to detect file type");
		final String mimeType = tika.detect(file);
		for (final String type : mimeTypes) {
			if (type.equals(mimeType)) {
				log.warn("Succeed");
				return mimeType.split("/")[1];
			}
		}
		log.warn("Fail");
		throw new WrongFileTypeException(mimeType + " - wrong file type");
	}

}
