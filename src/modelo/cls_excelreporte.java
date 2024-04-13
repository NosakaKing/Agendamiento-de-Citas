package modelo;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import vista.Escritorio;

public class cls_excelreporte {
    public static void reporte(JTable tablaAud, String titulo) {
        DefaultTableModel modelo = (DefaultTableModel) tablaAud.getModel();
        @SuppressWarnings("resource")
        Workbook book = new XSSFWorkbook();
        Sheet sheet = book.createSheet("Auditoria");

        try {
            // Insertar el logo y ajustar su tamaño
            InputStream is = Escritorio.class.getResourceAsStream("/images/LogoEmpresa.png");
            byte[] bytes = IOUtils.toByteArray(is);
            int imgIndex = book.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
            is.close();
            CreationHelper help = book.getCreationHelper();
            @SuppressWarnings("rawtypes")
            Drawing draw = sheet.createDrawingPatriarch();
            ClientAnchor anchor = help.createClientAnchor();
            anchor.setCol1(0);
            anchor.setRow1(0); // Cambiar la posición vertical del logo
            Picture pict = draw.createPicture(anchor, imgIndex);
            pict.resize(2, 6); // Ajustar el tamaño del logo

            // Estilo y posición del título
            CellStyle tituloEstilo = book.createCellStyle();
            tituloEstilo.setAlignment(HorizontalAlignment.CENTER);
            tituloEstilo.setVerticalAlignment(VerticalAlignment.CENTER);
            Font fuenteTitulo = book.createFont();
            fuenteTitulo.setFontName("Arial");
            fuenteTitulo.setBold(true);
            fuenteTitulo.setFontHeightInPoints((short) 18); 
            tituloEstilo.setFont(fuenteTitulo);
            Row filaTitulo = sheet.createRow(6);
            Cell celdaTitulo = filaTitulo.createCell(3);
            celdaTitulo.setCellStyle(tituloEstilo);
            celdaTitulo.setCellValue(titulo); // Cambiar el texto del título

            // Estilos para encabezados
            CellStyle headerStyle = book.createCellStyle();
            headerStyle.setFillForegroundColor(IndexedColors.ROSE.getIndex());
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            headerStyle.setBorderBottom(BorderStyle.THIN);
            headerStyle.setBorderLeft(BorderStyle.THIN);
            headerStyle.setBorderRight(BorderStyle.THIN);
            headerStyle.setBorderTop(BorderStyle.THIN);
            Font font = book.createFont();
            font.setFontName("Times New Roman");
            font.setBold(true);
            font.setFontHeightInPoints((short) 12);
            headerStyle.setFont(font);

            // Estilos para datos
            CellStyle dataStyle = book.createCellStyle();
            dataStyle.setBorderBottom(BorderStyle.THIN);
            dataStyle.setBorderLeft(BorderStyle.THIN);
            dataStyle.setBorderRight(BorderStyle.THIN);
            dataStyle.setBorderTop(BorderStyle.THIN);
            dataStyle.setVerticalAlignment(VerticalAlignment.CENTER);

            // Encabezado del reporte
            Row filaEncabezado = sheet.createRow(10); // Cambiar la posición vertical del encabezado
            for (int i = 0; i < modelo.getColumnCount(); i++) {
                Cell celdaEncabezado = filaEncabezado.createCell(i);
                celdaEncabezado.setCellValue(modelo.getColumnName(i));
                celdaEncabezado.setCellStyle(headerStyle);
            }

            // Datos de la tabla
            for (int i = 0; i < modelo.getRowCount(); i++) {
                Row filaDatos = sheet.createRow(i + 11); // Cambiar la posición vertical de los datos
                for (int j = 0; j < modelo.getColumnCount(); j++) {
                    Cell celdaDatos = filaDatos.createCell(j);
                    celdaDatos.setCellValue(modelo.getValueAt(i, j).toString());
                    celdaDatos.setCellStyle(dataStyle);
                }
            }

            // Ajustar el ancho de las columnas
            for (int i = 0; i < modelo.getColumnCount(); i++) {
                sheet.autoSizeColumn(i);
            }

            // Crear un diálogo de selección de archivo
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Guardar Reporte");
            
            // Mostrar el diálogo y obtener la respuesta del usuario
            int userSelection = fileChooser.showSaveDialog(null);
            
            if (userSelection == JFileChooser.APPROVE_OPTION) {
                // Obtener la ruta y el nombre del archivo seleccionado por el usuario
                File file = fileChooser.getSelectedFile();
                
                // Añadir la extensión .xlsx si no la tiene
                String fileName = file.getName();
                if (!fileName.endsWith(".xlsx")) {
                    fileName += ".xlsx";
                    file = new File(file.getParentFile(), fileName);
                }
                
                // Escribir el archivo Excel
                FileOutputStream fileOut = new FileOutputStream(file);
                // Supongo que "book" es tu objeto Workbook
                book.write(fileOut);
                fileOut.close();
                
                // Abrir el archivo con el visor predeterminado
                Desktop.getDesktop().open(file);
                
                JOptionPane.showMessageDialog(null, "Reporte Guardado: " + file.getAbsolutePath());
            }

        } catch (IOException ex) {
            Logger.getLogger(cls_excelreporte.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}