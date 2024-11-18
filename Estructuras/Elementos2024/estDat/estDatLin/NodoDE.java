package estDat.estDatLin;

public class NodoDE<T>{ 
    private NodoDE<T> ant;
    private T         dato;
    private NodoDE<T> suc;
    public NodoDE(T dato){
        this.dato = dato;
        ant       = null;
        suc       = null;
    }
    
    public NodoDE(NodoDE<T> ant, T dato, NodoDE<T> suc){
        this.ant = ant;
        this.dato = dato;
        this.suc = suc;
    }
    
    public NodoDE<T> clonar(){
        NodoDE<T> res = new NodoDE<T>(this.ant,this.dato,this.suc);
        if(suc!=null){
            res = new NodoDE<T>(this.ant,this.dato,this.suc.clonar());
        }
        return res;
    }
    
    public void setAnt(NodoDE<T> a){
        ant = a;
    }
    
    public void setSuc(NodoDE<T> s){
        suc = s;
    }
    
    public void setDato(T d){
        dato = d;
    }
    
    public NodoDE<T> getAnt(){
        return ant;
    }
    public NodoDE<T> getSuc(){
        return suc;
    }
    public T getDato(){
        return dato;
    }

}
