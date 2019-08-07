package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.example.demo.TodoService;


@Controller
public class TodoController{

    //最初の呼び出し
    @Autowired
    TodoRepository todoRepository;
    TodoService todoService;
    @RequestMapping(value = "/")
    public String index(Model model){
        List todo=todoRepository.findAll();
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
        List Todo=todoRepository.findAll();
        model.addAttribute("todolist", Todo);
        return "index";
    }

    //未完了、完了を押したとき
    @PostMapping(value = "/" ,params = "finish")
    public String finish(Model model,  @RequestParam("finish") Long id){
        System.out.println(id);
        Todo update = todoService.updateFinish(id);
        List todo=(List) todoRepository.findAll();
        model.addAttribute("todolist", todo);
        return "index";
    }

    @RequestMapping("/edit")
    public String edit(@RequestParam("edit") String  a){
        System.out.println(a);
        return "/edit";
    }

    @RequestMapping("/search")
    public String search(){
        return "/search";
    }

}


