package com.wideoapp.WideoAppSecurity.Web.Model;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AddReview {
    private String email;
    private String comment;
    private int videoId;

    @Builder
    public AddReview(String email, String comment, int videoId) {
        this.email = email;
        this.comment = comment;
        this.videoId = videoId;
    }
}
