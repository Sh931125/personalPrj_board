package com.example.personalprj_board.controller;

import com.example.personalprj_board.entity.ArticlesEntity;
import com.example.personalprj_board.entity.CommentsEntity;
import com.example.personalprj_board.repository.ArticlesRepo;
import com.example.personalprj_board.repository.CommentsRepo;
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
    @Autowired
    CommentsRepo commentsRepo;


    //메인 뷰
    @GetMapping(value = {"/", "/main"})
    public String mainRoot(){
        return "/articles_main";
    }

    //글 작성 뷰
    @GetMapping("/create")
    public String createRoot(){
        return "/articles_create";
    }

    //게시물 목록
    @GetMapping("/selectArtAll")
    public String listRoot(Model model){
        List<ArticlesEntity> artData = articlesRepo.findAll();
        model.addAttribute("articlesData", artData);
        return "/articles_list";
    }

    //글 작성 기능
    @PostMapping("/insertArt")
    public String insertArtRoot(ArticlesEntity articlesEntity){
        articlesRepo.save(articlesEntity);
        return "/articles_main";
    }


    //댓글 작성 기능
    @PostMapping("insertCom")
    public String insertComRoot(CommentsEntity commentsEntity){
        System.out.println(commentsEntity);

    //repo 각각 생성 안 했을 때 시도했던 형변환 자료
    //        int Aid = commentsEntity.getArtId().getArtId();
    //        Optional<ArticlesEntity> articlesEntity = articlesRepo.findById(Aid);
    //        commentsEntity.setArtId(articlesEntity.get());
        commentsRepo.save(commentsEntity);
        return "/articles_main";
    }


    //글 제목 클릭 시 글 내용 조회
    @PostMapping("/selectArt")
    public String selectRoot(ArticlesEntity articlesEntity, Model model){

        //게시글
        Optional<ArticlesEntity> optional = articlesRepo.findById(articlesEntity.getArtId());
        //댓글
        List<CommentsEntity> optional2 = commentsRepo.findByArtId(articlesEntity);

        model.addAttribute("selectArtRes", optional.get());
        model.addAttribute("selectComRes", optional2);
        return "/articles_join";
    }

    //조회수 증가 메소드
    @PostMapping("/plusHits")
    public String plusHitsRoot(ArticlesEntity articlesEntity) {
        //게시글
        Optional<ArticlesEntity> optional = articlesRepo.findById(articlesEntity.getArtId());

        //게시글 조회수 +1 증가
        optional.get().setArtHits(optional.get().getArtHits() + 1);
        articlesRepo.save(optional.get());

        return "/articles_main";
    }

    //게시글 삭제 메소드
    @PostMapping("/deleteArt")
    public String deleteArtRoot(ArticlesEntity articlesEntity) {
        commentsRepo.deleteAllByArtId(articlesEntity);
        articlesRepo.deleteById(articlesEntity.getArtId());
        return "/articles_main";
    }

    //댓글 삭제 메소드
    @PostMapping("/deleteCom")
    public String deleteComRoot(CommentsEntity commentsEntity) {
        commentsRepo.deleteById(commentsEntity.getComId());
        return "/articles_main";
    }

}
