package Main;

import java.util.concurrent.Semaphore;

 /** @author yanet
 * Ejercicio 2 De igual manera a lo visto en el tema, ahora te proponemos que resuelvas el clásico problema denominado 
 * "La cena de los filósofos" utilizando la clase Semaphore del paquete java.util.concurrent. El problema es el siguiente: 
 * Cinco filósofos se sientan alrededor de una mesa y pasan su vida comiendo y pensando. Cada filósofo tiene un plato de 
 * arroz chino y un palillo a la izquierda de su plato. Cuando un filósofo quiere comer arroz, cogerá los dos palillos de 
 * cada lado del plato y comerá. El problema es el siguiente: establecer un ritual (algoritmo) que permita comer a los 
 * filósofos. El algoritmo debe satisfacer la exclusión mutua (dos filósofos no pueden emplear el mismo palillo a la vez), 
 * además de evitar el interbloqueo y la inanición.*/

public class Mesa {
    //DECLARAMOS LAS VARIABLES
    private Semaphore[] palillos;

    //CREAMOS EL MÉTODO Mesa EL CUAL PEDIRÁ COMO ARGUMENTO EL NÚMERO DE PALILLOS
    public Mesa(int numPalillos){
        //CREAMOS UN NUEVO array Semaphore USANDO EL NÚMERO DE PALILLOS COMO LONGITIUD
        this.palillos = new Semaphore[numPalillos];
        
        //CREAMOS BUCLE for
        for (int i = 0; i < numPalillos; i++) {
            //SE CREA UN NUEVO Semaphore Y SE GUARDA EN LA POSICIÓN CORRESPONDIENTE
            this.palillos[i] = new Semaphore(1);
        }
    }
    
    //CREAMOS EL MÉTODO palilloI EL CUAL PEDIRÁ COMO PARÁMETRO UN ENTERO
    public int palilloI(int i) {
        //DEVOLBEMOS LA VARIABLE i
        return i;
    }
    
    //CREAMOS EL MÉTODO palilloD EL CUAL PEDIRÁ COMO PARÁMETRO UN ENTERO
    public int palilloD(int i) {
        //SI LA VARIABLE i ES IGUAL A 0 DEVOLVEMOS EL NÚMERO DE PALILLOS QUE HAY MENOS 1
        if(i == 0){
            return this.palillos.length - 1;
        //SI NO SE CUMPLE LA CONDICIÓN DEVOLVEMOS EL VALOR DE i MENOS 1
        }else{
            return i - 1;
        }
    }
    
    //CREAMOS EL MÉTODO cogerPalillo EL CUAL PEDIÁ EL FILÓSOFO COMO PARÁMETRO
    public void cogerPalillo(int filosofo) throws InterruptedException{
        //USAMOS acquire PARA QUE EL FILÓSOFO ADQUIERA EL PALILLO IZQUIERDO 
        this.palillos[this.palilloI(filosofo)].acquire();
        //USAMOS acquire PARA QUE EL FILÓSOFO ADQUIERA EL PALILLO DERECHO
        this.palillos[this.palilloD(filosofo)].acquire();
    }
    
    //CREAMOS EL MÉTODO dejarPalillo EL CUAL PEDIÁ EL FILÓSOFO COMO PARÁMETRO
    public void dejarPalillo(int filosofo){
        //USAMOS release PARA QUE EL FILÓSOFO LIBERE EL PALILLO IZQUIERDO 
        this.palillos[this.palilloI(filosofo)].release();
        //USAMOS release PARA QUE EL FILÓSOFO LIBERE EL PALILLO DERECHO
        this.palillos[this.palilloD(filosofo)].release();
    }
}
