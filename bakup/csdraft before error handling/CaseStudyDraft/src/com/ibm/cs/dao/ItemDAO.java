package com.ibm.cs.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import com.ibm.cs.model.Item;

/**
 * Data Access Object for Item
 * 
 * @author Dan Alejandro A. Vargas
 * @see Item
 */
public class ItemDAO extends MasterDAO {
	int count;

	public ItemDAO() {
		super();
	}

	/**
	 * Gets arraylist of all items from the item DAO
	 * 
	 * @return ArrayList containing all items in the database
	 * @see Item
	 */
	public ArrayList<Item> getItemList() {
		PreparedStatement pst = null;
		ResultSet rs = null;

		ArrayList<Item> itemList = new ArrayList<>();
		int itemId, fkBatchId;
		double purchasePrice;
		Date manufactureDate, expirationDate;

		try {
			pst = conn.prepareStatement("SELECT * FROM tbl_item");
			rs = pst.executeQuery();

			while (rs.next()) {
				itemId = rs.getInt("item_id");
				fkBatchId = rs.getInt("fk_batch_id");
				purchasePrice = rs.getDouble("purchase_price");
				manufactureDate = rs.getDate("manufacture_date");
				expirationDate = rs.getDate("expiration_date");
				itemList.add(new Item(itemId, fkBatchId, manufactureDate, expirationDate, purchasePrice));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources(rs, pst);
		}
		return itemList;
	}

	/**
	 * Gets arraylist of selected items from the database
	 * 
	 * @param itemIds
	 *            array of all items to be selected
	 * @return ArrayList containing all items requested in the parameter
	 * @see Item
	 */
	public ArrayList<Item> getItemList(int[] itemIds) {
		PreparedStatement pst = null;
		ResultSet rs = null;

		ArrayList<Item> itemList = new ArrayList<>();
		int fkBatchId;
		double purchasePrice;
		Date manufactureDate, expirationDate;

		try {
			pst = conn.prepareStatement("SELECT * FROM tbl_item WHERE item_id=?");
			for (int id : itemIds) {
				pst.setInt(1, id);
				rs = pst.executeQuery();

				if (rs.next()) {
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

	// v======= ITEM CRUD ===========v

	public boolean addItems(int batchId, int amount, Date manufactureDate, Date expirationDate, double purchasePrice) {
		PreparedStatement pst = null;
		count = 0;
		try {
			pst = conn.prepareStatement(
					"INSERT INTO tbl_item(fk_batch_id, manufacture_date, expiration_date, purchase_price) "
							+ "VALUES(?,?,?,?)");
			pst.setInt(1, batchId);
			if(manufactureDate != null) pst.setDate(2, new java.sql.Date(manufactureDate.getTime()));
			else pst.setNull(2, java.sql.Types.BOOLEAN);
			if(expirationDate != null) pst.setDate(3, new java.sql.Date(expirationDate.getTime()));
			else pst.setNull(3, java.sql.Types.BOOLEAN);
			pst.setDouble(4, purchasePrice);

			for (int i = 0; i < amount; i++) {
				count += pst.executeUpdate();
			}
			if (count > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean editItems(int[] itemIds, Date manufactureDate, Date expirationDate, double purchasePrice) {
		PreparedStatement pst = null;
		count = 0;

		try {
			pst = conn.prepareStatement(
					"UPDATE tbl_item SET manufacture_date=?, expiration_date=?, purchase_price=? " + "WHERE item_id=?");
			if(manufactureDate != null) pst.setDate(1, new java.sql.Date(manufactureDate.getTime()));
			else pst.setNull(1, java.sql.Types.BOOLEAN);
			if(expirationDate != null) pst.setDate(2, new java.sql.Date(expirationDate.getTime()));
			else pst.setNull(2, java.sql.Types.BOOLEAN);
			pst.setDouble(3, purchasePrice);

			for (int id : itemIds) {
				pst.setInt(4, id);
				count += pst.executeUpdate();
			}

			if (count > 0) {
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

			for (int id : itemIds) {
				pst.setInt(1, id);
				count += pst.executeUpdate();
			}

			if (count > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources(pst);
		}
		return false;
	}
}
