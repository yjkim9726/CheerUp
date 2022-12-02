package com.codepresso.cheerup.vo;

import lombok.Data;

@Data //lombok 사용해서 Getter, Setter 사용 X
public class FreeBoard {
    private int boardNo;
    private String title;
    private String content;
    private String category;
    private String writeId;
    private String regdate;
}
