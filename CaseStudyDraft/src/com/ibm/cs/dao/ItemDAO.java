package com.ibm.cs.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

public class ItemDAO extends MasterDAO {
	int count;

	public ItemDAO() {
		super();
	}

	public boolean addItems(int batchId, int amount, Date manufactureDate, Date expirationDate, double purchasePrice) {
		PreparedStatement pst = null;
		count = 0;
		try {
			pst = conn.prepareStatement("INSERT INTO tbl_item(fk_batch_id, manufacture_date, expiration_date, purchase_price) "
					+ "VALUES(?,?,?,?)");
			pst.setInt(1, batchId);
			pst.setDate(2, new java.sql.Date(manufactureDate.getTime()));
			pst.setDate(3, new java.sql.Date(expirationDate.getTime()));
			pst.setDouble(4, purchasePrice);
			
			for(int i=0; i<amount; i++) {
				count += pst.executeUpdate();
			}
			if(count > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
