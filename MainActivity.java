package com.example.yogendra19.excel;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void order (View v) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("FirstExcelSheet");
        HSSFRow row = sheet.createRow(0);
        HSSFCell cell = row.createCell(0);
        cell.setCellValue("Name");
        /*cell = row.createCell(1);
        DataFormat format = workbook.createDataFormat();
        CellStyle dateStyle = workbook.createCellStyle();
        dateStyle.setDataFormat(format.getFormat("dd.mm.yyyy"));
        cell.setCellStyle(dateStyle);
        cell.setCellValue(new Date());*/
        row.createCell(1).setCellValue("Surname");
        /*sheet.autoSizeColumn(1);*/

        try {
            workbook.write(new FileOutputStream("/storage/emulated/0/Android/excel.xls"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
        public void view(View v){

            HSSFWorkbook workbook = null;
            String value = null;
            String value1 = null;
            try {
                workbook = new HSSFWorkbook(new FileInputStream("/storage/emulated/0/Android/excel.xls"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            HSSFSheet sheet = workbook.getSheetAt(0);
            HSSFRow row = sheet.getRow(0);
            value = row.getCell(0).getStringCellValue();
            value1 = row.getCell(1).getStringCellValue();
           /* if (row.getCell(0).getCellType() == HSSFCell.CELL_TYPE_STRING) {
                 value=row.getCell(0).getStringCellValue();
            }
            if (row.getCell(1).getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                System.out.println(row.getCell(1).getDateCellValue());
            }*/
            TextView x=(TextView)findViewById(R.id.textView);
            TextView y=(TextView)findViewById(R.id.textView2);
            x.setText(value);
            y.setText(value1);
    }
  }
 /*   public void order(View v) {
        try {

            AssetManager am=getAssets();// If this line gives you ERROR then try AssetManager am=getActivity().getAssets();
            InputStream is=am.open("excel.xls");
            Workbook wb =Workbook.getWorkbook(is);
            Sheet s=wb.getSheet(0);
            int row =s.getRows();
            int col=s.getColumns();
            String xx="";
            for(int i=0;i<row;i++)
            {
                for (int c=0;c<col;c++)
                {
                    Cell z=s.getCell(c,i);
                    xx=xx+z.getContents();

                }
                xx=xx+"\n";
            }
            display(xx);
        }

        catch (Exception e)
        {

        }

    }
    public void display(String value)
    {
        TextView x=(TextView)findViewById(R.id.textView);
        x.setText(value);
    }*/
