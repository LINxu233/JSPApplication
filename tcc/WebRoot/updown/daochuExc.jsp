<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.jspsmart.upload.*" %>
<%@ page import="org.apache.poi.hssf.usermodel.*" %>
<%@ page import="java.io.*" %>
<jsp:directive.page import="com.model.TRuchang"/>
<%
String path = request.getContextPath();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
  
  <body> 
      <% 
      
            List ruchangList=(List)request.getAttribute("ruchangList");    
            
      
            HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = wb.createSheet("new sheet");
			
			HSSFRow row = sheet.createRow(0);
			HSSFCell cell=row.createCell((short)0);
			cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			cell.setCellValue("车型");
					
			cell=row.createCell((short)1);
			cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			cell.setCellValue("车牌号");
					
			cell=row.createCell((short)2);
			cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			cell.setCellValue("入场卡号");
			
			cell=row.createCell((short)3);
			cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			cell.setCellValue("入场时间");
			
			cell=row.createCell((short)4);
			cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			cell.setCellValue("出场时间");
			
			cell=row.createCell((short)5);
			cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			cell.setCellValue("停车费用");
			
			
			for(int i=0;i<ruchangList.size();i++)
			{
				TRuchang ruchang=(TRuchang)ruchangList.get(i);
				
				row=sheet.createRow(i+1);
				cell=row.createCell((short)0);
				cell.setEncoding(HSSFCell.ENCODING_UTF_16);
				cell.setCellValue(ruchang.getChexing());
						
				cell=row.createCell((short)1);
				cell.setEncoding(HSSFCell.ENCODING_UTF_16);
				cell.setCellValue(ruchang.getChehao());
				
				cell=row.createCell((short)2);
				cell.setEncoding(HSSFCell.ENCODING_UTF_16);
				cell.setCellValue(ruchang.getKahao());
				
				cell=row.createCell((short)3);
				cell.setEncoding(HSSFCell.ENCODING_UTF_16);
				cell.setCellValue(ruchang.getRushijian());
				
				cell=row.createCell((short)4);
				cell.setEncoding(HSSFCell.ENCODING_UTF_16);
				cell.setCellValue(ruchang.getChushijian());
				
				cell=row.createCell((short)5);
				cell.setEncoding(HSSFCell.ENCODING_UTF_16);
				cell.setCellValue(ruchang.getFeiyong());
				
			}
			
			
			String fujianPath="c:\\停车记录.xls";
			try
			{
				FileOutputStream fileOut = new FileOutputStream(fujianPath);
				wb.write(fileOut);
				fileOut.close();
			} 
			catch (Exception e)
			{
			       e.printStackTrace();
			}
			
          
      
          
          SmartUpload su = new SmartUpload(); // 新建一个SmartUpload对象 

          su.initialize(pageContext); // 初始化 

          su.setContentDisposition(null); 
          // 设定contentDisposition为null以禁止浏览器自动打开文件， 
          //保证点击链接后是下载文件。若不设定，则下载的文件扩展名为 
          //doc时，浏览器将自动用word打开它。扩展名为pdf时，将用acrobat打开

          //su.downloadFile("/uploadPath/file/liu.doc"); // 下载英文文件
          su.downloadFile(fujianPath, null, new String(java.net.URLDecoder.decode("停车记录.xls","UTF-8").getBytes(), "ISO8859-1")); // 下载中文文件
          //downloadFile(String sourceFilePathName, String contentType, String destFileName)
          out.clear();
          out=pageContext.pushBody(); 
      %> 

      
  </body>
</html>
