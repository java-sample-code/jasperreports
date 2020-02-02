package com.hrd;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.export.ExporterInput;
import net.sf.jasperreports.export.OutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

public class JaperReportsDemo {
	public static void main(String[] args) throws Exception {
		
		URL url = JaperReportsDemo.class.getResource("Product_Report.jasper");
		
		String path = url.getPath().replace("%20", " ");
		
		JasperPrint print = JasperFillManager.fillReport(path, null, getConnection());
		
//		JRPdfExporter pdfExporter = new JRPdfExporter();
//		JRXlsExporter -> Excel file
		JRDocxExporter pdfExporter = new JRDocxExporter();

		ExporterInput exporterInput = new SimpleExporterInput(print);
		pdfExporter.setExporterInput(exporterInput);
		
		String outputPath = "/Users/SoengSaravit/Desktop/Report/Product_Report.docx";
		OutputStreamExporterOutput output = new SimpleOutputStreamExporterOutput(outputPath);
		pdfExporter.setExporterOutput(output);
		
		pdfExporter.exportReport();
		System.out.println("==>Success");
	}
	
	public static Connection getConnection() {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		Connection connection = null;
		String url = "jdbc:postgresql://localhost:5432/sc8_db";
		String user = "postgres";
		String password = "1234";
		
		try {
			connection = DriverManager.getConnection(url, user, password);
			return connection;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}
}
