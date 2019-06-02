/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.serempre.ejb.inter.local;

import com.serempre.entity.Todo;
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
}
