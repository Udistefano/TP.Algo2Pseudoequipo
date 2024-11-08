Trabajo Práctico 2: TATETI V2.0

Informe del tp - https://docs.google.com/document/d/1jDic7u_7X4g6fcwvT8CqtnxWDXRox1iNNMU39qBBOig/edit?usp=sharing
Meet de reuniones - https://meet.google.com/bdc-atfk-rpm
Tablero de miro - https://miro.com/app/board/uXjVLY7r5_0=/

Enunciado del tp:

Objetivo: Generar una pieza de software que simule el funcionamiento del juego Tateti en su versión multi Jugador.
Enunciado:
Tateti es un juego de mesa para varios jugadores en el que se introducen X fichas en un
tablero de N por M por Z con el fin de alinear las fichas en una línea recta, ya sea horizontal,
vertical o diagonal. Por cada turno cada jugador ingresa una ficha y luego de tener todas las
fichas en el tablero se pueden mover en el sentido horizontal o vertical o profundo.
Cada jugador podrá tener hasta C cartas en su mano, pudiendo jugarlas después de su
turno. Las cartas se levantan al principio de cada turno de un mazo (cola de cartas). 

Los tipo de cartas disponibles son:
a) Hacer perder un turno a un jugador
b) Bloquear una ficha de otro jugador
c) Anular un casillero del tablero
d) Volver atrás una jugada del turno.
e) Cambiar de color una ficha.
El grupo debe añadir 2 tipos de cartas más, inventando su funcionalidad. Al iniciar cada turno,
se tira un dado que indica la cantidad de cartas a sacar, si se sobrepasa la cantidad que se
pueden tener en mano, se devuelven al mazo. La estructura principal debe estar hecha con
listas.

Interfaz de usuario:
Toda la interfaz de usuario debe estar basada en texto. El estado del tablero tiene que
mostrarse usando un archivo de imagen BMP. Después de cada turno, el programa debe
exportar el tablero en un archivo bitmap con el estado del tablero, de manera que quede una
secuencia de imágenes.
No es necesario que se limpie la pantalla, simplemente escribir el estado del tablero
luego de cada jugada.

Cuestionario:
Responder el siguiente Cuestionario:
1) ¿Qué es un svn?
2) ¿Que es una “Ruta absoluta” o una “Ruta relativa”?
3) ¿Qué es git?

Normas de entrega:
Trabajo práctico grupal: 6 personas. Cada grupo deberá definir un nombre.
Reglas generales: respetar el Apéndice A.
Se deberá subir un único archivo comprimido al campus por grupo, en un link que se
habilitará para esta entrega. Este archivo deberá tener un nombre formado de la siguiente manera:

NombreDelGrupo-TP2.zip

Deberá contener los archivos fuentes (no los binarios), el informe del trabajo realizado,
las respuestas al cuestionario, el manual del usuario y el manual del programador (Todo en el
mismo PDF). El nombre del grupo lo define el grupo.


La fecha de entrega vence el día jueves 15/11/24 a las 23.59hs.


Se evaluará: funcionalidad, eficiencia, algoritmos utilizados, buenas prácticas de programación,
modularización, documentación, gestión de memoria y estructuras de datos.


Apéndice A:

1) Usar las siguientes convenciones para nombrar identificadores.
a) Clases: Los nombres de clases siempre deben comenzar con la primera letra en
mayúscula en cada palabra, deben ser simples y descriptivos. Se concatenan
todas las palabras. Ejemplo: Coche, Vehiculo, CentralTelefonica.
b) Métodos: Deben comenzar con letra minúscula, y si está compuesta por 2 o más
palabras, la primera letra de la segunda palabra debe comenzar con mayúscula.
De preferencia que sean verbos. Ejemplo: arrancarCoche(), sumar().
c) Variables y objetos: las variables siguen la misma convención que los métodos.
Por Ejemplo: alumno, padronElectoral.
d) Constantes: Las variables constantes o finales, las cuales no cambian su valor
durante todo el programa se deben escribir en mayúsculas, concatenadas por
"_". Ejemplo: ANCHO, VACIO, COLOR_BASE.
2) Si el trabajo práctico requiere archivos para procesar, entregar los archivos de prueba
en la entrega del TP. Utilizar siempre rutas relativas y no absolutas.
3) Entregar el informe explicando el TP realizado, manual de usuario y manual del
programador.
4) Comentar el código. Todos los tipos, métodos y funciones deberían tener sus
comentarios.
5) Modularizar el código. No entregar 1 o 2 archivos, separar cada clase.
6) No utilizar variables globales.
7) Si cualquier estructura de control tiene 1 línea, utilizar {} siempre, por ejemplo:
for(int i = 0; i < 10; i++) {
std::cout << i;
}
