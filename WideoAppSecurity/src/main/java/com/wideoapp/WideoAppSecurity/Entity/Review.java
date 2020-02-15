package com.wideoapp.WideoAppSecurity.Entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "review")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "comment")
    private String comment;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name="review_user",
            joinColumns=@JoinColumn(name="review_id"),
            inverseJoinColumns=@JoinColumn(name="user_id")
    )
    private List<User> users;

    public Review() {
    }

    public Review(String comment, List<User> users) {
        this.comment = comment;
        this.users = users;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
