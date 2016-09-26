/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.blahami2.utils.table.model;

import java.util.List;
import java.util.function.Function;

/**
 *
 * @author Michael Blaha {@literal <michael.blaha@certicon.cz>}
 */
public interface TableBuilder<T> {

    public void setCell( int row, int column, T value );

    public void addColumn( List<T> values );

    public <V> void addColumn( List<V> values, Function<V, T> valueExtractors );

    public <V> void addColumns( List<V> values, Function<V, T>... valueExtractors );

    public void addRow( List<T> values );

    public <V> void addRow( List<V> values, Function<V, T> valueExtractor );

    public <V> void addRows( List<V> values, Function<V, T>... valueExtractors );

    public void setHeaders( List<String> headers );

    public void setHeader( int column, String header );

    public Table<T> build();

}
