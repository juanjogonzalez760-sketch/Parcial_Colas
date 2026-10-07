import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Menu {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Queue<ObjCliente> cola = new LinkedList<>();
        MetodosInmobiliaria m = new MetodosInmobiliaria();
        boolean continuar = true;

        while (continuar) {
            System.out.print("Seleccione una opcion: \n");
            System.out.println("1 Registrar interesado a lista de espera");
            System.out.println("2 Mostrar toda la lista");
            System.out.println("3 Atender visita");
            System.out.println("4 Cambiar horario de visita");
            System.out.println("5 Reemplazar interesado por persona autorizada");
            System.out.println("6 Cancelar visita");
            System.out.println("7 Salir");

            int opt = m.ValidarEntero(sc);
            switch (opt) {
                case 1:
                    cola = m.LlenarCola(cola, m, sc);
                    break;
                case 2:
                    System.out.println("\n" + m.MostrarLista(cola, 1));
                    break;
                case 3:
                    cola = m.RealizarVisita(cola);
                    break;
                case 4:
                    cola = m.CambiarHorario(cola, m, sc);
                    break;
                case 5:
                    cola = m.ReemplazarPersona(cola, sc);
                    break;
                case 6:
                    cola = m.CancelarVisita(cola, sc);
                    break;
                case 7:
                    System.out.println("Hasta luego");
                    continuar = false;
                    break;
                default:
                    System.out.println("Esta opcion no existe");
                    break;
            }
        }
    }
}