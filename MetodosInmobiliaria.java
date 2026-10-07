import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class MetodosInmobiliaria {

    public Queue<ObjCliente> LlenarCola(Queue<ObjCliente> cola, MetodosInmobiliaria m, Scanner sc) {
        boolean continuar = true;

        while (continuar) {
            ObjCliente o = new ObjCliente();
            o.setTurno(m.ValidarTurno(cola));

            System.out.println("Ingrese el documento/ID del interesado: ");
            o.setIdPersona(m.ValidarEntero(sc));
            o.setPropiedad(m.MenuPropiedades(sc));
            o.setHorario(m.MenuHorarios(sc));
            o.setEstate(1);

            cola.offer(o);

            System.out.println("Desea agregar otro interesado a la lista de espera? 1) Si, 2) No: ");
            int opt = m.ValidarEntero(sc);
            if (opt == 2) {
                continuar = false; 
            }
        }
        return cola;
    }

    public int ValidarTurno(Queue<ObjCliente> cola) {
        if (cola.isEmpty()) {
            return 1;
        }
        return cola.size() + 1;
    }

    public int MenuPropiedades(Scanner sc) {
        System.out.println("Seleccione la propiedad de interes:");
        System.out.println("1) Apartamento Poblado");
        System.out.println("2) Casa Laureles");
        System.out.println("3) Oficina Envigado");
        return sc.nextInt();
    }

    public int MenuHorarios(Scanner sc) {
        System.out.println("Seleccione el horario deseado:");
        System.out.println("1) 09:00 AM");
        System.out.println("2) 11:00 AM");
        System.out.println("3) 02:00 PM");
        System.out.println("4) 04:00 PM");
        return sc.nextInt();
    }

    public String NombrePropiedad(int opt) {
        switch (opt) {
            case 1:
                return "Apartamento Poblado";
            case 2:
                return "Casa Laureles";
            default:
                return "Oficina Envigado";
        }
    }

    public String NombreHorario(int opt) {
        switch (opt) {
            case 1:
                return "09:00 AM";
            case 2:
                return "11:00 AM";
            case 3:
                return "02:00 PM";
            default:
                return "04:00 PM";
        }
    }

    public String NombreEstado(int opt) {
        switch (opt) {
            case 1:
                return "En lista de espera";
            case 2:
                return "Visita realizada";
            case 3:
                return "Visita cancelada";
            case 4:
                return "Reemplazado por autorizado";
            default:
                return "Desconocido";
        }
    }

    public String MostrarLista(Queue<ObjCliente> cola, int optFiltro) {
        for (ObjCliente o : cola) {
            boolean mostrar = false;
            if (optFiltro == 1) { 
                mostrar = true;
            } else if (optFiltro == 2 && o.getEstate() == 1) {
                mostrar = true;
            } else if (optFiltro == 3 && o.getEstate() == 2) {
                mostrar = true;
            }

            if (mostrar) {
                System.out.println("Turno: " + o.getTurno());
                System.out.println("ID Persona: " + o.getIdPersona());
                System.out.println("Propiedad: " + NombrePropiedad(o.getPropiedad()));
                System.out.println("Horario: " + NombreHorario(o.getHorario()));
                System.out.println("Estado: " + NombreEstado(o.getEstate()));
                System.out.println("----------------------------------------- \n");
            }
        }
        return "Listado consultado con exito";
    }

    public Queue<ObjCliente> RealizarVisita(Queue<ObjCliente> cola) {
        for (ObjCliente o : cola) {
            if (o.getEstate() == 1) {
                System.out.println("Llamando al turno " + o.getTurno() + " (ID: " + o.getIdPersona() + ")");
                System.out.println(
                        "Propiedad: " + NombrePropiedad(o.getPropiedad()) + " a las " + NombreHorario(o.getHorario()));
                o.setEstate(2);
                System.out.println("Visita completada exitosamente.");
                return cola;
            }
        }
        System.out.println("No hay personas en lista de espera");
        return cola;
    }

    public Queue<ObjCliente> CancelarVisita(Queue<ObjCliente> cola, Scanner sc) {
        System.out.println("Ingrese el turno de la visita a cancelar: ");
        int turno = sc.nextInt();
        for (ObjCliente o : cola) {
            if (o.getTurno() == turno && o.getEstate() == 1) {
                o.setEstate(3); 
                System.out.println("La visita para el turno " + turno + " fue cancelada.");
                return cola;
            }
        }
        System.out.println("No se encontro un turno en espera con ese numero.");
        return cola;
    }

    public Queue<ObjCliente> CambiarHorario(Queue<ObjCliente> cola, MetodosInmobiliaria m, Scanner sc) {
        System.out.println("Ingrese el turno a cambiar de horario: ");
        int turno = sc.nextInt();
        for (ObjCliente o : cola) {
            if (o.getTurno() == turno && o.getEstate() == 1) {
                int nuevoHorario = m.MenuHorarios(sc);
                o.setHorario(nuevoHorario);
                System.out.println("Horario modificado a: " + m.NombreHorario(nuevoHorario));
                return cola;
            }
        }
        System.out.println("Turno no encontrado o no esta en lista de espera.");
        return cola;
    }

    public Queue<ObjCliente> ReemplazarPersona(Queue<ObjCliente> cola, Scanner sc) {
        System.out.println("Ingrese el turno del interesado a reemplazar: ");
        int turno = sc.nextInt();
        for (ObjCliente o : cola) {
            if (o.getTurno() == turno && o.getEstate() == 1) {
                System.out.println("Ingrese el documento/ID de la nueva persona autorizada: ");
                int nuevoId = sc.nextInt();
                o.setIdPersona(nuevoId);
                o.setEstate(4);
                System.out.println("Titular reemplazado por la persona autorizada con ID: " + nuevoId);
                return cola;
            }
        }
        System.out.println("El turno no existe o ya no esta activo en lista.");
        return cola;
    }

    


    public String MostrarPila(Stack<ObjCliente> p) {
        for (ObjCliente o : p) {
            System.out.println("Turno: " + o.getTurno());
            System.out.println("ID Titular/Autorizado: " + o.getIdPersona());
            System.out.println("Propiedad: " + NombrePropiedad(o.getPropiedad()));
            System.out.println("Horario: " + NombreHorario(o.getHorario()));
            System.out.println("Estado: " + NombreEstado(o.getEstate()));
            System.out.println("----------------------------------------- \n");
        }
        return "Pila mostrada correctamente";
    }

    public int ValidarEntero(Scanner sc) {
        while (!sc.hasNextInt()) {
            System.out.println("Por favor ingrese un numero valido: ");
            sc.next();
        }
        return sc.nextInt();
    }
}