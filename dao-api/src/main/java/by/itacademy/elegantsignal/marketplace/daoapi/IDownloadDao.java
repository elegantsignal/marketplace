package by.itacademy.elegantsignal.marketplace.daoapi;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IDownload;
import by.itacademy.elegantsignal.marketplace.daoapi.filter.DownloadFilter;

import java.util.List;


public interface IDownloadDao extends IDao<IDownload, Integer> {

	List<IDownload> find(DownloadFilter downloadFilter);
}
