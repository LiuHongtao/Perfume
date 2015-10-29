package util;



	import java.io.File;
	import java.io.FileInputStream;
	import java.io.IOException;
	import java.io.InputStream;
	import java.util.ArrayList;
	import java.util.List;
	import org.apache.poi.hssf.usermodel.HSSFCell;
	import org.apache.poi.hssf.usermodel.HSSFRow;
	import org.apache.poi.hssf.usermodel.HSSFSheet;
	import org.apache.poi.hssf.usermodel.HSSFWorkbook;
	import org.apache.poi.ss.usermodel.*;
	import dto.Student;
	/**
	 * POI读取Excel示例，分2003和2007
	 * 
	 * @author Nanlei
	 * 
	 */
	public class XlsInput {
		private static String xls2003 = "C:\\student.xls";
		private static String xlsx2007 = "C:\\student.xlsx";
		/**
		 * 读取Excel2003的示例方法
		 * 
		 * @param filePath
		 * @return
		 */
	private static List<Student> readFromXLS2003(String filePath) {
			File excelFile = null;// Excel文件对象
			InputStream is = null;// 输入流对象
			String cellStr = null;// 单元格，最终按字符串处理
			List<Student> studentList = new ArrayList<Student>();// 返回封装数据的List
			Student student = null;// 每一个学生信息对象
	try {
				excelFile = new File(filePath);
				is = new FileInputStream(excelFile);// 获取文件输入流
				HSSFWorkbook workbook2003 = new HSSFWorkbook(is);// 创建Excel2003文件对象
				HSSFSheet sheet = workbook2003.getSheetAt(0);// 取出第一个工作表，索引是0
				// 开始循环遍历行，表头不处理，从1开始
				for (int i = 1; i <= sheet.getLastRowNum(); i++) {
					student = new Student();// 实例化Student对象
					HSSFRow row = sheet.getRow(i);// 获取行对象
					if (row == null) {// 如果为空，不处理
						continue;
					}
	// 循环遍历单元格
					for (int j = 0; j < row.getLastCellNum(); j++) {
						HSSFCell cell = row.getCell(j);// 获取单元格对象
						if (cell == null) {// 单元格为空设置cellStr为空串
							cellStr = "";
						} else if (cell.getCellType() == HSSFCell.CELL_TYPE_BOOLEAN) {// 对布尔值的处理
							cellStr = String.valueOf(cell.getBooleanCellValue());
						} else if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {// 对数字值的处理
							cellStr = cell.getNumericCellValue() + "";
						} else {// 其余按照字符串处理
							cellStr = cell.getStringCellValue();
						}
	// 下面按照数据出现位置封装到bean中
						if (j == 0) {
							student.setName(cellStr);
						} else if (j == 1) {
							student.setGender(cellStr);
						} else if (j == 2) {
							student.setAge(new Double(cellStr).intValue());
						} else if (j == 3) {
							student.setSclass(cellStr);
						} else {
							student.setScore(new Double(cellStr).intValue());
						}
					}
					studentList.add(student);// 数据装入List
				}
	} catch (IOException e) {
				e.printStackTrace();
			} finally {// 关闭文件流
				if (is != null) {
					try {
						is.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			return studentList;
		}
	
	
//	public static List<Student> readFromXLSX2007(String filePath) {
//		File excelFile = null;// Excel文件对象
//		InputStream is = null;// 输入流对象
//		String cellStr = null;// 单元格，最终按字符串处理
//		List<Student> studentList = new ArrayList<Student>();// 返回封装数据的List
//		Student student = null;// 每一个学生信息对象
//		try {
//			excelFile = new File(filePath);
//			is = new FileInputStream(excelFile);// 获取文件输入流
//			XSSFWorkbook workbook2007 = new XSSFWorkbook(is);// 创建Excel2003文件对象
//			XSSFSheet sheet = workbook2007.getSheetAt(0);// 取出第一个工作表，索引是0
//			// 开始循环遍历行，表头不处理，从1开始
//			for (int i = 1; i <= sheet.getLastRowNum(); i++) {
//				student = new Student();// 实例化Student对象
//				XSSFRow row = sheet.getRow(i);// 获取行对象
//				if (row == null) {// 如果为空，不处理
//					continue;
//				}
//				// 循环遍历单元格
//				for (int j = 0; j < row.getLastCellNum(); j++) {
//					XSSFCell cell = row.getCell(j);// 获取单元格对象
//					if (cell == null) {// 单元格为空设置cellStr为空串
//						cellStr = "";
//					} else if (cell.getCellType() == HSSFCell.CELL_TYPE_BOOLEAN) {// 对布尔值的处理
//						cellStr = String.valueOf(cell.getBooleanCellValue());
//					} else if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {// 对数字值的处理
//						cellStr = cell.getNumericCellValue() + "";
//					} else {// 其余按照字符串处理
//						cellStr = cell.getStringCellValue();
//					}
//					// 下面按照数据出现位置封装到bean中
//					if (j == 0) {
//						student.setName(cellStr);
//					} else if (j == 1) {
//						student.setGender(cellStr);
//					} else if (j == 2) {
//						student.setAge(new Double(cellStr).intValue());
//					} else if (j == 3) {
//						student.setSclass(cellStr);
//					} else {
//						student.setScore(new Double(cellStr).intValue());
//					}
//				}
//				studentList.add(student);// 数据装入List
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		} finally {// 关闭文件流
//			if (is != null) {
//				try {
//					is.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//		}
//		return studentList;
//	}

	/**
		 * 主函数
		 * 
		 * @param args
		 */
		public static void main(String[] args) {
			long start = System.currentTimeMillis();
			List<Student> list = readFromXLS2003(xls2003);
			for (Student student : list) {
				System.out.println(student);
			}
			long end = System.currentTimeMillis();
			System.out.println((end - start) + " ms done!");
		}
	

}
