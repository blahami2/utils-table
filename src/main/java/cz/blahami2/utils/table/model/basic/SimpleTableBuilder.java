/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.blahami2.utils.table.model.basic;

import cz.blahami2.utils.table.model.ITable;
import cz.blahami2.utils.table.model.ITableBuilder;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Michael Blaha {@literal <michael.blaha@certicon.cz>}
 */
public class SimpleTableBuilder<T> implements ITableBuilder<T> {

    private final List<List<T>> table = new ArrayList<>();
    private int columnCount = 0;
    private int rowCount = 0;

    public SimpleTableBuilder() {
    }

    @Override
    public void setCell( int row, int column, T value ) {
        while ( table.size() <= row ) {
            table.add( new ArrayList<>() );
        }
        List<T> rowList = table.get( row );
        while ( rowList.size() <= column ) {
            rowList.add( null );
        }
        rowList.set( column, value );
        rowCount = Math.max( rowCount, row + 1 );
        columnCount = Math.max( columnCount, column + 1 );
    }

    @Override
    public void addColumn( List<T> values ) {
        int column = columnCount;
        int row = 0;
        for ( T value : values ) {
            setCell( row++, column, value );
        }
    }

    @Override
    public <V> void addColumn( List<V> values, ValueExtractor<T, V> valueExtractor ) {
        int column = columnCount;
        int row = 0;
        for ( V value : values ) {
            setCell( row++, column, valueExtractor.extract( value ) );
        }
    }

    @Override
    public <V> void addColumns( List<V> values, ValueExtractor<T, V>... valueExtractors ) {
        int lastColumnCount = columnCount;
        int row = 0;
        for ( V value : values ) {
            int column = lastColumnCount;
            for ( ValueExtractor<T, V> valueExtractor : valueExtractors ) {
                setCell( row, column++, valueExtractor.extract( value ) );
            }
            row++;
        }
    }

    @Override
    public void addRow( List<T> values ) {
        int column = 0;
        int row = rowCount;
        for ( T value : values ) {
            setCell( row, column++, value );
        }
    }

    @Override
    public <V> void addRow( List<V> values, ValueExtractor<T, V> valueExtractor ) {
        int column = 0;
        int row = rowCount;
        for ( V value : values ) {
            setCell( row, column++, valueExtractor.extract( value ) );
        }
    }

    @Override
    public <V> void addRows( List<V> values, ValueExtractor<T, V>... valueExtractors ) {
        int lastRowCount = rowCount;
        int column = 0;
        for ( V value : values ) {
            int row = lastRowCount;
            for ( ValueExtractor<T, V> valueExtractor : valueExtractors ) {
                setCell( row++, column, valueExtractor.extract( value ) );
            }
            column++;
        }
    }

    @Override
    public ITable<T> build() {
        // table is a [row][column] table
        List<List<T>> columns = new ArrayList<>();
        // add columns
        while ( columns.size() < columnCount ) {
            // create new column
            List<T> newList = new ArrayList<>();
            int currentRowCount = rowCount;
            // search last valid value from the bottom of this column (while the row does not have the column or the column is null
            while ( currentRowCount > 0 && ( table.get( currentRowCount - 1 ).size() <= columns.size() || table.get( currentRowCount - 1 ).get( columns.size() ) == null ) ) {
                currentRowCount--;
            }
            // add all the values up to the last valid value to the current column
            for ( int i = 0; i < currentRowCount; i++ ) {
                if ( table.get( i ).size() > columns.size() ) {
                    newList.add( table.get( i ).get( columns.size() ) );
                } else {
                    newList.add( null );
                }
            }
            columns.add( newList );
        }
        return new SimpleTable<>( table, columns );
    }

}
