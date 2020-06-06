package com.example.intergration.motel.repository;

import com.example.intergration.motel.beans.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
    Optional<Comment> findByIdcomment(int id);

    List<Comment> findByIdnew(int id );

}
