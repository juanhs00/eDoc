package es.upm.dit.isst.edocweb.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import es.upm.dit.isst.edocweb.model.Asignatura;
import es.upm.dit.isst.edocweb.model.Valoracion;

@Controller

public class AsignaturaController {

        public final String ASIGNATURAMANAGER_STRING= "http://localhost:8083/asignaturas/";

        public final String VALORACIONMANAGER_STRING= "http://localhost:8083/valoraciones/";

        public static final String VISTA_LOGIN = "login";

        public static final String VISTA_LISTA = "lista-asignaturas-profesor";

        public static final String VISTA_LISTA_VALORACIONES = "lista-valoraciones";

        public static final String VISTA_FORMULARIO = "formulario";

        public static final String VISTA_VALORACION = "formulario_valoracion";

        public static final String VISTA_ASIGNATURA = "formulario_asignatura";

        private RestTemplate restTemplate = new RestTemplate();
        
        @GetMapping("/")

        public String inicio() {

                return "redirect:/" + VISTA_LISTA;

        }


       @GetMapping("/login")

        public String login() {

                return "redirect:/" + VISTA_LISTA;

        }


       @GetMapping("/lista-asignaturas-profesor")

        public String lista(Model model, Principal principal) {

                List<Asignatura> lista = new ArrayList<Asignatura>();

        
                if (principal == null || principal.getName().equals("") || principal.getName().equals("admin@upm.es") || principal.getName().contains("@alumnos.upm.es"))

                        lista = Arrays.asList(restTemplate.getForEntity(ASIGNATURAMANAGER_STRING,

                                           Asignatura[].class).getBody());

                        
                else if (principal.getName().contains("@upm.es"))

                        lista = Arrays.asList(restTemplate.getForEntity(ASIGNATURAMANAGER_STRING

                                    + "profesor/" + principal.getName()

                                              ,Asignatura[].class).getBody());

                /*else if (principal.getName().contains("@alumnos.upm.es")){

                        lista = Arrays.asList(restTemplate.getForEntity(ASIGNATURAMANAGER_STRING

                                      + "alumno/" + principal.getName()

                                              ,Asignatura[].class).getBody());
                    
                }*/

                model.addAttribute("asignaturas", lista);

                return VISTA_LISTA;

        }

        @GetMapping("/crear")

        public String crear(Map<String, Object> model, Principal principal) {

            if (principal.getName().equals("admin@upm.es")) {

                Asignatura Asignatura = new Asignatura();

                model.put("Asignatura", Asignatura);

                model.put("accion", "guardar");
                
                return VISTA_ASIGNATURA;
            }
            return "redirect:/" + VISTA_LISTA;
        }
       

        @PostMapping("/guardar")

        public String guardar(@Validated Asignatura asignatura, BindingResult result) {

                if (result.hasErrors()) {

                        return VISTA_ASIGNATURA;

                }
                                
                try { restTemplate.postForObject(ASIGNATURAMANAGER_STRING, asignatura, Asignatura.class);

                } catch(Exception e) {}

                return "redirect:" + VISTA_LISTA;

        }

        @GetMapping("/editar/{id}")

        public String editar(@PathVariable(value = "id") String id,

                   Map<String, Object> model, Principal principal) {

                if (principal.getName().equals("admin@upm.es")){      

                    Asignatura asignatura = null;
    
                    try { asignatura = restTemplate.getForObject(ASIGNATURAMANAGER_STRING + id, Asignatura.class);
    
                    } catch (HttpClientErrorException.NotFound ex) {}
    
                    model.put("asignatura", asignatura);
    
                    model.put("accion", "../actualizar"); 
    
                    return asignatura != null ? VISTA_FORMULARIO : "redirect:/" + VISTA_LISTA;
            }
            return "redirect:/" + VISTA_LISTA;

        }

        @PostMapping("/actualizar")

        public String actualizar(@Validated Asignatura asignatura, BindingResult result) {

                if (result.hasErrors()) {

                        return VISTA_FORMULARIO;

                }

                try { restTemplate.put(ASIGNATURAMANAGER_STRING,// + asignatura.getEmail(), //Aqui de quien es el email??

                           asignatura, Asignatura.class);

                } catch(Exception e) {}

                return "redirect:" + VISTA_LISTA;

        }

        @GetMapping("/eliminar/{id}")

        public String eliminar(@PathVariable(value = "id") String id, Principal principal) {
            
            if (principal.getName().equals("admin@upm.es")){ 

                restTemplate.delete(ASIGNATURAMANAGER_STRING+ id);
            }

                return "redirect:/" + VISTA_LISTA;

        }


        @GetMapping("/lista-valoraciones/{id}")

        public String listaValoraciones(@PathVariable String id, Model model, Principal principal) { //AQUI HAY QUE FILTRAR LAS VALORACIONES

                List<Valoracion> lista = new ArrayList<Valoracion>();
                List<Valoracion> result = new ArrayList<Valoracion>();
                System.out.println(id);
        
                if (principal == null || principal.getName().equals("") || principal.getName().equals("admin@upm.es") || principal.getName().contains("@alumnos.upm.es")){

                        lista = Arrays.asList(restTemplate.getForEntity(VALORACIONMANAGER_STRING ,

                                           Valoracion[].class).getBody());
                        
                        for (Valoracion v: lista){
                                if(v.getNombreAsignatura().equals(id)){
                                        result.add(v);
                                }
                        }

                }
                        
                else if (principal.getName().contains("@upm.es")) {

                        lista = Arrays.asList(restTemplate.getForEntity(VALORACIONMANAGER_STRING

                                    + "profesor/" + principal.getName()

                                              ,Valoracion[].class).getBody());
                         for (Valoracion v: lista){
                                if(v.getNombreAsignatura().equals(id)){
                                        result.add(v);
                                }
                        }
                }

                /*else if (principal.getName().contains("@alumnos.upm.es")){

                        lista = Arrays.asList(restTemplate.getForEntity(ASIGNATURAMANAGER_STRING

                                      + "alumno/" + principal.getName()

                                              ,Asignatura[].class).getBody());
                    
                }*/
              
                model.addAttribute("valoraciones", result);
                
                return VISTA_LISTA_VALORACIONES;

        }



        @GetMapping("/crearval")

        public String crearval(Map<String, Object> model, Principal principal) {
        //public String crearval(Map<String, Object> model) {
            if (principal.getName().equals("admin@upm.es") || principal.getName().contains("@alumnos.upm.es")){ 
                
                Valoracion valoracion = new Valoracion();

                model.put("valoracion", valoracion);

                model.put("accion", "guardarval");
                System.out.println("LLEGO HASTA AQUI");
                return VISTA_VALORACION;
            }
            return  VISTA_LISTA_VALORACIONES;

        }

        @PostMapping("/guardarval")

        public String guardar(@Validated Valoracion valoracion, BindingResult result) {

                if (result.hasErrors()) {

                        return VISTA_VALORACION;

                }

               try {restTemplate.postForObject(VALORACIONMANAGER_STRING, valoracion, Valoracion.class);

                } catch(Exception e) {}

                return "redirect:" + VISTA_LISTA_VALORACIONES + "/" + valoracion.getNombreAsignatura();

        }

        @GetMapping("/editarval/{id}")

        public String editarval(@PathVariable(value = "id") String id,

                   Map<String, Object> model, Principal principal) {

                if (principal == null || ! principal.getName().equals(id))

                        return "redirect:/" + VISTA_LISTA_VALORACIONES;

                Valoracion valoracion = null;

                try { valoracion = restTemplate.getForObject(VALORACIONMANAGER_STRING + id, Valoracion.class);

                } catch (HttpClientErrorException.NotFound ex) {}

                model.put("valoracion", valoracion);

                model.put("accion", "../actualizarval"); 

                return valoracion != null ? VISTA_VALORACION : "redirect:/" + VISTA_LISTA_VALORACIONES;

        }

        @PostMapping("/actualizarval")

        public String actualizarval(@Validated Valoracion valoracion, BindingResult result) {

                if (result.hasErrors()) {

                        return VISTA_LISTA_VALORACIONES;

                }

                try { restTemplate.put(VALORACIONMANAGER_STRING + valoracion.getEmail(),

                           valoracion, Valoracion.class);

                } catch(Exception e) {}

                return "redirect:" + VISTA_VALORACION;

        }

        @GetMapping("/eliminarval/{id}")

        public String eliminarval(@PathVariable(value = "id") Integer id, Principal principal) {

            //if (principal == null || ! principal.getName().equals(id)){


                //return "redirect:/" + VISTA_LISTA_VALORACIONES;}
                List<Valoracion> lista = new ArrayList<Valoracion>();
                lista = Arrays.asList(restTemplate.getForEntity(VALORACIONMANAGER_STRING ,  Valoracion[].class).getBody());
                String asignatura = "";
                for(Valoracion v: lista){
                        if(v.getId() == id){
                                asignatura=v.getNombreAsignatura();
                        }
                }
                if (principal.getName().equals("admin@upm.es")){ 


                restTemplate.delete(VALORACIONMANAGER_STRING+ id);

                return "redirect:/" + VISTA_LISTA_VALORACIONES + "/" + asignatura;
                }
                
                return "redirect:/" + VISTA_LISTA_VALORACIONES + "/" + asignatura;

                }
                

     }