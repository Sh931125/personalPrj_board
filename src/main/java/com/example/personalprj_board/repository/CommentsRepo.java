package com.example.personalprj_board.repository;

import com.example.personalprj_board.entity.ArticlesEntity;
import com.example.personalprj_board.entity.CommentsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface CommentsRepo extends JpaRepository<CommentsEntity, Integer> {
    List<CommentsEntity> findByArtId(ArticlesEntity articlesEntity);
    @Transactional
    void deleteAllByArtId(ArticlesEntity articlesEntity);
}
