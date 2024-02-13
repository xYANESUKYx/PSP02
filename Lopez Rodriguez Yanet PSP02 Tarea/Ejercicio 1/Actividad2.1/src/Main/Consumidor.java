package Main;

import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;

 /** @author yanet
 * Ejercicio 1 De igual manera a lo visto en el tema, ahora te proponemos un ejercicio del tipo productor-consumidor que mediante 
 * un hilo productor almacene datos (15 caracteres) en un búfer compartido, de donde los debe recoger un hilo consumidor (consume 
 * 15 caracteres). La capacidad del búfer ahora es de 6 caracteres, de manera que el consumidor podrá estar cogiendo caracteres 
 * del búfer siempre que éste no esté vacío. El productor sólo podrá poner caracteres en el búfer, cuando esté vacío o haya espacio.*/

public class Consumidor extends Thread{
 
    //DECLARAMOS LAS VARIABLES
    private Buffer buffer;
    private int consumido;
    private final int limite = 15;
    
    //CREAMOS EL CONSTRUCTOR
    public Consumidor (Buffer buffer){
        this.consumido = 0;
        this.buffer = buffer;
    }
    
    //CREAMOS EL MÉTODO run
    public void run(){
        //CREAMOS UN BUCLE while QUE SE EJECUTARÁ MIENTRAS consumido SEA MENOR A limite
        while(consumido < limite){
            try {
                //OBTENEMOS LA ULTIMA LETRA DEL BUFFER Y LA ALMACENAMOS EN LA VARIABLE letra
                char letra = buffer.consumir();
                //LE SUMAMOS 1 A LA VARIABLE consumido
                consumido++;
                //MOSTRAMOS POR PANTALLA LA LETRA QUE SE RECOGE
                System.out.println("Recogiendo el carácter " + letra + " del buffer");
                //HACEMOS UNA PAUSA QUE PUEDE DURAR DE 0 A 5000 MILISEGUNDOS
                sleep((long)(Math.random() * 5000));
            //SI SE PRODUCE UNA EXCEPCIÓN EL catch LO CAPTURA Y MUESTRA EL ERROR Y EL stack trace 
            } catch (InterruptedException ex) {
                Logger.getLogger(Productor.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
}
