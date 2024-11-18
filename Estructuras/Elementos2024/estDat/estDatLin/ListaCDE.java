package estDat.estDatLin;
public class ListaCDE<T> implements Lista<T>{
    private NodoDE<T> ini;

    public ListaCDE(){
        ini = null;
    }

    private ListaCDE(NodoDE<T> ini, int size){
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
            p.setSuc(p);
            p.setAnt(p);
        }else{
            ult = ini.getAnt();
            ult.setSuc(p);
            p.setSuc(ini);
            p.setAnt(ult);
            ini.setAnt(p);
        }
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
            ini = p;
            p.setSuc(p);
            p.setAnt(p);
        }else{
            q = buscar(ini, pos);
            r = q.getAnt();
            r.setSuc(p);
            p.setAnt(r);
            p.setSuc(q);
            q.setAnt(p);
            if(pos == 0)
                ini = p;
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
     * elimina el dato igual al dato que cumple la igualdad del dato de parametro, en caso de existir y ademas te devuelve el dato eliminado
     * @param dato a buscar de clase T 
     * @return el dato eliminado si existiera
     */
    public T eliminar(T dato){
        T resultado;
        NodoDE<T> q,r,s;
        if(vacia()){
            resultado = null;
        }else{
            q = buscar(ini, dato);
            if(q == null){
                resultado = null;
            }else{
                resultado = q.getDato();
                s = q.getSuc();
                r = q.getAnt();
                r.setSuc(s);
                s.setAnt(r);
                if(q == ini) ini = s;
                if( q == r ) ini = null;
            }
        }
    
        return resultado;
    }

    private NodoDE<T> buscar(NodoDE<T> q, T dato){
        NodoDE<T> elNodo;
        if(q.getDato().equals(dato)){
            elNodo = q;
        }else{
            if(q.getSuc()==ini){//cuidado no vuelvas a iniciar y enciclarte
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
            if(aux.getDato().equals(dato) && aux.getSuc()==aux){
                ini = null;
            }else{
                int tam = longitud(), i = 0;
                while(i<tam){
                    if(aux.getDato().equals(dato)){
                        eliminar(dato);
                    }
                    aux = aux.getSuc();
                    i++;
                }
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
        NodoDE<T> q,r,s;
        if(vacia()){
            res = null;
        }else{
            q = buscar(ini, pos);
            s = q.getSuc();
            r = q.getAnt();
            res = q.getDato();
            s.setAnt(r);
            r.setSuc(s);
            if(q == ini) ini = s;
            if(q == r)   ini = null;
        }
        return res;
    }

    public T eliminarUlt(){
        T res;
        NodoDE<T> q,r;
        if(vacia()){
            res = null;
        }else{
            q = ini.getAnt();
            res = q.getDato();
            r = q.getAnt();
            if(r.getSuc()!=ini){ 
                r.setSuc(ini); 
                ini.setAnt(r);
            }else{ 
                ini = null;
            }
        }
        
        return res;
    }

    private NodoDE<T> buscar(NodoDE<T> q, int pos){
        NodoDE<T> elNodo;
        if(pos == 0)
            elNodo = q;
        else{
            elNodo = buscar(q.getSuc(), pos-1);
        }
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
            if(q!=null)resultado = q.getDato();
            else resultado = null;
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
    public ListaCDE<T> acceder(int desde, int hasta){
        ListaCDE<T> nueva = new ListaCDE<T>();
        if(desde>=0 && desde <= hasta && hasta >= desde && hasta <= longitud()){
            NodoDE<T> aux = ini;
            for(int i = 0; i<desde ;i++){
                aux = aux.getSuc();
            }
            rellenar(desde,hasta,nueva,ini);
        }else{
            nueva = null;
        }
        return nueva;
    }

    private void rellenar(int desde, int hasta, ListaCDE<T> res, NodoDE<T> act){
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
            if(q.getSuc() == ini){
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
        if(!vacia()){
            res = 1;
            NodoDE<T> aux = ini.getSuc();
            while(aux!=ini){
                res++;
                aux = aux.getSuc();
            }
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
            if(pos==0) ini.setDato(dato);
            else reemplazar(dato, pos-1, ini.getSuc());
        }
    }

    private void reemplazar(T dato, int pos, NodoDE<T> act){
        if(act!=ini){
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
        if(nodito != ini){
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
    public ListaCDE<T> clonar(){
        ListaCDE<T> clon = new ListaCDE<T>();
        if(!vacia()){
            clon.insertar(ini.getDato());
            NodoDE<T> aux = ini.getSuc();
            while(aux!=ini){
                clon.insertar(aux.getDato());
                aux = aux.getSuc();
            }
        }
        return clon;
    }

    /**
     * revierte la Lista 
     */
    public void invertir(){
        NodoDE<T> p,q;
        p = ini;
        q = ini.getAnt();
        int n = longitud();
        for(int i=0;i<n/2;i++){
            T aux1,aux2;
            aux1 = p.getDato();
            aux2 = q.getDato();
            p.setDato(aux2);
            q.setDato(aux1);
            p = p.getSuc();
            q = q.getAnt();
        }
    }
    
    public void rotar(){
        ini = ini.getSuc();
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
            if(ini.getSuc()!=ini){
                if( ini.getSuc().getSuc()!= ini) reporte += ",";
                NodoDE<T> aux = ini.getSuc();
                while(aux.getSuc() != ini){
                    reporte += " "+aux.getDato().toString();
                    aux=aux.getSuc();
                    if(aux.getSuc()!=ini) reporte += ",";
                }
                reporte += ", "+aux.getDato().toString();
            }
        }
        return reporte + "]";
    }
}
