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

* Si el jugador se pierde turno a si mismo no funciona, creo

* Si el jugador quiere jugar la carta cambiar color ficha, pero no hay fichas enemigas, tirar exception (la validacion), y decirle al jugador que no hay fichas enemigas para cambiarle el color 

* Hacer que solamente se puedan elegir tamaño tableros de tamaño 3x3x3, 4x4x5, o 5x5x5

* Antes de permitirle al jugador mover ficha, fijarse si tiene fichas en el tablero, si no tiene fichas, tirar exception (la validacion), y decirle al jugador que no tiene fichas para mover