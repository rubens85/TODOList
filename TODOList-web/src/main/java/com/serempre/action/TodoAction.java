/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.serempre.action;

import com.serempre.ejb.inter.local.ITodoLocal;
import com.serempre.ejb.test.CollaboratorFacadeLocal;
import com.serempre.entity.Todo;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author rubens
 */
@Named(value = "todoAction")
@ManagedBean
@SessionScoped
public class TodoAction implements Serializable{

   @EJB
    CollaboratorFacadeLocal todoDao;
    
    private List<Todo> listOfTODO;

    /**
     * Creates a new instance of TodoManagedBean
     */
    public TodoAction() {
        todoDao.findAll();
        javax.persistence.Persistence.createEntityManagerFactory("todolistPU");
    }

    public List<Todo> getListOfTODO() {
        return listOfTODO;
    }

    public void setListOfTODO(List<Todo> listOfTODO) {
        this.listOfTODO = listOfTODO;
    }
     
}
