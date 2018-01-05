package pl.coderslab.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Exercise {

	private int id;
	private String title;
	private String description;

	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public Exercise() {
	}

	public Exercise(String title, String description) {
		this.title = title;
		this.description = description;
	}

	public static Exercise[] loadAll() {
		ArrayList<Exercise> exercises = new ArrayList<>();
		try (Connection con = DbUtil.getConn()) {
			String sql = "select * from exercise";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Exercise ex = new Exercise();
				ex.id = rs.getInt("id");
				ex.title = rs.getString("title");
				ex.description = rs.getString("description");
				exercises.add(ex);
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		
		Exercise[] exTab = new Exercise[exercises.size()];
		exTab = exercises.toArray(exTab);
		return exTab;
	}

	public static Exercise loadById(int id) throws SQLException {
		try (Connection con = DbUtil.getConn()) {
			String sql = "select * from exercise where id = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Exercise ex = new Exercise();
				ex.id = rs.getInt("id");
				ex.title = rs.getString("title");
				ex.description = rs.getString("description");
				
				return ex;
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return null;
	}

	public void saveToDB() {
		if (this.id == 0) {
			try (Connection con = DbUtil.getConn()) {
				String[] generatedColumns = { "ID" };
				String sql = "insert into exercise (title, description) values (?,?)";
				PreparedStatement ps = con.prepareStatement(sql, generatedColumns);
				ps.setString(1, this.title);
				ps.setString(2, this.description);
				ps.executeUpdate();
				ResultSet rs = ps.getGeneratedKeys();
				if (rs.next()) {
					this.id = rs.getInt(1);
				}

			} catch (SQLException e) {
				System.err.println(e.getMessage());
			}
		} else {

			try (Connection con = DbUtil.getConn()) {
				String sql = "update exercise set title = ?, description = ? where id = ?";
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, this.title);
				ps.setString(2, this.description);
				ps.setInt(3, this.id);
				ps.executeUpdate();
			} catch (SQLException e) {
				System.err.println(e.getMessage());
			}
		}
	}

	public void delete(){
		if (this.id != 0){
			try (Connection con = DbUtil.getConn()) {
			String sql = "delete from exercise where id = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();
			this.id = 0;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	

	
	@Override
	public String toString() {
		return "id: " + id + ", tytuł: " + title + ", opis: " + description;
	}
}
/*
 * loadAll loadById delete saveToDB
 * pobranie wszystkich rozwiązań danego użytkownika (dopisz metodę loadAllByUserId
do klasy Exercise)
 */