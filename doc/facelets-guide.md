# Creating Web Pages Using Facelets - Guía Completa

## Introducción a Facelets

### Definición y propósito de Facelets
Facelets es una tecnología de plantillas web y el sistema de presentación predeterminado para JavaServer Faces (JSF) desde JSF 2.0. Fue diseñado específicamente para JSF, proporcionando características como plantillas reutilizables, composición de componentes y una mejor integración con el ciclo de vida de JSF.

### Historia y evolución como tecnología de vista para JSF
- Originalmente desarrollado por Jacob Hookom como una alternativa a JSP
- Adoptado oficialmente como la tecnología de vista predeterminada en JSF 2.0 (JSR-314)
- En JSF 1.2, Facelets era una biblioteca externa, mientras que desde JSF 2.0 forma parte de la especificación oficial
- Ha evolucionado para ofrecer mejor rendimiento y características más avanzadas en versiones posteriores de JSF

### Ventajas de Facelets sobre JSP
- Mejor rendimiento: compila directamente a árboles de componentes
- Soporte nativo para plantillas y reutilización de código
- Mejor manejo de errores y mensajes de diagnóstico
- Uso completo de XML/XHTML, lo que permite validación y herramientas más avanzadas
- Mayor facilidad para crear componentes compuestos
- No requiere TagLibs complejos como JSP
- Integración más limpia con el ciclo de vida de JSF

## Conceptos fundamentales

### Estructura de una aplicación con Facelets
Una aplicación típica utilizando Facelets se organiza de la siguiente manera:
- `/WEB-INF/web.xml`: Configuración del servlet de JSF
- `/WEB-INF/faces-config.xml`: Configuración de JSF (opcional en versiones recientes)
- `/resources/`: Directorio para recursos estáticos (CSS, JavaScript, imágenes)
- `/WEB-INF/templates/`: Directorio para plantillas reutilizables
- `/WEB-INF/includes/`: Componentes y fragmentos reutilizables
- Archivos `.xhtml` en el directorio raíz o en subdirectorios organizados por funcionalidad

### El ciclo de vida de una página Facelets
1. **Construcción del árbol de vista**: Facelets compila los archivos XHTML en un árbol de componentes JSF
2. **Aplicación de valores de solicitud**: Los valores de la solicitud se aplican a los componentes
3. **Validación**: Se validan los datos de entrada
4. **Actualización del modelo**: Los datos validados se transfieren a los beans de respaldo
5. **Invocación de aplicación**: Se ejecutan las acciones solicitadas
6. **Renderizado de respuesta**: Facelets renderiza la salida HTML

### Integración con el framework JSF
- Facelets es el renderizador predeterminado para JSF desde la versión 2.0
- Traduce etiquetas XHTML en componentes JSF
- Gestiona la construcción y reconstrucción eficiente del árbol de componentes
- Proporciona soporte para Expression Language (EL) para acceder a beans y métodos

## Elementos básicos

### Estructura de archivos XHTML en Facelets
```xml
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:h="http://xmlns.jcp.org/jsf/html"
      xmlns:f="http://xmlns.jcp.org/jsf/core"
      xmlns:ui="http://xmlns.jcp.org/jsf/facelets">
<h:head>
    <title>Título de la página</title>
</h:head>
<h:body>
    <h:form>
        <!-- Contenido JSF -->
    </h:form>
</h:body>
</html>
```

### Namespaces esenciales
- `xmlns="http://www.w3.org/1999/xhtml"`: Namespace XHTML estándar
- `xmlns:h="http://xmlns.jcp.org/jsf/html"`: Componentes HTML básicos de JSF
- `xmlns:f="http://xmlns.jcp.org/jsf/core"`: Componentes principales de JSF
- `xmlns:ui="http://xmlns.jcp.org/jsf/facelets"`: Etiquetas específicas de Facelets
- `xmlns:p="http://primefaces.org/ui"`: Componentes de PrimeFaces (si se usa)
- `xmlns:c="http://xmlns.jcp.org/jsp/jstl/core"`: Etiquetas JSTL Core
- `xmlns:cc="http://xmlns.jcp.org/jsf/composite"`: Para componentes compuestos

### Sintaxis y convenciones
- Los archivos Facelets usan la extensión `.xhtml`
- Deben ser documentos XHTML bien formados (con etiquetas cerradas correctamente)
- Las expresiones EL se escriben como `#{expresion}` o `${expresion}`
- Se recomienda usar `<h:head>` y `<h:body>` en lugar de las etiquetas HTML estándar
- Los componentes JSF generalmente se colocan dentro de un `<h:form>` para procesar entradas

## Componentes de Facelets

### Tags de composición
- `<ui:composition>`: Define un fragmento de página o una página que usa una plantilla
  ```xml
  <ui:composition template="/WEB-INF/templates/main.xhtml">
      <ui:define name="content">
          <!-- Contenido específico -->
      </ui:define>
  </ui:composition>
  ```
- `<ui:define>`: Define contenido para una región específica de una plantilla
- `<ui:insert>`: Define una región en una plantilla donde se puede insertar contenido

### Tags de inclusión
- `<ui:include>`: Incluye el contenido de otro archivo XHTML
  ```xml
  <ui:include src="/WEB-INF/includes/header.xhtml">
      <ui:param name="pageTitle" value="#{bean.title}" />
  </ui:include>
  ```
- `<ui:param>`: Pasa parámetros a las páginas incluidas o plantillas

### Tags de iteración y condicionales
- `<ui:repeat>`: Itera sobre una colección
  ```xml
  <ui:repeat value="#{bean.items}" var="item">
      <h:outputText value="#{item.name}" />
  </ui:repeat>
  ```
- `<c:if>`: Renderizado condicional
  ```xml
  <c:if test="#{bean.loggedIn}">
      <h:outputText value="Bienvenido de nuevo" />
  </c:if>
  ```
- `<c:choose>`, `<c:when>`, `<c:otherwise>`: Para condiciones múltiples

### Tags de depuración
- `<ui:debug>`: Proporciona información de depuración cuando se presiona una tecla específica
  ```xml
  <ui:debug hotkey="d" />
  ```

## Plantillas (Templates)

### Creación de plantillas maestras
Una plantilla maestra define la estructura común de las páginas:

```xml
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:h="http://xmlns.jcp.org/jsf/html"
      xmlns:ui="http://xmlns.jcp.org/jsf/facelets">
<h:head>
    <title><ui:insert name="title">Título por defecto</ui:insert></title>
    <h:outputStylesheet library="css" name="styles.css" />
</h:head>
<h:body>
    <div id="header">
        <ui:insert name="header">
            <ui:include src="/WEB-INF/includes/default-header.xhtml" />
        </ui:insert>
    </div>
    
    <div id="content">
        <ui:insert name="content">
            <p>Contenido por defecto</p>
        </ui:insert>
    </div>
    
    <div id="footer">
        <ui:insert name="footer">
            <ui:include src="/WEB-INF/includes/default-footer.xhtml" />
        </ui:insert>
    </div>
</h:body>
</html>
```

### Definición de regiones (slots)
Las regiones se definen con `<ui:insert>` en la plantilla:
- Cada región tiene un nombre único
- Puede contener contenido predeterminado
- El contenido predeterminado se muestra si la página cliente no define esa región

### Implementación de herencia de plantillas
Para usar una plantilla, se emplea `<ui:composition>` con el atributo `template`:

```xml
<ui:composition template="/WEB-INF/templates/main.xhtml"
                xmlns="http://www.w3.org/1999/xhtml"
                xmlns:h="http://xmlns.jcp.org/jsf/html"
                xmlns:ui="http://xmlns.jcp.org/jsf/facelets">
    
    <ui:define name="title">Página de ejemplo</ui:define>
    
    <ui:define name="content">
        <h1>Contenido específico de esta página</h1>
        <p>Este contenido reemplaza el contenido predeterminado.</p>
    </ui:define>
    
    <!-- Si no se define footer, se usará el predeterminado -->
</ui:composition>
```

### Anidación de plantillas
Las plantillas pueden ser anidadas para crear estructuras más complejas:

1. Plantilla base (`base.xhtml`):
```xml
<h:body>
    <ui:insert name="page-content" />
</h:body>
```

2. Plantilla de administración (`admin.xhtml`) que extiende la base:
```xml
<ui:composition template="/WEB-INF/templates/base.xhtml">
    <ui:define name="page-content">
        <div class="admin-layout">
            <div class="sidebar">
                <ui:insert name="sidebar" />
            </div>
            <div class="main">
                <ui:insert name="main-content" />
            </div>
        </div>
    </ui:define>
</ui:composition>
```

3. Página específica que usa la plantilla de administración:
```xml
<ui:composition template="/WEB-INF/templates/admin.xhtml">
    <ui:define name="sidebar">
        <!-- Menú de administración -->
    </ui:define>
    <ui:define name="main-content">
        <!-- Contenido principal -->
    </ui:define>
</ui:composition>
```

## Composición de interfaces

### Creación de componentes reutilizables
Hay varias formas de crear componentes reutilizables:

1. **Usando `<ui:include>`**: Para fragmentos simples
```xml
<!-- /WEB-INF/includes/userInfo.xhtml -->
<ui:composition xmlns="http://www.w3.org/1999/xhtml"
                xmlns:h="http://xmlns.jcp.org/jsf/html"
                xmlns:ui="http://xmlns.jcp.org/jsf/facelets">
    <div class="user-info">
        <h:outputText value="#{user.name}" />
        <h:outputText value="#{user.email}" />
    </div>
</ui:composition>

<!-- Uso: -->
<ui:include src="/WEB-INF/includes/userInfo.xhtml">
    <ui:param name="user" value="#{currentUser}" />
</ui:include>
```

### Implementación de Composite Components
Los Composite Components son componentes personalizados más avanzados:

1. Crear el componente (`/resources/components/inputWithLabel.xhtml`):
```xml
<ui:component
    xmlns="http://www.w3.org/1999/xhtml"
    xmlns:h="http://xmlns.jcp.org/jsf/html"
    xmlns:cc="http://xmlns.jcp.org/jsf/composite">
    
    <cc:interface>
        <cc:attribute name="label" required="true" />
        <cc:attribute name="value" required="true" />
        <cc:attribute name="required" default="false" />
    </cc:interface>
    
    <cc:implementation>
        <div class="form-group">
            <h:outputLabel for="input" value="#{cc.attrs.label}" />
            <h:inputText id="input" value="#{cc.attrs.value}" 
                          required="#{cc.attrs.required}" />
            <h:message for="input" styleClass="error" />
        </div>
    </cc:implementation>
</ui:component>
```

2. Usar el componente:
```xml
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:h="http://xmlns.jcp.org/jsf/html"
      xmlns:my="http://xmlns.jcp.org/jsf/composite/components">
    
    <h:body>
        <h:form>
            <my:inputWithLabel label="Nombre" value="#{usuario.nombre}" required="true" />
            <my:inputWithLabel label="Email" value="#{usuario.email}" />
        </h:form>
    </h:body>
</html>
```

### Paso de atributos y parámetros
- Para los componentes compuestos, se usan las etiquetas `<cc:attribute>` para definir los parámetros que acepta el componente
- Para inclusiones simples, se usan etiquetas `<ui:param>` para pasar valores

## Vinculación con Managed Beans

### Expresiones EL (Expression Language)
EL permite acceder a los beans y sus propiedades:
- `#{bean.property}`: Accede a una propiedad
- `#{bean.method(param)}`: Invoca un método
- `#{bean.property eq 'value'}`: Operadores de comparación
- `#{bean.property ? 'Yes' : 'No'}`: Operador ternario

### Acceso a propiedades y métodos
```xml
<h:inputText value="#{userBean.username}" />
<h:commandButton value="Guardar" action="#{userBean.save}" />
```

### Vinculación de eventos
```xml
<h:inputText value="#{userBean.searchTerm}">
    <f:ajax event="keyup" render="resultPanel" listener="#{userBean.search}" />
</h:inputText>

<h:commandButton value="Submit" action="#{userBean.submit}">
    <f:ajax execute="@form" render="@form" />
</h:commandButton>
```

## Conversión y validación

### Implementación de convertidores
Los convertidores transforman valores entre la vista y el modelo:

1. Convertidores predefinidos:
```xml
<h:inputText value="#{bean.date}">
    <f:convertDateTime pattern="dd/MM/yyyy" />
</h:inputText>

<h:inputText value="#{bean.number}">
    <f:convertNumber type="currency" currencySymbol="€" />
</h:inputText>
```

2. Convertidor personalizado:
```java
@FacesConverter("miConvertidor")
public class MiConvertidor implements Converter {
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        // Convertir String a objeto
    }
    
    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        // Convertir objeto a String
    }
}
```

```xml
<h:inputText value="#{bean.objeto}">
    <f:converter converterId="miConvertidor" />
</h:inputText>
```

### Uso de validadores predefinidos
```xml
<h:inputText value="#{bean.email}">
    <f:validateRequired />
    <f:validateLength minimum="5" maximum="50" />
    <f:validateRegex pattern="[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}" />
</h:inputText>

<h:inputText value="#{bean.edad}">
    <f:validateLongRange minimum="18" maximum="65" />
</h:inputText>
```

### Creación de validadores personalizados
```java
@FacesValidator("passwordValidator")
public class PasswordValidator implements Validator {
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) 
        throws ValidatorException {
        
        String password = (String) value;
        
        if (password.length() < 8) {
            FacesMessage message = new FacesMessage(
                FacesMessage.SEVERITY_ERROR, 
                "La contraseña debe tener al menos 8 caracteres", null);
            throw new ValidatorException(message);
        }
        
        // Más validaciones...
    }
}
```

```xml
<h:inputSecret value="#{userBean.password}">
    <f:validator validatorId="passwordValidator" />
</h:inputSecret>
```

## Manejo de recursos

### Gestión de CSS y JavaScript
JSF proporciona etiquetas específicas para incluir recursos estáticos:

```xml
<h:head>
    <h:outputStylesheet library="css" name="styles.css" />
    <h:outputScript library="js" name="functions.js" target="head" />
</h:head>

<h:body>
    <!-- Contenido -->
    <h:outputScript library="js" name="footer-script.js" target="body" />
</h:body>
```

### Implementación de recursos estáticos
Los recursos estáticos se organizan en el directorio `/resources/`:
- `/resources/css/`: Hojas de estilo
- `/resources/js/`: Archivos JavaScript
- `/resources/images/`: Imágenes

Para referenciar una imagen:
```xml
<h:graphicImage library="images" name="logo.png" alt="Logo" />
```

### Uso de recursos dinámicos
Para generar recursos dinámicamente, se puede implementar un `ResourceHandler` personalizado:

```java
@ResourceDependency(library="js", name="dynamic.js", target="head")
public class DynamicResourceBean {
    // Métodos para generar contenido dinámico
}
```

## Facelet Tag Libraries

### Bibliotecas estándar
- `h:` (JSF HTML): Componentes HTML básicos
- `f:` (JSF Core): Etiquetas principales de JSF
- `ui:` (Facelets): Etiquetas específicas de plantillas
- `c:` (JSTL Core): Estructuras de control
- `fn:` (JSTL Functions): Funciones de utilidad

### Creación de bibliotecas personalizadas
1. Crear archivo de configuración de la biblioteca (`/WEB-INF/my-tags.taglib.xml`):
```xml
<?xml version="1.0" encoding="UTF-8"?>
<facelet-taglib
    xmlns="http://xmlns.jcp.org/xml/ns/javaee"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-facelettaglibrary_2_2.xsd"
    version="2.2">
    
    <namespace>http://ejemplo.com/tags</namespace>
    
    <tag>
        <tag-name>formattedDate</tag-name>
        <component>
            <component-type>com.ejemplo.FormattedDate</component-type>
        </component>
    </tag>
    
    <function>
        <function-name>formatCurrency</function-name>
        <function-class>com.ejemplo.util.CurrencyFormatter</function-class>
        <function-signature>java.lang.String format(java.lang.Double)</function-signature>
    </function>
</facelet-taglib>
```

2. Registrar la biblioteca en `web.xml`:
```xml
<context-param>
    <param-name>javax.faces.FACELETS_LIBRARIES</param-name>
    <param-value>/WEB-INF/my-tags.taglib.xml</param-value>
</context-param>
```

### Extensión de funcionalidades
Se pueden agregar funciones personalizadas:

```java
public class StringUtils {
    public static String capitalize(String value) {
        if (value == null || value.isEmpty()) {
            return value;
        }
        return value.substring(0, 1).toUpperCase() + value.substring(1);
    }
}
```

```xml
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:h="http://xmlns.jcp.org/jsf/html"
      xmlns:my="http://ejemplo.com/tags">
    
    <h:outputText value="#{my:capitalize(bean.name)}" />
</html>
```

## Rendimiento y optimización

### Caché de plantillas
Facelets cachea automáticamente las plantillas compiladas para mejorar el rendimiento. Se puede configurar el tiempo de caché:

```xml
<context-param>
    <param-name>javax.faces.FACELETS_REFRESH_PERIOD</param-name>
    <param-value>-1</param-value> <!-- -1 para producción (nunca refrescar) -->
</context-param>
```

### Estrategias de carga
- Usar `<f:event type="preRenderView">` para cargar datos solo cuando sea necesario
- Implementar carga perezosa (lazy loading) para datos pesados
- Utilizar `<f:ajax>` para actualizar solo partes específicas de la página

### Mejores prácticas
- Minimizar el tamaño del árbol de componentes
- Usar IDs solo cuando sea necesario (para AJAX o JavaScript)
- Aprovechar el sistema de plantillas para reducir la duplicación
- Usar `<ui:repeat>` en lugar de `<h:dataTable>` para estructuras más eficientes
- Agrupar recursos CSS y JavaScript

## Depuración y resolución de problemas

### Herramientas de depuración
- `<ui:debug>`: Muestra información detallada sobre el árbol de componentes
```xml
<ui:debug hotkey="d" />
```
- Logging de JSF: Configurar en `logging.properties` o `log4j.properties`
```
javax.faces.level=FINE
```
- Developer Tools de navegadores para inspeccionar el HTML generado

### Errores comunes y soluciones
1. **Problemas de navegación a páginas**:
   - Verificar la configuración de `faces-config.xml`
   - Asegurar que las URLs incluyan el prefijo de FacesServlet (generalmente `/faces/`)

2. **Problemas de vinculación de datos**:
   - Verificar que los beans estén correctamente anotados y configurados
   - Comprobar la sintaxis de las expresiones EL

3. **Problemas de renderizado**:
   - Asegurar que los archivos XHTML sean válidos
   - Comprobar las referencias a recursos

### Estrategias de logging
```java
import java.util.logging.Logger;

@Named
@RequestScoped
public class MiBean {
    private static final Logger log = Logger.getLogger(MiBean.class.getName());
    
    public void accion() {
        log.fine("Método acción ejecutado");
        // ...
    }
}
```

## Casos de uso avanzados

### Internacionalización (i18n)
1. Crear archivos de propiedades para cada idioma:
   - `messages_es.properties`
   - `messages_en.properties`

2. Configurar en `faces-config.xml`:
```xml
<application>
    <locale-config>
        <default-locale>es</default-locale>
        <supported-locale>en</supported-locale>
    </locale-config>
    <resource-bundle>
        <base-name>mensajes.messages</base-name>
        <var>msg</var>
    </resource-bundle>
</application>
```

3. Usar en las páginas:
```xml
<h:outputText value="#{msg['welcome.message']}" />
```

4. Selector de idioma:
```xml
<h:form>
    <h:selectOneMenu value="#{localeBean.idioma}" onchange="submit()">
        <f:selectItem itemValue="es" itemLabel="Español" />
        <f:selectItem itemValue="en" itemLabel="English" />
    </h:selectOneMenu>
</h:form>
```

### Accesibilidad
- Usar atributos ARIA en componentes JSF
```xml
<h:inputText id="username" value="#{bean.username}" 
             aria-required="true" aria-labelledby="usernameLabel" />
<h:outputLabel id="usernameLabel" for="username" value="Nombre de usuario" />
```

- Asegurar un orden lógico de tabulación
```xml
<h:inputText tabindex="1" ... />
<h:inputSecret tabindex="2" ... />
```

### Integración con otros frameworks
- **PrimeFaces**: Biblioteca de componentes con UI avanzada
```xml
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:h="http://xmlns.jcp.org/jsf/html"
      xmlns:p="http://primefaces.org/ui">
    
    <h:body>
        <p:dataTable value="#{bean.items}" var="item">
            <p:column headerText="Nombre">
                <h:outputText value="#{item.nombre}" />
            </p:column>
        </p:dataTable>
    </h:body>
</html>
```

- **OmniFaces**: Utilidades y componentes adicionales
```xml
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:h="http://xmlns.jcp.org/jsf/html"
      xmlns:o="http://omnifaces.org/ui">
    
    <h:body>
        <o:highlight render="messages" />
    </h:body>
</html>
```

### Aplicaciones responsivas
- Integración con Bootstrap o Foundation
```xml
<h:head>
    <h:outputStylesheet library="webjars" name="bootstrap/5.1.3/css/bootstrap.min.css" />
</h:head>

<h:body>
    <div class="container">
        <div class="row">
            <div class="col-md-6">
                <!-- Contenido -->
            </div>
        </div>
    </div>
    
    <h:outputScript library="webjars" name="bootstrap/5.1.3/js/bootstrap.min.js" />
</h:body>
```

## Despliegue y entornos

### Configuración para desarrollo
```xml
<!-- web.xml -->
<context-param>
    <param-name>javax.faces.PROJECT_STAGE</param-name>
    <param-value>Development</param-value>
</context-param>

<context-param>
    <param-name>javax.faces.FACELETS_REFRESH_PERIOD</param-name>
    <param-value>0</param-value> <!-- 0 para refrescar siempre en desarrollo -->
</context-param>

<context-param>
    <param-name>facelets.DEVELOPMENT</param-name>
    <param-value>true</param-value>
</context-param>
```

### Ajustes para producción
```xml
<!-- web.xml -->
<context-param>
    <param-name>javax.faces.PROJECT_STAGE</param-name>
    <param-value>Production</param-value>
</context-param>

<context-param>
    <param-name>javax.faces.FACELETS_REFRESH_PERIOD</param-name>
    <param-value>-1</param-value> <!-- -1 para nunca refrescar en producción -->
</context-param>

<context-param>
    <param-name>javax.faces.STATE_SAVING_METHOD</param-name>
    <param-value>server</param-value> <!-- Mejor rendimiento -->
</context-param>
```

### Compatibilidad entre servidores
- Utilizar APIs estándar de Java EE/Jakarta EE
- Evitar características específicas del servidor
- Probar en diferentes contenedores (Tomcat, GlassFish, WildFly)
- Usar versiones compatibles de las bibliotecas
