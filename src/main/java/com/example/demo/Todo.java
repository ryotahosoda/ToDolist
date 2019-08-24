package com.example.demo;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import javax.validation.constraints.*;


@Entity
@Table(name = "todo")
public class Todo {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 1,max = 30,message = "1文字以上30文字以下で入力してください")
    private String name;

    @NotNull
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy年MM月dd日")
    @Column(name = "limit_date")
    private Date limitdate;

    @Temporal(TemporalType.DATE)
    @Column(name = "make_date")
    private Date makedate;

    private Boolean finish;

    public Todo() {
        super();
    }

    public Todo(String name, Date limit_date, Date make_date, Boolean finish) {
        super();
        this.name=name;
        this.limitdate=limit_date;
        this.makedate=make_date;
        this.finish=finish;
    }

    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id=id;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name=name;
    }
    public Date getLimitdate(){
        return limitdate;
    }
    public void setLimitdate(Date limit_date){
        this.limitdate=limit_date;
    }
    public Date getMakedate(){
        return makedate;
    }
    public void setMakedate(Date make_date){
        this.makedate=make_date;
    }
    public Boolean getFinish(){ return finish; }
    public void setFinish(Boolean finish){
        this.finish=finish;
    }



}

