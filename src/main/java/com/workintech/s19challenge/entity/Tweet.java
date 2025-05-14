package com.workintech.s19challenge.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.workintech.s19challenge.entity.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "tweet", schema = "public")
public class Tweet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "content")
    @NotBlank(message = "Tweet content cannot be empty!")
    private String content;

    //User - Tweet
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User user;

    //Tweet - Comment
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tweet")
    @JsonManagedReference
    private List<Comment> comments;

    public void addComment(Comment comment) {
        if(comments == null) {
            comments = new ArrayList<>();
        }
        comments.add(comment);
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tweet")
    @JsonManagedReference
    private List<Like> likes;

    public void addLike(Like like) {
        if(likes == null) {
            likes = new ArrayList<>();
        }
        likes.add(like);
    }
}
