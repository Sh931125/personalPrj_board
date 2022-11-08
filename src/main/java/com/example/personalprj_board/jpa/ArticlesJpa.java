package com.example.personalprj_board.jpa;

import com.example.personalprj_board.dto.Articles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticlesJpa extends JpaRepository<Articles, Integer> {

}
