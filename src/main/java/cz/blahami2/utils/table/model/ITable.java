/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.blahami2.utils.table.model;

import java.util.List;

/**
 *
 * @author Michael Blaha {@literal <michael.blaha@certicon.cz>}
 */
public interface ITable<T> {

    public int getColumnCount();

    public int getRowCount();

    public T getCellContent( int row, int column );

    public List<T> getColumn( int column );

    public List<T> getRow( int row );
}
