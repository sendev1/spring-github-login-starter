package io.javabrains;

import java.nio.file.Path;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.javabrains.entity.Folder;
import io.javabrains.entity.FolderRepository;

import org.springframework.boot.autoconfigure.cassandra.CqlSessionBuilderCustomizer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@RestController
public class InboxApplication {

	@Autowired FolderRepository folderRepository;

	public static void main(String[] args) {
		SpringApplication.run(InboxApplication.class, args);
	}

	/* @RequestMapping("/user")
	public String user(@AuthenticationPrincipal OAuth2User principal) {
		System.out.println(principal);
		return principal.getAttribute("name");
	} */


	
	@Bean
	public CqlSessionBuilderCustomizer sessionBuilderCustomizer(DataStaxAstraProperties astraProperties) {
		Path bundle = astraProperties.getSecureConnectBundle().toPath();
		return builder -> builder.withCloudSecureConnectBundle(bundle);
	}

	@PostConstruct
	public void addFolders(){
		final Folder inbox = new Folder("sendev1","Inbox","blue");
		final Folder sentItems = new Folder("sendev1","SentItems","red");
		final Folder important = new Folder("sendev1","Important","yellow");
		folderRepository.save(inbox);
		folderRepository.save(sentItems);
		folderRepository.save(important);
	}

}
