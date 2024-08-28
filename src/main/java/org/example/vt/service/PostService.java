package org.example.vt.service;

import lombok.RequiredArgsConstructor;
import org.example.vt.entity.Post;
import org.example.vt.repository.FacadePostRepository;
import org.example.vt.response.BoardResponse;
import org.example.vt.response.PostResponse;
import org.example.vt.response.WriterResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {

    private final FacadePostRepository jpaPostRepository;

    public Page<PostResponse> findAll(Pageable pageable) {
        return jpaPostRepository.findAll(pageable).map(post -> {
            BoardResponse boardResponse = new BoardResponse(post.getBoard().getId(), post.getBoard().getName());
            WriterResponse writerResponse = new WriterResponse(post.getMember().getId(), post.getMember().getUsername());
            return new PostResponse(post.getId(), post.getTitle(), post.getContent(), boardResponse, writerResponse);
        });
    }

    public Post save(Post post) {
        return jpaPostRepository.save(post);
    }

    public void delete(Long id) {
        jpaPostRepository.deleteById(id);
    }
}
