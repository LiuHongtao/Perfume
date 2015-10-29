package util;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import dto.Student;

/**
 * 生成Excel示例，2003和2007
 * 
 * @author Nanlei
 */

public class XlsOutput {

	/**
	 * 创建2003文件的方法
	 * 
	 * @param filePath
	 */
	public static void generateExcel2003(String projectName,
			List<List<String>> outputString) {


		// 先创建工作簿对象
		HSSFWorkbook workbook2003 = new HSSFWorkbook();
		// 创建工作表对象并命名
		HSSFSheet sheet = workbook2003.createSheet("项目信息");
		// 遍历集合对象创建行和单元格
		for (int i = 0; i < outputString.size(); i++) {
			// 取出每行的对象
			List<String> tempRow = outputString.get(i);
			// 创建行
			HSSFRow row = sheet.createRow(i);
			// 开始创建单元格并赋值
			int x=0;
			for (String s : tempRow) {
				HSSFCell nameCell = row.createCell(x);
				nameCell.setCellValue(s);
				x++;
			}

			
		}
		// 生成文件
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
