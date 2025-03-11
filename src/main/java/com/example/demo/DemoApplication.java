package com.example.demo;

import java.sql.*;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.ResourceBundle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
        locale();
        locale2();
        locale3();
		//jdbc();
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

    static void locale(){
        Locale defaultLocale = Locale.getDefault();
        System.out.println("Idioma por defecto: " + defaultLocale.getLanguage());
        System.out.println("País por defecto: " + defaultLocale.getCountry());

        // Crear un Locale específico para España
        Locale spainLocale = new Locale("es", "ES");
        System.out.println("Ejemplo Locale España: " + spainLocale.getDisplayName());
    }

    static void locale2(){
        Locale spanishLocale = new Locale("es", "ES");
        ResourceBundle bundle = ResourceBundle.getBundle("messages", spanishLocale);

        System.out.println(bundle.getString("greeting"));
        System.out.println(bundle.getString("farewell"));
    }

    static void locale3(){
        Locale localeFR = new Locale("fr", "FR");
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, dd MMMM yyyy", localeFR);
        System.out.println("Fecha en francés: " + today.format(formatter));

        // Formateo de número en Alemania
        Locale localeDE = new Locale("de", "DE");
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(localeDE);
        System.out.println("Precio en Alemania: " + currencyFormatter.format(1234.56));
    }

}
