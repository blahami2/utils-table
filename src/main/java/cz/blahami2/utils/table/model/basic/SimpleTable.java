/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.blahami2.utils.table.model.basic;

import cz.blahami2.utils.table.model.ITable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Michael Blaha {@literal <michael.blaha@certicon.cz>}
 */
public class SimpleTable<T> implements ITable<T> {

    private final List<List<T>> rows;
    private final List<List<T>> columns;

    public SimpleTable( List<List<T>> rows, List<List<T>> columns ) {
        this.rows = rows;
        this.columns = columns;
    }

    @Override
    public int getColumnCount() {
        return columns.size();
    }

    @Override
    public int getRowCount() {
        return rows.size();
    }

    @Override
    public T getCellContent( int row, int column ) {
        List<T> rowList = rows.get( row );
        // check if it is a part of the "virtual" table but not of this particular row
        if ( column < columns.size() && rowList.size() <= column ) {
            return null;
        }
        // let the java handle OutOfBound otherwiese
        return rows.get( row ).get( column );
    }

    @Override
    public List<T> getColumn( int column ) {
        return new ArrayList<>( columns.get( column ) );
    }

    @Override
    public List<T> getRow( int row ) {
        return new ArrayList<>( rows.get( row ) );
    }

}
