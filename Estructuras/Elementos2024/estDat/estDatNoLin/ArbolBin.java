package estDat.estDatNoLin;
import estDat.estDatLin.*;

public class ArbolBin<T>{
    private T           raiz;
    private ArbolBin<T> izq;
    private ArbolBin<T> der;
    //Constructor vacio
    public ArbolBin(){
        raiz = null;
        izq = der = null;
    }
    public ArbolBin(T dato){
        raiz = dato;
        izq = der = null;
    }
    //Constructor dado izq, der y raiz
    private ArbolBin(ArbolBin<T> izq, T raiz, ArbolBin<T> der){
        this.izq = izq;
        this.raiz = raiz;
        this.der = der;
    }
    //Constructor dado otro Arbol
    private ArbolBin(ArbolBin<T> otro){
        this.izq = otro.izq;
        this.raiz = otro.raiz;
        this.der = otro.der;
    }

    public boolean vacia(){
        return raiz == null;
    }

    /**
     * Por Profundidad y ruta
     */
    public boolean insertar(T dato, Lista<T> ruta) {
        boolean sePudo = false;
        T r1;
        if(vacia()){
            if(ruta.vacia()){
                raiz = dato;
                izq = new ArbolBin<T>();
                der = new ArbolBin<T>();
                sePudo = true;
            }
        }else{
            if(!ruta.vacia()){
                r1 = ruta.eliminar(0);
                if(r1.equals(raiz)){
                    sePudo = izq.insertar(dato,ruta);
                    if(!sePudo){
                        sePudo = der.insertar(dato,ruta);
                    }
                }
                ruta.insertar(r1,0);
            }
        }
        return sePudo;
    }
    
    public boolean insertar2(T dato, Lista<T> ruta){
        return insertar2(dato,ruta,0);
    }
    
    private boolean insertar2(T dato, Lista<T> ruta, int i){
        boolean sePudo = false;
        if(vacia()){
            if(i == ruta.longitud()){
                raiz = dato;
                izq = new ArbolBin<T>();
                der = new ArbolBin<T>();
                sePudo = true;
            }
        }else{
            if(i<ruta.longitud()){
                T r = ruta.acceder(i);
                if(raiz.equals(r)){
                    sePudo = izq.insertar2(dato,ruta,i+1);
                    if(!sePudo){
                        sePudo = der.insertar2(dato,ruta,i+1);
                    }
                }
            }
        }
        return sePudo;
    }
    
    public boolean insertar3(T dato, Lista<T> ruta){
        boolean sePudo = false;
        Pila<ArbolBin<T>> pila = new Pila();
        pila.push(this);
        int i = 0; ArbolBin<T> act;
        while(!pila.vacia() && !sePudo){
            act = pila.pop(); 
            if(act.vacia()){
                if(i == ruta.longitud()){
                    act.raiz = dato;
                    act.izq = new ArbolBin<T>();
                    act.der = new ArbolBin<T>();
                    sePudo = true;
                }
            }else{
                if(i < ruta.longitud()){
                    T r = ruta.acceder(i);
                    if(act.raiz.equals(r)){
                        i++;
                        pila.push(act.der);
                        pila.push(act.izq);
                    }
                }    
            }
        }
        return sePudo;
    }

    /**
     * Por Amplitud dado del Dato
     */
    public void insertar(T dato){
        Cola<ArbolBin<T>> cola;
        cola = new Cola<ArbolBin<T>>();
        cola.encolar(this);
        insertar(dato, cola);
    }

    private void insertar(T dato, Cola<ArbolBin<T>> cola){
        ArbolBin<T> arbCand;
        arbCand = cola.decolar();
        if(arbCand.vacia()){
            arbCand.raiz = dato;
            arbCand.izq = new ArbolBin<T>();
            arbCand.der = new ArbolBin<T>();
        }else{
            cola.encolar(arbCand.izq);
            cola.encolar(arbCand.der);
            insertar(dato,cola);
        }
    }
    
    public void insertar2(T dato){
        Cola<ArbolBin<T>> cola = new Cola<ArbolBin<T>>();
        cola.encolar(this);
        ArbolBin<T> act;
        boolean insert = false;
        while(!cola.vacia() && !insert){
            act = cola.decolar();
            if(act.vacia()){
                act.raiz = dato;
                act.izq = new ArbolBin<T>();
                act.der = new ArbolBin<T>();
                insert = true;
            }else{
                cola.encolar(act.izq);
                cola.encolar(act.der);
            }
        }
    }

    public Lista<T> amplitud(){
        Cola<ArbolBin<T>> cola = new Cola<ArbolBin<T>>();
        cola.encolar(this);
        Lista<T> res = new ListaSE<T>();
        amplitud(cola,res);
        return res;
    }

    private void amplitud(Cola<ArbolBin<T>> cola, Lista<T> res){
        ArbolBin<T> arbCand;
        if(!cola.vacia()){
            arbCand = cola.decolar();
            if(!arbCand.vacia()){
                res.insertar(arbCand.raiz);
                cola.encolar(arbCand.izq);
                cola.encolar(arbCand.der);
                amplitud(cola,res);
            }else{
                amplitud(cola,res);
            } 
        }
    }

    public boolean raizHoja(){
        return izq.vacia() && der.vacia();
    }

    /**
     * elimina el dato ingresado, te devuelve null si no lo encontro o si ese dato 
     *  tiene dependencia de datos, te devuelve el dato si logró eliminarlo solo si
     *  el dato es hoja.
     *  @param el dato a eliminar
     *  @return el dato eliminado
     */
    public T eliminar(T dato){
        T elDato;
        if(vacia()){
            elDato = null;
        }else{
            if(raiz.equals(dato)){
                if(raizHoja()){
                    elDato = raiz;
                    raiz = null;
                    izq = der = null;
                }else{
                    elDato = null;
                }
            }else{
                elDato = izq.eliminar(dato);
                if(elDato == null){
                    elDato = der.eliminar(dato);
                }
            }
        }
        return elDato;
    }

    public boolean eliminarDescendencia(T dato){
        boolean sePudo;
        if(vacia()){
            sePudo = false;
        }else{
            if(raiz.equals(dato)){
                sePudo = true;
                raiz = null;
                izq = der = null;
            }else{
                sePudo = izq.eliminarDescendencia(dato);
                if(!sePudo){
                    sePudo = der.eliminarDescendencia(dato);
                }
            }
        }
        return sePudo;
    }

    public T acceder(T dato){
        T elDato;
        if(vacia()){
            elDato = null;
        }else{
            if(raiz.equals(dato)){
                elDato = raiz;
            }else{
                elDato = izq.acceder(dato);
                if(elDato == null)
                    elDato = der.acceder(dato);
            }
        }
        return elDato;
    }

    public boolean contiene(T dato){
        boolean res;
        if(vacia()){
            res = false;
        }else{
            T aux = raiz; 
            if(aux.equals(dato)){
                res = true;
            }else{
                res = izq.contiene(dato);
                if(!res){
                    res = der.contiene(dato);
                }
            }
        }
        return res;
    }

    public int altura(T dato){
        int res;
        ArbolBin<T> q;
        if(!vacia()){
            q = buscarArbol(dato);
            if(q!=null)res = q.altura();
            else res = -1;
        }else{
            res = -1;
        }
        return res;
    }

    private ArbolBin<T> buscarArbol(T dato){
        ArbolBin<T> res;
        if(vacia()){
            res = null;
        }else{
            if(raiz.equals(dato)){
                res = this;
            }else{
                res = izq.buscarArbol(dato);
                if(res == null)
                    res = der.buscarArbol(dato);
            }
        }
        return res;
    }

    public int altura(){
        int res, aux1, aux2;
        if(vacia()){
            res = -1;
        }else{
            aux1 = 1 + izq.altura();
            aux2 = 1 + der.altura();
            if(aux1>aux2){
                res = aux1;
            }else{
                res = aux2;
            }
        }
        return res;
    }

    public Lista<T> acceder(int n){
        ListaSE<T> res = new ListaSE<T>();
        acceder(0,n,res);
        return res;
    }

    private void acceder(int i, int n, Lista<T> res){
        if(!vacia()){
            if(i == n){
                res.insertar(raiz);
            }else{
                izq.acceder(i+1,n,res);
                der.acceder(i+1,n,res);
            }
        }
    }

    public T getRaiz(){return raiz;}

    public ArbolBin<T> getIzq(){return izq;}

    public ArbolBin<T> getDer(){return der;}

    private ArbolBin<T> arbolPadre(ArbolBin<T> arbol){
        ArbolBin<T> res = new ArbolBin<T>();
        if( izq == arbol || der == arbol ){
            res = this;
        }else{
            if(!izq.vacia()||!der.vacia()){
                if(!izq.vacia()){
                    res = izq.arbolPadre(arbol);
                    if(res.izq!=arbol&&res.der!=arbol){//buscamos a la derecha ,podria ser res ==null
                        if(!der.vacia()) 
                            res = der.arbolPadre(arbol);
                    }
                }else if(!der.vacia()){
                    res = der.arbolPadre(arbol);
                    if(res.izq!=arbol&&res.der!=arbol){
                        if(!izq.vacia()) 
                            res = izq.arbolPadre(arbol);
                    }
                }
            }
        }
        return res;
    }

    public Lista<T> rama(T datoHoja){
        ListaSE<T> res = new ListaSE<T>();;
        ArbolBin<T> q,r;
        if(izq.vacia() && der.vacia()){
            if(raiz.equals(datoHoja)) res.insertar(raiz);
        }else{
            q = buscarArbol(datoHoja);
            r = arbolPadre(q);
            if(!r.vacia() && q!=null && q.raizHoja()){
                res.insertar(q.raiz,0);
                rama(res,q,r,this);
                res.insertar(raiz,0);
            }
        }
        return res;
    }

    private void rama(Lista<T> res,ArbolBin<T> hoja, ArbolBin<T> padre,ArbolBin<T> original){
        if(padre != original){
            res.insertar(padre.raiz,0);
            hoja = padre;
            padre = arbolPadre(padre);
            rama(res,hoja,padre,original);
        }
    }

    public Lista<T> otraRama(T datoHoja){
        Lista<T> res = new ListaSE<T>();
        if(izq.vacia() && der.vacia()){
            if(raiz.equals(datoHoja)) res.insertar(raiz);
        }else{
            otraRama(res,datoHoja);
        }
        return res;
    }

    private void otraRama(Lista<T> res,T datoHoja){
        if(!vacia()){
            boolean aux1,aux2;
            aux1 = izq.contiene(datoHoja);
            aux2 = der.contiene(datoHoja);
            if(aux1){
                res.insertar(this.raiz);
                izq.otraRama(res,datoHoja);
            }else if(aux2){
                res.insertar(this.raiz);
                der.otraRama(res,datoHoja);
            }else{
                if(this.raiz.equals(datoHoja))res.insertar(this.raiz);
                if(!this.raizHoja()) res.vaciar();
            }
        }
    }

    /**
     *  busca el camino de un dato1 y un dato2 conectadonse con el pariente mas cercano
     *  devuelve una lista vacia si estos datos no existen
     */
    public Lista<T> camino(T dato1, T dato2){
        ListaSE<T> res = new ListaSE<T>();
        ArbolBin<T> a,b;
        a = buscarArbol(dato1);
        b = buscarArbol(dato2);
        if(a!=null && b!=null ){
            camino(a,b,res,dato1,dato2);
        }
        return res;
    }

    private void camino(ArbolBin<T> a, ArbolBin<T> b, Lista<T> res,T dato1, T dato2){
        if(!res.buscar(dato1) || !res.buscar(dato2)){
            if(a.contiene(b.raiz)){
                res.insertarTodo(a.camino(b.raiz));
            }else if(b.contiene(a.raiz)){
                res.insertarTodo(b.camino(a.raiz));
            }else{
                res.insertar(a.raiz);
                a = arbolPadre(a);
                if(a.contiene(b.raiz)){
                    res.insertarTodo(a.camino(b.raiz));
                }else{
                    camino(a,b,res,dato1,dato2);
                }
            }
        }
    }

    public Lista<T> camino(T dato){
        Lista<T> res = new ListaSE<T>();
        if(izq.vacia() && der.vacia()){
            if(raiz.equals(dato)) res.insertar(raiz);
        }else{
            camino(res,dato);
        }
        return res;
    }

    private void camino(Lista<T> res,T dato){
        if(!vacia()){
            boolean aux1,aux2;
            aux1 = izq.contiene(dato);
            aux2 = der.contiene(dato);
            if(aux1){
                res.insertar(this.raiz);
                izq.camino(res,dato);
            }else if(aux2){
                res.insertar(this.raiz);
                der.camino(res,dato);
            }else{
                if(this.raiz.equals(dato))res.insertar(this.raiz);
            }
        }
    }

    public Lista<T> sobrinos(T dato){
        ArbolBin<T> q,r;
        Lista<T> res, aux; 
        Lista<ArbolBin<T>> listaArbol;
        int nivel;
        q = buscarArbol(dato);
        res = new ListaSE<T>();
        if(q!=null){
            nivel = nivel(q);
            listaArbol = arbolNivel(nivel);
            int fin = listaArbol.longitud();
            for(int i = 0; i < fin ;i++){
                r = listaArbol.acceder(i);
                if(!r.raiz.equals(dato)){
                    aux = r.amplitud();
                    aux.eliminar(0);
                    res.insertarTodo(aux);
                }
            }
        }
        return res;
    }

    private Lista<ArbolBin<T>> arbolNivel(int n){
        ListaSE<ArbolBin<T>> res = new ListaSE<ArbolBin<T>>();
        arbolNivel(0,n,res);
        return res;
    }

    private void arbolNivel(int i, int n, Lista<ArbolBin<T>> res){
        if(!vacia()){
            if(i == n){
                res.insertar(this);
            }else{
                izq.arbolNivel(i+1,n,res);
                der.arbolNivel(i+1,n,res);
            }
        }
    }

    private int nivel(ArbolBin<T> q){
        int res;
        Lista<T> aux = this.camino(q.raiz);
        res = aux.longitud()-1;
        return res;
    }

    public int nivel(T dato){
        int res;
        ArbolBin<T> q = buscarArbol(dato);
        if(q!=null){
            Lista<T> aux = this.camino(q.raiz);
            res = aux.longitud()-1;
        }else{
            res = -1;
        }
        return res;
    }

    public Lista<T> recolectarHojas(){
        Lista<T> res = new ListaSE<T>();
        if(!vacia())recolectarHojas(res);
        return res;
    }

    private void recolectarHojas(Lista<T> res){
        if(!vacia()){
            if(raizHoja()){
                res.insertar(raiz);
            }else{
                izq.recolectarHojas(res);
                der.recolectarHojas(res);
            }
        }
    }

    public boolean sonSemejantes(ArbolBin<T> otro){
        return sonSemejantes(this,otro);
    }

    private boolean sonSemejantes(ArbolBin<T> a, ArbolBin<T> b){
        boolean res;
        if((!a.vacia()&&b.vacia())||(!b.vacia()&&a.vacia())) 
            res = false;
        else if((b.vacia()&&a.vacia())){
            res = true;
        }else{
            res = sonSemejantes(a.izq,b.izq);
            if(res) res = sonSemejantes(a.der,b.der);
        }
        return res;
    }

    public boolean nodosCompletos(){
        boolean res;
        if(!raizHoja()){
            if(!izq.vacia()&&!der.vacia()){
                res = izq.nodosCompletos();
                if(res) res = res && der.nodosCompletos();
            }else{
                res = false;
            }
        }else{
            res = true;
        }
        return res;
    }

    public boolean esBalanceado(){
        boolean esBal;
        if(vacia()){
            esBal = true;
        }else{
            esBal = Math.abs(izq.altura()-der.altura())<=1;
            if(esBal){
                esBal = izq.esBalanceado();
                if(esBal) esBal = der.esBalanceado();
            }
        }
        return esBal;
    }

    public Lista<ListaDE<T>> accederXNiv(){
        Lista<ListaDE<T>> listalist ;
        listalist = new ListaDE<ListaDE<T>>();
        accederXNiv(0,listalist);
        return listalist;
    }

    private void accederXNiv(int n, Lista<ListaDE<T>> ll){
        ListaDE<T> lista;
        if(vacia()){}
        else{
            lista = ll.acceder(n);
            if(lista!=null) lista.insertar(raiz);
            else{
                lista = new ListaDE<T>();
                lista.insertar(raiz);
                ll.insertar(lista);
            }
            izq.accederXNiv(n+1,ll);
            der.accederXNiv(n+1,ll);
        }
    }

    public boolean igualdadDeBordes(ArbolBin<T> otro){
        boolean res;
        Lista<T> lista1 = this.recolectarHojas();
        Lista<T> lista2 = otro.recolectarHojas();
        if(lista1.equals(lista2)) res =true;
        else res = false;
        return res;
    }

    public Lista<Lista<T>> ramas(){
        Lista<Lista<T>> res = new ListaSE<Lista<T>>();
        Lista<T> hojas = this.recolectarHojas();
        int fin = hojas.longitud();
        for(int i = 0; i<fin ; i++){
            res.insertar(this.camino(hojas.acceder(i)));
        }
        return res;
    }

    public boolean simetrico(){
        boolean res;
        if(vacia()){
            res = true;
        }else{
            res = simetrico(izq,der);
        }
        return res;
    }

    private boolean simetrico(ArbolBin<T> actIzq,ArbolBin<T> actDer){
        boolean res;
        if(actIzq.vacia() && actDer.vacia()){
            res =true;
        }else if(actIzq.raiz.equals(actDer.raiz)){
            res = simetrico(actIzq.izq,actDer.der);
            if(res) simetrico(actIzq.der,actDer.izq);
        }else{
            res = false;
        }
        return res;
    } 
    
    public void podar(int altura){
        podar(altura,this,0);
    }

    private void podar(int altura, ArbolBin<T> act, int nivelAct){
        if(nivelAct==altura){
            act.izq = new ArbolBin<T>();
            act.der = new ArbolBin<T>();
        }else{
            if(!act.izq.vacia()){
                podar(altura,act.izq,nivelAct+1);
            }
            if(!act.der.vacia()){
                podar(altura,act.der,nivelAct+1);
            }
        }
    }

    public Lista<T> frontera(){
        ListaSE<T> res = new ListaSE<T>();
        preordenMod(res,this);
        recolectarHojas(res);
        Pila<T> p = preordenInvMod();
        while(!p.vacia()){
            if(!p.top().equals(raiz)) res.insertar(p.pop());
        }
        return res;
    }

    private Pila<T> preordenInvMod(){
        Pila<T> res = new Pila();
        if(!this.der.vacia()){
            preordenInvMod(res,this.der);
        }else if(!this.izq.vacia()){
            preordenInvMod(res,this.izq);
        }
        return res;
    }

    private void preordenInvMod(Pila<T> res, ArbolBin<T> act){
        if(!act.raizHoja()){
            res.push(act.raiz);
            if(!act.der.vacia())
                preordenInvMod(res,act.der);
            else
                preordenInvMod(res,act.izq);
        }
    }

    private boolean preordenMod(ListaSE<T> res, ArbolBin<T> act){
        boolean finalizo = false;
        if(!act.raizHoja()){
            res.insertar(act.raiz);
            if(!act.izq.vacia())finalizo = preordenMod(res,act.izq);
            if(!act.der.vacia() && !finalizo)preordenMod(res,act.izq);
        }else{
            finalizo = true;
        }
        return finalizo;
    }
    
    public Lista<T> recogerTodos(T dato){
        ListaSE<T> lista = new ListaSE<T>();
        recogerTodos(dato,lista);
        return lista;
    }
    
    private void recogerTodos(T dato, ListaSE<T> lista){
        if(!vacia()){
            if(raiz.equals(dato)){
                lista.insertar(raiz);
            }
            izq.recogerTodos(dato,lista);
            der.recogerTodos(dato,lista);
        }
    }
}