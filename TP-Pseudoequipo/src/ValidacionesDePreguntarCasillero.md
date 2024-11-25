# Aca esta mi razonamiento de como separe cada jugada, que pregunta un casillero
# al jugador, cada una, hace distintas validaciones, y no es posible hacer un metodo
# universal, lo unico que comparten todas es ValidarCasilleroExiste

# Mover
* ValidarCasilleroExiste
* ValidarCasilleroEstaOcupado
* ValidarCasilleroContieneFichaPropia
* ValidarCasilleroNoContieneFichaBloqueada

# JugadaInicial
* ValidarCasilleroExiste
* ValidarCasilleroEstaLibre

# JugadaCambiarColorFicha
* ValidarCasilleroExiste
* ValidarCasilleroEstaOcupado
* ValidarCasilleroNoContieneFichaPropia
* ValidarCasilleroNoContieneFichaBloqueada

# JugadaAnularCasillero
* ValidarCasilleroExiste

# JugadaBloquearFicha
* ValidarCasilleroExiste
* ValidarCasilleroEstaOcupado
* ValidarCasilleroNoContieneFichaBloqueada

# Cosas por hacer

* Hacer que solamente se puedan elegir tamaño tableros de tamaño 3x3x3, 4x4x5, o 5x5x5

* Doble Turno digamos, que no cambia las cartas jugadas, ni saca una carta de la mano, no funciona logicamente