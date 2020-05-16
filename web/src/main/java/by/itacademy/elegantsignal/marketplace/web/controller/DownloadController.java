package by.itacademy.elegantsignal.marketplace.web.controller;

import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IBook;
import by.itacademy.elegantsignal.marketplace.filestorage.IFileUtils;
import by.itacademy.elegantsignal.marketplace.service.IBookService;
import by.itacademy.elegantsignal.marketplace.service.IDownloadService;
import by.itacademy.elegantsignal.marketplace.web.security.ExtendedToken;
import org.apache.tika.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;


@Controller
@RequestMapping("/downloads")
public class DownloadController {

	@Autowired IBookService bookService;
	@Autowired IDownloadService downloadService;
	@Autowired IFileUtils fileUtils;

	@GetMapping(value = "/{token}", produces = MediaType.APPLICATION_PDF_VALUE)
	@ResponseBody
	public byte[] download(@PathVariable(name = "token") final String token, final HttpServletResponse response) throws IOException {
		final IBook book = downloadService.getDownloadByToken(token).getOrderItem().getProduct().getBook();
		final File pdf = fileUtils.getAbsolutePath(book.getPdf());
		// final InputStream in = FileUtils.openInputStream(pdf);
		final InputStream in = new FileInputStream(pdf);

		response.setHeader("Content-Disposition", "filename=" + fileUtils.getFileNameFromEntity(book, "pdf"));
		return IOUtils.toByteArray(in);
	}

	@GetMapping()
	public ModelAndView index(final HttpServletRequest req, final ExtendedToken token) {
		return null;
	}
}
