package estDat.estDatLin;
public class NodoSE<T>{
    private T         dato;
    private NodoSE<T> suc;
    
    public NodoSE(T dato){
        this.dato = dato;
        suc = null;
    }

    public void setDato(T dato){
        this.dato = dato;
    }
    
    public void setSuc(NodoSE<T> suc){
        this.suc = suc;
    }
    
    public NodoSE<T> getSuc(){
        return suc;
    }
    
    public T getDato(){
        return dato;
    }
}
