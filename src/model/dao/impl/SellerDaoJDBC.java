package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Departament;
import model.entities.Seller;

public class SellerDaoJDBC implements SellerDao {
	
	private Connection conn;
	
	public SellerDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Seller obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Seller obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Seller findById(Integer id) {
		
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			
			st = conn.prepareStatement("SELECT seller.*,department.Name as DepName  \r\n"
					+ "FROM seller INNER JOIN department  \r\n"
					+ "ON seller.DepartmentId = department.Id  \r\n"
					+ "WHERE seller.Id = ?");
			
			st.setInt(1, id);
			rs = st.executeQuery();
			
			if(rs.next()) {
				Departament dep = instantiateDepartment(rs);
				
				Seller obj = intantiateSelle(rs, dep);
				
				return obj;
				
			}
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
		
		
		return null;
	}

	private Seller intantiateSelle(ResultSet rs, Departament dep) throws SQLException {
		Seller obj =  new Seller();
		
		obj.setId(rs.getInt("Id"));
		obj.setNome(rs.getNString("Name"));
		obj.setEmail(rs.getNString("Email"));
		obj.setBaseSalary(rs.getDouble("BaseSalary"));
		obj.setBirthDate(rs.getDate("BirthDate"));
		
		obj.setDepartament(dep);
		return obj;
	}

	private Departament instantiateDepartment(ResultSet rs) throws SQLException {
		Departament dep = new Departament();
		dep.setId(rs.getInt("DepartmentId"));
		dep.setNome(rs.getString("Name"));
		return dep;
	}

	@Override
	public List<Seller> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
