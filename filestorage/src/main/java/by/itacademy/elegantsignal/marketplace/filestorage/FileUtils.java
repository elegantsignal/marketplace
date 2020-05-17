package by.itacademy.elegantsignal.marketplace.filestorage;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IBook;
import org.apache.tika.Tika;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;


@Repository
public class FileUtils implements IFileUtils {

	private static final Logger LOGGER = LoggerFactory.getLogger(FileUtils.class);

	@Override
	public File saveTmpFile(final InputStream inputStream) {
		final String uuid = UUID.randomUUID().toString();

		File tempFile = null;
		try {
			tempFile = File.createTempFile(uuid, ".tmp");
		} catch (final IOException e) {
			LOGGER.error("Can't create temporary file");
			LOGGER.error(String.valueOf(e));
			System.exit(1);
		}

		try {
			Files.copy(inputStream, tempFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
		} catch (final IOException e) {
			LOGGER.warn("Can't save tmp file");
		}

		LOGGER.info("Uploaded file: {}", tempFile.getAbsolutePath());

		return tempFile;
	}

	@Override
	public String getFileExtension(final File image) {
		final Tika tika = new Tika();

		final String mimeType;
		try {
			mimeType = tika.detect(image);
		} catch (final IOException e) {
			throw new IllegalArgumentException("Can't detect type of this file");
		}

		final String[] tmp = mimeType.split("/");
		final String type = tmp[0];
		final String extension = tmp[1];

		return extension;
	}

	@Override
	public File getAbsolutePath(final Path path) {
		final Path rootDir = Paths.get(System.getenv("ASSETS_ROOT"));
		return rootDir.resolve(path).toFile();
	}

	@Override
	public File getAbsolutePath(final String path) {
		return getAbsolutePath(Paths.get(path));
	}

	@Override
	public File getAbsolutePath(final File path) {
		return getAbsolutePath(Paths.get(path.getPath()));
	}

	@Override public String getFileNameFromEntity(final IBook book, final String extension) {
		return book.getTitle().toLowerCase().trim().replaceAll("\\W", "_") + "." + extension;
	}
}
