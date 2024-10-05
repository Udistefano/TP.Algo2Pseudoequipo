package tp2prueba;

public class Carta {
    //ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
    //ATRIBUTOS -----------------------------------------------------------------------------------------------
    private TipoDeCarta tipo = null;

    //CONSTRUCTORES -------------------------------------------------------------------------------------------
    public Carta(TipoDeCarta tipo) throws Exception {
        if (tipo == null) {
            throw new Exception("El tipo de carta no puede ser nulo");
        }

        this.tipo = tipo;
    }

    //METODOS DE CLASE ----------------------------------------------------------------------------------------
    //METODOS GENERALES ---------------------------------------------------------------------------------------
    //METODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------
    public void realizarHabilidadEspecial() {

    }

    //GETTERS SIMPLES -----------------------------------------------------------------------------------------
    public TipoDeCarta getTipo() {
        return this.tipo;
    }
    //SETTERS SIMPLES -----------------------------------------------------------------------------------------
}
