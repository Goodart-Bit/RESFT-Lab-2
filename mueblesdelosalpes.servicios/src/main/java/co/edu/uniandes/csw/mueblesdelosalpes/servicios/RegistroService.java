/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mueblesdelosalpes.servicios;

import co.edu.uniandes.csw.mueblesdelosalpes.dto.Usuario;
import co.edu.uniandes.csw.mueblesdelosalpes.excepciones.OperacionInvalidaException;
import co.edu.uniandes.csw.mueblesdelosalpes.logica.interfaces.IServicioRegistroMockLocal;
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

/**
 *
 * @author juand
 */
@Path("/Registro")
@Stateless
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RegistroService {
 
    /**
     * Referencia al Ejb del catalogo encargada de realizar las operaciones del mismo.
     */
    @EJB
    private IServicioRegistroMockLocal registroEjb;
    

    @GET
    @Path("clientes/")
    public List<Usuario> getTodosLosClientes() {
        return registroEjb.darClientes();
    }
    
    @POST
    @Path("nuevoCliente/")
    public void crearCliente(Usuario nuevoUsuario) throws OperacionInvalidaException{
        registroEjb.registrar(nuevoUsuario);
    }
    
    @DELETE
    @Path("eliminarCliente/{login}")
    public void eliminarCliente(@PathParam("login") String username) throws OperacionInvalidaException{
        registroEjb.eliminarCliente(username);
    }
}
