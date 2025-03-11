package com.example.demo;

import java.sql.*;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		jdbc();
		SpringApplication.run(DemoApplication.class, args);
		
	}

	static void jdbc(){
		String url = "jdbc:h2:mem:testdb";
        String user = "sa";
        String password = "";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement()) {

            // Crear tabla
            stmt.execute("CREATE TABLE usuarios (id INT PRIMARY KEY, nombre VARCHAR(255))");

            // Insertar datos
            stmt.execute("INSERT INTO usuarios (id, nombre) VALUES (1, 'Juan Pérez')");
            stmt.execute("INSERT INTO usuarios (id, nombre) VALUES (2, 'Ana Gómez')");

            // Consultar datos
            ResultSet rs = stmt.executeQuery("SELECT * FROM usuarios");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") + ", Nombre: " + rs.getString("nombre"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

	}

}
