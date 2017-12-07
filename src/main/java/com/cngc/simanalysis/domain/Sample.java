package com.cngc.simanalysis.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Sample {
    @Id
    @GeneratedValue
    private Long id;
    @NotEmpty(message = "主题必填!")
    @Column(nullable = true)
    private String title;
    @NotEmpty(message = "内容必填!")
    @Column(nullable = true)
    private String message;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
