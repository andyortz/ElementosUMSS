package estDat.estDatLin;
/**
 * Lista que permite almacenar datos de tipo generico <T>
 * 
 * @author Slend
 * @version 2.1
   */
public interface Lista<T>{
    /**
     * permite saber el estado actual de la lista
     */
    public boolean vacia();
    
    /**
     * insertar el dato al final de la lista
     * @param dato a insertar de clase T
     * @return nada
     */
    public void insertar(T dato);
    
    /**
     * inserta dato en la posicion pos, si ya hay un dato en esa posicion lo recorre una posicion
     * @param dato de clase T a insertar
     * @param pos en la que se quiere insertar pos>=0 entero
     * @return nada
     */
    public void insertar(T dato, int pos);
    
    public void insertarIni(T dato);
    
    /**
     * inserta toda una Lista al final de la Lista original
     * @param Lista de clase T a insertar en la Lista original
     * @return nada
     */
    public void insertarTodo(Lista<T> lista);
    
    /**
     * elimina el dato igual al dato que cumple la igualdad del dato de parametro, en caso de existir y ademas te devuelve el dato eliminado
     * @param dato a buscar de clase T 
     * @return el dato eliminado si existiera
     */
    public T eliminar(T dato);
    
    /**
     * elimina las ocurrencias del dato en caso de existir y ademas te devuelve el dato eliminado
     * @param dato a buscar de clase T 
     * @return nada
     */
    public void eliminarTodas(T dato);
    
    /**
     * elimina el dato en la posicion pos, en caso de existir y ademas te devuelve el dato eliminado
     * @param pos es un entero positivo inlucido el 0
     * @return el dato eliminado si existiera
     */
    public T eliminar(int pos);
    
    public T eliminarUlt();
    
    /**
     * elimina todos los elementos de la lista
     */
    public void vaciar();
    
    /**
     * Te devuelve el dato en la posicion posicion pos, en caso de existir
     * @param pos es un entero positivo incluido el 0
     * @return el dato al que se desea acceder si existiera
     */
    public T acceder(int pos);
    
    /**
     * Devuelve el dato que cumple la igual del dato de parametro, en caso de existir.
     * @param dato a acceder de clase T
     * @return el dato al que se desea acceder si existiera
     */
    public T acceder(T dato);
    
    /**
     * @param enteros positivos incluido el 0
     * @return la Lista desde una posicion hasta otra
     */
    public Lista<T> acceder(int desde, int hasta);
    
    /**
     * busca el dato, en caso de existir devuelve true
     * @param dato a buscar de clase T
     */
    public boolean buscar(T dato);
    
    /**
     * busca el dato
     * @param dato a buscar de clase T
     * @return posicion del dato en caso de existir, -1 en caso de no existir
     */
    public int indiceDe(T dato);
    
    /**
     * permite saber la longitud de la Lista
     */
    public int longitud();
    
    /**
     * busca el dato en la posicion i por el dato en la posicion j
     * @param enteros positivos inluidos el 0
     * @return true si ambos datos existen, false si al menos uno de los datos no existe
     */
    public boolean intercambiar(int i, int j);
    
    /**
     * reemplaza el dato de la posicion pos
     * @param dato de clase T, que reemplazara al dato de la posicion pos, en caso de no existir lo añade al final de la lista,
     *        pos entero positivo incluido el 0
     * @return nada
     */
    public void reemplazar(T dato, int pos);
    
    /**
     * compara dos datos ubicados en las posiciones pos1 y pos2
     * @param enteros positivos incluidos el 0 < longitud de la Lista
     * @return true si los datos de clase T en las posiciones pos son iguales, false si no lo son y ademas si uno de las posiciones no existen
     */
    public boolean compararDatos(int pos1, int pos2);
    
    /**
     * busca todas las ocurrencias del dato en caso de existir
     * @param dato de clase T a buscar
     * @return la cant de ocurrencias del dato en caso de exitir, 0 en caso de no existir
     */
    public int contarOcurrencias(T dato);
    
    /**
     * compara la Lista original con otra lista
     * @param Lista de clase T
     * @return true si ambas listas son iguales en cada posicion
     */
    public boolean equals(Lista<T> otraLista);
    
    /**
     * te devuelve una Lista igual a la Lista original
     */
    public Lista<T> clonar();
    
    /**
     * revierte la Lista 
     */
    public void invertir();
    
    /**
     * Devuelve una representacion de String del contenido de la Lista
     */
    public String toString();
}
