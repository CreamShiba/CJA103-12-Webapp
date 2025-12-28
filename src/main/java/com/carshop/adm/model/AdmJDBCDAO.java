package com.carshop.adm.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdmJDBCDAO implements AdmDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/cja103g1?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "inuyama0213";

	private static final String INSERT_STMT = 
		"INSERT INTO adm (adm_account,adm_password,adm_name,adm_email,hiredate) VALUES (?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT adm_no,adm_account,adm_password,adm_name,adm_email,adm_status,hiredate FROM adm order by adm_no";
	private static final String GET_ONE_STMT = 
		"SELECT adm_no,adm_account,adm_password,adm_name,adm_email,adm_status,hiredate FROM adm where adm_no = ?";
	private static final String DELETE = 
		"DELETE FROM adm where adm_no = ?";
	private static final String UPDATE = 
		"UPDATE adm set adm_account=?, adm_password=?, adm_name=?, adm_email=?, hiredate=? , adm_status=? where adm_no = ?";

	@Override
	public void insert(AdmVO admVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, admVO.getAdmAccount());
			pstmt.setString(2, admVO.getAdmPassword());
			pstmt.setString(3, admVO.getAdmName());
			pstmt.setString(4, admVO.getAdmEmail());
			pstmt.setDate(5, new java.sql.Date(admVO.getHiredate().getTime()));

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void update(AdmVO admVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, admVO.getAdmAccount());
			pstmt.setString(2, admVO.getAdmPassword());
			pstmt.setString(3, admVO.getAdmName());
			pstmt.setString(4, admVO.getAdmEmail());
			pstmt.setDate(5, admVO.getHiredate());
			pstmt.setInt(6, admVO.getAdmStatus());
			pstmt.setInt(7, admVO.getAdmno());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void delete(Integer admno) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, admno);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public AdmVO findByPK(Integer admno) {

		AdmVO admVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, admno);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// admVo 也稱為 Domain objects
				admVO = new AdmVO();
				admVO.setAdmno(rs.getInt("adm_no"));
				admVO.setAdmAccount(rs.getString("adm_account"));
				admVO.setAdmPassword(rs.getString("adm_password"));
				admVO.setAdmName(rs.getString("adm_name"));
				admVO.setAdmEmail(rs.getString("adm_email"));
				admVO.setHiredate(rs.getDate("hiredate"));
				admVO.setAdmStatus(rs.getByte("adm_status"));

			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return admVO;
	}

	@Override
	public List<AdmVO> getAll() {
		List<AdmVO> list = new ArrayList<AdmVO>();
		AdmVO admVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				admVO = new AdmVO();
				admVO.setAdmno(rs.getInt("adm_no"));
				admVO.setAdmAccount(rs.getString("adm_account"));
				admVO.setAdmPassword(rs.getString("adm_password"));
				admVO.setAdmName(rs.getString("adm_name"));
				admVO.setAdmEmail(rs.getString("adm_email"));
				admVO.setHiredate(rs.getDate("hiredate"));
				admVO.setAdmStatus(rs.getByte("adm_status"));
				list.add(admVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}

}
