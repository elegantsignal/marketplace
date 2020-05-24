package by.itacademy.elegantsignal.marketplace.web.dto;

import org.hibernate.validator.constraints.NotEmpty;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class UserDTO {

	private Integer id;
	@NotNull @NotEmpty private String name;
	@NotNull @NotEmpty private String email;
	@Size(min = 6, max = 32) private String password;
	private Date created;
	private Date updated;

	public Integer getId() {
		return this.id;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(final Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(final Date updated) {
		this.updated = updated;
	}
}
