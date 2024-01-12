package com.example.demo.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "posting")
public class PostingInfo {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postingId;
    

    @Column(name = "login_id")
    private String loginId;
    
    @Column(name = "posting_title")
    private String postingTitle;
    
    @Column(name = "posting_text")
    private String postingText;

    @Column(name = "image_url")
    private String imageUrl;

    @CreationTimestamp
    @Column(name = "create_at")
    private LocalDateTime createTime;

    @UpdateTimestamp
    @Column(name = "update_at")
    private LocalDateTime updateTime;
    
    @Column(name = "item_id")
    private Integer itemId;

}