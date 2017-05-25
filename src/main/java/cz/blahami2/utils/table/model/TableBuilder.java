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
 * @param <T> type of the data in table
 * @param <B> flow design identification of the extending class
 */
public interface TableBuilder<T, B extends TableBuilder<T, B>> {

    B setCell( int row, int column, T value );

    B addColumn( List<T> values );

    <V> B addColumn( List<V> values, Function<V, T> valueExtractors );

    <V> B addColumns( List<V> values, Function<V, T>... valueExtractors );

    B addRow( List<T> values );

    <V> B addRow( List<V> values, Function<V, T> valueExtractor );

    <V> B addRows( List<V> values, Function<V, T>... valueExtractors );

    B setHeaders( List<String> headers );

    B setHeader( int column, String header );

    Table<T> build();

}
