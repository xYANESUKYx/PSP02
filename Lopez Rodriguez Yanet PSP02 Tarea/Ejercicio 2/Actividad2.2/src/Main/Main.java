package Main;

 /** @author yanet
 * Ejercicio 2 De igual manera a lo visto en el tema, ahora te proponemos que resuelvas el clásico problema denominado 
 * "La cena de los filósofos" utilizando la clase Semaphore del paquete java.util.concurrent. El problema es el siguiente: 
 * Cinco filósofos se sientan alrededor de una mesa y pasan su vida comiendo y pensando. Cada filósofo tiene un plato de 
 * arroz chino y un palillo a la izquierda de su plato. Cuando un filósofo quiere comer arroz, cogerá los dos palillos de 
 * cada lado del plato y comerá. El problema es el siguiente: establecer un ritual (algoritmo) que permita comer a los 
 * filósofos. El algoritmo debe satisfacer la exclusión mutua (dos filósofos no pueden emplear el mismo palillo a la vez), 
 * además de evitar el interbloqueo y la inanición.*/

public class Main {
    
    public static void main(String[] args) {
        //CREAMOS UN ENTERO CON EL NÚMERO DE FILOSOFOS
        int numFilosofos = 5;
        
        //CREAMOS UNA INSTANCIA DE LA CLASE Mesa PASÁNDOLE COMO PARÁMETRO EL NÚMERO DE FILÓSOFOS
        Mesa mesa = new Mesa(numFilosofos);
    
        //CREAMOS UN BUCLE for QUE EJECUTARÁ UN NÚMERO DE HILOS IGUAL A LA CANTIDAD DE FILOSOFOS
        for (int i = 1 ; i < numFilosofos + 1; i++) {
            //CREAMOS UNA INSTANCIA DE LA CLASE Filosofo PASÁNDOLE COMO PARÁMETRO LA INSTANCIA DE Mesa Y EL NÚMERO DE LA POSICIÓN DE CADA FILOSOFO
            Filosofo filosofo = new Filosofo(mesa, i);
            //EJECUTAMOS EL MÉTODO start DEL HILO  filosofo PARA INICIAR SU EJECUCIÓN
            filosofo.start();
        }
    }
    
}
