# Configuración de CORS en Spring Boot

En Spring Boot, la configuración de CORS (Cross-Origin Resource Sharing) permite que las aplicaciones frontend en dominios diferentes puedan acceder a los recursos del backend.

A continuación, se presentan tres formas de configurar CORS en Spring Boot, asegurando que se incluya `maxAge` para mejorar el rendimiento al reducir las solicitudes preflight innecesarias.

---

## **Opción 1: Configurar CORS a nivel global con WebMvcConfigurer**
Esta opción aplica CORS a todas las rutas de la aplicación.

```java
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // Aplica CORS a todas las rutas
                        .allowedOrigins("http://localhost:4200") // Permite este origen
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Métodos permitidos
                        .allowedHeaders("*") // Permite todos los headers
                        .allowCredentials(true) // Permite credenciales
                        .maxAge(3600); // Tiempo de cache en segundos (1 hora)
            }
        };
    }
}
```

---

## **Opción 2: Configurar CORS en un controlador específico**
Si solo quieres aplicar CORS en ciertos controladores, puedes usar la anotación `@CrossOrigin`:

```java
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600) // Cache por 1 hora
public class TestController {

    @GetMapping("/test")
    public String test() {
        return "CORS configurado con maxAge";
    }
}
```

---

## **Opción 3: Configurar CORS con un filtro personalizado**
Si necesitas más control, puedes usar un `CorsFilter`:

```java
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.List;

@Configuration
public class CorsFilterConfig {

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of("http://localhost:4200"));
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(List.of("*"));
        config.setAllowCredentials(true);
        config.setMaxAge(3600L); // Cache por 1 hora

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return new CorsFilter(source);
    }
}
```

---

## **¿Qué hace `maxAge`?**
- Especifica cuánto tiempo (en segundos) el navegador puede usar la respuesta preflight (`OPTIONS`) en caché sin volver a enviarla.
- Evita que se hagan solicitudes preflight innecesarias en cada petición.
- 3600 segundos (1 hora) es un valor comúnmente usado para mejorar el rendimiento de la aplicación.



