package com.sapna.Spendwise.dto;

public class ExpenseSummaryUserDto {

	private int User_Id;
	private double TotalExpense;
	public int getUser_Id() {
		return User_Id;
	}
	public void setUser_Id(int user_Id) {
		User_Id = user_Id;
	}
	public double getTotalExpense() {
		return TotalExpense;
	}
	public void setTotalExpense(double totalExpense) {
		TotalExpense = totalExpense;
	}
	
}
