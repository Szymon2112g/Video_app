package com.wideoapp.WideoAppSecurity.Web.Model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class VideoToSend {

    @Builder
    public VideoToSend(String email, String url, String title, String description, int display, String photoUrl) {
        this.email = email;
        this.url = url;
        this.title = title;
        this.description = description;
        this.display = display;
        this.photoUrl = photoUrl;
    }

    private String email;
    private String url;
    private String title;
    private String description;
    private int display;
    private String photoUrl;

    @Override
    public String toString() {
        return email +" "+
        url +" "+
        title +" "+
        description +" "+
        display +" "+
        photoUrl ;
    }
}

