package com.wideoapp.WideoAppSecurity.Web.Model;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
public class ExtendedVideoInformation {
    private int id;
    private String url;
    private String title;
    private String description;
    private String firstName;
    private String lastName;
    private int userId;
    private int display;
    private String photoUrl;
    private String date;
    private int likes;
    private int dislikes;

    @Builder
    public ExtendedVideoInformation(
            int id,
            String url,
            String title,
            String description,
            String firstName,
            String lastName,
            int userId,
            int display,
            String photoUrl,
            String date,
            int likes,
            int dislikes) {
        this.id = id;
        this.url = url;
        this.title = title;
        this.description = description;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userId = userId;
        this.display = display;
        this.photoUrl = photoUrl;
        this.date = date;
        this.likes = likes;
        this.dislikes = dislikes;
    }
}



