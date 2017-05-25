/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.blahami2.utils.table.data;

import cz.blahami2.utils.table.model.Table;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Michael Blaha {@literal <blahami2@gmail.com>}
 */
public class XlsxTableExporter implements TableExporter {

    @Override
    public <T> void export( OutputStream destination, Table<T> table, StringExtractor<T> stringExtractor, boolean append ) throws IOException {
        if ( append ) {
            throw new UnsupportedOperationException( "Append option is not available yet." );
        }
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet( "Sheet" );
        if ( table.hasHeaders() ) {
            CellStyle headerStyle = workbook.createCellStyle();
            headerStyle.setBorderBottom( BorderStyle.THIN );
            headerStyle.setBottomBorderColor( IndexedColors.BLACK.getIndex() );
            Row headerRow = sheet.createRow( 0 );
            for ( int i = 0; i < table.getHeaders().size(); i++ ) {
                headerRow.createCell( i ).setCellValue( table.getHeader( i ) );
            }
        }
        for ( int i = 0; i < table.getRowCount(); i++ ) {
            List<T> tableRow = table.getRow( i );
            Row row = sheet.createRow( i + 1 );
            for ( int j = 0; j < tableRow.size(); j++ ) {
                row.createCell( j ).setCellValue( stringExtractor.extract( tableRow.get( j ) ) );
            }
        }
        workbook.write( destination );
        destination.close();
    }
}
