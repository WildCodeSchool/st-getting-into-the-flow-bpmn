package dev.wcs.tutoring.ssm.persistentstate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PersistentStateApplication implements CommandLineRunner {

	private final Persist persist;

	public PersistentStateApplication(Persist persist) {
		this.persist = persist;
	}

	public static void main(String[] args) {
		SpringApplication.run(PersistentStateApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println(persist.listDbEntries());
		persist.change(3, "CLOSE");
		System.out.println(persist.listDbEntries());

	}
}
