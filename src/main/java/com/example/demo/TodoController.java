package com.example.demo;

import com.example.demo.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;


import java.util.List;


@Controller
//@RequestMapping("/")//これいる？？
public class TodoController{

    @Autowired
    TodoRepository todoRepository;
    @RequestMapping(value = "/")
    public String index(Model model, Model model2){
        List todolist=(List) todoRepository.findAll();
        model.addAttribute("todolist", todolist);
        model2.addAttribute("todo", new Todo());
        return "index";
    }

    @GetMapping("/")
    public String todoForm(Model model){
        model.addAttribute("todo", new Todo());
        return "index";
    }

    @PostMapping("/")
    public String todoRegister(@ModelAttribute Todo todo, Model model){
        model.addAttribute("todo", todo);
        //データベースに登録させる
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


