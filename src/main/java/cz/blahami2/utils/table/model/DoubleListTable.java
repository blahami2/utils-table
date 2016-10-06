/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.blahami2.utils.table.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

/**
 *
 * @author Michael Blaha {@literal <michael.blaha@certicon.cz>}
 */
public class DoubleListTable<T> implements Table<T> {

    private final List<List<T>> rows;
    private final List<List<T>> columns;
    private final List<String> headers;

    public DoubleListTable( List<List<T>> rows, List<List<T>> columns ) {
        this.rows = rows;
        this.columns = columns;
        this.headers = null;
    }

    public DoubleListTable( List<List<T>> rows, List<List<T>> columns, List<String> headers ) {
        this.rows = rows;
        this.columns = columns;
        this.headers = headers;
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

    @Override
    public List<String> getHeaders() {
        if ( !hasHeaders() ) {
            throw new IllegalStateException( "Headers not set!" );
        }
        return new ArrayList<>( headers );
    }

    @Override
    public boolean hasHeaders() {
        return headers != null;
    }

    @Override
    public String getHeader( int column ) {
        return headers.get( column );
    }

    @Override
    public Table filter( Predicate<List<T>> filteringCondition ) {
        DoubleListTableBuilder<T> builder = new DoubleListTableBuilder<>();
        if ( hasHeaders() ) {
            builder.setHeaders( headers );
        }
        rows.stream().filter( filteringCondition ).forEach( ( row ) -> {
            builder.addRow( row );
        } );
        return builder.build();
    }

    @Override
    public Table filter( int column, Predicate<T> filteringCondition ) {
        DoubleListTableBuilder<T> builder = new DoubleListTableBuilder<>();
        if ( hasHeaders() ) {
            builder.setHeaders( headers );
        }
        rows.stream().filter( ( row ) -> ( filteringCondition.test( row.get( column ) ) ) ).forEach( ( row ) -> {
            builder.addRow( row );
        } );
        return builder.build();
    }

    @Override
    public Table subtable( int firstRow, int lastRow ) {
        DoubleListTableBuilder<T> builder = new DoubleListTableBuilder<>();
        if ( hasHeaders() ) {
            builder.setHeaders( headers );
        }
        for ( int i = firstRow; i <= lastRow; i++ ) {
            builder.addRow( rows.get( i ) );
        }
        return builder.build();
    }

    @Override
    public String toString() {
        return toString( x -> x.toString() );
    }

}
