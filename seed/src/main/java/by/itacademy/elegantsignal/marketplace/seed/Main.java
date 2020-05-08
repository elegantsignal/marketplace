package by.itacademy.elegantsignal.marketplace.seed;

import java.io.IOException;
import java.sql.SQLException;


public class Main {

	public static void main(String[] args) throws IOException, SQLException {
		Seed seed = new Seed();
		seed.seedData();
	}
}
