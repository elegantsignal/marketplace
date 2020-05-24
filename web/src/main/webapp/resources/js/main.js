function passwordValidation(password, repeat) {
	if (password.length < 6) {
		return "Min password length is 6";
	}
	return password != repeat ? "Passwords do not match." : ""
}
