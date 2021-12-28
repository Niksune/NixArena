package net.niksune.NixArena.Web;

import net.niksune.NixArena.Core.beans.Account;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class NixArenaWebApplication {

	public static void main(String[] args) {

		SpringApplication.run(NixArenaWebApplication.class, args);

		System.out.println("Hello Server");

		Account a = new Account("moi@gmail.com","paaaaass");

		System.out.println(a);


	}

}
