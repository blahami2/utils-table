/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.blahami2.utils.table.data;

import java.io.File;
import java.io.IOException;
import cz.blahami2.utils.table.model.Table;

/**
 *
 * @author Michael Blaha {@literal <michael.blaha@certicon.cz>}
 */
public interface TableExporter {

    public <T> void export( File destination, Table<T> table, StringExtractor<T> stringExtractor ) throws IOException;
    
    public <T> void export( File destination, Table<T> table, StringExtractor<T> stringExtractor, boolean append ) throws IOException;

    public interface StringExtractor<T> {

        public default String extract( T value ) {
            if ( value == null ) {
                return "";
            } else {
                return nullSafeExtract( value );
            }
        }

        public String nullSafeExtract( T value );
    }
}
