package com.ibm.cs.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;

import com.ibm.cs.model.Batch;

/**
 * Data Access Object for Batch
 * 
 * @author Dan Alejandro A. Vargas
 * @see Batch
 */
public class BatchDAO extends MasterDAO {
	int count;

	public BatchDAO() {
		super();
	}

	/**
	 * Gets hash map of all batches from the database
	 * 
	 * @return HashMap(key = batch id, value = Batch object) containing all batches
	 *         in the database
	 * @see Batch
	 */
	public HashMap<Integer, Batch> getBatchMap() {
		PreparedStatement pst = null;
		ResultSet rs = null;

		HashMap<Integer, Batch> batchMap = new HashMap<>();
		int batchId, amount, remainingAmount, fkProductId;
		String comments, supplier;
		Date entryTimestamp;

		try {
			pst = conn.prepareStatement("SELECT * FROM tbl_batch");
			rs = pst.executeQuery();

			while (rs.next()) {
				batchId = rs.getInt("batch_id");
				fkProductId = rs.getInt("fk_product_id");
				amount = rs.getInt("amount");
				// remainingAmount = calculateRemainingAmount(batchId);
				remainingAmount = 0; // will just increment later at mapping stage, instead of making separate DB query
				comments = rs.getString("comments");
				supplier = rs.getString("supplier");
				entryTimestamp = rs.getTimestamp("entry_timestamp");
				batchMap.put(batchId,
						new Batch(batchId, fkProductId, amount, remainingAmount, comments, supplier, entryTimestamp));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources(rs, pst);
		}
		return batchMap;
	}

	/**
	 * Utility method to calculate remaining items in the database that belong to
	 * this batch
	 * 
	 * @param batchId
	 *            id of the batch
	 * @return int representing the remaining items in the db that belong to this
	 *         batch
	 */
	private int calculateRemainingAmount(int batchId) {
		PreparedStatement pst = null;
		ResultSet rs = null;

		int remainingAmount = 0;
		try {
			pst = conn.prepareStatement("SELECT COUNT(*) AS amount FROM tbl_item " + "WHERE tbl_item.fk_batch_id = ?");
			pst.setInt(1, batchId);

			rs = pst.executeQuery();
			if (rs.next()) {
				remainingAmount = rs.getInt("amount");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources(rs, pst);
		}
		return remainingAmount;
	}
	
	// v======= BATCH CRUD ===========v

	public int addBatch(int productId, int amount, String comments, String supplier) {
		int newBatchId;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			pst = conn.prepareStatement(
					"INSERT INTO tbl_batch(fk_product_id, amount, comments, supplier) " + "VALUES(?,?,?,?)");
			pst.setInt(1, productId);
			pst.setInt(2, amount);
			pst.setString(3, comments);
			pst.setString(4, supplier);
			pst.executeUpdate();

			closeResources(pst);
			pst = conn.prepareStatement("SELECT LAST_INSERT_ID() AS lastId from tbl_batch");
			rs = pst.executeQuery();
			if (rs.next()) {
				newBatchId = rs.getInt("lastId");
				return newBatchId;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources(pst);
		}
		return -1;
	}

	public Batch getBatch(int batchId) {
		PreparedStatement pst = null;
		ResultSet rs = null;

		int fkProductId, amount, remainingAmount;
		String comments, supplier;
		Date entryTimestamp;
		Batch batch = null;

		try {
			pst = conn.prepareStatement("SELECT * FROM tbl_batch WHERE batch_id=?");
			pst.setInt(1, batchId);

			rs = pst.executeQuery();

			if (rs.next()) {
				fkProductId = rs.getInt("fk_product_id");
				amount = rs.getInt("amount");
				remainingAmount = calculateRemainingAmount(batchId);
				comments = rs.getString("comments");
				supplier = rs.getString("supplier");
				entryTimestamp = rs.getTimestamp("entry_timestamp");
				batch = new Batch(batchId, fkProductId, amount, remainingAmount, comments, supplier, entryTimestamp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources(rs, pst);
		}
		return batch;
	}

	public boolean editBatch(int batchId, int productId, int amount, String comments, String supplier) {
		PreparedStatement pst = null;

		try {
			pst = conn.prepareStatement(
					"UPDATE tbl_batch SET fk_product_id=?, amount=?, comments=?, supplier=? " + "WHERE batch_id=?");
			pst.setInt(1, productId);
			pst.setInt(2, amount);
			pst.setString(3, comments);
			pst.setString(4, supplier);
			pst.setInt(5, batchId);
			count = pst.executeUpdate();

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

	public boolean deleteBatch(int batchId) {
		PreparedStatement pst = null;

		try {
			pst = conn.prepareStatement("DELETE FROM tbl_batch WHERE batch_id=?");
			pst.setInt(1, batchId);

			count = pst.executeUpdate();
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
