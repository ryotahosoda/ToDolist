package com.example.demo;

import com.example.demo.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;


import java.util.Date;
import java.util.List;


@Controller
//@RequestMapping("/")//これいる？？
public class TodoController{

    @Autowired
    TodoRepository todoRepository;
    @RequestMapping(value = "/")
    public String index(Model model){
        //, Model model2
        List todo=(List) todoRepository.findAll();
        //Iterable<Todo> list = todoRepository.findAll();
        model.addAttribute("todolist", todo);
        System.out.println(todo);
//        model2.addAttribute("todo", new Todo());
        return "index";
    }

//    @GetMapping("/")
//    public String todoForm( Model model){
//        model.addAttribute("todo", new Todo());
//        return "index";
//    }

//    @PostMapping("/")
//    public String todoRegister(Model model,
//                               @RequestParam("name") String name){
//        Todo todo = new Todo(name);
//        todoRepository.saveAndFlush(todo);
//        List Todo=(List) todoRepository.findAll();
//        model.addAttribute("todolist", Todo);
//        //データベースに登録させる
//        return "index";
//    }

    @PostMapping("/")
    public String todoRegister(Model model,
                               @RequestParam("name") String name,
                               @RequestParam("limit_date") Date limit_date,
                               @RequestParam("make_date") Date make_date,
                               @RequestParam("finish") Boolean finish){
        Todo todo = new Todo(name,limit_date,make_date,finish);
        todoRepository.saveAndFlush(todo);
        List Todo=(List) todoRepository.findAll();
        model.addAttribute("todolist", Todo);
        //データベースに登録させる→sampleのデータベースでできた。Date型の処理とBoolean型の処理をどうするか
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


