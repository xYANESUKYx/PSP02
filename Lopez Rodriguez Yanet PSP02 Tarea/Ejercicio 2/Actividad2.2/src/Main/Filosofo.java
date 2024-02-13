package Main;

import java.util.logging.Level;
import java.util.logging.Logger;

 /** @author yanet
 * Ejercicio 2 De igual manera a lo visto en el tema, ahora te proponemos que resuelvas el clásico problema denominado 
 * "La cena de los filósofos" utilizando la clase Semaphore del paquete java.util.concurrent. El problema es el siguiente: 
 * Cinco filósofos se sientan alrededor de una mesa y pasan su vida comiendo y pensando. Cada filósofo tiene un plato de 
 * arroz chino y un palillo a la izquierda de su plato. Cuando un filósofo quiere comer arroz, cogerá los dos palillos de 
 * cada lado del plato y comerá. El problema es el siguiente: establecer un ritual (algoritmo) que permita comer a los 
 * filósofos. El algoritmo debe satisfacer la exclusión mutua (dos filósofos no pueden emplear el mismo palillo a la vez), 
 * además de evitar el interbloqueo y la inanición.*/

public class Filosofo extends Thread{
    //DECLARAMOS LAS VARIABLES
    private Mesa mesa;
    private int filosofo;
    private int pFilosofo;
    
    //CREAMOS EL CONSTRUCTOR
    public Filosofo(Mesa mesa, int comensal){
        this.filosofo = comensal;
        this.pFilosofo = comensal - 1;
        this.mesa = mesa;
    }
    
    //CREAMOS EL MÉTODO run
    public void run(){
        //CREAMOS UN BUCLE while INFINITO
        while(true){
            try {
                //LLAMAMOS AL MÉTODO pensando
                this.pensando();
                //MOSTRAMOS POR PANTALLA QUE FILOSOFO ESTA HAMBRIENTO
                System.out.println("Filósofo " + this.filosofo + " Hambriento");
                //LLAMAMOS AL MÉTODO cogerPalillo PASANDOLE COMO PARÁMETRO LA POSICIÓN DEL FILÓSOFO
                mesa.cogerPalillo(this.pFilosofo);
                //LLAMAMOS AL MÉTODO COMIENDO
                this.comiendo();
                //MOSTRAMOS QUE FILÓSOFO TERMINA DE COMER Y QUE PALILLOS QUEDAN LIBRES
                System.out.println("Filósofo " + this.filosofo + " Termina de comer, Libres palillos: " + 
                        (this.mesa.palilloI(this.pFilosofo) + 1) + ", " + (this.mesa.palilloD(this.pFilosofo) + 1));
                //LLAMAMOS AL MÉTODO dejarPalillo PASANDOLE COMO PARÁMETRO LA POSICIÓN DEL FILÓSOFO
                mesa.dejarPalillo(this.pFilosofo);
            //SI SE PRODUCE UNA EXCEPCIÓN EL catch LO CAPTURA Y MUESTRA EL ERROR Y EL stack trace 
            } catch (InterruptedException ex) {
                Logger.getLogger(Filosofo.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
    
    //CREAMOS EL MÉTODO pensando
    public void pensando() throws InterruptedException{
        //MOSTRAMOS POR PANTALLA QUE FILOSOFO ESTA PENSANDO
        System.out.println("Filósofo " + this.filosofo + " Pensando");
        //DETENEMOS LA EJECUCIÓN DEL HILO DURANTE 2 SEGUNDOS
        Thread.sleep(2000);
    }
        
    //CREAMOS EL MÉTODO comiendo
    public void comiendo() throws InterruptedException{
        //MOSTRAMOS POR PANTALLA QUE FILOSOFO ESTA COMIENDO
        System.out.println("Filosofo " + this.filosofo + " Comiendo");
        //DETENEMOS LA EJECUCIÓN DEL HILO DURANTE 2 SEGUNDOS
        Thread.sleep(2000);
    }
    
}
