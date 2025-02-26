# JAVA Sprint Boot - Richard Aguirre

#### Internacionalización y CRUD de Productos en Java con Spring Boot
Este proyecto es una aplicación Java con Spring Boot que implementa un sistema de internacionalización (i18n) y un CRUD básico para productos. La aplicación expone varios endpoints RESTful para interactuar con los recursos.


## Requisitos

 - [Java 17 o superior](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
 - [Maven 3.x.](https://maven.apache.org/download.cgi)
 - [PostgreSQL](https://www.postgresql.org/)
 - [Spring Boot 3.x.](https://spring.io/blog/2022/05/24/preparing-for-spring-boot-3-0)
## Dependencias

 - spring-boot-starter-web
 - spring-boot-starter-data-jpa
 - spring-boot-starter-webflux
 - reactor-test



## Endpoints Disponibles

#### Saludo Internacionalizado

```http
  GET /api/saludo
```

| Campo | Tipo     | Descripción                |
| :-------- | :------- | :------------------------- |
| `lang` | `string` | **Opcional**. Idioma del saludo (**es** para español, **en** para inglés). Por defecto es **es**. |

Ejemplo de solicitud:
```sh
curl -X GET "http://localhost:8080/api/saludo?lang=en"
```
Respuesta exitosa:
```sh
Hello, RESTful API in English!
```

#### Agregar un Producto

```http
  POST /api/productos
```

| Campo | Tipo     | Descripción                |
| :-------- | :------- | :------------------------- |
| `nombre` | `string` | **Obligatorio**. Nombre del producto. |
| `precio` | `number` | **Obligatorio**. Precio del producto. |

Ejemplo de solicitud:
```sh
curl -X POST "http://localhost:8080/api/productos" -H "Content-Type: application/json" -d '{"nombre": "Tenis", "precio": 120000}'
```
Respuesta exitosa:
```sh
{
    "id": 39,
    "nombre": "Tenis",
    "precio": 120000
}
```

#### Agregar Múltiples Productos (Bulk)

```http
  POST /api/productos/bulk
```

| Campo | Tipo     | Descripción                |
| :-------- | :------- | :------------------------- |
| `nombre` | `string` | **Obligatorio**. Nombre del producto. |
| `precio` | `number` | **Obligatorio**. Precio del producto. |

Ejemplo de solicitud:
```sh
curl -X POST "http://localhost:8080/api/productos/bulk" -H "Content-Type: application/json" -d '[{"nombre": "Tenis", "precio": 120000}, {"nombre": "Reloj", "precio": 80000}]'
```
Respuesta exitosa:
```sh
[
    {
        "id": 39,
        "nombre": "Tenis",
        "precio": 120000
    },
    {
        "id": 42,
        "nombre": "Reloj",
        "precio": 80000
    }
]
```

#### Consultar Todos los Productos

```http
  GET /api/productos
```

Ejemplo de solicitud:
```sh
curl -X GET "http://localhost:8080/api/productos"
```
Respuesta exitosa:
```sh
[
    {
        "id": 39,
        "nombre": "Tenis",
        "precio": 120000
    },
    {
        "id": 42,
        "nombre": "Reloj",
        "precio": 80000
    }
]
```

#### Consultar un Producto por ID

```http
  GET /api/productos/{id}
```

| Campo | Tipo     | Descripción                |
| :-------- | :------- | :------------------------- |
| `id` | `number` | **Obligatorio**. ID del producto a consultar. |

Ejemplo de solicitud:
```sh
curl -X GET "http://localhost:8080/api/productos/39"
```
Respuesta exitosa:
```sh
{
    "id": 39,
    "nombre": "Tenis",
    "precio": 120000
}
```

#### Actualizar un Producto

```http
  PUT /api/productos/{id}
```

| Campo | Tipo     | Descripción                |
| :-------- | :------- | :------------------------- |
| `id` | `number` | **Obligatorio**. ID del producto a actualizar. |

Body (JSON):
| Campo | Tipo     | Descripción                |
| :-------- | :------- | :------------------------- |
| `nombre` | `string` | **Obligatorio**. Nuevo nombre del producto. |
| `precio` | `number` | **Obligatorio**. Nuevo precio del producto. |

Ejemplo de solicitud:
```sh
curl -X PUT "http://localhost:8080/api/productos/39" -H "Content-Type: application/json" -d '{"nombre": "Tenis Deportivos", "precio": 130000}'
```
Respuesta exitosa:
```sh
{
    "id": 39,
    "nombre": "Tenis Deportivos",
    "precio": 130000
}
```

#### Eliminar un Producto

```http
  DELETE /api/productos/{id}
```

| Campo | Tipo     | Descripción                |
| :-------- | :------- | :------------------------- |
| `id` | `number` | **Obligatorio**. ID del producto a eliminar. |

Ejemplo de solicitud:
```sh
curl -X DELETE "http://localhost:8080/api/productos/39"
```
Respuesta exitosa:
```sh
{
    "message": "Producto con ID 39 eliminado correctamente."
}
```


## Pruebas Realizadas

Se puede implementar pruebas unitarias para validar el comportamiento de los endpoints. Actualmente las pruebas incluyen:

#### Prueba de Internacionalización:

Verifica que el mensaje de saludo cambie según el idioma (es o en).

#### Pruebas del CRUD de Productos:

Verifica que los productos existen.

Se utiliza StepVerifier para probar el flujo reactivo de los productos.

#### Ejecución de Pruebas

Para ejecutar las pruebas, se usa el siguiente comando:

```sh
mvn test
```

![image](https://github.com/user-attachments/assets/617e90a6-821a-4c70-a742-89722c35e139)


