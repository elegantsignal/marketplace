package by.itacademy.elegantsignal.marketplace.service.impl;

import by.itacademy.elegantsignal.marketplace.daoapi.IBookDao;
import by.itacademy.elegantsignal.marketplace.daoapi.IProductDao;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.enums.ProductType;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IBook;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IProduct;
import by.itacademy.elegantsignal.marketplace.daoapi.filter.BookFilter;
import by.itacademy.elegantsignal.marketplace.filestorage.IFileStorage;
import by.itacademy.elegantsignal.marketplace.filestorage.IFileUtils;
import by.itacademy.elegantsignal.marketplace.filestorage.IPrivateFileStorage;
import by.itacademy.elegantsignal.marketplace.service.IBookService;
import by.itacademy.elegantsignal.marketplace.service.IProductService;
import by.itacademy.elegantsignal.marketplace.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;


@Service
public class BookServiceImpl implements IBookService {

	@Autowired private IUserService userService;
	@Autowired private IProductService productService;
	@Autowired private IBookDao bookDao;
	@Autowired private IProductDao productDao;
	@Autowired private IFileStorage fileStorage;
	@Autowired private IPrivateFileStorage privateFileStorage;
	@Autowired private IFileUtils fileUtils;

	@Override public IBook createEntity() {
		return bookDao.createEntity();
	}

	@Override public IBook save(final IBook book) {
		final Date modifiedOn = new Date();

		book.setUpdated(modifiedOn);

		fileStorage.autoMoveAllFiles(book);

		if (book.getId() == null) {
			book.setId(book.getProduct().getId());
			book.setCreated(modifiedOn);
			bookDao.insert(book);
		} else {
			bookDao.update(book);
		}

		//		sendMail(book.getTitle(), book.getDescription());
		return book;
	}

	@Override public IBook save(final IBook book,
		final Map<String, InputStream> inputStreamMap,
		final BigDecimal price,
		final Integer productOwnerId
	) {
		inputStreamMap.forEach((fileField, inputStream) -> {
			if (inputStream == null) {
				return;
			}

			final File tmp = fileUtils.saveTmpFile(inputStream);
			switch (fileField) {
				case ("cover"):
					book.setCover(tmp);
					break;
				case ("pdf"):
					book.setPdf(tmp);
					break;
				default:
					break;
			}
		});

		final IProduct product;
		if (book.getId() != null) {
			product = productService.get(book.getId());
		} else {
			product = productService.createEntity();
		}
		product.setUser(userService.get(productOwnerId));
		product.setType(ProductType.BOOK);
		product.setPrice(price);
		productService.save(product);

		book.setProduct(product);
		return save(book);
	}

	@Override public IBook get(final Integer id) {
		return bookDao.get(id);
	}

	@Override public IBook getFullInfo(final Integer id) {
		return bookDao.getFullInfo(id);
	}

	@Override public void delete(final Integer id) {
		final IBook book = bookDao.getFullInfo(id);
		bookDao.delete(id);
		productDao.delete(id);
		fileStorage.deleteFiles(book);
	}

	@Override public void deleteAll() {
		bookDao.deleteAll();
	}

	@Override public List<IBook> getAll() {
		return bookDao.selectAll();
	}

	@Override public List<IBook> find(final BookFilter filter) {
		return bookDao.find(filter);
	}

	@Override public long getCount(final BookFilter filter) {
		return bookDao.getCount(filter);
	}

	private void sendMail(final String body, final String subject) {

		final String username = "elegantsignal@gmail.com";
		final String password = "changeme";

		Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", "465");
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.socketFactory.port", "465");
		prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

		Session session = Session.getInstance(prop, new javax.mail.Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {

			final Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("elegantsignal@gmail.com"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("dmitri.zhyvushko@gmail.com"));
			message.setSubject(subject);
			message.setText(body);

			Transport.send(message);

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

	@Override public List<IBook> search(final String string) {
		return bookDao.search(string);
	}

	@Override public IBook createBook(final Integer userId) {
		final IProduct product = productService.createEntity();
		product.setUser(userService.get(userId));
		final IBook book = createEntity();
		book.setProduct(product);
		return book;
	}

	@Override public List<IBook> getBooksByGenres(final List<String> genres) {
		return bookDao.getBooksByGenres(genres);
	}

	@Override public List<IBook> getBooksByGenres(final String... asList) {
		return getBooksByGenres(Arrays.asList(asList));
	}

	@Override public List<IBook> getBooksByAuthorId(final List<Integer> ids) {
		return bookDao.getBooksByAuthorId(ids);
	}

	@Override public List<IBook> getBooksByAuthorId(final Integer... ids) {
		return getBooksByAuthorId(Arrays.asList(ids));
	}
}
