package org.example.vt.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.vt.entity.Board;
import org.example.vt.entity.Member;
import org.example.vt.entity.Post;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JdbcFacadePostRepository implements FacadePostRepository {
    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String userName;
    @Value("${spring.datasource.password}")
    private String password;

    @Override
    public Page<Post> findAll(Pageable pageable) {
        int pageNumber = pageable.getPageNumber();
        int pageSize = pageable.getPageSize();
        try (Connection connection = createConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement("select * from post limit ?, ?");
            preparedStatement.setInt(1, (pageNumber - 1) * pageSize);
            preparedStatement.setInt(2, pageSize);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Post> posts = new ArrayList<>();
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String title = resultSet.getString("title");
                String content = resultSet.getString("content");
                long memberId = resultSet.getLong("member_id");
                long boardId = resultSet.getLong("board_id");
                posts.add(new Post(id, title, content,new Member(memberId,null,null), new Board(boardId,null)));
            }
            posts = getPosts(posts, connection);
            preparedStatement = connection.prepareStatement("select count(post.id) as C from post");
            resultSet = preparedStatement.executeQuery();
            long size = resultSet.getLong("C");
            return new PageImpl<>(posts, pageable, size);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static List<Post> getPosts(List<Post> posts, Connection connection) {
        return posts.stream().map(post -> {
            try {
                PreparedStatement preparedStatement2 = connection.prepareStatement("select * from member where id = ?");
                preparedStatement2.setLong(1, post.getMember().getId());
                ResultSet resultSet2 = preparedStatement2.executeQuery();
                Long memberId = resultSet2.getLong("id");
                String username = resultSet2.getString("username");
                String password = resultSet2.getString("password");
                Member member = new Member(memberId, username, password);
                return new Post(post.getId(), post.getTitle(), post.getContent(), member, post.getBoard());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }).map(post -> {
            try {
                PreparedStatement preparedStatement2 = connection.prepareStatement("select * from board where id = ?");
                preparedStatement2.setLong(1, post.getBoard().getId());
                ResultSet resultSet2 = preparedStatement2.executeQuery();
                Long boardId = resultSet2.getLong("id");
                String name = resultSet2.getString("name");
                Board board = new Board(boardId, name);
                return new Post(post.getId(), post.getTitle(), post.getContent(), post.getMember(), board);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }).toList();
    }

    @Override
    public Post save(Post post) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    private Connection createConnection() {
        try {
            return DriverManager.getConnection(url, userName, password);
        } catch (SQLException e) {
            throw new RuntimeException("DB 접속 에러", e);
        }
    }
}

