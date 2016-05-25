/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.blahami2.utils.table.data;

import cz.blahami2.utils.table.model.ITable;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author Michael Blaha {@literal <michael.blaha@certicon.cz>}
 */
public interface ITableExporter {

    public <T> void export( File destination, ITable<T> table, StringExtractor<T> stringExtractor ) throws IOException;

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
