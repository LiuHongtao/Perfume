package perfume.util;

//import java.io.File;
//import java.io.FileOutputStream;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import org.apache.poi.hssf.usermodel.HSSFCell;
//import org.apache.poi.hssf.usermodel.HSSFRow;
//import org.apache.poi.hssf.usermodel.HSSFSheet;
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//import dto.Student;
//
///**
// * ����Excelʾ����2003��2007
// * 
// * @author Nanlei
// */
//
//public class XlsOutput {
//
//	/**
//	 * ����2003�ļ��ķ���
//	 * 
//	 * @param filePath
//	 */
//	public static void generateExcel2003(String projectName,
//			List<List<String>> outputString) {
//
//
//		// �ȴ�������������
//		HSSFWorkbook workbook2003 = new HSSFWorkbook();
//		// �����������������
//		HSSFSheet sheet = workbook2003.createSheet("��Ŀ��Ϣ");
//		// �������϶��󴴽��к͵�Ԫ��
//		for (int i = 0; i < outputString.size(); i++) {
//			// ȡ��ÿ�еĶ���
//			List<String> tempRow = outputString.get(i);
//			// ������
//			HSSFRow row = sheet.createRow(i);
//			// ��ʼ������Ԫ�񲢸�ֵ
//			int x=0;
//			for (String s : tempRow) {
//				HSSFCell nameCell = row.createCell(x);
//				nameCell.setCellValue(s);
//				x++;
//			}
//
//			
//		}
//		// �����ļ�
//		File file = new File("./result/"+projectName+".xls");
//		FileOutputStream fos = null;
//		try {
//			fos = new FileOutputStream(file);
//			workbook2003.write(fos);
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			if (fos != null) {
//				try {
//					fos.close();
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		}
//
//	}
//
//}
