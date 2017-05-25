/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.blahami2.utils.table.view.cli;

import cz.blahami2.utils.table.view.CliTableViewer;
import cz.blahami2.utils.table.model.DoubleListTableBuilder;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import cz.blahami2.utils.table.model.TableBuilder;
import cz.blahami2.utils.table.view.TableViewer;

/**
 *
 * @author Michael Blaha {@literal <michael.blaha@certicon.cz>}
 */
public class CliTableViewerTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    public CliTableViewerTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of displayTable method, of class CliTableViewer.
     */
    @Test
    public void testDisplayTable() {
        PrintStream old = System.out;
        System.setOut( new PrintStream( outContent ) );
        System.out.println( "displayTable" );
        DoubleListTableBuilder<Double> builder = new DoubleListTableBuilder<>();
        for ( int i = 0; i < 2; i++ ) {
            for ( int j = 0; j < 1; j++ ) {
                if ( i != j ) {
                    builder.setCell( i, j, i * j / (double) 3 );
                }
            }
        }
        CliTableViewer instance = new CliTableViewer();
        instance.displayTable( builder.build(), ( Double val ) -> {
            return String.format( "%.3f", val );
        } );

        StringBuilder sb = new StringBuilder();
        sb.append( "displayTable\r\n" );
        for ( int i = 0; i < 2; i++ ) {
            for ( int j = 0; j < 1; j++ ) {
                if ( i != j ) {
                    sb.append( String.format( "%.3f", i * j / (double) 3 ) );
                }
                sb.append( "\t" );
            }
            sb.append( "\r\n" );
        }
        System.setOut( old );
        assertEquals( sb.toString(), outContent.toString() );

    }

    private String bytesToString( byte[] bytes ) {
        StringBuilder sb = new StringBuilder();
        for ( byte b : bytes ) {
            sb.append( (int) b );
            sb.append( " " );
        }
        return sb.toString();
    }
}
