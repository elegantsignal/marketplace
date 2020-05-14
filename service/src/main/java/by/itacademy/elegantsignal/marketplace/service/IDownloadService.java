package by.itacademy.elegantsignal.marketplace.service;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IDownload;

import javax.transaction.Transactional;
import java.util.List;


public interface IDownloadService {

	IDownload createEntity();

	IDownload get(Integer id);

	@Transactional IDownload save(IDownload download);

	@Transactional List<IDownload> getDownloadsByUserId(Integer userId);

	IDownload getDownloadByToken(String token);
}
