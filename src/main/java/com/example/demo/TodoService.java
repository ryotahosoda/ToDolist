package com.example.demo;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;


@Service
@Transactional
public class TodoService {
    @Autowired
    TodoRepository todoRepository;

    @Transactional
    public Todo getTodo(Long id){
        Todo todo = todoRepository.findById(id).get();
        if (todo==null){
            System.out.println("nullです");
            System.out.println(todo);
            return todo;
        }
        System.out.println(todo);
        return todo;
    }

    @Transactional
    public List<Todo> getTodoAll(){
        return todoRepository.findAll(new Sort(Sort.Direction.ASC, "make_date"));
    }

    @Transactional
    public Todo createTodo(String name, Date limit_date){
        Todo todo = new Todo();
        Date make_date = new Date();
        todo.setName(name);
        todo.setLimit_date(limit_date);
        todo.setMake_date(make_date);
        todo.setFinish(false);
        return todoRepository.saveAndFlush(todo);
    }

    @Transactional
    public Todo updateNameandLimitDate(Long id, String name, Date limit_date){
        Todo todo = getTodo(id);
        todo.setName(name);
        todo.setLimit_date(limit_date);
        return todoRepository.saveAndFlush(todo);
    }

    @Transactional
    public Todo updateFinish(Long id){
        Todo todo = getTodo(id);c
        if (todo.getFinish()==false){
            todo.setFinish(true);
        }
        else {
            todo.setFinish(false);
        }
        return todoRepository.saveAndFlush(todo);
    }

    @Transactional
    public void deleteTodo(Long id){
        todoRepository.deleteById(id);
    }

}
