package by.itacademy.elegantsignal.marketplace.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.itacademy.elegantsignal.marketplace.daoapi.IBookDao;
import by.itacademy.elegantsignal.marketplace.daoapi.entity.table.IBook;
import by.itacademy.elegantsignal.marketplace.daoapi.filter.BookFilter;
import by.itacademy.elegantsignal.marketplace.filestorage.IFileStorage;
import by.itacademy.elegantsignal.marketplace.filestorage.IFileUtils;
import by.itacademy.elegantsignal.marketplace.service.IBookService;


@Service
public class BookServiceImpl implements IBookService {

	@Autowired
	private IBookDao bookDao;

	@Autowired
	private IFileStorage fileStorage;

	@Autowired
	private IFileUtils fileUtils;

	@Override
	public IBook createEntity() {
		return bookDao.createEntity();
	}

	@Override
	public void save(final IBook book) throws IOException {
		final Date modifiedOn = new Date();

		book.setUpdated(modifiedOn);

		if (book.getId() == null) {
			book.setId(book.getProduct().getId());
			book.setCreated(modifiedOn);
			bookDao.insert(book);
		} else {
			bookDao.update(book);
		}
		
//		sendMail(book.getTitle(), book.getDescription());
	}

	@Override
	public void save(final IBook book, final InputStream inputStream) throws IOException {

		book.setCover(fileUtils.saveTmpImage(inputStream));
		fileStorage.saveCover(book);

		save(book);
	}

	@Override
	public IBook get(final Integer id) {
		return bookDao.get(id);
	}

	@Override
	public IBook getFullInfo(final Integer id) {
		return bookDao.getFullInfo(id);

	}

	@Override
	public void delete(final Integer id) {
		bookDao.delete(id);
	}

	@Override
	public void deleteAll() {
		bookDao.deleteAll();
	}

	@Override
	public List<IBook> getAll() {
		return bookDao.selectAll();
	}

	@Override
	public List<IBook> find(final BookFilter filter) {
		return bookDao.find(filter);
	}

	@Override
	@Deprecated
	public void saveWithId(final IBook book) {
		final Date modifiedOn = new Date();
		book.setUpdated(modifiedOn);
		book.setCreated(modifiedOn);
		bookDao.insert(book);
	}

	@Override
	public long getCount(final BookFilter filter) {
		return bookDao.getCount(filter);
	}

	private void sendMail(String body, String subject) {

		final String username = "elegantsignal@gmail.com";
		final String password = "jiuqjpxfhtbkukgg";

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

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("elegantsignal@gmail.com"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("dmitri.zhyvushko@gmail.com"));
			message.setSubject(subject);
			message.setText(body);

			Transport.send(message);

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

}
