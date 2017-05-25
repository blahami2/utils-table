/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.blahami2.utils.table.data;

import cz.blahami2.utils.table.model.DoubleListTableBuilder;
import cz.blahami2.utils.table.model.Table;
import java.io.ByteArrayOutputStream;
import org.junit.Before;
import org.junit.Test;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 *
 * @author Michael Blaha {@literal <blahami2@gmail.com>}
 */
public class TextTableExporterTest {
    private TextTableExporter exporter;

    @Before
    public void setUp() throws Exception {
        exporter = new TextTableExporter(";");
    }

    @Test
    public void export_returns_proper_string() throws Exception {
        Table<String> table = new DoubleListTableBuilder<String>()
                .setHeader(0, "H1")
                .setHeader(1, "H2")
                .setHeader(2, "H3")
                .setCell(0, 0, "C00")
                .setCell(0, 1, "C01")
                .setCell(0, 2, "C02")
                .setCell(1, 0, "C10")
                .setCell(1, 1, "C11")
                .setCell(1, 2, "C12")
                .build();
        String expected = "H1;H2;H3\n" +
                "C00;C01;C02\n" +
                "C10;C11;C12\n";
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        exporter.export(baos, table);
        String actual = new String(baos.toByteArray());
        
//        System.out.println( "expected: " + expected );
//        System.out.println( "actual__: " + actual );
        assertThat(actual).isEqualTo(expected);
    }
    
}
