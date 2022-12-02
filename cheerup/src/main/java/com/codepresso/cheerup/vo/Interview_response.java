package com.codepresso.cheerup.vo;


import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Interview_response {

    private int interview_id;
    private String id;
    private String category;
    private String question;
    private String interview_answer;
}
