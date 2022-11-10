package com.example.personalprj_board.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class CommentsEntity {

    @ManyToOne
    @JoinColumn (name="art_id")
    ArticlesEntity artId;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    int comId;

    @Column
    String comBody;

    @Column
    String comPw;

//    public CommentsEntity(ArticlesEntity articlesEntity){
//        this.artId = articlesEntity;
//    }

}
