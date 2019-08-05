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

    //最初の呼び出し
    @Autowired
    TodoRepository todoRepository;
    @RequestMapping(value = "/")
    public String index(Model model){
        List todo=(List) todoRepository.findAll();
        model.addAttribute("todolist", todo);
        //System.out.println(todo);
        return "index";
    }

    //ToDoの追加を押した時
    @PostMapping(value = "/" ,params = "addTodo")
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
    //未完了、完了を押したとき
    @PostMapping(value = "/" ,params = "finish")
    public String finish(Model model){
        List todo=(List) todoRepository.findAll();
        model.addAttribute("todolist", todo);
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


