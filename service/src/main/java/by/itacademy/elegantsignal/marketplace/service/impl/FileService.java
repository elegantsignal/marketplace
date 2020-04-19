package by.itacademy.elegantsignal.marketplace.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import by.itacademy.elegantsignal.marketplace.service.IFileService;


@Service
public class FileService implements IFileService {

	private static final Logger LOGGER = LoggerFactory.getLogger(FileService.class);

	@Override
	public File saveTmpImage(final InputStream inputStream) throws IOException {
		final String uuid = UUID.randomUUID().toString();

		final File tempFile = File.createTempFile(uuid, ".tmp");
		LOGGER.info("Uploaded file: {}", tempFile.getAbsolutePath());

		Files.copy(inputStream, tempFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
		return tempFile;
	}

}
