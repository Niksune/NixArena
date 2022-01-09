package net.niksune.NixArena.Web;

import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class NixArenaWebApplication {

	public static void main(String[] args) {

		SpringApplication.run(NixArenaWebApplication.class, args);

	}


	//Crappy solution to manage the Lazy Fetch : if I do not load the associated entities (AccountService->loadAccounts) it returns null instead of crash
	@Bean
	public static Hibernate5Module datatypeHibernateModule() { return new Hibernate5Module();}


}
