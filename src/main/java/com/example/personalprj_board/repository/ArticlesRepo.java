package com.example.personalprj_board.repository;

import com.example.personalprj_board.entity.ArticlesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticlesRepo extends JpaRepository<ArticlesEntity, Integer> {

}
