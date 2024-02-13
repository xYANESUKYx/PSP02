package Main;

import java.util.logging.Level;
import java.util.logging.Logger;

 /** @author yanet
 * Ejercicio 1 De igual manera a lo visto en el tema, ahora te proponemos un ejercicio del tipo productor-consumidor que mediante 
 * un hilo productor almacene datos (15 caracteres) en un búfer compartido, de donde los debe recoger un hilo consumidor (consume 
 * 15 caracteres). La capacidad del búfer ahora es de 6 caracteres, de manera que el consumidor podrá estar cogiendo caracteres 
 * del búfer siempre que éste no esté vacío. El productor sólo podrá poner caracteres en el búfer, cuando esté vacío o haya espacio.*/

public class Main {
    public static void main(String[] args){
        //CREAMOS UNA VARIABLE CON LA CAPACIDAD DEL BUFFER
        int capacidad = 6;
        
        try {
            //CREAMOS UNA INSTANCIA DE LA CLASE Buffer PASÁNDOLE LA CAPACIDAD COMO ARGUMENTO
            Buffer buffer = new Buffer(capacidad);
            //CREAMOS UNA INSTANCIA DE LA CLASE Productor PASÁNDOLE buffer COMO ARGUMENTO
            Productor productor = new Productor(buffer);
            //CREAMOS UNA INSTANCIA DE LA CLASE Consumidor PASÁNDOLE buffer COMO ARGUMENTO
            Consumidor consumidor = new Consumidor(buffer);
            
            //LLAMAMOS AL MÉTODO start DE LA CLASE Productor
            productor.start();
            //PAUSAMOS LA EJECUCIÓN DURANTE 5 SEGUNDOS
            Thread.sleep(5000);
            //LLAMAMOS AL MÉTODO start DE LA CLASE Consumidor
            consumidor.start();
            
            //ESPERAMOS A QUE EL HILO DE Productor TERMINE ANTES DE CONTINUAR
            productor.join();
            //ESPERAMOS A QUE EL HILO DE Consumidor TERMINE ANTES DE CONTINUAR
            consumidor.join();
            //MOSTRAMOS POR PANTALLA QUE EL PROGRAMA HA FINALIZADO
            System.out.println("Programa finalizado");
            
        //SI SE PRODUCE UNA EXCEPCIÓN EL catch LO CAPTURA Y MUESTRA EL ERROR Y EL stack trace 
        } catch (InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}