package by.itacademy.elegantsignal.marketplace.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;


public interface IFileService {

	File saveTmpImage(InputStream inputStream) throws IOException;

}
