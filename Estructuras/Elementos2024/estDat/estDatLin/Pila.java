package estDat.estDatLin;
public class Pila<T>{
    private T       tope;   
    private Pila<T> base;
    
    public Pila(){
        tope=null;
        base=null; 
    }
    
    public Pila(Pila<T> otro){
        tope = otro.tope;
        base = otro.base;
    }

    private Pila(T dato, Pila<T> pila){
        tope=dato;
        base=pila;
    }

    public boolean vacia(){
        return tope==null;
    }

    public void push(T dato){
        if(vacia()){
            tope=dato;
            base=new Pila<T>();
        }else{
            base=new Pila<T>(tope,base);
            tope=dato;
        }
    }

    public T pop(){
        T res;
        if(vacia()){
            res = null;
        }else{
            res=tope;
            tope=base.tope;
            base=base.base;
        }
        return res;
    }

    public T top(){
        return tope;
    }
    
    public T base(){
        T res;
        if(base.vacia()){
            res = tope;
        }else{
            res = base.base();
        }
        return res;
    }
    
    public void invertir(){
        if(!vacia()){
            Pila<T> aux = new Pila<T>();
            while(!vacia()){
                aux.push(this.pop());
            }
            tope = aux.tope;
            base = aux.base;
        }
    }
    
    public boolean contiene(T dato){
        boolean res;
        if(vacia()){
            res = false;
        }else{
            if(tope.equals(dato)){
                res = true;
            }else{
                res = base.contiene(dato);
            }
        }
        return res;
    }
    
}
