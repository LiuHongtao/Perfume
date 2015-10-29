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
	 * POI��ȡExcelʾ������2003��2007
	 * 
	 * @author Nanlei
	 * 
	 */
	public class XlsInput {
		private static String xls2003 = "C:\\student.xls";
		private static String xlsx2007 = "C:\\student.xlsx";
		/**
		 * ��ȡExcel2003��ʾ������
		 * 
		 * @param filePath
		 * @return
		 */
	private static List<Student> readFromXLS2003(String filePath) {
			File excelFile = null;// Excel�ļ�����
			InputStream is = null;// ����������
			String cellStr = null;// ��Ԫ�����հ��ַ�������
			List<Student> studentList = new ArrayList<Student>();// ���ط�װ���ݵ�List
			Student student = null;// ÿһ��ѧ����Ϣ����
	try {
				excelFile = new File(filePath);
				is = new FileInputStream(excelFile);// ��ȡ�ļ�������
				HSSFWorkbook workbook2003 = new HSSFWorkbook(is);// ����Excel2003�ļ�����
				HSSFSheet sheet = workbook2003.getSheetAt(0);// ȡ����һ��������������0
				// ��ʼѭ�������У���ͷ��������1��ʼ
				for (int i = 1; i <= sheet.getLastRowNum(); i++) {
					student = new Student();// ʵ����Student����
					HSSFRow row = sheet.getRow(i);// ��ȡ�ж���
					if (row == null) {// ���Ϊ�գ�������
						continue;
					}
	// ѭ��������Ԫ��
					for (int j = 0; j < row.getLastCellNum(); j++) {
						HSSFCell cell = row.getCell(j);// ��ȡ��Ԫ�����
						if (cell == null) {// ��Ԫ��Ϊ������cellStrΪ�մ�
							cellStr = "";
						} else if (cell.getCellType() == HSSFCell.CELL_TYPE_BOOLEAN) {// �Բ���ֵ�Ĵ���
							cellStr = String.valueOf(cell.getBooleanCellValue());
						} else if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {// ������ֵ�Ĵ���
							cellStr = cell.getNumericCellValue() + "";
						} else {// ���ఴ���ַ�������
							cellStr = cell.getStringCellValue();
						}
	// ���水�����ݳ���λ�÷�װ��bean��
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
					studentList.add(student);// ����װ��List
				}
	} catch (IOException e) {
				e.printStackTrace();
			} finally {// �ر��ļ���
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
//		File excelFile = null;// Excel�ļ�����
//		InputStream is = null;// ����������
//		String cellStr = null;// ��Ԫ�����հ��ַ�������
//		List<Student> studentList = new ArrayList<Student>();// ���ط�װ���ݵ�List
//		Student student = null;// ÿһ��ѧ����Ϣ����
//		try {
//			excelFile = new File(filePath);
//			is = new FileInputStream(excelFile);// ��ȡ�ļ�������
//			XSSFWorkbook workbook2007 = new XSSFWorkbook(is);// ����Excel2003�ļ�����
//			XSSFSheet sheet = workbook2007.getSheetAt(0);// ȡ����һ��������������0
//			// ��ʼѭ�������У���ͷ��������1��ʼ
//			for (int i = 1; i <= sheet.getLastRowNum(); i++) {
//				student = new Student();// ʵ����Student����
//				XSSFRow row = sheet.getRow(i);// ��ȡ�ж���
//				if (row == null) {// ���Ϊ�գ�������
//					continue;
//				}
//				// ѭ��������Ԫ��
//				for (int j = 0; j < row.getLastCellNum(); j++) {
//					XSSFCell cell = row.getCell(j);// ��ȡ��Ԫ�����
//					if (cell == null) {// ��Ԫ��Ϊ������cellStrΪ�մ�
//						cellStr = "";
//					} else if (cell.getCellType() == HSSFCell.CELL_TYPE_BOOLEAN) {// �Բ���ֵ�Ĵ���
//						cellStr = String.valueOf(cell.getBooleanCellValue());
//					} else if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {// ������ֵ�Ĵ���
//						cellStr = cell.getNumericCellValue() + "";
//					} else {// ���ఴ���ַ�������
//						cellStr = cell.getStringCellValue();
//					}
//					// ���水�����ݳ���λ�÷�װ��bean��
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
//				studentList.add(student);// ����װ��List
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		} finally {// �ر��ļ���
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
		 * ������
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
