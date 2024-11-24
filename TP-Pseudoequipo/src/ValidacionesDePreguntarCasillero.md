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
* Es comportamiento esperado que un jugador pueda perderse un turno a si mismo??
* Si el jugador se pierde turno a si mismo no funciona, creo
* Que pasa si el jugador quiere cambiar color ficha, y todavia no hay ninguna ficha enemiga?, se bugea el juego