/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.serempre.action;

import com.serempre.ejb.inter.local.ICollaboratorLocal;
import com.serempre.ejb.inter.local.ITodoLocal;
import com.serempre.ejb.inter.local.IWorkingTimeLocal;
import com.serempre.entity.Collaborator;
import com.serempre.entity.Todo;
import com.serempre.entity.WorkingTime;
import com.serempre.handleexception.UserNotFoundException;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;
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
    @EJB
    IWorkingTimeLocal workingTimeDAO;
    
    private List<Todo> listOfTODO;
    private List<WorkingTime> workingTimeList;
    private Todo todo;
    private Todo todoAux;
    
    private Float time;
    private Date dateReg;
    private boolean buttonUpdateFlag = true;

    /**
     * Creates a new instance of TodoManagedBean
     */
    public TodoAction() {
        this.initFields();
    }

    @PostConstruct
    public void initializeList() {
        listOfTODO = todoDao.getTodoALL();
    }

    public void keyupListener() {
        try {
            todo.setCollaborator(
                collaboratorDAO.findByIdentification(todo.getCollaborator().getIdentificationCard())
            );
            buttonUpdateFlag = false;
        }catch(UserNotFoundException unfe) {
            buttonUpdateFlag = true;
            todo.getCollaborator().resetFields();
        }
    }
    
    public void addTODO() {
        try {
        todo.setTimeRemaining(Float.parseFloat(todo.getEstimatedTime().toString()));
        if(todo.getCollaborator().getId() == null)
            collaboratorDAO.save(todo.getCollaborator());
        todoDao.save(todo);
        this.initializeList();
        addMessage("Registro Exitoso!", FacesMessage.SEVERITY_INFO);
        this.buttonUpdateFlag = false;
    }catch(Exception e) {
        
        addMessage("Error al intentar guardar registro", FacesMessage.SEVERITY_FATAL);
    }
    }
    
    private void initFields() {
        todo = new Todo(new Collaborator());
    }
    
    public void deleteTODO() {
        todoDao.delete(todoAux);
        listOfTODO.remove(todoAux);
        addMessage("Eliminación Exitosa!", FacesMessage.SEVERITY_INFO);
    }
    
    public void updateCollaborator() {
        todo.setCollaborator(collaboratorDAO.updateCollaborator(todo.getCollaborator()));
        this.initializeList();
        addMessage("Actualización Exitosa!", FacesMessage.SEVERITY_INFO);
    }
    
    public void saveWorkingTime(){
        workingTimeDAO.save(new WorkingTime(dateReg, time, todoAux));
        todoDao.saveWorkingTime(todoAux, time);
        addMessage("Registro Exitoso!", FacesMessage.SEVERITY_INFO);
    }
    
    public void showWorkTime() {
        workingTimeList = workingTimeDAO.find(todoAux);
    }
    
    
    
    
    
    public void addMessage(String summary, FacesMessage.Severity severity) {
        FacesMessage message = new FacesMessage(severity, summary, null);
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

    public Todo getTodoAux() {
        return todoAux;
    }

    public void setTodoAux(Todo todoAux) {
        this.todoAux = todoAux;
    }

    public Float getTime() {
        return time;
    }

    public void setTime(Float time) {
        this.time = time;
    }

    public boolean isButtonUpdateFlag() {
        return buttonUpdateFlag;
    }

    public void setButtonUpdateFlag(boolean buttonUpdateFlag) {
        this.buttonUpdateFlag = buttonUpdateFlag;
    }

    public List<WorkingTime> getWorkingTimeList() {
        return workingTimeList;
    }

    public void setWorkingTimeList(List<WorkingTime> workingTimeList) {
        this.workingTimeList = workingTimeList;
    }

    public Date getDateReg() {
        return dateReg;
    }

    public void setDateReg(Date dateReg) {
        this.dateReg = dateReg;
    }

}
