package com.suman.finance.personal_finance_backend.utility;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.suman.finance.personal_finance_backend.model.UPITransactionEntity;


public class ExcelHelper {

    public static List<UPITransactionEntity> parseExcel(InputStream is) throws IOException {
        List<UPITransactionEntity> transactions = new ArrayList<>();
        Workbook workbook = new XSSFWorkbook(is);
        Sheet sheet = workbook.getSheetAt(0);

        for (Row row : sheet) {
            if (row.getRowNum() == 0) continue; // Skip header

            UPITransactionEntity txn = new UPITransactionEntity();
            txn.setTxnDate(row.getCell(0).getLocalDateTimeCellValue().toLocalDate());
            txn.setTxnTime(row.getCell(1).getLocalDateTimeCellValue().toLocalTime());
            txn.setBankName(row.getCell(2).getStringCellValue());
            txn.setAccountNumber(row.getCell(3).getStringCellValue());
            txn.setSender(row.getCell(4).getStringCellValue());
            txn.setReceiver(row.getCell(5).getStringCellValue());
            txn.setPaymentReference(row.getCell(6).getStringCellValue());
            txn.setPayCollect(row.getCell(7).getStringCellValue());
            txn.setAmount(BigDecimal.valueOf(row.getCell(8).getNumericCellValue()));
            txn.setDrCr(row.getCell(9).getStringCellValue());
            txn.setStatus(row.getCell(10).getStringCellValue());

            transactions.add(txn);
        }

        workbook.close();
        return transactions;
    }
}