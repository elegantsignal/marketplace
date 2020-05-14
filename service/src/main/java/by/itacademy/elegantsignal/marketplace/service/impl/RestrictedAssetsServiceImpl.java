package by.itacademy.elegantsignal.marketplace.service.impl;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IDownload;
import by.itacademy.elegantsignal.marketplace.service.IDownloadService;
import by.itacademy.elegantsignal.marketplace.service.IRestrictedAssetsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;


@Service
public class RestrictedAssetsServiceImpl implements IRestrictedAssetsService {

	@Autowired IDownloadService downloadService;

	@Override public File getBookPdfByToken(final String token) {
		final IDownload download = downloadService.getDownloadByToken(token);
		return download.getOrderItem().getProduct().getBook().getPdf();
	}
}
