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
* reparar metodos violando encapusulamiento devolviendo listas y vectores, pilas y demas de Clases
* Es comportamiento esperado que un jugador pueda perderse un turno a si mismo??
* Que pasa si el jugador quiere cambiar color ficha, y todavia no hay ninguna ficha enemiga?, se bugea el juego
* Si el jugador se pierde turno a si mismo no funciona
* Quiza haya q Verificar que no puedan haber nombres repetidos entre jugadores