
import java.util.Scanner;

/**
 *
 * @author cflv
 */
public class AlgoritmoDelBanquero {

    private int necesarios[][],
            asignados[][],
            maximos[][],
            disponibles[][],
            numeroProcesos,
            numeroRecursos;

    private void entrada() {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.print("Ingrese no. de los procesos y recursos : \n");
            numeroProcesos = sc.nextInt();  //no. de procesos
            numeroRecursos = sc.nextInt();  //no. de recursos
            necesarios = new int[numeroProcesos][numeroRecursos];  //inicializacion de arrays
            maximos = new int[numeroProcesos][numeroRecursos];
            asignados = new int[numeroProcesos][numeroRecursos];
            disponibles = new int[1][numeroRecursos];
            
            System.out.println("Introduzca matriz de asignados -->");
            for (int i = 0; i < numeroProcesos; i++) {
                for (int j = 0; j < numeroRecursos; j++) {
                    asignados[i][j] = sc.nextInt();  //matriz de asignados
                }
            }
            System.out.println("Introduzca matriz máxima -->");
            for (int i = 0; i < numeroProcesos; i++) {
                for (int j = 0; j < numeroRecursos; j++) {
                    maximos[i][j] = sc.nextInt();  //matriz maxima
                }
            }
            System.out.println("Introduzca matriz disponibles -->");
            for (int j = 0; j < numeroRecursos; j++) {
                disponibles[0][j] = sc.nextInt();  //matriz de disponibles
            }
        }
    }

    private int[][] calculoNecesarios() {
        for (int i = 0; i < numeroProcesos; i++) {
            for (int j = 0; j < numeroRecursos; j++) //calculando matriz de necesarios
            {
                necesarios[i][j] = maximos[i][j] - asignados[i][j];
            }
        }

        return necesarios;
    }

    private boolean chequear(int i) {
        //chequeando si todos los recursos para el proceso pueden ser asignados
        for (int j = 0; j < numeroRecursos; j++) {
            if (disponibles[0][j] < necesarios[i][j]) {
                return false;
            }
        }

        return true;
    }

    public void esSeguro() {
        entrada();
        calculoNecesarios();
        boolean done[] = new boolean[numeroProcesos];
        int j = 0;

        while (j < numeroProcesos) {  //hasta que todos los procesos se asignen
            boolean asignado = false;
            for (int i = 0; i < numeroProcesos; i++) {
                if (!done[i] && chequear(i)) {  //intentando asignar
                    for (int k = 0; k < numeroRecursos; k++) {
                        disponibles[0][k] = disponibles[0][k] - necesarios[i][k] + maximos[i][k];
                    }
                    System.out.println("Proceso asignado : " + i);
                    asignado = done[i] = true;
                    j++;
                }
            }
            if (!asignado) {
                break;  //si no esta asignado
            }
        }
        if (j == numeroProcesos) //si todos los procesos estan asignados
        {
            System.out.println("\nAsignado de forma segura");
        } else {
            System.out.println("Todos los procesos se pueden asignar de forma segura");
        }
    }
}
