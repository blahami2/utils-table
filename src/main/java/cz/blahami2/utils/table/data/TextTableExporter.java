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
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 *
 * @author Michael Blaha {@literal <michael.blaha@certicon.cz>}
 */
public class TextTableExporter implements TableExporter {

    private static final String DEFAULT_NEWLINE = "\n";
    private static final String DEFAULT_DELIMITER = "\t";

    private final String delimiter;

    public TextTableExporter() {
        this.delimiter = DEFAULT_DELIMITER;
    }

    public TextTableExporter( String delimiter ) {
        this.delimiter = delimiter;
    }

    @Override
    public <T> void export( OutputStream destination, Table<T> table, StringExtractor<T> stringExtractor, boolean append ) throws IOException {
//        append = append ? destination.exists() : false; // if the file does not exist, make append false in order to add headers as well
        try ( OutputStreamWriter osw = new OutputStreamWriter( destination ) ) {
            if ( !append && table.hasHeaders() ) {
                osw.write( table.getHeaders().stream().collect( Collectors.joining( delimiter ) ) );
                osw.write( DEFAULT_NEWLINE );
            }
            osw.write(
                    IntStream.range( 0, table.getRowCount() ).mapToObj( i -> table.getRow( i ) )
                    .map( row -> row.stream()
                            .map( cell -> stringExtractor.extract( cell ) )
                            .collect( Collectors.joining( delimiter ) )
                    ).collect( Collectors.joining( DEFAULT_NEWLINE ) )
                    + DEFAULT_NEWLINE
            );
        }
    }

}
