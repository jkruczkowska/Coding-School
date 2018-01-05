package pl.coderslab.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class Solution {

	private int id;
	private String created;
	private String updated;
	private String description;
	private int exercise_id;
	private int users_id;

	public Solution() {

	}

	public Solution(String created, String updated, String description, int exercise_id, int users_id) {
		super();
		this.created = created;
		this.updated = updated;
		this.description = description;
		this.exercise_id = exercise_id;
		this.users_id = users_id;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	public String getupdated() {
		return updated;
	}

	public void setupdated(String updated) {
		this.updated = updated;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getExercise_id() {
		return exercise_id;
	}

	public void setExercise_id(int exercise_id) {
		this.exercise_id = exercise_id;
	}

	public int getUsers_id() {
		return users_id;
	}

	public void setUsers_id(int users_id) {
		this.users_id = users_id;
	}

	public int getId() {
		return id;
	}

	
	public Solution[] loadAll(int limit) {
		List<Solution> solList = new ArrayList<>();
		try (Connection con = DbUtil.getConn()) {
			String sql = "select * from solution s join users u on s.users_id = u.id join exercise e on s.exercise_id = e.id order by created desc limit ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, limit);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Solution sol = new Solution();
				sol.id = rs.getInt("id");
				sol.created = rs.getString("created");
				sol.updated = rs.getString("updated");
				sol.description = rs.getString("description");
				sol.exercise_id = rs.getInt("exercise_id");
				sol.users_id = rs.getInt("users_id");
				solList.add(sol);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Solution[] solTabLimit = new Solution[solList.size()];
		solTabLimit = solList.toArray(solTabLimit);
		return solTabLimit;
	}
	
	public void saveToDB() {
		if (this.id == 0) {
			try (Connection con = DbUtil.getConn()) {
				String[] genCol = new String[] { "id" };
				String sql = "insert into solution (created, updated, description, exercise_id, users_id) values (?, ?, ?, ?, ?)";

				PreparedStatement ps = con.prepareStatement(sql, genCol);
				ps.setString(1, created);
				ps.setString(2, updated);
				ps.setString(3, description);
				ps.setInt(4, exercise_id);
				ps.setInt(5, users_id);
				ps.executeUpdate();
				ResultSet rs = ps.getGeneratedKeys();
				while (rs.next()) {
					this.id = rs.getInt(1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			try (Connection con = DbUtil.getConn()) {
				String sql = "update solution set created = ?, updated = ?, description = ?, exercise_id = ?, users_id = ? where id = ?";
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, this.created);
				ps.setString(2, this.updated);
				ps.setString(3, this.description);
				ps.setInt(4, this.exercise_id);
				ps.setInt(5, this.users_id);
				ps.setInt(6, this.id);
				ps.executeUpdate();
				ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static Solution[] loadAll() {
		List<Solution> solutionsList = new ArrayList<>();

		String sql = "select * from solution";
		try (Connection con = DbUtil.getConn()) {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Solution solution = new Solution();
				solution.id = rs.getInt("id");
				// System.out.println(solution.getId());
				solution.created = rs.getString("created");
				solution.updated = rs.getString("updated");
				solution.description = rs.getString("description");
				solution.exercise_id = rs.getInt("exercise_id");
				solution.users_id = rs.getInt("users_id");
				solutionsList.add(solution);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		Solution[] solutionsTab = new Solution[solutionsList.size()];
		solutionsTab = solutionsList.toArray(solutionsTab);

		return solutionsTab;
	}

	public static Solution loadById(int id) {
		Solution sol = new Solution();
		try (Connection con = DbUtil.getConn()) {
			String sql = "select * from solution where id = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				sol.id = rs.getInt("id");
				sol.created = rs.getString("created");
				sol.updated = rs.getString("updated");
				sol.description = rs.getString("description");
				sol.exercise_id = rs.getInt("exercise_id");
				sol.users_id = rs.getInt("users_id");

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sol;
	}

	public void delete() {
		String sql = "delete from solution where id = ?";
		try (Connection con = DbUtil.getConn()) {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setLong(1, id);
			ps.executeUpdate();
			this.id = 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static Solution[] loadAllByUserId(int users_id) {
		List<Solution> solList = new ArrayList<>();

		try (Connection con = DbUtil.getConn()) {
			String sql = "select * from solution where users_id = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, users_id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Solution sol = new Solution();
				sol.id = rs.getInt("id");
				sol.created = rs.getString("created");
				sol.updated = rs.getString("updated");
				sol.description = rs.getString("description");
				sol.exercise_id = rs.getInt("exercise_id");
				sol.users_id = rs.getInt("users_id");
				solList.add(sol);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		Solution[] solTab = new Solution[solList.size()];
		solTab = solList.toArray(solTab);
		return solTab;
	}

	// pobranie wszystkich rozwiązań danego zadania posortowanych od najnowszego
	// do
	// najstarszego loadAllByExerciseId do klasy Solution)

	public static Solution[] loadAllByExerciseId(int exercise_id) {
		List<Solution> solList = new ArrayList<>();
		try (Connection con = DbUtil.getConn()) {
			String sql = "select * from solution where exercise_id = ? order by created desc";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, exercise_id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Solution sol = new Solution();
				sol.created = rs.getString("created");
				sol.updated = rs.getString("updated");
				sol.description = rs.getString("description");
				sol.exercise_id = rs.getInt("exercise_id");
				sol.users_id = rs.getInt("users_id");
				solList.add(sol);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Solution[] solTab = new Solution[solList.size()];
		solTab = solList.toArray(solTab);
		return solTab;
	}

	@Override
	public String toString() {
		return "\nc: " + created + "\nu: " + updated + "\nopis: " + description + "\nex: " + exercise_id + "\nuser: "
				+ users_id;
	}

}
