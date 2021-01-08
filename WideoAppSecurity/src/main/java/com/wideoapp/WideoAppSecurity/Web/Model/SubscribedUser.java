package com.wideoapp.WideoAppSecurity.Web.Model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SubscribedUser {
    private int userId;
    private String email;
    private String name;

    @Builder
    public SubscribedUser(int userId, String email, String name) {
        this.userId = userId;
        this.email = email;
        this.name = name;
    }
}
