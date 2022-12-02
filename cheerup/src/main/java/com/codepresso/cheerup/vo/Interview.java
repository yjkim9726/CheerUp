package com.codepresso.cheerup.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Data
public class Interview {

    private int interview_id;
    private String category;
    private String question;
    private int total;
    private int fail;
    private LocalDateTime createdAt;
    private int response_rate;


}
