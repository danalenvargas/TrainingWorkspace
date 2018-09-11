package com.ibm.cs.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import com.ibm.cs.model.Batch;

public class BatchDAO extends MasterDAO {
	int count;
	
	public BatchDAO() {
		super();
	}
	
	public ArrayList<Batch> getBatchList(int productId){
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		ArrayList<Batch> batchList = new ArrayList<>();
		int batchId, amount, remainingAmount;
		String comments, supplier;
		Date entryTimestamp;
		
		try {
			pst = conn.prepareStatement("SELECT * FROM tbl_batch WHERE is_active = 1 AND fk_product_id = ?");
			pst.setInt(1, productId);
			rs = pst.executeQuery();
			
			while(rs.next()) {
				batchId = rs.getInt("batch_id");
				amount = rs.getInt("amount");
				remainingAmount = calculateRemainingAmount(batchId);
				comments = rs.getString("comments");
				supplier = rs.getString("supplier");
				entryTimestamp = rs.getTimestamp("entry_timestamp");
				batchList.add(new Batch(batchId, productId, amount, remainingAmount, comments, supplier, entryTimestamp));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
            closeResources(rs, pst);
        }
		return batchList;
	}
	
	public int addBatch(int productId, int amount, String comments, String supplier) {
		int newBatchId;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			pst = conn.prepareStatement("INSERT INTO tbl_batch(fk_product_id, amount, comments, supplier) "
					+ "VALUES(?,?,?,?)");
			pst.setInt(1, productId);
			pst.setInt(2, amount);
			pst.setString(3, comments);
			pst.setString(4, supplier);
			pst.executeUpdate();
			
			closeResources(pst);
			pst = conn.prepareStatement("SELECT LAST_INSERT_ID() AS lastId from tbl_batch");
			rs = pst.executeQuery();
			if(rs.next()) {
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
			
			if(rs.next()) {
				fkProductId = rs.getInt("fk_product_id");
				amount = rs.getInt("amount");
				remainingAmount = calculateRemainingAmount(batchId);
				comments = rs.getString("comments");
				supplier = rs.getString("supplier");
				entryTimestamp = rs.getTimestamp("entry_timestamp");
				batch = new Batch(batchId, fkProductId, amount, remainingAmount, comments, supplier, entryTimestamp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
            closeResources(rs, pst);
        }
		return batch;
	}
	
	public boolean editBatch(int batchId, int productId, int amount, String comments, String supplier) {
		PreparedStatement pst = null;
		
		try {
			pst = conn.prepareStatement("UPDATE tbl_batch SET fk_product_id=?, amount=?, comments=?, supplier=? "
					+ "WHERE batch_id=?");
			pst.setInt(1, productId);
			pst.setInt(2, amount);
			pst.setString(3, comments);
			pst.setString(4, supplier);
			pst.setInt(5, batchId);
			count = pst.executeUpdate();
			
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
	

	public boolean deleteBatch(int batchId) {
		PreparedStatement pst = null;
		
		try {
			pst = conn.prepareStatement("UPDATE tbl_batch SET is_active=0 WHERE batch_id=?");
			pst.setInt(1, batchId);
			
			count = pst.executeUpdate();
			if(count>0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
            closeResources(pst);
        }
		return false;
	}
	
	private int calculateRemainingAmount(int batchId) {
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		int remainingAmount = 0;
		try {
			pst = conn.prepareStatement("SELECT COUNT(*) AS amount FROM tbl_item "
					+ "WHERE tbl_item.fk_batch_id = ?");
			pst.setInt(1, batchId);
			
			rs = pst.executeQuery();
			if(rs.next()) {
				remainingAmount = rs.getInt("amount");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
            closeResources(rs, pst);
        }
		return remainingAmount;
	}

}
