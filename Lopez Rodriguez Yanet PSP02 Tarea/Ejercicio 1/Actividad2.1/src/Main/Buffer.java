package Main;

import java.util.logging.Level;
import java.util.logging.Logger;

 /** @author yanet
 * Ejercicio 1 De igual manera a lo visto en el tema, ahora te proponemos un ejercicio del tipo productor-consumidor que mediante 
 * un hilo productor almacene datos (15 caracteres) en un búfer compartido, de donde los debe recoger un hilo consumidor (consume 
 * 15 caracteres). La capacidad del búfer ahora es de 6 caracteres, de manera que el consumidor podrá estar cogiendo caracteres 
 * del búfer siempre que éste no esté vacío. El productor sólo podrá poner caracteres en el búfer, cuando esté vacío o haya espacio.*/

public class Buffer {
    //DECLARAMOS LAS VARIABLES
    private char[] buffer;
    private int sucesivo;
    private boolean completo, libre;
    
    //CREAMOS EL CONSTRUCTOR
    public Buffer(int longitud){
        this.buffer = new char[longitud];
        this.sucesivo = 0;
        this.completo = false;
        this.libre = true;
    }
    
    //CREAMOS EL MÉTODO consumir
    public synchronized char consumir(){
        //MIENTRAS QUE EL BUFFER TENGA ESPACIO LIBRE  
        while (this.libre) {
            try {
                //HACEMOS QUE EL HILO ACTUAL ESPERE HASTA QUE SE RECIBA UNA NOTIFICACIÓN
                wait();
            //SI SE PRODUCE UNA EXCEPCIÓN EL catch LO CAPTURA Y MUESTRA EL ERROR Y EL stack trace 
            } catch (InterruptedException ex) {
                Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        //RESTAMOS 1 A LA VARIABLE sucesivo
        this.sucesivo--;
        //PASAMOS LA VARIABLE completo A FALSE
        this.completo = false;
            
        //SI sucesivo ES IGUAL A CERO PASAMOS LA VARIABLE LIBRE A VERDADERO
        if (this.sucesivo == 0) {
            this.libre = true;
        }
         
        //NOTIFOCAMOS A LOS HILOS EN ESPERA QUE PUEDEN REANUDAR SU EJECUCION
        notifyAll();
           
        //DEVOLVEMOS EL VALOR DE LA POSICIÓN DE sucesivo DEL buffer
        return this.buffer[this.sucesivo];
    }
        
    //CREAMOS EL MÉTODO producir
    public synchronized void producir(char letra){
        //MIENTRAS QUE EL BUFFER NO TENGA ESPACIO LIBRE  
        while(this.completo){
            try {
                //HACEMOS QUE EL HILO ACTUAL ESPERE HASTA QUE SE RECIBA UNA NOTIFICACIÓN
                wait();
                //SI SE PRODUCE UNA EXCEPCIÓN EL catch LO CAPTURA Y MUESTRA EL ERROR Y EL stack trace 
            } catch (InterruptedException ex) {
                Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        //GUARDAMOS EN LA POSICIÓN sucesivo DE buffer EL VALOR DE letra
        this.buffer[this.sucesivo] = letra;
            
        //LE SUMAMOS 1 A LA VARIABLE sucesivo
        this.sucesivo++;
        //PASAMOS LA VARIABLE libre A FALSO
        this.libre = false;
            
        //SI sucesivo VALE LO MISMO QUE EL LARGO DE buffer
        if (this.sucesivo == this.buffer.length) {
            //PASAMOS LA VARIABLE completo A VERDADERO
            this.completo = true;
        }
        
        //NOTIFICAMOS A LOS HILOS EN ESPERA QUE PUEDEN REANUDAR SU EJECUCION
        notifyAll();
    }
}
