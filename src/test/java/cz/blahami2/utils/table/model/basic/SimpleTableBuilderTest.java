/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.blahami2.utils.table.model.basic;

import cz.blahami2.utils.table.model.ITable;
import cz.blahami2.utils.table.model.ITableBuilder;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Michael Blaha {@literal <michael.blaha@certicon.cz>}
 */
public class SimpleTableBuilderTest {

    public SimpleTableBuilderTest() {
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
     * Test of setCell method, of class SimpleTableBuilder.
     */
    @Test
    public void testSetCell() {
        System.out.println( "setCell" );
        int row = 5;
        int column = 5;
        Integer value = 5;
        SimpleTableBuilder<Integer> instance = new SimpleTableBuilder<>();
        instance.setCell( row, column, value );
        ITable<Integer> build = instance.build();
        assertEquals( row + 1, build.getRowCount() );
        assertEquals( column + 1, build.getColumnCount() );
        assertEquals( value, build.getCellContent( row, column ) );
        assertNull( build.getCellContent( row - 1, column - 1 ) );
    }

    /**
     * Test of addColumn method, of class SimpleTableBuilder.
     */
    @Test
    public void testAddColumn_List() {
        System.out.println( "addColumn" );
        List<Integer> column = new ArrayList<>();
        for ( int i = 0; i < 10; i++ ) {
            column.add( i );
        }
        SimpleTableBuilder<Integer> instance = new SimpleTableBuilder<>();
        instance.addColumn( column );
        ITable<Integer> build = instance.build();

        assertEquals( 10, build.getRowCount() );
        assertEquals( 1, build.getColumnCount() );
        assertEquals( Integer.valueOf( 5 ), build.getCellContent( 5, 0 ) );
    }

    /**
     * Test of addColumn method, of class SimpleTableBuilder.
     */
    @Test
    public void testAddColumn_List_ITableBuilderValueExtractor() {
        System.out.println( "addColumn" );
        List<Integer> column = new ArrayList<>();
        for ( int i = 0; i < 10; i++ ) {
            column.add( i );
        }
        SimpleTableBuilder<String> instance = new SimpleTableBuilder<>();
        instance.addColumn( column, ( Integer value ) -> {
            return value.toString();
        } );
        ITable<String> build = instance.build();

        assertEquals( 10, build.getRowCount() );
        assertEquals( 1, build.getColumnCount() );
        assertEquals( "5", build.getCellContent( 5, 0 ) );
    }

    /**
     * Test of addColumns method, of class SimpleTableBuilder.
     */
    @Test
    public void testAddColumns() {
        System.out.println( "addColumns" );
        List<Integer> column = new ArrayList<>();
        for ( int i = 0; i < 10; i++ ) {
            column.add( i );
        }
        SimpleTableBuilder<String> instance = new SimpleTableBuilder<>();
        instance.addColumns( column, ( Integer value ) -> {
            return value.toString();
        }, ( Integer value ) -> {
            return value.toString() + value.toString();
        } );
        ITable<String> build = instance.build();

        assertEquals( 10, build.getRowCount() );
        assertEquals( 2, build.getColumnCount() );
        assertEquals( "55", build.getCellContent( 5, 1 ) );
    }

    /**
     * Test of addRow method, of class SimpleTableBuilder.
     */
    @Test
    public void testAddRow_List() {
        System.out.println( "addRow" );
        List<Integer> row = new ArrayList<>();
        for ( int i = 0; i < 10; i++ ) {
            row.add( i );
        }
        SimpleTableBuilder<Integer> instance = new SimpleTableBuilder<>();
        instance.addRow( row );
        ITable<Integer> build = instance.build();

        assertEquals( 1, build.getRowCount() );
        assertEquals( 10, build.getColumnCount() );
        assertEquals( Integer.valueOf( 5 ), build.getCellContent( 0, 5 ) );
    }

    /**
     * Test of addRow method, of class SimpleTableBuilder.
     */
    @Test
    public void testAddRow_List_ITableBuilderValueExtractor() {
        System.out.println( "addRow" );
        List<Integer> row = new ArrayList<>();
        for ( int i = 0; i < 10; i++ ) {
            row.add( i );
        }
        SimpleTableBuilder<String> instance = new SimpleTableBuilder<>();
        instance.addRow( row, ( Integer value ) -> {
            return value.toString();
        } );
        ITable<String> build = instance.build();

        assertEquals( 1, build.getRowCount() );
        assertEquals( 10, build.getColumnCount() );
        assertEquals( "5", build.getCellContent( 0, 5 ) );
    }

    /**
     * Test of addRows method, of class SimpleTableBuilder.
     */
    @Test
    public void testAddRows() {
        System.out.println( "addRows" );
        List<Integer> row = new ArrayList<>();
        for ( int i = 0; i < 10; i++ ) {
            row.add( i );
        }
        SimpleTableBuilder<String> instance = new SimpleTableBuilder<>();
        instance.addRows( row, ( Integer value ) -> {
            return value.toString();
        }, ( Integer value ) -> {
            return value.toString() + value.toString();
        } );
        ITable<String> build = instance.build();

        assertEquals( 2, build.getRowCount() );
        assertEquals( 10, build.getColumnCount() );
        assertEquals( "55", build.getCellContent( 1, 5 ) );
    }

    /**
     * Test of build method, of class SimpleTableBuilder.
     */
    @Test
    public void testBuild() {
        System.out.println( "build" );
        List<Integer> column = new ArrayList<>();
        for ( int i = 0; i < 10; i++ ) {
            column.add( i );
        }
        List<Integer> row = new ArrayList<>();
        for ( int i = 0; i < 20; i++ ) {
            row.add( i );
        }
        SimpleTableBuilder<Integer> instance = new SimpleTableBuilder<>();
        instance.addColumn( column );
        instance.addRow( row );
        ITable<Integer> build = instance.build();

        assertEquals( 11, build.getRowCount() ); // 10 + the added one
        assertEquals( 20, build.getColumnCount() );
        assertEquals( null, build.getCellContent( 5, 15 ) );
    }

}
