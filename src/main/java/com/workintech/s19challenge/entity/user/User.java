package com.workintech.s19challenge.entity.user;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.workintech.s19challenge.entity.Comment;
import com.workintech.s19challenge.entity.Like;
import com.workintech.s19challenge.entity.Tweet;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "user", schema = "public")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_name")
    @NotBlank(message = "Username cannot be empty!")
    @Size(max = 50, message = "Username cannot be more than 50 characters!")
    private String userName;

    @Column(name = "email")
    @NotBlank(message = "Email cannot be empty!")
    @Pattern(regexp = "^(.+)@(\\S+)$", message = "Email format is not correct!")
    @Size(max = 50, message = "Email cannot be more than 50 characters!")
    private String email;

    @Column(name = "password")
    @NotBlank(message = "Password cannot be empty!")
    @Size(min=6, max = 100, message = "Password must be between 6 and 100 characters!")
    private String password;


    //User - Tweet
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    @JsonManagedReference
    private List<Tweet> tweets;

    public void addTweet(Tweet tweet) {
        if(tweets == null) {
            tweets = new ArrayList<>();
        }
        tweets.add(tweet);
    }

    //User - Comment
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    @JsonManagedReference
    private List<Comment> comments;

    public void addComment(Comment comment) {
        if(comments == null) {
            comments = new ArrayList<>();
        }
        comments.add(comment);
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    @JsonManagedReference
    private List<Like> likes;

    public void addLike(Like like) {
        if(likes == null) {
            likes = new ArrayList<>();
        }
        likes.add(like);
    }


    //User - Role
    //Uni-directional bağ
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH}, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", schema = "public",
            joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> authorities = new HashSet<>();



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        //return UserDetails.super.isAccountNonExpired();
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        //return UserDetails.super.isAccountNonLocked();
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        //return UserDetails.super.isCredentialsNonExpired();
        return true;
    }

    @Override
    public boolean isEnabled() {
        //return UserDetails.super.isEnabled();
        return true;
    }
}
