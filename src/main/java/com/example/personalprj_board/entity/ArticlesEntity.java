package com.example.personalprj_board.entity;

import lombok.Data;
import lombok.Generated;

import javax.persistence.*;

@Entity
@Data
public class ArticlesEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    int artId;
    @Column
    String artHead;
    @Column
    String artBody;
    @Column
    String artDate;
    @Column
    int artHits;
    @Column
    String artPw;
}
