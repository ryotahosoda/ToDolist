package com.example.demo;

import com.example.demo.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Controller
public class TodoController{

    @Autowired
    TodoRepository todoRepository;
    @RequestMapping(value = "/")
    public String index(Model model){
        List todo=(List) todoRepository.findAll();
        model.addAttribute("todolist", todo);
        System.out.println(todo);
        return "index";
    }

//    @GetMapping("/")
//    public String todoForm( Model model){
//        model.addAttribute("todo", new Todo());
//        return "index";
//    }

    @PostMapping("/")
    public String todoRegister(Model model,
                               @RequestParam("name") String name,
                               @RequestParam("limit_date") @DateTimeFormat(pattern = "yyyy/MM/dd") Date limit_date)
    {
        Date make_date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        Todo todo = new Todo(name,limit_date,make_date, false);
        todoRepository.saveAndFlush(todo);//レポジトリに保存する
        List Todo=(List) todoRepository.findAll();
        model.addAttribute("todolist", Todo);
        return "index";
    }

    @RequestMapping("/edit")
    public String edit(){
        return "/edit";
    }

    @RequestMapping("/search")
    public String search(){
        return "/search";
    }

}


