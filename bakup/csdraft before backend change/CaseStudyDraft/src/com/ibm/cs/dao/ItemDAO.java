package com.ibm.cs.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import com.ibm.cs.model.Item;

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
	
	public ArrayList<Item> getItemList(int batchId){
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		ArrayList<Item> itemList = new ArrayList<>();
		int itemId;
		double purchasePrice;
		Date manufactureDate, expirationDate;
		
		try {
			pst = conn.prepareStatement("SELECT * FROM tbl_item WHERE fk_batch_id=?");
			pst.setInt(1,  batchId);
			rs = pst.executeQuery();
			
			while(rs.next()) {
				itemId = rs.getInt("item_id");
				purchasePrice = rs.getDouble("purchase_price");
				manufactureDate = rs.getDate("manufacture_date");
				expirationDate = rs.getDate("expiration_date");
				itemList.add(new Item(itemId, batchId, manufactureDate, expirationDate, purchasePrice));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
            closeResources(rs, pst);
        }
		return itemList;
	}
	
	public ArrayList<Item> getItemList(int[] itemIds){
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		ArrayList<Item> itemList = new ArrayList<>();
		int fkBatchId;
		double purchasePrice;
		Date manufactureDate, expirationDate;
		
		try {
			pst = conn.prepareStatement("SELECT * FROM tbl_item WHERE item_id=?");
			for(int id : itemIds) {
				pst.setInt(1,  id);
				rs = pst.executeQuery();
				
				if(rs.next()) {
					fkBatchId = rs.getInt("fk_batch_id");
					purchasePrice = rs.getDouble("purchase_price");
					manufactureDate = rs.getDate("manufacture_date");
					expirationDate = rs.getDate("expiration_date");
					itemList.add(new Item(id, fkBatchId, manufactureDate, expirationDate, purchasePrice));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
            closeResources(rs, pst);
        }
		return itemList;
	}
	
	public boolean editItems(int[] itemIds, Date manufactureDate, Date expirationDate, double purchasePrice) {
		PreparedStatement pst = null;
		count = 0;
		
		try {
			pst = conn.prepareStatement("UPDATE tbl_item SET manufacture_date=?, expiration_date=?, purchase_price=? "
					+ "WHERE item_id=?");
			pst.setDate(1, new java.sql.Date(manufactureDate.getTime()));
			pst.setDate(2, new java.sql.Date(expirationDate.getTime()));
			pst.setDouble(3, purchasePrice);
			
			for(int id : itemIds) {
				pst.setInt(4, id);
				count += pst.executeUpdate();
			}
			
			if(count > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
            closeResources(pst);
        }
		return false;
	}
	
	public boolean deleteItems(int[] itemIds) {
		PreparedStatement pst = null;
		count = 0;
		
		try {
			pst = conn.prepareStatement("DELETE FROM tbl_item WHERE item_id=?;");
			
			for(int id : itemIds) {
				pst.setInt(1, id);
				count += pst.executeUpdate();
			}
			
			if(count > 0) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeResources(pst);
        }
		return false;
	}
}
