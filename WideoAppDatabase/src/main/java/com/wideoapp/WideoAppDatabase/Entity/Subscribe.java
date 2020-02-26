package com.wideoapp.WideoAppDatabase.Entity;

import javax.persistence.*;

@Entity
@Table(name = "subscribe")
public class Subscribe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "subscribe_id")
    private int subscribe;

    public Subscribe() {}

    public Subscribe(int subscribe) {
        this.subscribe = subscribe;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSubscribe() {
        return subscribe;
    }

    public void setSubscribe(int subscribe) {
        this.subscribe = subscribe;
    }
}
