package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class TodoController{

    //最初の呼び出し
    @Autowired
    TodoRepository todoRepository;
    @Autowired
    TodoService todoService;
    @RequestMapping(value = "/")
    public String index(Model model){
        List todo=todoRepository.findAll();
        model.addAttribute("todolist", todo);
        return "index";
    }

    //ToDoの追加を押した時
    @PostMapping(value = "/" ,params = "addTodo")
    public String todoRegister(Model model,
                               @RequestParam("name") String name,
                               @RequestParam("limit_date") String limit_date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        String make_date = sdf.format(new Date());
        Date makedate = sdf.parse(make_date);
        Date limitdate = sdf.parse(limit_date);
        Todo todo = new Todo(name,limitdate,makedate, false);
        todoRepository.saveAndFlush(todo);//レポジトリに保存する
        List Todo=todoRepository.findAll();
        model.addAttribute("todolist", Todo);
        return "index";
    }

    //未完了、完了を押したとき　8/8 ok
    @PostMapping(value = "/" ,params = "finish")
    public String finish(Model model,  @RequestParam("finish") Long id){
        Todo a = todoService.updateFinish(id);//aいらない？？
        List todo = todoRepository.findAll();
        model.addAttribute("todolist", todo);
        return "index";
    }

    @RequestMapping("/edit")
    public String edit(Model model, @RequestParam("edit") Long id){
        Todo todo = todoService.getTodo(id);
        model.addAttribute("todolist", todo);
        return "edit";
    }

    //更新する　8/8 ok
    @PostMapping(value = "/",params = "update")
    public String update(Model model,
                         @RequestParam("update") Long id,
                         @RequestParam("name") String name,
                         @RequestParam("limit_date") @DateTimeFormat(pattern = "yyyy/MM/dd") Date limit_date){
        todoService.updateNameandLimitDate(id,name,limit_date);
        List todo = todoRepository.findAll();
        model.addAttribute("todolist", todo);
        return "index";
    }

    @RequestMapping("/search")
    public String search(Model model){
        int count = -1;
        model.addAttribute("count",count);
        return "search";
    }

    @PostMapping(value = "/search", params = "search")
    public String search_preview(Model model,@RequestParam("search_name") String name){
        StringBuilder buf = new StringBuilder();//後で関数にわける
        buf.append("%");
        buf.append(name);
        buf.append("%");
        List todo = todoRepository.findByNameLikeAndFinish(buf.toString(), false);
        int count = todo.size();
        model.addAttribute("data", todo);
        model.addAttribute("count",count);
        model.addAttribute("search_data",name);
        return "search";
    }

    @PostMapping(value = "/search", params = "finish", produces="text/plain;charset=UTF-8")
    public String search_finish(Model model, @RequestParam("finish") Long id,@RequestParam("search_name") String name){
        todoService.updateFinish(id);
        StringBuilder buf = new StringBuilder();//後で関数にわける
        buf.append("%");
        buf.append(name);
        buf.append("%");
        List todo = todoRepository.findByNameLike(buf.toString());
        int count = todo.size();
        model.addAttribute("data", todo);
        model.addAttribute("count",count);
        model.addAttribute("search_data",name);
        return "search";
    }

}


