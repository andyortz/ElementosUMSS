package estDat.estDatLin;
public class ListaDE<T> implements Lista<T>{
    private NodoDE<T> ini;

    public ListaDE(){
        ini =  null;
    }

    private ListaDE(NodoDE<T> ini){
        this.ini = ini;
    }

    /**
     * permite saber el estado actual de la lista
     */
    public boolean vacia(){
        return ini == null;
    }

    /**
     * insertar el dato al final de la lista
     * @param dato a insertar de clase T
     * @return nada
     */
    public void insertar(T dato){
        NodoDE<T> p, ult;
        p = new NodoDE<T>(dato);
        if(vacia()){
            ini = p;
        }else{
            ult = fin(ini);
            ult.setSuc(p);
            p.setAnt(ult);
        }
    }

    private NodoDE<T> fin(NodoDE<T> actual){
        NodoDE<T> res;
        if(actual.getSuc()==null){
            res = actual;
        }else{
            res = fin(actual.getSuc());
        }
        return res;
    }

    /**
     * inserta dato en la posicion pos, si ya hay un dato en esa posicion lo recorre una posicion
     * @param dato de clase T a insertar
     * @param pos en la que se quiere insertar pos>=0 entero
     * @return nada
     */
    public void insertar(T dato, int pos){
        NodoDE<T> p,q,r;
        p = new NodoDE<T>(dato);
        if(vacia()){
            if(pos==0){
                ini = p;
            }
        }else{
            q = buscar(ini, pos);
            if(q!=null){
                r = q.getAnt();
                if(pos != 0){
                    r.setSuc(p);
                    p.setAnt(r);
                    p.setSuc(q);
                    q.setAnt(p);
                }else{
                    ini = p;
                    ini.setSuc(q);
                    q.setAnt(p);
                }
            }
        }
    }

    public void insertarIni(T dato){
        insertar(dato,0);
    }

    /**
     * inserta toda una Lista al final de la Lista original
     * @param Lista de clase T a insertar en la Lista original
     * @return nada
     */
    public void insertarTodo(Lista<T> otraLista){
        if(!otraLista.vacia()){
            int pos = 0,fin = otraLista.longitud();
            while(pos < fin){
                insertar(otraLista.acceder(pos));
                pos++;
            }
        }
    }

    /**
     * elimina el primer dato igual al dato que cumple la igualdad del dato de parametro, en caso de existir y ademas te devuelve el dato eliminado
     * @param dato a buscar de clase T 
     * @return el dato eliminado si existiera
     */
    public T eliminar(T dato){
        T res;
        NodoDE<T> actu,ante, suce;
        if(vacia()){
            res=null;
        }else{
            actu = buscar(ini, dato);
            if(actu != null){
                suce = actu.getSuc();
                ante=actu.getAnt();
                res = actu.getDato();
                if(actu == ini){
                    if(suce== null){
                        ini=null;
                    }else{
                        ini=suce;
                        suce.setAnt(ante);
                    }
                }else{
                    if(suce!= null){
                        suce.setAnt(ante);
                        ante.setSuc(suce);
                    }else{
                        ante.setSuc(suce);
                    }
                } 
            }else{
                res = null;
            }
        }

        return res;
    }

    private NodoDE<T> buscar(NodoDE<T> q, T dato){
        NodoDE<T> elNodo;
        if(q.getDato().equals(dato)){
            elNodo = q;
        }else{
            if(q.getSuc()==null){//cuidado no vuelvas a iniciar y enciclarte
                elNodo = null;
            }else{
                elNodo = buscar(q.getSuc(),dato);
            }
        }
        return elNodo;
    }

    /**
     * elimina las ocurrencias del dato en caso de existir y ademas te devuelve el dato eliminado
     * @param dato a buscar de clase T 
     * @return nada
     */
    public void eliminarTodas(T dato){
        if(!vacia()){
            NodoDE<T> aux = ini;
            while(aux!=null){
                if(aux.getDato().equals(dato)){
                    eliminar(dato);
                }
                aux = aux.getSuc();
            }
        }
    }

    /**
     * elimina el dato en la posicion pos, en caso de existir y ademas te devuelve el dato eliminado
     * @param pos es un entero positivo inlucido el 0
     * @return el dato eliminado si existiera
     */
    public T eliminar(int pos){
        T res;
        NodoDE<T> r,q,s;
        if(vacia()){
            res = null;
        }else{
            q = buscar(ini, pos);
            if(q!=null){
                s = q.getSuc();
                r = q.getAnt();
                res = q.getDato();
                if(q == ini){
                    if(s == null)  ini = null;
                    else{
                        ini = s;
                        s.setAnt(r);
                    }
                }else if(s != null){
                    s.setAnt(r);
                    r.setSuc(s);  
                }else if(s == null){
                    r.setSuc(s);
                }
            }else{
                res = null;
            }
        }
        
        return res;
    }

    public T eliminarUlt(){
        T res;
        NodoDE<T> q,r;
        if(vacia()){
            res = null;
        }else{
            q = ultimo(ini);
            res = q.getDato();
            r = q.getAnt();
            if(r!=null) r.setSuc(null); 
            else{ 
                ini = null;
            }
        }
        
        return res;
    }

    private NodoDE<T> ultimo(NodoDE<T> ini){
        NodoDE<T> res;
        if(ini.getSuc()==null){
            res = ini;
        }else{
            res = ultimo(ini.getSuc());
        }
        return res;
    }

    private NodoDE<T> buscar(NodoDE<T> q, int pos){
        NodoDE<T> elNodo;
        if(pos>=longitud()){
            elNodo = null;
        }else{
            elNodo = buscar1(q, pos);
        }
        return elNodo;
    }

    private NodoDE<T> buscar1(NodoDE<T>q, int pos){
        NodoDE<T> elNodo;
        if(pos == 0)
            elNodo = q;
        else
            elNodo = buscar1(q.getSuc(), pos-1);
        return elNodo;
    }

    /**
     * elimina todos los elementos de la lista
     */
    public void vaciar(){
        ini = null;
    }

    /**
     * Te devuelve el dato en la posicion posicion pos, en caso de existir
     * @param pos es un entero positivo incluido el 0
     * @return el dato al que se desea acceder si existiera
     */
    public T acceder(int pos){
        T resultado;
        NodoDE<T> q;
        if(vacia()){
            resultado = null;
        }else{
            q = buscar(ini, pos);
            if(q==null)
                resultado = null;
            else
                resultado = q.getDato();
        }
        return resultado;
    }

    /**
     * Devuelve el dato que cumple la igual del dato de parametro, en caso de existir.
     * @param dato a acceder de clase T
     * @return el dato al que se desea acceder si existiera
     */
    public T acceder(T dato){
        T resultado;
        NodoDE<T> q;
        if(vacia()){
            resultado = null;
        }else{
            q = buscar(ini, dato);
            if( q == null )
                resultado = null;
            else 
                resultado = q.getDato();
        }
        return resultado;
    }

    /**
     * @param enteros positivos incluido el 0
     * @return la Lista desde una posicion hasta otra
     */
    public ListaDE<T> acceder(int desde, int hasta){
        ListaDE<T> nueva = new ListaDE<T>();
        if(desde>=0 && desde <= hasta && hasta >= desde && hasta <= longitud()){
            NodoDE<T> aux = ini;
            for(int i = 0; i<desde ;i++){
                aux = aux.getSuc();
            }
            rellenar(desde,hasta,nueva,aux);
        }else{
            nueva = null;
        }
        return nueva;
    }

    private void rellenar(int desde, int hasta, ListaDE<T> res, NodoDE<T> act){
        if(desde<hasta){
            res.insertar(act.getDato());
            rellenar(desde+1,hasta,res,act.getSuc());
        }
    }

    /**
     * busca el dato, en caso de existir devuelve true
     * @param dato a buscar de clase T
     */
    public boolean buscar(T dato){
        boolean res;
        NodoDE<T> q;
        if(vacia()){
            res = false;
        }else{
            q = buscar(ini, dato);
            res = q != null;
        }
        return res;
    }

    /**
     * busca el dato
     * @param dato a buscar de clase T
     * @return posicion del dato en caso de existir, -1 en caso de no existir
     */
    public int indiceDe(T dato){
        int indice;
        if(vacia()){
            indice = -1;
        }else{
            indice = buscarPosicion(ini, dato);
        }
        return indice;
    }

    private int buscarPosicion(NodoDE<T> q, T dato){
        int indice;
        if(q.getDato().equals(dato)){
            indice = 0;
        }else{
            if(q.getSuc() == null){
                indice = -1;
            }else{
                indice = buscarPosicion(q.getSuc(), dato);
                if(indice != -1){
                    indice++;
                }
            }
        }
        return indice;
    }

    /**
     * permite saber la longitud de la Lista
     */
    public int longitud(){
        int res = 0;
        NodoDE<T> aux = ini;
        while(aux!=null){
            res ++;
            aux = aux.getSuc();
        }
        return res;
    }

    /**
     * busca el dato en la posicion i por el dato en la posicion j
     * @param enteros positivos inluidos el 0
     * @return true si ambos datos existen, false si al menos uno de los datos no existe
     */
    public boolean intercambiar(int i, int j){
        boolean res = false;
        T aux1,aux2;
        if(!vacia()){
            aux1 = acceder(i);
            aux2 = acceder(j);
            if(aux1 != null && aux2 != null){
                reemplazar(aux2,i);
                reemplazar(aux1,j);
                res = true;
            }
        }
        return res;
    }

    /**
     * reemplaza el dato de la posicion pos
     * @param dato de clase T, que reemplazara al dato de la posicion pos, en caso de no existir lo añade al final de la lista,
     *        pos entero positivo incluido el 0
     * @return nada
     */
    public void reemplazar(T dato, int pos){
        if(pos < longitud()){
            reemplazar(dato, pos, ini);
        }
    }

    private void reemplazar(T dato, int pos, NodoDE<T> act){
        if(act!=null){
            if(pos == 0){
                act.setDato(dato);
            }else{
                reemplazar(dato,pos-1,act.getSuc());
            }
        }
    }

    /**
     * compara dos datos ubicados en las posiciones pos1 y pos2
     * @param enteros positivos incluidos el 0 < longitud de la Lista
     * @return true si los datos de clase T en las posiciones pos son iguales, false si no lo son y ademas si uno de las posiciones no existen
     */
    public boolean compararDatos(int pos1, int pos2){
        boolean res = false;
        T aux1 = acceder(pos1) , aux2 = acceder(pos2);
        if(aux1!=null && aux2!=null){
            res = aux1.equals(aux2);
        }
        return res;
    }

    /**
     * busca todas las ocurrencias del dato en caso de existir
     * @param dato de clase T a buscar
     * @return la cant de ocurrencias del dato en caso de exitir, 0 en caso de no existir
     */
    public int contarOcurrencias(T dato){
        int res;
        if(vacia()){
            res = 0;
        }else{
            if(ini.getDato().equals(dato)){
                res = 1 + contarOcurrencias(ini.getSuc(),dato);
            }else {
                res = contarOcurrencias(ini.getSuc(),dato);
            }
        }
        return res;
    }

    private int contarOcurrencias(NodoDE<T> nodito,T dato){
        int res=0;
        if(nodito != null){
            if(nodito.getDato().equals(dato)){
                res = 1 + contarOcurrencias(nodito.getSuc(),dato);
            }else{
                res = contarOcurrencias(nodito.getSuc(),dato);
            }
        }
        return res;
    }

    /**
     * compara la Lista original con otra lista
     * @param Lista de clase T
     * @return true si ambas listas son iguales en cada posicion
     */
    public boolean equals(Lista<T> otraLista){
        boolean res = false;
        if(longitud()== otraLista.longitud()){
            int pos=0, fin = longitud();
            while( pos < fin ){
                if(acceder(pos).equals(otraLista.acceder(pos))){
                    res = true;
                    pos++;
                }else{
                    pos=fin;
                }
            }
        }
        return res;
    }

    /**
     * te devuelve una Lista igual a la Lista original
     */
    public ListaDE<T> clonar(){
        ListaDE<T> clon = new ListaDE<T>();
        if(!vacia()){
            clon.ini = this.ini.clonar();
        }
        return clon;
    }

    /**
     * revierte la Lista 
     */
    public void invertir(){
        ListaDE<T> res = new ListaDE<T>();
        invertir(this,res);
        ini = res.buscar(res.ini,0);
    }

    private void invertir(Lista<T> lista, Lista<T> res){
        if(!lista.vacia()){
            int tam = lista.longitud();
            res.insertar(lista.acceder(tam-1));
            invertir(lista.acceder(0,tam-1),res);
        }
    }

    /**
     * Devuelve una representacion de String del contenido de la Lista
     */
    public String toString(){
        String reporte = "[";
        if(vacia()){
            reporte += "";
        }else{
            reporte += ini.getDato().toString();
            if(ini.getSuc()!=null){
                if( ini.getSuc().getSuc()!= null) reporte += ",";
                NodoDE<T> aux = ini.getSuc();
                while(aux.getSuc() != null){
                    reporte += " "+aux.getDato().toString();
                    aux=aux.getSuc();
                    if(aux.getSuc()!=null) reporte += ",";
                }
                reporte += ", "+aux.getDato().toString();
            }
        }
        return reporte + "]";
    }

}
