package org.example.vt.repository;

import org.example.vt.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FacadePostRepository {
    Page<Post> findAll(Pageable pageable);
    Post save(Post post);

    void deleteById(Long id);
}
