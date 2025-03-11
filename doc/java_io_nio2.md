# 📌 Java I/O (Fundamentals & NIO2)

Java proporciona dos principales APIs para la manipulación de archivos y flujos de datos:  
**1. Java I/O (Input/Output Streams)**  
**2. NIO2 (New Input/Output 2 - desde Java 7)**  

---

## 📝 1. Java I/O (Fundamentos)

Java I/O se basa en **flujos (streams)** para manejar archivos, redes y otros tipos de entrada/salida.  

### 📌 1.1. Flujos de Entrada y Salida

🔹 **InputStream y OutputStream** (para datos binarios)  
🔹 **Reader y Writer** (para datos de texto)

```java
// Leer un archivo con FileInputStream (Byte Stream)
try (FileInputStream fis = new FileInputStream("archivo.txt")) {
    int data;
    while ((data = fis.read()) != -1) {
        System.out.print((char) data);
    }
} catch (IOException e) {
    e.printStackTrace();
}
```

```java
// Escribir en un archivo con FileOutputStream (Byte Stream)
try (FileOutputStream fos = new FileOutputStream("salida.txt")) {
    fos.write("Hola, mundo!".getBytes());
} catch (IOException e) {
    e.printStackTrace();
}
```

---

## 📝 2. Java NIO (New Input/Output)

**Java NIO** introdujo buffers, canales y operaciones no bloqueantes.  

🔹 **Buffers**: Almacenan datos para procesarlos más rápido.  
🔹 **Channels**: Alternativa eficiente a Streams.  
🔹 **Selectors**: Manejan múltiples canales en un solo hilo.

### 📌 2.1. Uso de Buffers

```java
ByteBuffer buffer = ByteBuffer.allocate(1024);
buffer.put("Hola NIO".getBytes());
buffer.flip(); // Preparar para lectura

while (buffer.hasRemaining()) {
    System.out.print((char) buffer.get());
}
```

---

## 📝 3. NIO2 (Java 7+)

NIO2 introduce la clase **`java.nio.file`**, que simplifica la manipulación de archivos.

### 📌 3.1. Leer archivos con `Files.readAllLines`

```java
import java.nio.file.*;

try {
    Path path = Paths.get("archivo.txt");
    List<String> lines = Files.readAllLines(path);
    lines.forEach(System.out::println);
} catch (IOException e) {
    e.printStackTrace();
}
```

### 📌 3.2. Crear, mover y eliminar archivos

```java
Path filePath = Paths.get("nuevoArchivo.txt");
Files.createFile(filePath);

Path destino = Paths.get("carpeta/nuevoArchivo.txt");
Files.move(filePath, destino);

Files.delete(destino);
```

---

## 🚀 Conclusión

📌 **Java I/O** es simple pero puede ser lento para grandes volúmenes de datos.  
📌 **NIO y NIO2** ofrecen mejor rendimiento, manejo eficiente de archivos y procesamiento en paralelo.

🔹 **Usa Java I/O para tareas simples**.  
🔹 **Usa NIO/NIO2 para alto rendimiento y manipulación avanzada de archivos.**  
