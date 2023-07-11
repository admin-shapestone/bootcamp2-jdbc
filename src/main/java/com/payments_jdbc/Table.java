package com.payments_jdbc;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Table {

	public static String createTable1() {

		StringBuffer sb = new StringBuffer("create table payments(");
		sb.append("PaymentId Int,");
		sb.append("AccountId BIGINT NOT NULL, ");
		sb.append("PurposeOfPayment varchar(20),");
		sb.append("AmountPaid Decimal(10,2) NOT NULL,");
		sb.append("AmountRecived Decimal(10,2),");
		sb.append("DateOfPayment Date NOT NULL,");
		sb.append("FOREIGN KEY (AccountId) REFERENCES AccountsTable(AccountId));");

		return sb.toString();

	}

	public static ArrayList<String> prepareInsertQueries(List<Payments_Pojo> payments) {
		ArrayList<String> queryList = new ArrayList<>();

		for (int i = 0; i < payments.size(); i++) {

			StringBuffer sb = new StringBuffer("Insert into payments(");
			sb.append("PaymentId,AccountId,PurposeOfPayment,AmountPaid,AmountRecived,DateOfPayment)");
			sb.append("values(");
			sb.append(payments.get(i).getPaymentId() + " ,");
			sb.append(payments.get(i).getAccountId() + " ,");
			sb.append("' " + payments.get(i).getPurposeOfPayment() + " ' , ");
			sb.append(payments.get(i).getAmountPaid() + " ,");
			sb.append(payments.get(i).getAmountRecived() + " ,");
			sb.append(" ' " + payments.get(i).getDateOfPayment() + " ' )");

			queryList.add(sb.toString());
		}
		return queryList;

	}

	public static void insertRecordsIntoPayments(ArrayList<String> prepareInsertQueries, Statement statement)
			throws SQLException {

		for (String query : prepareInsertQueries) {
			statement.execute(query);
		}

	}

}
