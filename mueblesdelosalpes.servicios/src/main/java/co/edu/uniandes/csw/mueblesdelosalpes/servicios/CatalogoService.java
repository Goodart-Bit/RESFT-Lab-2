/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.edu.uniandes.csw.mueblesdelosalpes.servicios;

import co.edu.uniandes.csw.mueblesdelosalpes.dto.Mueble;
import co.edu.uniandes.csw.mueblesdelosalpes.logica.interfaces.IServicioCatalogoMockLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
 
@Path("/Catalogo")
@Stateless
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CatalogoService {
 
    /**
     * Referencia al Ejb del catalogo encargada de realizar las operaciones del mismo.
     */
    @EJB
    private IServicioCatalogoMockLocal catalogoEjb;
   
 
    /**
     * Servicio que ofrece una lista JSON con el catálogo de Muebles de los alpes (Los muebles disponibles para la venta).
     * @param nuevoMueble
     */
    
    @POST
    @Path("nuevoMueble/")
    public void agregarMueble(Mueble nuevoMueble){
        catalogoEjb.agregarMueble(nuevoMueble);
    }
    
    @DELETE
    @Path("remover/{muebleRef}")
    @Produces(MediaType.TEXT_PLAIN)
    public String eliminarMueble(@PathParam("muebleRef") Long ref){
        Mueble muebleTarget = catalogoEjb.darMueble(ref);
        catalogoEjb.eliminarMueble(ref);
        return muebleTarget.toString() + " HA SIDO ELIMINADO";
    }
    
    @DELETE
    @Path("removerEjemplar/{muebleRef}")
    @Produces(MediaType.TEXT_PLAIN)
    public String eliminarEjemplar(@PathParam("muebleRef") long ref){
        catalogoEjb.removerEjemplarMueble(ref);
        return "El mueble " + ref + " se ha borrado con exito";
    }
    
    @GET
    @Path("muebles/")
    public List<Mueble> getTodosLosMuebles() {
        return catalogoEjb.darMuebles();
    }
}
