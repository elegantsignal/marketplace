package by.itacademy.elegantsignal.marketplace.service;

import java.io.File;


public interface IRestrictedAssetsService {

	File getBookPdfByToken(String token);
}
