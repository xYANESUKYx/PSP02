package Main;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

 /** @author yanet
 * Ejercicio 1 De igual manera a lo visto en el tema, ahora te proponemos un ejercicio del tipo productor-consumidor que mediante 
 * un hilo productor almacene datos (15 caracteres) en un búfer compartido, de donde los debe recoger un hilo consumidor (consume 
 * 15 caracteres). La capacidad del búfer ahora es de 6 caracteres, de manera que el consumidor podrá estar cogiendo caracteres 
 * del búfer siempre que éste no esté vacío. El productor sólo podrá poner caracteres en el búfer, cuando esté vacío o haya espacio.*/

public class Productor extends Thread{
    
    //DECLARAMOS LAS VARIABLES
    private Buffer buffer;
    private final String letras = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private int producido;
    private final int limite = 15;
    
    //CREAMOS EL CONSTRUCTOR EL CUAL RECIBIRÁ COMO PARAMETRO UN OBJETO Buffer 
    public Productor (Buffer buffer){
        this.producido = 0;
        this.buffer = buffer;
    }
    
    //CREAMOS EL METODO RUN
    public void run(){
        //CREAMOS UN BUCLE while QUE SE EJECUTARÁ MIENTRAS producido SEA MENOR A limite
        while(producido < limite){
            try {
                //INSTANCIAMOS LA CLASE Random
                Random random = new Random();
                //GENERAMOS UN NÚMERO ALEATORIO PARA ESCOGER UNA LETRA DE NUESTRO String
                char letraAleatoria = letras.charAt((int)(random.nextInt(letras.length())));
                //LLAMAMOS A LA FUNCIÓN producir DE LA CLASE Buffer Y LE PASAMOS LA LETRA ALEATORIA COMO PARÁMETRO
                buffer.producir(letraAleatoria);
                //LE SUMAMOS 1 A LA VARIABLE producido
                producido++;
                //MOSTRAMOS POR PANTALLA LA LETRA DEPOSITADA EN EL BUFFER
                System.out.println("Depositando el cáracter " + letraAleatoria + " en el buffer");
                //HACEMOS UNA PAUSA QUE PUEDE DURAR DE 0 A 6000 MILISEGUNDOS
                sleep((long)(random.nextInt(6000)));
            //SI SE PRODUCE UNA EXCEPCIÓN EL catch LO CAPTURA Y MUESTRA EL ERROR Y EL stack trace 
            } catch (InterruptedException ex) {
                Logger.getLogger(Productor.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
    
}
