package pl.coderslab.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

public class User {

	private int id;
	private int person_group_id;
	private String name;
	private String email;
	private String password;
	
	public int getPerson_group_id() {
		return person_group_id;
	}

	public void setPerson_group_id(int person_group_id) {
		this.person_group_id = person_group_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public String getPassword() {
		return password;
	}

	public User() {
	};

	public User(int person_group_id, String name, String email, String password) {
		this.person_group_id = person_group_id;
		this.name = name;
		this.email = email;
		setPassword(password);
	}

	public void setPassword(String password) {
		String hash = BCrypt.hashpw(password, BCrypt.gensalt());
		this.password = hash;
	}

	public static User[] loadAll() {
		List<User> users = new ArrayList<>();
		try (Connection con = DbUtil.getConn()) {
			String query = "select * from users";
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				User user = new User();
				user.id = rs.getInt("id");
				user.person_group_id = rs.getInt("person_group_id");
				user.name = rs.getString("name");
				user.email = rs.getString("email");
				user.password = rs.getString("password");
				users.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		User[] usersTab = new User[users.size()];
		usersTab = users.toArray(usersTab);
		return usersTab;
	}

	public static User loadById(int id) throws SQLException {
		try (Connection con = DbUtil.getConn()) {
			String sql = "select * from users where id=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				User loadedUser = new User();
				loadedUser.id = rs.getInt("id");
				loadedUser.name = rs.getString("name");
				loadedUser.password = rs.getString("password");
				loadedUser.email = rs.getString("email");
				loadedUser.person_group_id = rs.getInt("person_group_id");
				return loadedUser;
			}
		} catch (SQLException e) {
//			System.err.println(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	public void saveToDB() {
		if (this.id == 0) {
			try (Connection con = DbUtil.getConn()) {
				String generatedColumns[] = { "ID" };
				String sql = "insert into users(person_group_id, name, email, password) values (?,?,?,?)";
				PreparedStatement ps = con.prepareStatement(sql, generatedColumns);
				ps.setInt(1, person_group_id);
				ps.setString(2, name);
				ps.setString(3, email);
				ps.setString(4, password);
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
				String sql = "update users set name = ?, email = ?, password = ?, person_group_id = ? where id = ?";
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, this.name);
				ps.setString(2, this.email);
				ps.setString(3, this.password);
				ps.setLong(4, this.person_group_id);
				ps.setInt(5, this.id);
				ps.executeUpdate();
			} catch (SQLException e) {
				System.err.println(e.getMessage());
			}
		}
	}

	public void delete() {
		String sql = "delete from users where id = ?";
		try (Connection con = DbUtil.getConn()) {
			if (this.id != 0) {
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setLong(1, id);
				ps.executeUpdate();
				this.id = 0;
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}
	
//	pobranie wszystkich członków danej grupy (dopisz metodę loadAllByGrupId do klasyUser)

	public static User[] loadAllByGrupId(int person_group_id){
		List<User> userList = new ArrayList<>();
		try (Connection con = DbUtil.getConn()) {
			String sql = "select * from users where person_group_id = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, person_group_id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()){
				User usr = new User();
				usr.id = rs.getInt("id");
				usr.name = rs.getString("name");
				usr.email = rs.getString("email");
				usr.password = rs.getString("password");
				usr.person_group_id = rs.getInt("person_group_id");
				userList.add(usr);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		User[] usersTab = new User[userList.size()];
		usersTab = userList.toArray(usersTab);
		return usersTab;
	}
	
	@Override
	public String toString() {
		return "\nuser: " + this.id + " " + this.name + ", email: " + email + ", password: " + password + ", nr grupy: " + this.person_group_id;
	}
	
}













