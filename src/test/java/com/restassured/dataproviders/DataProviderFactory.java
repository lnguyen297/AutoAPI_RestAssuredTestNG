package com.restassured.dataproviders;

import com.restassured.constants.ConfigData;
import com.restassured.helpers.ExcelHelper;
import org.testng.annotations.DataProvider;

public class DataProviderFactory {
    @DataProvider(name = "registerDataSuccess")
    public Object[][] registerDataSuccess() {
        ExcelHelper excelHelper = new ExcelHelper();
        return excelHelper.getDataHashTable(
                ConfigData.EXCEL_DATA_FILE_PATH,
                "RegisterUser",
                1,
                5
        );
    }
}
