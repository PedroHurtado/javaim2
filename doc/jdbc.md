# 📌 JDBC (Java Database Connectivity)

JDBC es una API de Java que permite la conexión y manipulación de bases de datos relacionales mediante SQL.

---

## 🔹 Objetos básicos de JDBC

### 1️⃣ `DriverManager`
Se encarga de gestionar los controladores de base de datos y establecer conexiones.

```java
Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/miDB", "usuario", "password");
```

---

### 2️⃣ `Connection`
Representa la conexión con la base de datos.

```java
try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/miDB", "usuario", "password")) {
    System.out.println("Conexión establecida con éxito.");
} catch (SQLException e) {
    e.printStackTrace();
}
```

---

### 3️⃣ `Statement`
Permite ejecutar consultas SQL sin parámetros.

```java
Statement statement = connection.createStatement();
String sql = "CREATE TABLE usuarios (id INT PRIMARY KEY, nombre VARCHAR(50))";
statement.executeUpdate(sql);
```

⚠ **¡Cuidado!** Usar `Statement` con parámetros puede causar SQL Injection.

---

### 4️⃣ `PreparedStatement`
Es una versión más segura de `Statement` que permite consultas parametrizadas.

```java
String sql = "INSERT INTO usuarios (id, nombre) VALUES (?, ?)";
PreparedStatement pstmt = connection.prepareStatement(sql);
pstmt.setInt(1, 1);
pstmt.setString(2, "Juan Pérez");
pstmt.executeUpdate();
```

---

### 5️⃣ `ResultSet`
Contiene los resultados de una consulta `SELECT`.

```java
Statement statement = connection.createStatement();
ResultSet rs = statement.executeQuery("SELECT * FROM usuarios");
while (rs.next()) {
    System.out.println("ID: " + rs.getInt("id") + ", Nombre: " + rs.getString("nombre"));
}
```

---

## 🚀 Operaciones CRUD con JDBC

### 🔹 1. Insertar datos (`INSERT`)
```java
String sql = "INSERT INTO usuarios (id, nombre) VALUES (?, ?)";
try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
    pstmt.setInt(1, 2);
    pstmt.setString(2, "Ana Gómez");
    pstmt.executeUpdate();
    System.out.println("Usuario insertado correctamente.");
}
```

---

### 🔹 2. Actualizar datos (`UPDATE`)
```java
String sql = "UPDATE usuarios SET nombre = ? WHERE id = ?";
try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
    pstmt.setString(1, "Carlos López");
    pstmt.setInt(2, 2);
    pstmt.executeUpdate();
    System.out.println("Usuario actualizado correctamente.");
}
```

---

### 🔹 3. Eliminar datos (`DELETE`)
```java
String sql = "DELETE FROM usuarios WHERE id = ?";
try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
    pstmt.setInt(1, 2);
    pstmt.executeUpdate();
    System.out.println("Usuario eliminado correctamente.");
}
```

---

### 🔹 4. Consultar datos (`SELECT`)
```java
String sql = "SELECT * FROM usuarios";
try (Statement statement = connection.createStatement();
     ResultSet rs = statement.executeQuery(sql)) {
    while (rs.next()) {
        System.out.println("ID: " + rs.getInt("id") + ", Nombre: " + rs.getString("nombre"));
    }
}
```

---

## ✅ Buenas prácticas con JDBC

✔ **Usar `PreparedStatement`** para prevenir SQL Injection.  
✔ **Cerrar los recursos (`Connection`, `Statement`, `ResultSet`)** para evitar fugas de memoria.  
✔ **Usar transacciones (`commit`, `rollback`)** para garantizar la integridad de los datos.  
✔ **Configurar el `autoCommit(false)`** si se necesita mayor control en las transacciones.

---

## 🎯 Conclusión

JDBC proporciona una forma eficiente de interactuar con bases de datos en Java. Es fundamental seguir buenas prácticas para garantizar seguridad y rendimiento en nuestras aplicaciones.
