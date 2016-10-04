/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.blahami2.utils.table.model;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 *
 * @author Michael Blaha {@literal <michael.blaha@certicon.cz>}
 */
public interface Table<T> {

    public int getColumnCount();

    public int getRowCount();

    public T getCellContent( int row, int column );

    public List<T> getColumn( int column );

    public List<T> getRow( int row );

    public List<String> getHeaders();

    public String getHeader( int column );

    public Table filter( Predicate<List<T>> filteringCondition );

    public Table filter( int column, Predicate<T> filteringCondition );

    public Table subtable( int firstRow, int lastRow );

    public boolean hasHeaders();

    default public String toString( Function<T, String> mapper ) {
        StringBuilder sb = new StringBuilder();
        sb.append( getClass().getSimpleName() ).append( "{\n" );
        if ( hasHeaders() ) {
            getHeaders().stream().forEach( ( header ) -> {
                sb.append( header ).append( "," );
            } );
            sb.append( "\n" );
        }
        for ( int i = 0; i < getRowCount(); i++ ) {
            for ( int j = 0; j < getColumnCount(); j++ ) {
                sb.append( mapper.apply( getCellContent( i, j ) ) ).append( "," );
            }
            sb.append( "\n" );
        }
        sb.append( "}\n" );
        return sb.toString();
    }

}
