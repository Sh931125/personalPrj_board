package com.example.personalprj_board.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Articles {
    @Id
    int artId;
    @Column
    String artHead;
    @Column
    String artBody;
    @Column
    String artDate;
    @Column
    String artHits;
    @Column
    String artPw;
}
