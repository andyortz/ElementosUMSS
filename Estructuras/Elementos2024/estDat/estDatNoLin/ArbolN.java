package estDat.estDatNoLin;
import estDat.estDatLin.*;

public class ArbolN<T>{
    private T                raiz;
    private Lista<ArbolN<T>> desc; 

    public ArbolN(){
        raiz = null;
        desc = null;
    }

    public ArbolN(T padre, T h){
        ArbolN<T> hijo;
        raiz = padre;
        desc = new ListaSE<ArbolN<T>>();
        hijo = new ArbolN<T>(h);
        desc.insertar(hijo);
    }

    public ArbolN(T padre){
        raiz = padre;
        desc = new ListaSE<ArbolN<T>>();
    }

    public boolean vacia(){
        return raiz == null;
    }

    public ArbolN<T> buscar(T dato){
        ArbolN<T> res;
        if(raiz.equals(dato)){
            res = this;
        }else{
            res = buscar(dato,desc,0,desc.longitud());
        }
        return res;
    }

    private ArbolN<T> buscar(T dato, Lista<ArbolN<T>> desc, int i, int n){
        ArbolN<T> res;
        if(vacia()){
            res = null;
        }else{
            if(i<n){
                ArbolN<T> cand = desc.acceder(i);
                if(cand.raiz.equals(dato)){
                    res = cand; 
                }else{
                    res = buscar(dato,desc,i+1,n);
                    if(res == null){
                        res = cand.buscar(dato);
                    }
                }
            }else{
                res = null;
            }
        }
        return res;
    }

    public boolean insertar(T padre, T hijo){
        boolean res;
        ArbolN<T> cand;
        cand = buscar(padre);
        if(cand!=null){
            cand.desc.insertar(new ArbolN<T>(hijo));
            res = true;
        }else{
            res = false;
        }
        return  res;
    }

    private boolean raizHoja(){
        return desc.vacia();
    }

    public T eliminar(T dato){
        T res;
        ArbolN<T> cand;
        cand = buscar(dato);
        if(cand!=null){
            if(cand.raizHoja()) {
                res = cand.raiz;
                cand.raiz = null;
                cand.desc = null;
            }else {
                res = null;
            }
        }else{
            res = null;
        }
        return res;
    }

    public boolean eliminarDescendencia(T dato){
        boolean res;
        ArbolN<T> cand;
        cand = buscar(dato);
        if(cand!=null){
            cand.raiz = null;
            cand.desc = null;
            res = true;
        }else{
            res = false;
        }
        return res;
    }
    
    public void vaciar(){
        raiz = null;
        desc = null;
    }

    public boolean contiene(T dato){
        boolean res = false;
        ArbolN<T> cand = buscar(dato);
        if(cand!=null) res = true;
        return res;
    }

    public Lista<T> preOrden(){
        ListaSE<T> res = new ListaSE<T>();
        if(!vacia()){
            preOrden(this,res);
        }
        return res;
    }

    private void preOrden(ArbolN<T> arbol, ListaSE<T> res){
        //raiz izq resto
        if(arbol.raizHoja()){
            res.insertar(arbol.raiz);
        }else{
            res.insertar(arbol.raiz);
            preOrden(arbol.desc.acceder(0),res);
            for(int i = 1; i<arbol.desc.longitud();i++){
                preOrden(arbol.desc.acceder(i),res);
            }
        }
    }

    public Lista<T> inOrden(){
        ListaSE<T> res = new ListaSE<T>();
        if(!vacia()){
            inOrden(this,res);
        }
        return res;
    }

    private void inOrden(ArbolN<T> arbol, ListaSE<T> res){
        //HijoIzq raiz restoHijos
        if(arbol.raizHoja()){
            res.insertar(arbol.raiz);
        }else{
            inOrden(arbol.desc.acceder(0),res);
            res.insertar(arbol.raiz);
            for(int i = 1; i<arbol.desc.longitud();i++){
                inOrden(arbol.desc.acceder(i),res);
            }
        }
    }

    public Lista<T> posOrden(){
        ListaSE<T> res = new ListaSE<T>();
        if(!vacia()){
            posOrden(this,res);
            res.insertar(raiz);
        }
        return res;
    }

    private void posOrden(ArbolN<T> arbol, ListaSE<T> res){
        //hijos raiz
        if(!arbol.desc.vacia()){
            for(int i = 0;i<arbol.desc.longitud();i++){
                posOrden(arbol.desc.acceder(i),res);
                res.insertar(arbol.desc.acceder(i).raiz);
            }
        }
    }

    public int altura(){
        int res = 0;
        if(!desc.vacia()){
            res = 1+desc.acceder(0).altura();
            for(int i = 1; i<desc.longitud(); i++){
                res = Math.max(res,1+desc.acceder(i).altura());
            }
        }
        return res;
    }

    public int altura(T dato){
        int res;
        ArbolN<T> cand = buscar(dato);
        if(cand!=null) res = cand.altura();
        else res = -1;
        return res;
    }

    public int nivel(T dato){
        int res;
        if(raiz.equals(dato)){
            res = 0;
        }else{
            res = nivel(this,dato);
            if(res==0) res = -1;
        }
        return res;
    }

    private int nivel(ArbolN<T> arbol, T dato){
        int res = 0;
        if(!arbol.vacia()){
            if(!arbol.raiz.equals(dato)){
                boolean tiene = false;
                for(int i = 0;i<arbol.desc.longitud()&&!tiene;i++){
                    tiene = arbol.desc.acceder(i).contiene(dato);
                    if(tiene){
                        res = 1+nivel(arbol.desc.acceder(i),dato);
                    }
                }
            }
        }
        return res;
    }
}
