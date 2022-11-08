package com.example.personalprj_board.controller;

import com.example.personalprj_board.dto.Articles;
import com.example.personalprj_board.jpa.ArticlesJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ArtController {
    @Autowired
    ArticlesJpa articlesJpa;
//메인 뷰
    @GetMapping("/")
    public String root(){
        return "redirect:/main";
    }
//글 작성 뷰
    @GetMapping("/create")
    public String createRoot(){
        return "/articles_create";
    }
//메인 뷰 + 게시물 목록
    @GetMapping("/main")
    public String mainRoot(Model model){
        List<Articles> artData = articlesJpa.findAll();
        model.addAttribute("articlesData", artData);
        return "/articles_main";
    }


//글 작성 기능
    @PostMapping("/insertArt")
    public String insertRoot(Articles articles){
        articlesJpa.save(articles);
        return"/articles_main";
    }

    @PostMapping("selectArt")
    public void selectRoot(Articles articles){
        articlesJpa.findById(articles.getArtId());
    }


}
