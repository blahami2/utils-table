/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.blahami2.utils.table.data;

/**
 *
 * @author Michael Blaha {@literal <blahami2@gmail.com>}
 */
public class CsvTableExporter extends TextTableExporter {

    public CsvTableExporter() {
        super( Delimiter.COMMA.getDelimiter() );
    }

    public CsvTableExporter( Delimiter delimiter ) {
        super( delimiter.getDelimiter() );
    }

    public static enum Delimiter {
        COMMA( "," ), SEMICOLON( ";" );

        private final String delim;

        private Delimiter( String delim ) {
            this.delim = delim;
        }

        public String getDelimiter() {
            return delim;
        }

    }
}
