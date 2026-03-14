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

    @DataProvider(name = "registerDataFailure")
    public Object[][] registerDataSuccessFailure() {
        ExcelHelper excelHelper = new ExcelHelper();
        return excelHelper.getDataHashTable(
                ConfigData.EXCEL_DATA_FILE_PATH,
                "RegisterUser",
                6,
                12
        );
    }

    @DataProvider(name = "createCategorySuccess")
    public Object[][] createCategorySuccess() {
        ExcelHelper excelHelper = new ExcelHelper();
        return excelHelper.getDataHashTable(
                ConfigData.EXCEL_DATA_FILE_PATH,
                "Category",
                1,
                2
        );
    }

    @DataProvider(name = "createCategoryFailure")
    public Object[][] createCategoryFailure() {
        ExcelHelper excelHelper = new ExcelHelper();
        return excelHelper.getDataHashTable(
                ConfigData.EXCEL_DATA_FILE_PATH,
                "Category",
                3,
                3
        );
    }

}
