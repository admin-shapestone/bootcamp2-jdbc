
package com.shapestone.jdbc;

import java.util.ArrayList;
import java.util.List;

public class EmployeeTable {
    public static String createTable(List<EmployeePojo> employee) {
        StringBuffer sb = new StringBuffer("CREATE TABLE employee (");
        sb.append("empId INT PRIMARY KEY,");
        sb.append("name VARCHAR(20) NOT NULL,");
        sb.append("age INT NOT NULL,");
        sb.append("gender VARCHAR(20) NOT NULL,");
        sb.append("address VARCHAR(20) NOT NULL,");
        sb.append("phoneNumber BIGINT NOT NULL)");
        return sb.toString();
    }

    public static ArrayList<String> prepareInsertQueries(List<EmployeePojo> employee) {
        ArrayList<String> queryList = new ArrayList<>();
        for (int i = 0; i < employee.size(); i++) {
            StringBuffer sb = new StringBuffer("INSERT INTO employee (");
            sb.append("empId, name, age, gender, address, phoneNumber)");
            sb.append(" VALUES (");
            sb.append(employee.get(i).getEmpId() + ", ");
            sb.append("'" + employee.get(i).getName() + "', ");
            sb.append(employee.get(i).getAge() + ", ");
            sb.append("'" + employee.get(i).getGender() + "', ");
            sb.append("'" + employee.get(i).getAddress() + "', ");
            sb.append(employee.get(i).getPhoneNumber() + ")");
            
            queryList.add(sb.toString());
        }
        return queryList;
    }
}

