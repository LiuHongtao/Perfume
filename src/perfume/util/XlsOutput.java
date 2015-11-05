package perfume.util;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;



public class XlsOutput {

	public static void generateExcel2003(String projectName,
			List<List<String>> outputString) {

		HSSFWorkbook workbook2003 = new HSSFWorkbook();

		HSSFSheet sheet = workbook2003.createSheet("jieguo");
		
		for (int i = 0; i < outputString.size(); i++) {
			
			List<String> tempRow = outputString.get(i);
			
			HSSFRow row = sheet.createRow(i);
			int x=0;
			for (String s : tempRow) {
				HSSFCell nameCell = row.createCell(x);
				nameCell.setCellValue(s);
				x++;
			}		
		}
	
		File file = new File("./result/"+projectName+".xls");
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(file);
			workbook2003.write(fos);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
