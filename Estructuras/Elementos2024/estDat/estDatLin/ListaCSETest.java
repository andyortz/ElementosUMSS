package estDat.estDatLin;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;  

/**
 * The test class ListaCSETest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class ListaCSETest
{
    /**
     * Default constructor for test class ListaCSETest
     */
    public ListaCSETest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }
    
    /**
     * indica si la lista esta vacia
     * @param ninguni
     * @return un resultado logico true/false
     */
    @Test
    public void testVacia(){
        ListaCSE<Character> lista;
        lista = new ListaCSE<Character>();
        //assertEquals(valoresperado, comportamientoObjeto)
        assertEquals( true , lista.vacia());
    }
    
    /**
     * permite insertar un dato al final de la lista
     * @param dato que es el dato a insertar
     * @return inserta el dato al final de la lista
     */
    @Test
    public void testInsertar1(){
        ListaCSE<Character> lista;
        lista = new ListaCSE<Character>(); 
        lista.insertar(new Character('a'));
        assertEquals(false   , lista.vacia());
        
    }
    
    /**
     * elimina el dato que esta en la posicion pos y delvuelve el dato eliminado
     * si no existe la posicion pos retorna null
     * @param pos que es la posicion del dato a eliminar, debe ser entero positivo incluido el 0
     * @return el dato eliminado o null
     */
    @Test
    public void testEliminar(){
        ListaCSE<Character> lista;
        lista = new ListaCSE<Character>(); 
        
        assertEquals(true,    lista.vacia());
        assertEquals(null,    lista.eliminar(0));
        
        lista.insertar(new Character('a'));
        assertEquals(new Character('a')  , lista.eliminar(1)); 
        assertEquals(true  , lista.vacia()); 
        assertEquals(null  , lista.eliminar(0)); 
        assertEquals(true  , lista.vacia()); 
    }

    /**
     * elimina el dato delvuelve el dato eliminado
     * si no existe el dato retorna null
     * @param dato a eliminar
     * @return el dato eliminado o null
     */
    @Test
    public void testEliminar1(){
        ListaCSE<Character> lista;
        lista = new ListaCSE<Character>(); 
        
        assertEquals(true,    lista.vacia());
        assertEquals(null,    lista.eliminar(0));
        
        lista.insertar(new Character('a'));
        assertEquals(null   , lista.eliminar(new Character('b'))); 
        assertEquals(false  , lista.vacia()); 
        assertEquals(new Character('a')  , lista.eliminar(new Character('a'))); 
        assertEquals(true  , lista.vacia()); 
    }

    /**
     * permite acceder al dato de la posicion pos, si la posicio pos no existe retorna null
     * @param pos es la posicion del dato a acceder, es un entero positivo incluido el 0
     * @return el dato de la posicion requerida o null
     */
    @Test
    public void testAcceder1(){
        ListaCSE<Character> lista;
        lista = new ListaCSE<Character>(); 
        assertEquals(true,    lista.vacia());
        assertEquals(null,    lista.acceder(0));
        
        lista.insertar(new Character('a'));
        
        assertEquals(new Character('a')  , lista.acceder(0)); 
        assertEquals(false  , lista.vacia()); 
        
    }

    /**
     * insertar una dato en la posicion pos, si la posicion no existe y no es posible crearla,
     * no inserta el dato
     * @param dato que es el dato a insertar
     * @param pos que es la posicion en la que se va a insertar, es un entero positivo incluido el 0
     * @return inserta el dato si es posible
     */
    @Test
    public void testInsertar2(){
        ListaCSE<Character> lista;
        lista = new ListaCSE<Character>(); 
        
        assertEquals(true   , lista.vacia());
        lista.insertar(new Character('a'), 10);
        assertEquals(false   , lista.vacia());
        lista.insertar(new Character('b'), 0);
        assertEquals(false   , lista.vacia());
        assertEquals(new Character('b'),lista.acceder(0));
        
    }
    
    
    
    
    
    /**
     * busca el dato en la lista
     * @param dato que el dato a buscar
     * @return un valor boolean que indica si el dato esta o no
     */
    @Test
    public void testBuscar(){
        ListaCSE<Character> lista;
        lista = new ListaCSE<Character>(); 
        assertEquals(true,    lista.vacia());
        assertEquals(false,    lista.buscar(new Character('a')));
        lista.insertar(new Character('a'));
        lista.insertar(new Character('b'));
        lista.insertar(new Character('e'));
        assertEquals(false,    lista.buscar(new Character('d')));
        assertEquals(true,    lista.buscar(new Character('e')));
        
    }
    
    /**
     * encuentra la posicion del dato si el dato pertence a la lista,
     * caso contrario devuelve -1
     * @param dato a buscar
     * @return indice del dato o -1
     */
    @Test
    public void testIndiceDe(){
        ListaCSE<Character> lista;
        lista = new ListaCSE<Character>(); 
        assertEquals(true,    lista.vacia());
        
        lista.insertar(new Character('a'));
        lista.insertar(new Character('b'));
        lista.insertar(new Character('e'));
        lista.insertar(new Character('p'));
        lista.insertar(new Character('q'));
        lista.insertar(new Character('s'));
        assertEquals(1, lista.indiceDe(new Character('b')));
        assertEquals(3, lista.indiceDe(new Character('p')));
    }

    /**
     * calcula la cantida de datos que  tiene la lista
     * @param nada
     * @return la longitud de  la lista
     */
    @Test
    public void testLongitud(){
        ListaCSE<Character> lista;
        lista = new ListaCSE<Character>(); 
        assertEquals(true,    lista.vacia());
        
        lista.insertar(new Character('a'));
        lista.insertar(new Character('b'));
        lista.insertar(new Character('e'));
        lista.insertar(new Character('p'));
        lista.insertar(new Character('q'));
        lista.insertar(new Character('s'));
        
        assertEquals(6, lista.longitud());
    }


    /**
     * modifica el dato de la posicion pos con el dato enviado
     * @param pos que indica la posicion a modificar
     * @param dato el nuevo dato
     * @return modifica el dato de la posicion pos, si es posible
     */
    @Test
    public void testSet(){
        ListaCSE<Character> lista;
        lista = new ListaCSE<Character>(); 
        assertEquals(true,    lista.vacia());
        lista.reemplazar(new Character('a'), 10);
        lista.insertar(new Character('a'));
        lista.insertar(new Character('b'));
        lista.insertar(new Character('e'));
        lista.reemplazar(new Character('f'), 1);

        assertEquals(true,    lista.buscar(new Character('f')));
        assertEquals(false,    lista.buscar(new Character('b')));
    }
    
    /**
     * intercambia datos de las posiciones si existieran
     * @param pos1, pos2
     * @return boolean informe del intercambio
     */
    @Test
    public void testIntercambiar(){
        ListaCSE<Character> lista;
        lista = new ListaCSE<Character>(); 
        lista.insertar(new Character('a'));
        lista.insertar(new Character('b'));
        lista.insertar(new Character('c'));
        lista.intercambiar(0,2);
        assertEquals(new Character('c'),lista.acceder(0));
        assertEquals(new Character('a'),lista.acceder(2));
    }

    /**
     * compara datos de las posiciones si existieran
     * @param pos1, pos2
     * @return boolean informe de la compararcion
     */
    @Test
    public void testCompararDatos(){
        ListaCSE<Character> lista;
        lista = new ListaCSE<Character>(); 
        
    }
    
    
    
    /**
     * permite generar una lista copia de la original
     * @param nada
     * @return una lista copia
     */
    @Test
    public void testClonar(){
        ListaCSE<Character> lista;
        lista = new ListaCSE<Character>(); 
        assertEquals(true,    lista.vacia());
        
        lista.insertar(new Character('a'));
        lista.insertar(new Character('b'));
        lista.insertar(new Character('e'));
        lista.insertar(new Character('p'));
        lista.insertar(new Character('q'));
        lista.insertar(new Character('s'));
        
        Lista<Character> lista1 = lista.clonar();
        assertEquals(true,    lista1.buscar(new Character('p')));
        assertEquals(true,    lista.buscar(new Character('p')));

        assertEquals(false,    lista1.vacia());
    }
    
    
    
    /**
     * permite eliminar todos los datos de la lista
     * @param nada
     * @return nada lista vacia
     */
    @Test
    public void testVaciar(){
        ListaCSE<Character> lista;
        lista = new ListaCSE<Character>(); 
        assertEquals(true,    lista.vacia());
        
        lista.insertar(new Character('a'));
        lista.insertar(new Character('b'));
        lista.insertar(new Character('e'));
        lista.insertar(new Character('p'));
        lista.insertar(new Character('q'));
        lista.insertar(new Character('s'));
        assertEquals(false, lista.vacia());
        lista.vaciar();
        assertEquals(true, lista.vacia());
    }


    
    
    /**
     * muestra los datos de la lista como una cadena
     * @param nada
     * @return la cadena que serializa la lista
     */
    @Test
    public void testToString(){
        ListaCSE<Character> lista;
        lista = new ListaCSE<Character>(); 
        assertEquals(true,    lista.vacia());
        
        lista.insertar(new Character('a'));
        lista.insertar(new Character('b'));
        lista.insertar(new Character('e'));
        lista.insertar(new Character('p'));
        lista.insertar(new Character('q'));
        lista.insertar(new Character('s'));
        assertEquals("[a, b, e, p, q, s]", lista.toString());
    }
    
    
    /**
     * invierte los datos de la lista
     * @param nada
     * @return lista invertida
     */
    @Test
    public void testInvertir(){
        ListaCSE<Character> lista;
        lista = new ListaCSE<Character>(); 
        assertEquals(true,    lista.vacia());
        
        lista.insertar(new Character('a'));
        lista.insertar(new Character('b'));
        lista.insertar(new Character('e'));
        lista.insertar(new Character('p'));
        lista.insertar(new Character('q'));
        lista.insertar(new Character('s'));
        lista.invertir();
        assertEquals(new Character('q'), lista.acceder(1));
    }
    
    
    
    
    /**
     * permite decidir si la lista es igual a la otraLista
     * @param otraLista lista con la que se va a comparar, debe tener datos del mismo tipo que la lista 
     * @return si son iguales  o no
     */
    @Test
    public void testEquals(){
        ListaCSE<Character> lista;
        lista = new ListaCSE<Character>(); 
        assertEquals(true,    lista.vacia());
        
        lista.insertar(new Character('a'));
        lista.insertar(new Character('b'));
        lista.insertar(new Character('e'));
        lista.insertar(new Character('p'));
        lista.insertar(new Character('q'));
        lista.insertar(new Character('s'));
        
        ListaCSE<Character> lista1;
        lista1 = new ListaCSE<Character>(); 
        
        assertEquals(false, lista.equals(lista1));

        lista1.insertar(new Character('a'));
        lista1.insertar(new Character('b'));
        lista1.insertar(new Character('e'));
        lista1.insertar(new Character('p'));
        lista1.insertar(new Character('q'));
        lista1.insertar(new Character('s'));
        assertEquals(true, lista.equals(lista1));

    }

    /**
     * permite insertar todos los datos la otraLista al final de la lista
     * @param otraLista lista de datos del mismo tipo que la lista
     * @return nada los datos se insertan al final
     */
    @Test
    public void testInsertar3(){
        ListaCSE<Character> lista;
        lista = new ListaCSE<Character>(); 
        assertEquals(true,    lista.vacia());
        
        lista.insertar(new Character('a'));
        lista.insertar(new Character('b'));
        lista.insertar(new Character('e'));
        lista.insertar(new Character('p'));
        lista.insertar(new Character('q'));
        lista.insertar(new Character('s'));
        
        ListaCSE<Character> lista1 = new ListaCSE<Character>();
        
        lista1.insertar(new Character('f'));
        lista1.insertar(new Character('h'));
        lista1.insertar(new Character('o'));
        lista1.insertar(new Character('m'));
        lista1.insertar(new Character('x'));
        lista1.insertar(new Character('w'));
        lista.insertarTodo(lista1);
        
        assertEquals(false,    lista1.buscar(new Character('p')));
        assertEquals(true,    lista.buscar(new Character('x')));

        
    }
}
