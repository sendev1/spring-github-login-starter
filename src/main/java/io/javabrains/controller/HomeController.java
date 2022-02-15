package io.javabrains.controller;

import java.util.Date;
import java.util.List;

import com.datastax.oss.driver.api.core.uuid.Uuids;

import org.ocpsoft.prettytime.PrettyTime;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;

import io.javabrains.entity.Folder;
import io.javabrains.entity.FolderRepository;
import io.javabrains.entity.Message;
import io.javabrains.entity.MessageRepository;
import io.javabrains.service.FolderService;

@Controller
public class HomeController {

    private final FolderRepository folderRepository;
    private final FolderService folderService;
    private List<Folder> userFolders;
    private final MessageRepository messageRepository;

    public HomeController(FolderRepository folderRepository, FolderService folderService, MessageRepository messageRepository) {
        this.folderRepository = folderRepository;
        this.folderService = folderService;
        this.messageRepository = messageRepository;
    }

    @GetMapping(value = "/")
    public String getHomePage(@AuthenticationPrincipal OAuth2User principal, Model model){
        System.out.println(principal);
        if(principal ==null || !StringUtils.hasText(principal.getName())){
            return "index";
        }

        String userId = principal.getAttribute("login");
        userFolders = folderRepository.findByUserId(userId);
        model.addAttribute("userFolders", userFolders);

        model.addAttribute("defaultFolders", folderService.getDefaultFolders(userId));

        
        final List<Message> messages = messageRepository.findAllByMessageId_UserIdAndMessageId_FolderName("sendev1", "Inbox");
        final PrettyTime perttyTime = new PrettyTime();
        messages.stream()
        .forEach(m-> m.setPerttyTime(perttyTime.format(new Date(Uuids.unixTimestamp(m.getMessageId().getTimeUuid())))));
        
        model.addAttribute("messages", messages);

        return "inbox-home-page";
    }
}
