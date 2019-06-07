/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.serempre.ejb.inter.local;

import com.serempre.entity.Todo;
import com.serempre.handleexception.RemainingTimeException;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author rubens
 */
@Local
public interface ITodoLocal {
    
    public List<Todo> getTodoALL();
    public void save(Todo todo);
    public void delete(Todo todo);
    public void merge(Todo todo);
    public void saveWorkingTime(Todo todoAux, Float time) throws RemainingTimeException;
}
