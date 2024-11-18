package estDat.estDatLin;
public class ListaSE<T> implements Lista<T>
{
    protected T           ini;
    protected ListaSE<T>  sig;

    public ListaSE(){
        ini = null;
        sig = null;
    }

    private ListaSE(T ini, ListaSE<T> sig){
        this.ini = ini;
        this.sig = sig;
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
        if(vacia()){
            ini = dato;
            sig = new ListaSE<T>();
        }else{
            sig.insertar(dato);
        }
    }

    /**
     * inserta dato en la posicion pos, si ya hay un dato en esa posicion lo recorre una posicion
     * @param dato de clase T a insertar
     * @param pos en la que se quiere insertar pos>=0 entero
     * @return nada
     */
    public void insertar(T dato, int pos){
        if(vacia()){
            if(pos == 0){
                ini = dato;
                sig = new ListaSE<T>();
            }
        }else{
            if(pos == 0){
                sig.insertar(ini, 0);
                ini = dato;
            }else{
                sig.insertar(dato, pos-1);
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
            int pos = 0;
            while(pos < otraLista.longitud()){
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
        T resultado;
        if(vacia()){
            resultado = null;
        }else{
            if(ini.equals(dato)){
                resultado = ini;
                ini = sig.ini;
                sig = sig.sig;
            }else{
                resultado = sig.eliminar(dato);
            }
        }

        return resultado;
    }

    /**
     * elimina las ocurrencias del dato en caso de existir y ademas te devuelve el dato eliminado
     * @param dato a buscar de clase T 
     * @return nada
     */
    public void eliminarTodas(T dato){
        if(!vacia()){
            boolean bb = false;
            while(!bb){
                if(eliminar(dato)==null) bb = true;
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
        if(vacia()){
            res = null;
        }else{
            if(pos == 0){
                res = ini;
                ini = sig.ini;
                sig = sig.sig;
            }else{
                res = sig.eliminar(pos-1);
            }
        }
        
        return res;
    }

    public T eliminarUlt(){
        T res;
        ListaSE<T> q,r;
        if(vacia()){
            res = null;
        }else{
            q = this;
            while(!q.sig.vacia()){
                q = q.sig;
            }
            res = q.ini;
            q.ini =null;
            q.sig = null;
        }
        return res;
    }

    /**
     * elimina todos los elementos de la lista
     */
    public void vaciar(){
        ini = null;
        sig = null;
    }

    /**
     * Te devuelve el dato en la posicion posicion pos, en caso de existir
     * @param pos es un entero positivo incluido el 0
     * @return el dato al que se desea acceder si existiera
     */
    public T acceder(int pos){
        T resultado;
        if(vacia()){
            resultado = null;
        }else{
            if(pos == 0){
                resultado = ini;
            }else{
                resultado = sig.acceder(pos-1);
            }
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
        if(vacia()){
            resultado = null;
        }else{
            if(ini.equals(dato)){
                resultado = ini;
            }else{
                resultado = sig.acceder(dato);
            }
        }
        return resultado;
    }

    /**
     * @param enteros positivos incluido el 0
     * @return la Lista desde la posicion "desde" hasta las pocion "hasta"-1
     */
    public ListaSE<T> acceder(int desde, int hasta){
        ListaSE<T> nueva = new ListaSE<T>();
        if(desde>=0 && desde <= hasta && hasta >= desde && hasta <= this.longitud()){
            ListaSE<T> aux = this;
            for(int i = 0; i<desde; i++){
                aux = aux.sig;
            }
            rellenar(desde,hasta,nueva,aux);
        }else{
            nueva = null;
        }
        return nueva;
    }

    private void rellenar(int desde, int hasta, Lista<T> res, ListaSE<T> ant){
        if(desde<hasta){
            res.insertar(ant.ini);
            rellenar(desde+1,hasta,res,ant.sig);
        }
    }

    /**
     * busca el dato, en caso de existir devuelve true
     * @param dato a buscar de clase T
     */
    public boolean buscar(T dato){
        boolean res;
        if(vacia()){
            res = false;
        }else{
            if(ini.equals(dato))
                res = true;
            else
                res = sig.buscar(dato);
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
            if(ini.equals(dato)){
                indice = 0;
            }else{
                indice = sig.indiceDe(dato);
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
        int res;
        if(vacia()){
           res = 0; 
        }else{
            res = 1 + sig.longitud();
        }
        return res;
    }

    /**
     * busca el dato en la posicion i por el dato en la posicion j
     * @param enteros positivos inluidos el 0
     * @return true si ambos datos existen, false si al menos uno de los datos no existe
     */
    public boolean intercambiar(int i, int j){
        boolean res=false;
        T aux,aux2;
        if(!vacia()){
            aux=acceder(i);
            aux2=acceder(j);
            if(aux!=null && aux2!=null){
                reemplazar(aux2,i);
                reemplazar(aux,j);
                res=true;
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
        if(!vacia()){
            if(pos == 0){
                ini = dato;
            }else{
                sig.reemplazar(dato, pos-1);
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
            if(ini.equals(dato)){
                res = 1 + contarOcurrencias(sig,dato);
            }else {
                res = contarOcurrencias(sig,dato);
            }
        }
        return res;
    }

    private int contarOcurrencias(ListaSE<T> lista,T dato){
        int res=0;
        if(lista.sig != null){
            if(lista.ini.equals(dato)){
                res = 1 + contarOcurrencias(lista.sig,dato);
            }else{
                res = contarOcurrencias(lista.sig,dato);
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
            int pos=0;
            while( pos<longitud() && !res){
                if(acceder(pos).equals(otraLista.acceder(pos))){
                    res = true;
                    pos++;
                }else{
                    res=false;
                    pos=longitud();
                }
            }
        }
        return res;
    }

    /**
     * te devuelve una Lista igual a la Lista original
     */
    public Lista<T> clonar(){
        ListaSE<T> clon;
        if(!vacia()){
            clon = new ListaSE<T>(ini,(ListaSE)sig.clonar());
        }else{
            clon = new ListaSE<T>();
        }
        return clon;
    }

    /**
     * revierte la Lista 
     */
    public void invertir(){
        invertirEnlace();
    }
    
    private void invertirEnlace(){
        if(sig != null){
            sig.invertirEnlace();
            sig.insertar(ini);
            ini = sig.ini;
            sig = sig.sig;
        }
    }
    
    private ListaSE<T> ultimo(ListaSE<T> lista){
        ListaSE<T> res;
        if(lista.sig.vacia()){
            res = lista;
        }else{
            res = ultimo(lista.sig);
        }
        return res;
    }

    public boolean concatenar(ListaSE<T> otra){
        boolean res = false;
        int tam, i;
        if(!vacia()){
            ListaSE<T> ult = ultimo(this); 
            while(!otra.vacia()){
                if(!buscar(otra.ini)){
                    ult.sig.ini = otra.ini;
                    ult.sig.sig = new ListaSE<T>();
                    otra = otra.sig;
                    ult = ult.sig;
                }else{
                    otra = otra.sig;
                }
            }
            res = true;
        }
        return res;
    }

    public ListaSE<ListaSE<T>> dividir(){
        ListaSE<ListaSE<T>> res = new ListaSE<ListaSE<T>>();
        if(!vacia()){
            ListaSE<T> aux1,aux2,q;
            aux1 = new ListaSE<T>(); aux2 = new ListaSE<T>();
            q = (ListaSE)this.clonar();
            while(!q.vacia()){
                T eliminado = q.eliminar(0);
                aux1.insertar(eliminado);
                if(!q.vacia()) {
                    eliminado = q.eliminar(0);
                    aux2.insertar(eliminado);
                }
            }
            res.insertar(aux1); res.insertar(aux2);
        }
        return res;
    }

    /**
     * Devuelve una representacion de String del contenido de la Lista
     */
    public String toString(){
        String reporte = "[";
        if(vacia()){
            reporte += "";
        }else{
            reporte += ini.toString();
            if(sig.ini!=null){
                reporte += ",";
                ListaSE<T> aux= sig;
                while(aux.sig != null){
                    reporte += " "+aux.ini.toString();
                    aux=aux.sig;
                    if(aux.sig!=null) reporte += ",";
                }
            }
        }
        return reporte + "]";
    }
}
