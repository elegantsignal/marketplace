package by.itacademy.elegantsignal.marketplace.service.impl;

import by.itacademy.elegantsignal.marketplace.daoapi.IDownloadDao;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IDownload;
import by.itacademy.elegantsignal.marketplace.daoapi.filter.DownloadFilter;
import by.itacademy.elegantsignal.marketplace.service.IDownloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;


@Service
public class DownloadServiceImpl implements IDownloadService {

	@Autowired
	private IDownloadDao downloadDao;

	@Override public IDownload createEntity() {
		return downloadDao.createEntity();
	}

	@Override public IDownload get(final Integer id) {
		return downloadDao.get(id);
	}

	@Override public IDownload save(final IDownload download) {
		final Date modifiedOn = new Date();
		if (download.getId() == null) {
			download.setToken(UUID.randomUUID().toString());
			download.setCreated(modifiedOn);
			downloadDao.insert(download);
		} else {
			downloadDao.update(download);
		}
		return download;
	}

	@Override public List<IDownload> getDownloadsByUserId(final Integer userId) {
		final DownloadFilter downloadFilter = new DownloadFilter().setUserId(userId);
		return downloadDao.find(downloadFilter);
	}

}
