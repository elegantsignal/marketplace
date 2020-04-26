package by.itacademy.elegantsignal.marketplace.filestorage;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import org.apache.tika.Tika;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;


@Repository
public class FileUtils implements IFileUtils {

	private static final Logger LOGGER = LoggerFactory.getLogger(FileUtils.class);

	@Override
	public File saveTmpImage(final InputStream inputStream) {
		final String uuid = UUID.randomUUID().toString();

		File tempFile = null;
		try {
			tempFile = File.createTempFile(uuid, ".tmp");
		} catch (final IOException e) {
			LOGGER.error("Can't create temporary file");
			e.printStackTrace();
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
	public String getFileExtension(final File image) throws IllegalArgumentException {
		final Tika tika = new Tika();

		String mimeType = "";
		try {
			mimeType = tika.detect(image);
		} catch (final IOException e) {
			throw new IllegalArgumentException("Can't detect type of this file");
		}

		final String[] tmp = mimeType.split("/");
		final String type = tmp[0];
		final String extension = tmp[1];

		if (!"image".equals(type)) {
			throw new IllegalArgumentException("This is not image file");
		}

		return extension;
	}

}
