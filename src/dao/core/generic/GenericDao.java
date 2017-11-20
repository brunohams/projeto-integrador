/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.core.generic;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Aluno
 */
public interface GenericDao <K extends Serializable, T> {

    public K insert(T obj);

    public void update(T obj);

    public void delete(T obj);

    public void delete(K id);

    public List<T> listAll();

    public T listByKey(K id);
}
