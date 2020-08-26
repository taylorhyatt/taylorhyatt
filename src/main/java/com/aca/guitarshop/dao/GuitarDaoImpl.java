package com.aca.guitarshop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.aca.guitarshop.model.Brand;
import com.aca.guitarshop.model.Guitar;

public class GuitarDaoImpl implements GuitarDao {

	private static String selectAllGuitars =
			"SELECT id, model, releaseYear, brandId, bodyStyle, updateDateTime, createDateTime "
			+ "FROM guitar ";

	private static String selectGuitarsByBrand = 
			"SELECT id, model, releaseYear, brandId, bodyStyle, updateDateTime, createDateTime "
			+ "FROM guitar " + "WHERE BrandId = ? ";

	private static String selectGuitarsByModel =
			"SELECT id, model, releaseYear, brandId, bodyStyle, updateDateTime, createDateTime "
			+ "FROM guitar " + "WHERE model = ? ";

	private static String selectGuitarsByBodyStyle = 
			"SELECT id, model, releaseYear, brandId, bodyStyle, updateDateTime, createDateTime "
			+ "FROM guitar " + "WHERE bodyStyle = ? ";

	private static String selectGuitarsByReleaseYear = 
			"SELECT id, model, releaseYear, brandId, bodyStyle, updateDateTime, createDateTime "
			+ "FROM guitar " + "WHERE releaseYear = ? ";

	private static String selectGuitarsById = 
			"SELECT id, model, releaseYear, brandId, bodyStyle, updateDateTime, createDateTime "
			+ "FROM guitar " + "WHERE id = ? ";

	private static String insertGuitar = 
			"INSERT INTO guitar (model, releaseYear, brandId, bodyStyle) " + "VALUES "
			+ "(?,?,?,?) ";

	private static String selectNewGuitarId = 
			"SELECT LAST_INSERT_ID() AS 'guitarId' ";

	private static String deleteGuitarById = 
			"DELETE FROM guitar " + "WHERE id = ? ";
// prepared statement is needed to substitute for "?"

	private static String updateGuitar = 
			" UPDATE guitar " + " SET brandId = ?, " + "	model = ?, "
			+ "	bodyStyle = ?, " + "	releaseYear = ? " + " WHERE id = ? ";

	private static String selectGuitarByRangeOfReleaseYear = 
			"SELECT id, model, releaseYear, brandId, bodyStyle, updateDateTime, createDateTime "
			+ "FROM guitar " + "WHERE releaseYear >= ? " + "AND releaseYear <= ? ";

	@Override
	public List<Guitar> getGuitars() {
		List<Guitar> myGuitars = new ArrayList<Guitar>();
		ResultSet rs = null;
		Statement statement = null;

		Connection conn = MariaDbUtil.getConnection();

		try {
			statement = conn.createStatement();
			rs = statement.executeQuery(selectAllGuitars);
			myGuitars = makeGuitar(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				statement.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return myGuitars;
	}

	private List<Guitar> makeGuitar(ResultSet rs) throws SQLException {
		List<Guitar> myGuitars = new ArrayList<Guitar>();

		while (rs.next()) {
			Guitar guitar = new Guitar();
			guitar.setId(rs.getInt("id"));
			guitar.setReleaseYear(rs.getInt("releaseYear"));
			guitar.setModel(rs.getString("model"));
			guitar.setBodyStyle(rs.getString("bodyStyle"));

			// TODO need to convert string from db to java enum (brand)
			String brandString = rs.getString("brandId");
			guitar.setBrand(Brand.convertStringToBrand(brandString));

			// TODO need to update movie to include create and update datetime
			guitar.setUpdateDateTime(rs.getObject("updateDateTime", LocalDateTime.class));
			guitar.setCreateDateTime(rs.getObject("createDateTime", LocalDateTime.class));
			myGuitars.add(guitar);
		}
		return myGuitars;
	}

	@Override
	public List<Guitar> getGuitarsByBrand(Brand brand) {
		List<Guitar> myGuitars = new ArrayList<Guitar>();
		ResultSet rs = null;
		PreparedStatement ps = null;

		Connection conn = MariaDbUtil.getConnection();

		try {
			ps = conn.prepareStatement(selectGuitarsByBrand);
			ps.setString(1, brand.toString());
			rs = ps.executeQuery();
			myGuitars = makeGuitar(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				ps.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		return myGuitars;
	}

	@Override
	public List<Guitar> getGuitarsByReleaseYear(Integer releaseYear) {
		List<Guitar> myGuitars = new ArrayList<Guitar>();
		ResultSet rs = null;
		PreparedStatement ps = null;

		Connection conn = MariaDbUtil.getConnection();

		try {
			ps = conn.prepareStatement(selectGuitarsByReleaseYear);
			ps.setInt(1, releaseYear);
			rs = ps.executeQuery();
			myGuitars = makeGuitar(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				ps.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		return myGuitars;
	}

	@Override
	public List<Guitar> getGuitarsById(Integer guitarId) {
		List<Guitar> myGuitars = new ArrayList<Guitar>();
		ResultSet rs = null;
		PreparedStatement ps = null;

		Connection conn = MariaDbUtil.getConnection();

		try {
			ps = conn.prepareStatement(selectGuitarsById);
			ps.setInt(1, guitarId);
			rs = ps.executeQuery();
			myGuitars = makeGuitar(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				ps.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		return myGuitars;
	}

	@Override
	public Guitar createGuitar(Guitar newGuitar) {

		PreparedStatement ps = null;

		Connection conn = MariaDbUtil.getConnection();

		try {
			ps = conn.prepareStatement(insertGuitar);
			ps.setString(1, newGuitar.getModel());
			ps.setInt(2, newGuitar.getReleaseYear());
			ps.setString(3, newGuitar.getBrand().toString());
			ps.setString(4, newGuitar.getBodyStyle().toString());
			ps.executeUpdate();

			int newGuitarId = getNewGuitarId(conn);
			newGuitar.setId(newGuitarId);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		return newGuitar;
	}

	private int getNewGuitarId(Connection conn) {
		int newGuitarId = 0;
		ResultSet rs = null;
		Statement statement = null;

		try {
			statement = conn.createStatement();
			rs = statement.executeQuery(selectNewGuitarId);

			while (rs.next()) {
				newGuitarId = rs.getInt("guitarId");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				statement.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		return newGuitarId;
	}

	@Override
	public Guitar updateGuitar(Guitar updateGuitar) {
		List<Guitar> myGuitars = getGuitarsById(updateGuitar.getId());

		PreparedStatement ps = null;

		if (myGuitars.size() > 0) {

			int updateRowCount = 0;
			Connection conn = MariaDbUtil.getConnection();
			try {
				ps = conn.prepareStatement(GuitarDaoImpl.updateGuitar);
				ps.setString(1, updateGuitar.getBrand().toString()); // this order must match the order in the sql code
				ps.setString(2, updateGuitar.getModel().toString());
				ps.setString(3, updateGuitar.getBodyStyle().toString());
				ps.setInt(4, updateGuitar.getReleaseYear());
				ps.setInt(5, updateGuitar.getId());
				updateRowCount = ps.executeUpdate();
				System.out.println("rows update: " + updateRowCount);
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					ps.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}

			}

		}
		return updateGuitar;

	}

	@Override
	public Guitar deleteGuitar(Integer guitarId) {
		List<Guitar> myGuitars = this.getGuitarsById(guitarId);
		Guitar guitarToDelete = null;
		PreparedStatement ps = null;

		if (myGuitars.size() > 0) {
			guitarToDelete = myGuitars.get(0);
			int updateRowCount = 0;
			Connection conn = MariaDbUtil.getConnection();
			try {
				ps = conn.prepareStatement(deleteGuitarById);
				ps.setInt(1, guitarId);
				updateRowCount = ps.executeUpdate();
				System.out.println("rows deleted: " + updateRowCount);
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					ps.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}

			}

		}
		return guitarToDelete;
	}

	@Override
	public List<Guitar> getReport(Integer startReleaseYear, Integer endReleaseYear) {
		List<Guitar> myGuitars = new ArrayList<Guitar>();
		ResultSet rs = null;
		PreparedStatement ps = null;

		Connection conn = MariaDbUtil.getConnection();

		try {
			ps = conn.prepareStatement(selectGuitarByRangeOfReleaseYear);
			ps.setInt(1, startReleaseYear);
			ps.setInt(2, endReleaseYear);
			rs = ps.executeQuery();
			myGuitars = makeGuitar(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				ps.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		return myGuitars;
	}

	@Override
	public List<Guitar> getGuitarsByModel(String model) {
		List<Guitar> myGuitars = new ArrayList<Guitar>();
		ResultSet rs = null;
		PreparedStatement ps = null;

		Connection conn = MariaDbUtil.getConnection();

		try {
			ps = conn.prepareStatement(selectGuitarsByModel);
			ps.setString(1, model);
			rs = ps.executeQuery();
			myGuitars = makeGuitar(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				ps.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		return myGuitars;
	}

	@Override
	public List<Guitar> getGuitarsByBodyStyle(String bodyStyle) {
		List<Guitar> myGuitars = new ArrayList<Guitar>();
		ResultSet rs = null;
		PreparedStatement ps = null;

		Connection conn = MariaDbUtil.getConnection();

		try {
			ps = conn.prepareStatement(selectGuitarsByBodyStyle);
			ps.setString(1, bodyStyle);
			rs = ps.executeQuery();
			myGuitars = makeGuitar(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				ps.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		return myGuitars;
	}

}