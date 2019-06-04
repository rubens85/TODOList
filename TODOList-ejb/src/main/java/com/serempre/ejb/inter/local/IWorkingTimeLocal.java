/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.serempre.ejb.inter.local;

import com.serempre.entity.Todo;
import com.serempre.entity.WorkingTime;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author rubens
 */
@Local
public interface IWorkingTimeLocal {
    
    public void save(WorkingTime wt);
    public List<WorkingTime> find(Todo todo);
}
