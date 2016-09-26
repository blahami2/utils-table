/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.blahami2.utils.table.data;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import cz.blahami2.utils.table.model.Table;

/**
 *
 * @author Michael Blaha {@literal <michael.blaha@certicon.cz>}
 */
public class TextTableExporter implements TableExporter {

    private static final String DEFAULT_DELIMITER = "\t";

    private final String delimiter;

    public TextTableExporter() {
        this.delimiter = DEFAULT_DELIMITER;
    }

    public TextTableExporter( String delimiter ) {
        this.delimiter = delimiter;
    }

    @Override
    public <T> void export( File destination, Table<T> table, StringExtractor<T> stringExtractor ) throws IOException {
        try ( FileWriter fw = new FileWriter( destination ) ) {
            if ( table.hasHeaders() ) {
                for ( String header : table.getHeaders() ) {
                    fw.write( header );
                    fw.write( delimiter );
                }
                fw.write( "\n" );
            }
            for ( int i = 0; i < table.getRowCount(); i++ ) {
                for ( int j = 0; j < table.getColumnCount(); j++ ) {
                    fw.write( stringExtractor.extract( table.getCellContent( i, j ) ) );
                    fw.write( delimiter );
                }
                fw.write( "\n" );
            }
        }
    }

}
