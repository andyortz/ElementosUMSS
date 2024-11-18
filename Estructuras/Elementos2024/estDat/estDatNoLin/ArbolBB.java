package estDat.estDatNoLin;
import estDat.estDatLin.*;
public class ArbolBB<T extends Comparable<T>>{
    private ArbolBB<T> izq;
    private T          raiz;
    private ArbolBB<T> der;

    public ArbolBB(){
        raiz = null;
        izq = der = null;
    }

    public boolean vacia(){
        return raiz == null;
    }

    public void insertar(T dato){
        if(vacia()){
            raiz = dato;
            izq = new ArbolBB<T>();
            der = new ArbolBB<T>();
        }else{
            if(dato.compareTo(raiz)<0){ // dato menor a la raiz
                izq.insertar(dato);
            }else{
                der.insertar(dato);
            }
        }
    }

    private int estado(){
        int estado;
        if(izq.vacia()){
            if(der.vacia())
                estado = 0;
            else
                estado = 2;
        }else{
            if(der.vacia())
                estado = 1;
            else
                estado = 3;
        }
        return estado;
    }

    private ArbolBB<T> descIzq(){
        ArbolBB<T> sust;
        if(izq.vacia()){
            sust = this;
        }else{
            sust = izq.descIzq();
        }
        return sust;
    }

    public void eliminar(T dato){
        int opcion;
        ArbolBB<T> sust;
        if(!vacia()){
            if(dato.equals(raiz)){
                opcion = estado();
                switch(opcion){
                        //hoja
                    case 0: raiz = null; izq = null; der = null; break;
                        //solo izq        
                    case 1: raiz = izq.raiz; der = izq.der; izq = izq.izq; break;
                        //solo der
                    case 2: raiz = der.raiz; izq = der.izq; der = der.der; break;
                        //tiene izq y der
                    case 3: sust = der.descIzq();
                        raiz = sust.raiz;
                        sust.eliminar(raiz);
                }
            }else{
                if(dato.compareTo(raiz)<0){
                    izq.eliminar(dato);
                }else{
                    der.eliminar(dato);
                }
            }
        }
    }

    public T buscar(T dato){
        T res;
        if(vacia()){
            res =null;
        }else if(raiz.compareTo(dato)==0){
            res = raiz;
        }else{
            if(dato.compareTo(raiz)<0){ // dato menor a la raiz
                res = izq.buscar(dato);
            }else{
                res = der.buscar(dato);
            }
        }
        return res;
    }

    public boolean contiene(T dato){
        boolean res;
        if(vacia()){
            res = false;
        }else if(raiz.compareTo(dato)==0){
            res = true;
        }else{
            if(dato.compareTo(raiz)<0){ // dato menor a la raiz
                res = izq.contiene(dato);
            }else{
                res = der.contiene(dato);
            }
        }
        return res;
    }

    public boolean injertar(ArbolBB<T> injerto){
        boolean res = false;
        ArbolBB rama = buscarMenor(injerto.raiz);

        return res;
    }

    public ArbolBB<T> buscarMenor(T dato){
        ArbolBB<T> res = new ArbolBB<T>();
        if(!vacia()){
            if(dato.compareTo(raiz)<=0){
                ArbolBB<T> aux1 = izq;
                ArbolBB<T> aux2 = izq.buscarMenor(dato);
                if(aux1.raiz!=null && aux2.raiz!=null){
                    if(aux1.raiz.compareTo(aux2.raiz)<0){
                        res = aux1;
                    }else{
                        res = aux2;
                    }
                }else if(aux1.raiz!=null){
                    res = aux1;
                }else{
                    res = aux2;
                }
            }else{
                ArbolBB<T> aux1 = der;
                ArbolBB<T> aux2 = der.buscarMenor(dato);
                if(aux1.raiz!=null && aux2.raiz!=null){
                    if(aux1.raiz.compareTo(aux2.raiz)<0){
                        res = aux1;
                    }else{
                        res = aux2;
                    }
                }else if(aux1.raiz!=null){
                    res = aux1;
                }else{
                    res = aux2;
                }
            }
        }
        return res;
    }

    public ListaSE<T> recorridoAlternado(){
        ListaSE<T> res = new ListaSE();
        ArbolBB<T> clon = clonar();
        recorridoAlternado(res);
        raiz = clon.raiz;
        izq = clon.izq;
        der = clon.der;
        return res;
    }

    private ArbolBB<T> clonar(){
        ArbolBB<T> res = new ArbolBB<>();
        clonar(res);
        return res;
    }

    private void clonar(ArbolBB<T> res){
        if(!vacia()){
            res.insertar(raiz);
            izq.clonar(res);
            der.clonar(res);
        }
    }

    public boolean esHoja(){
        return !vacia() && izq.vacia() && der.vacia();
    }

    private T masIzq(){
        T res = null;
        if(!vacia()){
            if(esHoja() || (izq.vacia())){
                res = raiz;
            }else{
                res = izq.masIzq();
            }
        }
        return res;
    }

    private T masDer(){
        T res = null;
        if(!vacia()){
            if(esHoja() || (der.vacia())){
                res = raiz;
            }else{
                res = der.masDer();
            }
        }
        return res;
    }

    private void recorridoAlternado(ListaSE<T> res){
        if(!vacia()){
            boolean cerrar = false;
            while(!cerrar){
                T masIzq = izq.masIzq();
                T masDer = der.masDer();
                if(masIzq!=masDer){
                    res.insertar(masIzq);
                    res.insertar(masDer);
                    eliminar(masIzq);
                    eliminar(masDer);
                }else{
                    res.insertar(raiz);
                    cerrar = true;
                }
            }
        }
    }
}
