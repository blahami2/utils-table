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
public interface ITableBuilder<T> {

    public void setCell( int row, int column, T value );

    public void addColumn( List<T> values );

    public <V> void addColumn( List<V> values, ValueExtractor<T, V> valueExtractors );
    public <V> void addColumns( List<V> values, ValueExtractor<T, V>... valueExtractors );

    public void addRow( List<T> values );

    public <V> void addRow( List<V> values, ValueExtractor<T, V> valueExtractor );
    public <V> void addRows( List<V> values, ValueExtractor<T, V>... valueExtractors );

    public ITable<T> build();

    public interface ValueExtractor<T, V> {

        public T extract( V value );
    }
}
