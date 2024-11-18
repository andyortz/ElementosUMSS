package estDat.estDatLin;

public class Cola<T>
{
    private NodoDE<T> frente, fin;
    
    public Cola(){ 
        frente = null;
        fin    = null;
    }
    
    public Cola(Cola<T> otro){
        frente = otro.frente;
        fin = otro.fin;
    }
    
    public void encolar(T dato){
        NodoDE<T> p;
        p = new NodoDE<T>(dato);
        if(vacia()){
            frente = fin = p;
        }else{
            fin.setSuc(p);
            p.setAnt(fin);
            fin = p;
        }
    }
    
    public boolean vacia(){
        return frente == null;
    }
    
    public T decolar(){
        T res;
        if(vacia()){
            res = null;
        }else{
            res = frente.getDato();
            frente = frente.getSuc();
            if(frente == null)
                fin = null;
            else 
                frente.setAnt(null);
        }
        return res;
    }
    
    public T ver(){
        T res;
        if(vacia()){
            res = null;
        }else{
            res = frente.getDato();
        }
        return res;
    }
    public void invertir(){
        Cola<T> aux = new Cola();
        Pila<T> c = new Pila();
        while(!vacia()){
            c.push(decolar());
        }
        while(!c.vacia()){
            aux.encolar(c.pop());
        }
        this.frente = aux.frente;
        this.fin = aux.fin;
    }
}
