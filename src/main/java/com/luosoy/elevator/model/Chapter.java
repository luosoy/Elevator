package com.luosoy.elevator.model;

import java.util.Date;
import javax.persistence.*;
import lombok.Data;

@Data
public class Chapter {
    /**
     * 序号
     */
    @Id
    private String xh;

    /**
     * 书xh
     */
    @Column(name = "book_xh")
    private String bookXh;

    /**
     * 章节名称
     */
    private String name;

    /**
     * 章节数据
     */
    private Integer chapternum;

    /**
     * 字数
     */
    private Integer number;

    /**
     * 点击数
     */
    private Integer clickcount;

    /**
     * 更新时间
     */
    private Date updatetime;

    /**
     * 有效标志
     */
    private String yxbz;

    /**
     * 序号
     */
    private String nextxh;

    /**
     * 序号
     */
    private String prexh;

    /**
     * 章节内容
     */
    private String context;
}