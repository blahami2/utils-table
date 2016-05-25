/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.blahami2.utils.table.data.text;

import cz.blahami2.utils.table.data.ITableExporter;
import cz.blahami2.utils.table.model.ITable;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Michael Blaha {@literal <michael.blaha@certicon.cz>}
 */
public class TextTableExporter implements ITableExporter {

    private static final String DEFAULT_DELIMITER = "\t";

    private final String delimiter;

    public TextTableExporter() {
        this.delimiter = DEFAULT_DELIMITER;
    }

    public TextTableExporter( String delimiter ) {
        this.delimiter = delimiter;
    }

    @Override
    public <T> void export( File destination, ITable<T> table, StringExtractor<T> stringExtractor ) throws IOException {
        FileWriter fw = new FileWriter( destination );
        for ( int i = 0; i < table.getRowCount(); i++ ) {
            for ( int j = 0; j < table.getColumnCount(); j++ ) {
                fw.write( stringExtractor.extract( table.getCellContent( i, j ) ) );
                fw.write( delimiter );
            }
            fw.write( "\n" );
        }
    }

}
