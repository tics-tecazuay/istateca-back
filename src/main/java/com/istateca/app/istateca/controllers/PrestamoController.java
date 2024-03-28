package com.istateca.app.istateca.controllers;

import com.istateca.app.istateca.models.Persona;
import com.istateca.app.istateca.models.Prestamo;
import com.istateca.app.istateca.services.BaseService;
import com.istateca.app.istateca.services.LibroService;
import com.istateca.app.istateca.services.PersonaService;
import com.istateca.app.istateca.services.PrestamoService;
import com.istateca.app.istateca.utilidades.NotificacionDevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping("/prestamo")
public class PrestamoController  extends BaseController<Prestamo> {

    /*
    controladores basicos estan en controlador general
    crear: /prestamo/crear -> pasar requestbody
    listar: /prestamo/listar
    buscar: /prestamo/buscar/{id} -> pasar PathVariable("id")
    editar: /prestamo/editar/{id} -> pasar requestbody
    */

    @Autowired
    private PrestamoService service;

    //@Autowired
    //private CarreraService carreraService;

    @Autowired
    private PersonaService personaService;

    @Autowired
    private LibroService libroService;

    @Override
    protected BaseService<Prestamo, Integer> getService() {
        return service;
    }

    @Override
    public ResponseEntity<Prestamo> crear(@RequestBody Prestamo prestamo) {
        Prestamo nuevoPrestamo = service.save(prestamo);
        if (nuevoPrestamo != null && nuevoPrestamo.getTipoPrestamo()!=3) {
            List<Persona> biblios = personaService.bibliotecarioDevice();
            if(biblios!=null){
                for (Persona biblio:biblios) {
                    NotificacionDevice.enviarNotificacion(biblio.getDevice(),"PRESTAMO SOLICITADO",nuevoPrestamo.mensaje(null));
                }
                System.out.println("Enviado a perosnas: "+biblios.size());
            }
            return ResponseEntity.ok(nuevoPrestamo);
        } else if(nuevoPrestamo != null && nuevoPrestamo.getTipoPrestamo()==3) {
            return ResponseEntity.ok(nuevoPrestamo);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/listarxestado")
    public ResponseEntity<List<Prestamo>> prestamosxestado(@RequestParam(value = "parametro") Integer parametro) {
        List<Prestamo> prestamos = service.prestamoxestadoprestamo(parametro);
        if (prestamos.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(prestamos);
        }
    }

    @GetMapping("/listarxcedula")
    public ResponseEntity<List<Prestamo>> prestamosxcedula(@RequestParam(value = "cedula") String cedula) {
        List<Prestamo> prestamos = service.prestamoxcedula(cedula);
        if (prestamos.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(prestamos);
        }
    }

    @GetMapping("/listarpasados")
    public ResponseEntity<List<Prestamo>> prestamospasados() {
        LocalDate localDate = LocalDate.now();
        Date date = java.sql.Date.valueOf(localDate);
        List<Prestamo> prestamos = service.prestamopasados(date);
        if (prestamos.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(prestamos);
        }
    }

    //endopint que devuelve un HashMap
    /*@GetMapping("/reporteprestamos")
    public ResponseEntity<?> reportePrestamo(@RequestParam(value = "inicio") String inicio,@RequestParam(value = "fin") String fin) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date finicio= new Date();
        Date ffin= new Date();
        try {
            finicio = dateFormat.parse(inicio);
            ffin = dateFormat.parse(fin);
            System.out.println(finicio + " "+ ffin);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Map<String, Object> reporte = new HashMap<>();
        Map<String, Object> estudiantes = new HashMap<>();
        Map<String, Object> docentes = new HashMap<>();
        int total = 0;
        if (inicio != null && fin != null) {
            for (int tipo = 1; tipo < 3; tipo++) {
                int total_tipo = 0;
                Map<String, Object> carreralistado = new HashMap<>();
                for (Carrera carrera : carreraService.findAll()) {
                    Map<String, Object> carrerasingular = new HashMap<>();
                    int total_carrera = 0;
                    Map<String, Object> prestamos = new HashMap<>();
                        int total_devuetos = 0;
                        int total_nodevuetos = 0;
                        List<String> libros_devueltos = new ArrayList<>();
                        List<String> libros_nodevueltos = new ArrayList<>();
                        for (Prestamo prestamo : service.reporteprestamosinestado(tipo, carrera.getId(), finicio, ffin)) {

                            if (prestamo.getEstadoPrestamo() == 4 || prestamo.getEstadoPrestamo() == 5) {
                                total++;
                                total_tipo++;
                                total_carrera++;
                                total_nodevuetos++;
                                if (!libros_nodevueltos.contains(prestamo.getLibro().getTitulo())) {
                                    libros_nodevueltos.add(prestamo.getLibro().getTitulo());
                                }
                            }
                            if (prestamo.getEstadoPrestamo() == 3 || prestamo.getEstadoPrestamo() == 6) {
                                total++;
                                total_tipo++;
                                total_carrera++;
                                total_devuetos++;
                                if (!libros_devueltos.contains(prestamo.getLibro().getTitulo())) {
                                    Map<String, Object> libroMap = new HashMap<>();
                                    libros_devueltos.add(prestamo.getLibro().getTitulo());
                                }
                            }
                        }
                        prestamos.put("total devueltos", total_devuetos);
                        prestamos.put("libros devueltos", libros_devueltos);
                        prestamos.put("total no devueltos", total_nodevuetos);
                        prestamos.put("libros no devueltos", libros_nodevueltos);

                    if (total_carrera>0) {
                        //carrerasingular.put("carrera",carrera.getNombre());
                        carrerasingular.put("prestamos", prestamos);
                        carrerasingular.put("total_carrera", total_carrera);
                        carreralistado.put(carrera.getNombre(),carrerasingular);
                    }
                }
                if (tipo == 1){
                    estudiantes.put("carreras", carreralistado);
                    estudiantes.put("total tipo",total_tipo);
                }
                else{
                    docentes.put("carreras", carreralistado);
                    docentes.put("total tipo", total_tipo);
                }
            }
            reporte.put("total",total);
            reporte.put("estudiantes", estudiantes);
            reporte.put("docentes", docentes);
        }
        if (reporte.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(reporte);
        }
    }*/

    @GetMapping("/prestamoconcarrera")
    public ResponseEntity<List<Prestamo>> prestamosreportecarrera
                (@RequestParam(value = "carreraId") Integer id,@RequestParam(value = "inicio") String inicio,@RequestParam(value = "fin") String fin) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date finicio= new Date();
        Date ffin= new Date();
        System.out.println(finicio+" "+ffin);
        try {
            finicio = dateFormat.parse(inicio);
            ffin = dateFormat.parse(fin);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        List<Prestamo> prestamos;
        if(id>0) prestamos = service.reporteprestamoconcarrera(id,finicio,ffin);
        else prestamos = service.reporteprestamosincarrera(finicio,ffin);
        if (prestamos.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(prestamos);
        }
    }

    @GetMapping("/habilitado")
    public ResponseEntity<String> habilitado(
            @RequestParam(value = "personaId") Integer personaId,@RequestParam(value = "libroId") Integer libroId) {

        boolean libroDisponible = libroService.Disponible(libroId);
        boolean personaHabilitada = personaService.Habilitado(personaId, 0);
        Integer conteoPrestamos = service.numeroPrestamosActivos(Arrays.asList(2,4,5),personaId);

        if (libroDisponible&&personaHabilitada&&conteoPrestamos<3)
            return ResponseEntity.ok("Habilitado");
        else{
            String mensaje="";
            if(!libroDisponible) mensaje+="Libro: No disponible;";
            if(!personaHabilitada) mensaje+="Persona: No Califica;";
            if(conteoPrestamos>2) mensaje+="Prestamos: Ya posee 3";
            return ResponseEntity.ok(mensaje);
        }
    }

    @GetMapping("/listaractivosxcedula")
    public ResponseEntity<List<Prestamo>> prestamosActivosxCedula(@RequestParam(value = "cedula") String cedula) {
        List<Prestamo> prestamos = service.prestamoActivoxCedula(cedula);
        if (prestamos.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(prestamos);
        }
    }

}
