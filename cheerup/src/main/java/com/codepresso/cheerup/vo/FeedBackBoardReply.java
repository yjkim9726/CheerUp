package com.codepresso.cheerup.vo;

import lombok.Data;

@Data
public class FeedBackBoardReply {
    private int replyNo;
    private int boardNo;
    private String reply;
    private String writeId;
    private String replydate;
}
