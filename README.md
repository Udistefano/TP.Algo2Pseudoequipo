# Trabajo Práctico 2: TATETI V2.0

## Enunciado del Trabajo Práctico

### Objetivo
Desarrollar una pieza de software que simule el funcionamiento del juego Tateti en su versión multijugador.

### Descripción
Tateti es un juego de mesa para varios jugadores que consiste en colocar fichas en un tablero de N x M x Z casillas con el objetivo de alinear las fichas en una línea recta (horizontal, vertical o diagonal). Los jugadores, en cada turno, colocan una ficha en el tablero y, una vez completado, pueden mover las fichas en direcciones horizontales, verticales o de profundidad. Cada jugador puede tener hasta **C** cartas en su mano, las cuales pueden jugarse después de colocar su ficha. Las cartas se extraen de un mazo al inicio de cada turno y si un jugador excede el límite de cartas permitidas en su mano, las cartas sobrantes se devuelven al mazo.

### Tipos de Cartas
El juego debe incluir las siguientes cartas:

- **Hacer perder un turno a un jugador**
- **Bloquear una ficha de otro jugador**
- **Anular un casillero del tablero**
- **Volver atrás una jugada del turno**
- **Cambiar el color de una ficha**

Además, el grupo debe inventar dos tipos adicionales de cartas y definir su funcionalidad.

### Mecánica
Al inicio de cada turno, se tira un dado que indica la cantidad de cartas a extraer del mazo. La interfaz de usuario debe ser completamente basada en texto. El estado del tablero debe mostrarse en un archivo de imagen BMP y exportarse después de cada turno para formar una secuencia de imágenes. No es necesario limpiar la pantalla; basta con imprimir el estado del tablero después de cada jugada.

## Recursos y Herramientas de Trabajo

- [Informe del Trabajo Práctico](https://docs.google.com/document/d/1jDic7u_7X4g6fcwvT8CqtnxWDXRox1iNNMU39qBBOig/edit?usp=sharing)
- [Reuniones en Google Meet](https://meet.google.com/bdc-atfk-rpm)
- [Tablero de planificación en Miro](https://miro.com/app/board/uXjVLY7r5_0=/)

## Cuestionario

1. **¿Qué es un SVN?**
2. **¿Qué es una “Ruta absoluta” o una “Ruta relativa”?**
3. **¿Qué es Git?**

## Normas de Entrega

### Trabajo en Equipo
El trabajo debe realizarse en grupos de 6 personas. Cada grupo debe asignar un nombre propio.

### Reglas Generales de Entrega
- Subir un único archivo comprimido al campus virtual en el enlace habilitado.
- El archivo debe seguir la siguiente convención de nombre: `NombreDelGrupo-TP2.zip`.
- El archivo comprimido debe contener:
  - Archivos fuente (sin binarios).
  - Informe del trabajo realizado.
  - Respuestas al cuestionario.
  - Manual de usuario.
  - Manual del programador (en un único archivo PDF).

### Fecha de Entrega
El trabajo debe entregarse antes del **jueves 15/11/24 a las 23:59 hs**.

### Criterios de Evaluación
- **Funcionalidad** y **eficiencia** del software.
- **Algoritmos utilizados** y buenas prácticas de programación.
- **Modularización** y **documentación** del código.
- **Gestión de memoria** y uso adecuado de **estructuras de datos**.

## Apéndice A: Convenciones de Nomenclatura

1. **Clases**: Usar nombres en formato `CamelCase` con la primera letra de cada palabra en mayúscula. Ejemplos: `Coche`, `Vehiculo`, `CentralTelefonica`.
2. **Métodos**: Comenzar con minúscula y usar `camelCase` para múltiples palabras. Ejemplos: `arrancarCoche()`, `sumar()`.
3. **Variables y Objetos**: Mismas reglas que los métodos. Ejemplos: `alumno`, `padronElectoral`.
4. **Constantes**: Usar mayúsculas con palabras separadas por guiones bajos (`_`). Ejemplos: `ANCHO`, `VACIO`, `COLOR_BASE`.

### Notas Adicionales
- Utilizar **rutas relativas** en lugar de absolutas.
- Comentar todos los tipos, métodos y funciones.
- Modularizar el código para evitar archivos monolíticos.
- Evitar el uso de **variables globales**.
- Incluso si una estructura de control tiene una sola línea, utilizar siempre `{}`. Ejemplo:
  ```cpp
  for (int i = 0; i < 10; i++) {
      std::cout << i;
  }
