package Cartas;

import java.util.Objects;

import Main.TipoDeCarta;

public abstract class  Carta {
    //ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
    
	private static long idActual = 1L;
	
	//ATRIBUTOS -----------------------------------------------------------------------------------------------
    private TipoDeCarta tipo = null;
    private Long id = null;

    //CONSTRUCTORES -------------------------------------------------------------------------------------------
    protected Carta(TipoDeCarta tipo) throws Exception {
        if (tipo == null) {
            throw new Exception("El tipo de carta no puede ser nulo");
        }

        this.tipo = tipo;
        this.id = idActual++;
    }

    //METODOS DE CLASE ----------------------------------------------------------------------------------------
    
    private static Long getIdActual() {
    	return Carta.idActual++;
    }
    
    //METODOS GENERALES ---------------------------------------------------------------------------------------
    
    @Override
    public String toString() {
    	// TODO Auto-generated method stub
    	return super.toString();
    }
    
    @Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Carta other = (Carta) obj;
		return Objects.equals(id, other.id);
	}
    
    //METODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------
	public void realizarEfecto(){
    	
    }
	

    //GETTERS SIMPLES -----------------------------------------------------------------------------------------
    public TipoDeCarta getTipo() {
        return this.tipo;
    }
    
    public Long getId() {
    	return this.id;
    }
    
    
    //SETTERS SIMPLES -----------------------------------------------------------------------------------------
}
