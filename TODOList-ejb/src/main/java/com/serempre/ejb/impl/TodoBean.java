/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.serempre.ejb.impl;

import com.serempre.ejb.inter.local.ITodoLocal;
import com.serempre.entity.Todo;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.xml.ws.WebServiceRef;
import org.tempuri.Calculator;

@Stateless
public class TodoBean implements ITodoLocal {

    @PersistenceContext(unitName = "todolistPU")
    protected EntityManager em;

    @WebServiceRef(wsdlLocation = "http://www.dneonline.com/calculator.asmx?WSDL")
    public Calculator calculator;

    @Override
    public List<Todo> getTodoALL() {
        return em.createQuery("select t from Todo t JOIN FETCH t.collaborator", Todo.class).getResultList();
    }

    @Override
    public void save(Todo todo) {
        em.persist(todo);
    }

    @Override
    public void merge(Todo todo) {
        em.merge(todo);
    }

    @Override
    public void delete(Todo todo) {
        if (!em.contains(todo)) {
            todo = em.merge(todo);
        }
        em.remove(todo);
    }

    @Override
    public void saveWorkingTime(Todo todo, Float time) {
        todo.setTimeRemaining(
                toFloatCustom(
                        calculator.getCalculatorSoap12().subtract(toIntCustom(todo.getTimeRemaining()), toIntCustom(time))
                )
        );
        em.merge(todo);
    }

    private Integer toIntCustom(Float number) {
        return Integer.valueOf(number.toString().replace(".", ""));
    }

    private Float toFloatCustom(Integer number) {
        return number / 10f;
    }
}
