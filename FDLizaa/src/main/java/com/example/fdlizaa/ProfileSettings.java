package com.example.fdlizaa;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.io.IOException;
import java.sql.*;

public class ProfileSettings {

    @FXML
    private TextField username;

    @FXML
    private TextField email;

    @FXML
    private PasswordField password;

    @FXML
    private RadioButton male;

    @FXML
    private RadioButton female;

    @FXML
    private ToggleGroup genderGroup;

    @FXML
    private Label statusLabel;

    @FXML
    private Button save;

    @FXML
    public void initialize() {
        genderGroup = new ToggleGroup();
        male.setToggleGroup(genderGroup);
        female.setToggleGroup(genderGroup);

        loadUserData();
    }

    private void loadUserData() {
        int id = HelloApplication.loggedInUserId;

        try (Connection conn = DriverManager.getConnection(
                HelloApplication.url, HelloApplication.username, HelloApplication.password)) {

            String sql = "SELECT * FROM users WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                username.setText(rs.getString("username"));
                password.setText(rs.getString("password"));
                email.setText(rs.getString("email"));
                String gender = rs.getString("Gender");
                if ("Male".equalsIgnoreCase(gender)) male.setSelected(true);
                else if ("Female".equalsIgnoreCase(gender)) female.setSelected(true);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void save(ActionEvent event) {
        String user = username.getText().trim();
        String mail = email.getText().trim();
        String pass = password.getText().trim();
        String gender = male.isSelected() ? "Male" : (female.isSelected() ? "Female" : "");

        if (user.isEmpty() || mail.isEmpty() || pass.isEmpty() || gender.isEmpty()) {
            statusLabel.setText("Fill all fields before saving.");
            return;
        }

        int id = HelloApplication.loggedInUserId;

        try (Connection conn = DriverManager.getConnection(
                HelloApplication.url, HelloApplication.username, HelloApplication.password)) {

            String checkSql = "SELECT id FROM users WHERE email = ? AND id != ?";
            PreparedStatement checkStmt = conn.prepareStatement(checkSql);
            checkStmt.setString(1, mail);
            checkStmt.setInt(2, id);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                statusLabel.setText("⚠️ Email already in use.");
                return;
            }

            String sql = "UPDATE users SET username = ?, password = ?, email = ?, Gender = ? WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, user);
            stmt.setString(2, pass);
            stmt.setString(3, mail);
            stmt.setString(4, gender);
            stmt.setInt(5, id);
            stmt.executeUpdate();

            statusLabel.setText("✅ All changes saved successfully.");

            Parent root = FXMLLoader.load(getClass().getResource("/fxml/Home.fxml"));
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void changeUsername() {
        updateField("username", username.getText().trim(), "Username");
    }

    @FXML
    private void changeEmail() {
        updateField("email", email.getText().trim(), "Email");
    }

    @FXML
    private void changePassword() {
        updateField("password", password.getText().trim(), "Password");
    }

    @FXML
    private void changeGender() {
        String genderValue = male.isSelected() ? "Male" : (female.isSelected() ? "Female" : "");
        updateField("Gender", genderValue, "Gender");
    }

    private void updateField(String columnName, String newValue, String fieldName) {
        if (newValue.isEmpty()) {
            statusLabel.setText("⚠️ " + fieldName + " cannot be empty.");
            return;
        }

        int id = HelloApplication.loggedInUserId;

        try (Connection conn = DriverManager.getConnection(
                HelloApplication.url, HelloApplication.username, HelloApplication.password)) {

            String sql = "UPDATE users SET " + columnName + " = ? WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, newValue);
            stmt.setInt(2, id);
            int updated = stmt.executeUpdate();

            if (updated > 0) {
                statusLabel.setText("✅ " + fieldName + " updated.");
            } else {
                statusLabel.setText("❌ Failed to update " + fieldName + ".");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}