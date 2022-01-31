package io.javabrains.controller;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;

import io.javabrains.entity.Folder;
import io.javabrains.entity.FolderRepository;
import io.javabrains.service.FolderService;

@Controller
public class HomeController {

    private final FolderRepository folderRepository;
    private final FolderService folderService;
    private List<Folder> userFolders;

    public HomeController(FolderRepository folderRepository, FolderService folderService) {
        this.folderRepository = folderRepository;
        this.folderService = folderService;
    }

    @GetMapping(value = "/")
    public String getHomePage(@AuthenticationPrincipal OAuth2User principal,
    Model model){
        System.out.println(principal);
        if(principal ==null || !StringUtils.hasText(principal.getName())){
            return "index";
        }

        String userId = principal.getAttribute("login");
        userFolders = folderRepository.findByUserId(userId);
        model.addAttribute("userFolders", userFolders);

        model.addAttribute("defaultFolders", folderService.getDefaultFolders(userId));

        return "inbox-home-page";
    }
}
