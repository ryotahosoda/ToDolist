package com.example.demo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;


@Entity
@Table(name = "todo")
public class Todo {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Date limit_date;
    private Date make_date;
    private Boolean finish;

    public Todo() {
        super();
    }

    //日付の処理必要
    public Todo(String name, Date limit_date, Date make_date, Boolean finish) {
        super();
        this.name=name;
        this.limit_date=limit_date;
        this.make_date=make_date;
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
    }    public Date getLimit_date(){
        return limit_date;
    }
    public void setLimit_date(Date limit_date){
        this.limit_date=limit_date;
    }
    public Date getMake_date(){
        return make_date;
    }
    public void setMake_date(Date make_date){
        this.make_date=make_date;
    }
    public Boolean getFinish(){ return finish; }
    public void setFinish(Boolean finish){
        this.finish=finish;
    }



}
