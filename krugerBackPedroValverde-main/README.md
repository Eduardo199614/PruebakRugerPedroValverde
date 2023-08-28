# PedroValverdeBackkruger
# ------------------Creación de la base de datos----------------------

Se debe considerar lo siguiente para levantar el proyecto:

Ir a la carpeta EXTRAS y correr los scripts.

1. Crear la base de datos.
2. Crear el nuevo usuario rol para la conexion con la base 

# -------------------- Levantar el servicio back --------------------

Se debe seguir los pasos  3, 4 y 5 solo si el sistema a la hora de levantar el proyecto no ejecutó las inserciones en la base.
(Verificar en la base -> Tabla employee)

3. Insertar los roles directamente con los scripts
4. Insertar el usuario admin de pruebas 
5. Ingresar el rol con el usuario

# ---------------------------Extras del back-------------------------------------------

En el directorio EXTRAS tambien encontrará adjunto el modelado de la base.

El sistema cuenta con un usuario ADMINISTRADOR mismo que se ingreso anteriormente este usuario corresponde a
username = 0123456789 
password = 12345

Los usuarios de tipo empleado solo se crean atraves de la aplicación.

Las credenciales del usuario y la clave nos muestra en formato json en los campos:
username y password

Para mayor información puede consultar la documentacion en swagger

Ingresar al link proporcionado
link: localhost:8080/swagger-ui.html
