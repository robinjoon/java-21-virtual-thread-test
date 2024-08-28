package org.example.vt.repository;

import lombok.RequiredArgsConstructor;
import org.example.vt.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@RequiredArgsConstructor
public class FacadeJpaPostRepository implements FacadePostRepository {
    private final JpaPostRepository jpaPostRepository;
    @Override
    public Page<Post> findAll(Pageable pageable) {
        return jpaPostRepository.findAll(pageable);
    }

    @Override
    public Post save(Post post) {
        return jpaPostRepository.save(post);
    }

    @Override
    public void deleteById(Long id) {
        jpaPostRepository.deleteById(id);
    }
}
