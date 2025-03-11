# Secure Code: Autenticación y Autorización

## JWT (JSON Web Token)

### RS256 - Firmas con la privada y validación con la pública

- La aplicación principal (`APP`) contiene 10 subaplicaciones.
- `APP` tiene un servicio de `LOGIN` que recupera la clave privada y la pública.
- La clave pública se expone a través de un endpoint:
  
  ```http
  GET /auth/public-key
  ```

## Seguridad en Consultas SQL

### Parametrización de Queries

**Evitar:**

```java
String Sql = "select * from user where user = " + user; // SQL Injection
```

**Usar:**

```java
String Sql = "select * from user where user = ?"; // Protegido contra SQL Injection

var result = Query.builder(Sql).addParameters(user).build();
connection.executeCommand(result);
```

## Validación de Inputs

### Validaciones en Cliente y Servidor

- **Cliente:** Mejora la usabilidad y evita viajes innecesarios al servidor.
- **Servidor:** Imprescindible para evitar ataques y validaciones manipuladas en el cliente.

## Manejo de Excepciones

### No exponer datos críticos en excepciones

```java
try {
    // Código
} catch (Exception ex) {
    log(ex); // Registrar error internamente
    throw new CustomException(); // Lanzar una excepción genérica
}
```

## Garantizar la Unicidad de IDs de Sesión

- **Números simples:** Se pueden repetir.
- **UUID:** Difícil de repetir.
- **Redis:** Generar un token de sesión y comprobar que no ha sido emitido previamente.

Ejemplo de transacción segura:

```text
trans
    get key
    set key
```

## Protección Contra Ataques Comunes

### XSS, SQL Injection y JavaScript Injection

- **No usar:**
  - Sanitizadores del lado del servidor sin precaución.
  - Expresiones regulares ineficientes.
- **Implementar CSP (Content Security Policy):**

  ```html
  <style nonce=asdfasdfasdfasdfdas>
  </style>
  ```

### CSRF (Cross-Site Request Forgery)

- **Protección basada en cookies y tokens ocultos:**

  ```html
  <form>
      <input type="hidden" name="csrf_token" value="TOKEN_GENERADO">
  </form>
  ```

### X-Frame-Options

- Evita la carga de la aplicación en iframes no autorizados.
- Configuración recomendada:

  ```http
  X-Frame-Options: DENY
  ```

## Seguridad en Cookies y Almacenamiento de Tokens

- **Cookies:**
  - `HttpOnly` (Evita acceso desde JavaScript).
  - `Secure` (Solo en HTTPS).

- **JWT:**
  - Evitar almacenamiento en `localStorage` o `sessionStorage`.
  - Aplicar `CSP` para mitigar riesgos.

