package ma.inetum.brique.utilities;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import ma.inetum.brique.model.principale.PieceComptable;

public class ExcelGenerator {
	private XSSFWorkbook workbook = new XSSFWorkbook();
	private XSSFSheet sheet;
	public void generateEcritures(List<PieceComptable> pieces, HttpServletResponse response) throws IOException {
		GeneratorEcritures generator = new GeneratorEcritures(pieces);
		generator.writeHeader();
		generator.write();
		ServletOutputStream outputStream = response.getOutputStream();
		workbook.write(outputStream);
		workbook.close();
		outputStream.close();
	}
	
	
	private class GeneratorEcritures {
		List<PieceComptable> pieces;
		private GeneratorEcritures(List<PieceComptable> pieces) {
			this.pieces = pieces;
		}
		void writeHeader() {
			sheet = workbook.createSheet("Ecritures");
			Row row = sheet.createRow(0);

			CellStyle style = workbook.createCellStyle();
			XSSFFont font = workbook.createFont();
			font.setBold(true);
			font.setFontHeight(16);
			style.setFont(font);
			createCell(row, 0, "Flux", style);
			createCell(row, 1, "Code Journale", style);
			createCell(row, 2, "Entête Doc", style);
			createCell(row, 3, "Date Piéce", style);
			createCell(row, 4, "Numéro Piéce", style);
			
			createCell(row, 5, "Numéro Compte", style);
			createCell(row, 6, "Centre de coût", style);
			createCell(row, 7, "Description", style);
			createCell(row, 8, "MNT Débit « MAD »", style);
			createCell(row, 9, "MNT Crédit « MAD »", style);
			createCell(row, 10, "MNT Débit", style);
			createCell(row, 11, "MNT Crédit", style);
			
			createCell(row, 12, "Réf. Externes N°1", style);
			createCell(row, 13, "Réf. Externes N°2", style);
			createCell(row, 14, "Réf. Externes N°3", style);
			createCell(row, 15, "Réf. Externes N°4", style);
			createCell(row, 16, "Réf. Externes N°5", style);
			createCell(row, 17, "Réf. Externes N°6", style);
			createCell(row, 18, "Réf. Externes N°7", style);
			createCell(row, 19, "Réf. Externes N°8", style);
		}
		void write() {
			int rowCount = 1;

			CellStyle style = workbook.createCellStyle();
			XSSFFont font = workbook.createFont();
			font.setFontHeight(14);
			style.setFont(font);

			for (var record : pieces) {
				for(var ecriture : record.getEcritures()) {
					Row row = sheet.createRow(rowCount++);
					int columnCount = 0;
					createCell(row, columnCount++, record.getFlux().getNom(), style);
					createCell(row, columnCount++, record.getCodeJournale(), style);
					createCell(row, columnCount++, record.getEnteteDoc(), style);
					createCell(row, columnCount++, record.getDate() != null ?  record.getDate().format(Constantes.FORMAT_DATE) : "", style);
					createCell(row, columnCount++, record.getNumeroPiece(), style);
					createCell(row, columnCount++, ecriture.getNcpt(), style);
					createCell(row, columnCount++, ecriture.getCostCenter(), style);
					createCell(row, columnCount++, ecriture.getAccountDescription(), style);
					if(ecriture.getSens() =='C') {
						createCell(row, columnCount++, "", style);
						createCell(row, columnCount++, ecriture.getMontantMAD() != null ? Constantes.DECIMAL_FORMAT_MONTANT.format(ecriture.getMontantMAD()) : "", style);
						createCell(row, columnCount++, "", style);
						createCell(row, columnCount++, ecriture.getMontant() != null ? Constantes.DECIMAL_FORMAT_MONTANT.format(ecriture.getMontant()) : "", style);
					} else {
						createCell(row, columnCount++, ecriture.getMontantMAD() != null ? Constantes.DECIMAL_FORMAT_MONTANT.format(ecriture.getMontantMAD()) : "", style);
						createCell(row, columnCount++, "", style);
						createCell(row, columnCount++, ecriture.getMontant() != null ? Constantes.DECIMAL_FORMAT_MONTANT.format(ecriture.getMontant()) : "", style);
						createCell(row, columnCount++, "", style);
					}
					createCell(row, columnCount++, ecriture.getRef1(), style);
					createCell(row, columnCount++, ecriture.getRef2(), style);
					createCell(row, columnCount++, ecriture.getRef3(), style);
					createCell(row, columnCount++, ecriture.getRef4(), style);
					createCell(row, columnCount++, ecriture.getRef5(), style);
					createCell(row, columnCount++, ecriture.getRef6()!=null?ecriture.getRef6():"", style);
					createCell(row, columnCount++, ecriture.getRef7(), style);
					createCell(row, columnCount++, ecriture.getRef8(), style);
				}
			}
		}

	}
	private void createCell(Row row, int columnCount, Object value, CellStyle style) {
		sheet.autoSizeColumn(columnCount);
		Cell cell = row.createCell(columnCount);
		if (value == null) {
			cell.setCellValue("");
		} else if (value instanceof Integer) {
			cell.setCellValue((Integer) value);
		} 
        else if (value instanceof Long) {
			cell.setCellValue((Long) value);
		} else if (value instanceof Boolean) {
			cell.setCellValue((Boolean) value);
		} else {
			cell.setCellValue((String) value);
		}
		cell.setCellStyle(style);
	}
}
