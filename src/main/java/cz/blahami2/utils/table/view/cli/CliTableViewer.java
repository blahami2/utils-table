/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.blahami2.utils.table.view.cli;

import cz.blahami2.utils.table.model.ITable;
import cz.blahami2.utils.table.view.ITableViewer;

/**
 *
 * @author Michael Blaha {@literal <michael.blaha@certicon.cz>}
 */
public class CliTableViewer implements ITableViewer {

    @Override
    public <T> void displayTable( ITable<T> table, StringExtractor<T> stringExtractor ) {
        for ( int i = 0; i < table.getRowCount(); i++ ) {
            for ( int j = 0; j < table.getColumnCount(); j++ ) {
                System.out.print( stringExtractor.extract( table.getCellContent( i, j ) ) );
                System.out.print( "\t" );
            }
            System.out.println();
        }
    }

}
