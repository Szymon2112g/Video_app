package com.wideoapp.WideoAppDatabase.web.controller;

import com.wideoapp.WideoAppDatabase.Service.VideoInformationService;
import com.wideoapp.WideoAppDatabase.Web.Model.ExtendedVideoInformation;
import com.wideoapp.WideoAppDatabase.Web.controller.VideoInformationRestController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(VideoInformationRestController.class)
public class VideoInformationRestControllerTest {

    @MockBean
    VideoInformationService videoInformationService;

    @Autowired
    MockMvc mockMvc;

    List<ExtendedVideoInformation> extendedVideosInformation;

    @BeforeEach
    void setUp() {

        List<ExtendedVideoInformation> cos = new ArrayList<>();

        cos.add(
                new ExtendedVideoInformation(1,"url","title","desc","fn",
                        "ln",1,2,"purl","date",1,2));

        extendedVideosInformation = cos;

    }

    @Test
    void getVideosOnTime() {
        given(videoInformationService.getVideosOnTime()).willReturn(extendedVideosInformation);

        try {
            mockMvc.perform(get("/videos/ontime"))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$[0].id", is(1)))
                    .andExpect(jsonPath("$[0].url", is("url")))
                    .andExpect(jsonPath("$[0].title", is("title")))
                    .andExpect(jsonPath("$[0].description", is("desc")))
                    .andExpect(jsonPath("$[0].firstName", is("fn")))
                    .andExpect(jsonPath("$[0].lastName", is("ln")))
                    .andExpect(jsonPath("$[0].userId", is(1)))
                    .andExpect(jsonPath("$[0].display", is(2)))
                    .andExpect(jsonPath("$[0].photoUrl", is("purl")))
                    .andExpect(jsonPath("$[0].date", is("date")))
                    .andExpect(jsonPath("$[0].likes", is(1)))
                    .andExpect(jsonPath("$[0].dislikes", is(2)));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
