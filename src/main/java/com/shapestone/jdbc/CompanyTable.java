package com.shapestone.jdbc;

import java.util.ArrayList;
import java.util.List;

public class CompanyTable {
    public static  String createTable(List<CompanyPojo> companyList) {
        StringBuilder sb = new StringBuilder("CREATE TABLE Company (");
        sb.append("EmpID INT PRIMARY KEY, ");
        sb.append("CompanyId INT NOT NULL, ");
        sb.append("CompanyName VARCHAR(30) NOT NULL, ");
        sb.append("EmailId VARCHAR(40) NOT NULL, ");
        sb.append("DateOfReleving VARCHAR(90) NOT NULL, ");
        sb.append("AmountReceived INT NOT NULL, ");
        sb.append("CtcTimeOfReleving VARCHAR(90), ");
        sb.append("DateOfJoining VARCHAR(90))");
        return sb.toString();
    }

    public static ArrayList<String> prepareInsertQueries(List<CompanyPojo> companyList) {
        ArrayList<String> queryList = new ArrayList<>();
        for (CompanyPojo company : companyList) {
            StringBuilder sb = new StringBuilder("INSERT INTO Company (");
            sb.append("EmpID, CompanyId, CompanyName, EmailId, DateOfReleving, AmountReceived, CtcTimeOfReleving, DateOfJoining)");
            sb.append(" VALUES (");
            sb.append(company.getEmpId()).append(", ");
            sb.append(company.getCompanyId()).append(", ");
            sb.append("'").append(company.getCompanyName()).append("', ");
            sb.append("'").append(company.getEmailId()).append("', ");
            sb.append("'").append(company.getDateOfReleving()).append("', ");
            sb.append(company.getAmountReceived()).append(", ");
            sb.append("'").append(company.getCtcTimeOfReleving()).append("', ");
            sb.append("'").append(company.getDateOfJoining()).append("')");
            queryList.add(sb.toString());
        }
        return queryList;
    }


		}


