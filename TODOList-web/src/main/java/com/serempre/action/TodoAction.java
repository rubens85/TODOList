/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.serempre.action;

import com.serempre.ejb.inter.local.ICollaboratorLocal;
import com.serempre.ejb.inter.local.ITodoLocal;
import com.serempre.entity.Collaborator;
import com.serempre.entity.Todo;
import com.serempre.handleexception.UserNotFoundException;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author rubens
 */
@ManagedBean
@SessionScoped
public class TodoAction implements Serializable {

    @EJB
    ITodoLocal todoDao;
    @EJB
    ICollaboratorLocal collaboratorDAO;
    
    private List<Todo> listOfTODO;
    private Todo todo;

    /**
     * Creates a new instance of TodoManagedBean
     */
    public TodoAction() {
        todo = new Todo(new Collaborator());
    }

    @PostConstruct
    public void initialize() {
        listOfTODO = todoDao.getTodoALL();
    }

    public void keyupListener() {
        try {
            todo.setCollaborator(
                collaboratorDAO.findByIdentification(todo.getCollaborator().getIdentificationCard())
            );
        }catch(UserNotFoundException unfe) {
            todo.getCollaborator().resetFields();
        }
    }
    
    public void addTODO() {
        todo.setTimeRemaining(Float.parseFloat(todo.getEstimatedTime().toString()));
        todoDao.save(todo);
        this.initialize();
        addMessage("Welcome to Primefaces!!");
    }
    
    public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public List<Todo> getListOfTODO() {
        return listOfTODO;
    }

    public void setListOfTODO(List<Todo> listOfTODO) {
        this.listOfTODO = listOfTODO;
    }

    public Todo getTodo() {
        return todo;
    }

    public void setTodo(Todo todo) {
        this.todo = todo;
    }

}
