package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class TodoController {

    //最初の呼び出し
    @Autowired
    TodoRepository todoRepository;
    @Autowired
    TodoService todoService;

    @RequestMapping(value = "/")
    public String index(Model model) {
        List alltodo = todoRepository.findAllByOrderByMakedateDesc();
        Todo todo = new Todo();
        if (CollectionUtils.isEmpty(alltodo)) {
            model.addAttribute("todolist", null);
            model.addAttribute("todo", todo);
            return "index";
        } else {
            model.addAttribute("todolist", alltodo);
            model.addAttribute("todo", todo);
            return "index";
        }

    }

    //ToDoの追加を押した時
    @PostMapping(value = "/", params = "addTodo")
    public String todoRegister(@Valid @ModelAttribute Todo todo,
                               BindingResult result,
                               Model model,
                               @RequestParam("name") String name,
                               @RequestParam("limitdate") String limit_date) throws ParseException {

        if (result.hasErrors()) {
            System.out.println(result);
            List todo2 = todoRepository.findAllByOrderByMakedateDesc();
            model.addAttribute("todolist", todo2);
            return "index";
        }
        int errorcheck = errorcheck(name);
        if (errorcheck == -1) {//error
            List todo3 = todoRepository.findAll();
            model.addAttribute("todolist", todo3);
            String err = "既に作成されています。";
            System.out.println("the same");
            model.addAttribute("err", err);
            return "index";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        String make_date = sdf.format(new Date());
        Date makedate = sdf.parse(make_date);
        Date limitdate = sdf.parse(limit_date);
        Todo addtodo = new Todo(name, limitdate, makedate, false);
        todoRepository.saveAndFlush(addtodo);//レポジトリに保存する
        List newTodo = todoRepository.findAllByOrderByMakedateDesc();
        model.addAttribute("todolist", newTodo);
        model.addAttribute("todoform", todo);
        return "index";

    }

    //未完了、完了を押したとき　8/8 ok
    @PostMapping(value = "/", params = "finish")
    public String finish(Model model, @RequestParam("finish") Long id) {
        Todo a = todoService.updateFinish(id);//aいらない？？
        List todo = todoRepository.findAllByOrderByMakedateDesc();
        model.addAttribute("todolist", todo);
        return "index";
    }

    @RequestMapping("/edit")
    public String edit(Model model, @RequestParam("edit") Long id) {
        Todo todo = new Todo();
        Todo onetodo = todoService.getTodo(id);
        model.addAttribute("todolist", onetodo);
        model.addAttribute("todo", todo);
        return "edit";
    }

    //更新する　8/8 ok
    @PostMapping(value = "/", params = "update")
    public String update(@Valid @ModelAttribute Todo todo,
                         BindingResult result,
                         Model model,
                         @RequestParam("update") Long id,
                         @RequestParam("name") String name,
                         @RequestParam("limitdate") String limit_date) throws ParseException {
        if (result.hasErrors()) {
            System.out.println(result);
            Todo todo1 = todoService.getTodo(id);
            model.addAttribute("todolist", todo1);
            return "edit";
        }
        int errorcheck = errorcheck(name);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        Date limitdate = sdf.parse(limit_date);
        todoService.updateNameandLimitDate(id, name, limitdate);
        List updatetodo = todoRepository.findAllByOrderByMakedateDesc();
        model.addAttribute("todolist", updatetodo);
        return index(model);
    }

    @RequestMapping("/search")
    public String search(Model model) {
        int count = -1;
        model.addAttribute("count", count);
        return "search";
    }

    @PostMapping(value = "/search", params = "search")
    public String search_preview(Model model, @RequestParam("search_name") String name) {
        if(name==""){
            int count=0;
            List todo=null;
            model.addAttribute("data", todo);
            model.addAttribute("count", count);
            model.addAttribute("search_data", name);
            return "search";
        }
        else{
            StringBuilder buf = new StringBuilder();//後で関数にわける
            buf.append("%");
            buf.append(name);
            buf.append("%");
            List todo = todoRepository.findByNameLikeAndFinishOrderByMakedateDesc(buf.toString(), false);
            int count = todo.size();
            model.addAttribute("data", todo);
            model.addAttribute("count", count);
            model.addAttribute("search_data", name);
            return "search";
        }

    }

    @PostMapping(value = "/search", params = "finish", produces = "text/plain;charset=UTF-8")
    public String search_finish(Model model, @RequestParam("finish") Long id, @RequestParam("search_name") String name) {
        todoService.updateFinish(id);
        StringBuilder buf = new StringBuilder();//後で関数にわける
        buf.append("%");
        buf.append(name);
        buf.append("%");
        List todo = todoRepository.findByNameLikeOrderByMakedateDesc(buf.toString());
        int count = todo.size();
        model.addAttribute("data", todo);
        model.addAttribute("count", count);
        model.addAttribute("search_data", name);
        return "search";
    }

    public int errorcheck(String name) {//エラーメッセージも渡す
        List todo = todoRepository.findByNameOrderByMakedateDesc(name);
        if (CollectionUtils.isEmpty(todo)) {
            int mes = 1;
            return mes;
        } else {
            int mes = -1;//同じものが存在
            return mes;
        }
    }
}


