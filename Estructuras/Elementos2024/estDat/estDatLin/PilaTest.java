package estDat.estDatLin;


import static org.junit.Assert.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class PilaTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class PilaTest
{
    /**
     * Default constructor for test class PilaTest
     */
    public PilaTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @BeforeEach
    public void setUp()
    {
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @AfterEach
    public void tearDown()
    {
    }

    @Test
    public void testVacia(){
        Pila<Integer> pila = new Pila<>();
        assertEquals(true, pila.vacia());
    }
    
    @Test
    public void testInsertar(){
        Pila<Integer> pila = new Pila<>();
        assertEquals(true, pila.vacia());
        pila.push(new Integer(3));
        assertEquals(false, pila.vacia());
    }
    @Test
    public void testTop(){
        Pila<Integer> pila = new Pila<>();
        assertEquals(true, pila.vacia());
        assertEquals(null, pila.top());
        pila.push(new Integer(3));
        assertEquals(false, pila.vacia());
        assertEquals(new Integer(3), pila.top());
    }
}
