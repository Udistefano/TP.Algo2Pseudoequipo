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