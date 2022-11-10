package com.example.personalprj_board.controller;

import com.example.personalprj_board.entity.ArticlesEntity;
import com.example.personalprj_board.repository.ArticlesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class ArtController {
    @Autowired
    ArticlesRepo articlesRepo;
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
        List<ArticlesEntity> artData = articlesRepo.findAll();
        model.addAttribute("articlesData", artData);
        return "/articles_main";
    }


    //글 작성 기능
    @PostMapping("/insertArt")
    public String insertRoot(ArticlesEntity articlesEntity){
        articlesRepo.save(articlesEntity);
        return "/articles_main";
    }

    @PostMapping("/selectArt")
    public String selectRoot(ArticlesEntity articlesEntity, Model model){
        Optional<ArticlesEntity> optional = articlesRepo.findById(articlesEntity.getArtId());
        model.addAttribute("selectArtRes", optional.get());
        return "/articles_join";
    }


}
