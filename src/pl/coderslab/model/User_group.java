package pl.coderslab.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class User_group {

	private int id;
	private String name;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public User_group(String name) {
		this.name = name;
	}

	public User_group() {
	}

	public static User_group[] loadAll() {
		List<User_group> groupsList = new ArrayList<>();
		try (Connection con = DbUtil.getConn()){
			String sql = "select * from user_group";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				User_group usrgr = new User_group();
				usrgr.id = rs.getInt("id");
				usrgr.name = rs.getString("name");
				groupsList.add(usrgr);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		User_group[] groupsTab = new User_group[groupsList.size()];
		groupsTab = groupsList.toArray(groupsTab);
		return groupsTab;
	}

	public static User_group loadById(int id) {
		try (Connection con = DbUtil.getConn()){
			String sql = "select * from user_group where id = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				User_group ug = new User_group();
				ug.id = rs.getInt("id");
				ug.name = rs.getString("name");
				return ug;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void saveToDB() {
		if (this.id == 0) {
			try (Connection con = DbUtil.getConn()){
				String sql = "insert into user_group set name = ?";
				String[] genCol = new String[] { "id" };
				PreparedStatement ps = con.prepareStatement(sql, genCol);
				ps.setString(1, name);
				ps.executeUpdate();
				ResultSet rs = ps.getGeneratedKeys();
				while (rs.next()) {
					this.id = rs.getInt(1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			try (Connection con = DbUtil.getConn()){
			String sql = "update user_group set name = ? where id = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, this.name);
			ps.setInt(2, this.id);
			ps.executeUpdate();
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void delete() {
		try (Connection con = DbUtil.getConn()){
			String sql = "delete from user_group where id = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();
			this.id = 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return "id: " + id + " name: " + name;
	}
}
