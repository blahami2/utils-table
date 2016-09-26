/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.blahami2.utils.table.model;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 *
 * @author Michael Blaha {@literal <michael.blaha@certicon.cz>}
 */
public class DoubleListTableBuilder<T> implements TableBuilder<T> {

    private final List<List<T>> table = new ArrayList<>();
    private final List<String> headers = new ArrayList<>();
    private int columnCount = 0;
    private int rowCount = 0;

    public DoubleListTableBuilder() {
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
    public <V> void addColumn( List<V> values, Function<V, T> valueExtractor ) {
        int column = columnCount;
        int row = 0;
        for ( V value : values ) {
            setCell( row++, column, valueExtractor.apply( value ) );
        }
    }

    @Override
    public <V> void addColumns( List<V> values, Function<V, T>... valueExtractors ) {
        int lastColumnCount = columnCount;
        int row = 0;
        for ( V value : values ) {
            int column = lastColumnCount;
            for ( Function<V, T> valueExtractor : valueExtractors ) {
                setCell( row, column++, valueExtractor.apply( value ) );
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
    public <V> void addRow( List<V> values, Function<V, T> valueExtractor ) {
        int column = 0;
        int row = rowCount;
        for ( V value : values ) {
            setCell( row, column++, valueExtractor.apply( value ) );
        }
    }

    @Override
    public <V> void addRows( List<V> values, Function<V, T>... valueExtractors ) {
        int lastRowCount = rowCount;
        int column = 0;
        for ( V value : values ) {
            int row = lastRowCount;
            for ( Function<V, T> valueExtractor : valueExtractors ) {
                setCell( row++, column, valueExtractor.apply( value ) );
            }
            column++;
        }
    }

    @Override
    public void setHeaders( List<String> headers ) {
        this.headers.clear();
        this.headers.addAll( headers );
    }

    @Override
    public void setHeader( int column, String header ) {
        while ( this.headers.size() <= column ) {
            headers.add( "" );
        }
        headers.set( column, header );
    }

    @Override
    public Table<T> build() {
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
        return headers != null ? new DoubleListTable<>( table, columns, headers ) : new DoubleListTable<>( table, columns );
    }

}
