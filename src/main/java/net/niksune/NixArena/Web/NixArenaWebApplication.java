package net.niksune.NixArena.Web;

import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class NixArenaWebApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(NixArenaWebApplication.class);
	}

	public static void main(String[] args) {

		SpringApplication.run(NixArenaWebApplication.class, args);

	}

	//Manage the lazy fetch, if a property is not loaded, fill it with a null (not crappy at all)
	@Bean
	public static Hibernate5Module datatypeHibernateModule() { return new Hibernate5Module();}


}
