package com.elopez.almacen.core.controllers;

import com.elopez.almacen.core.models.entity.Cliente;
import com.elopez.almacen.core.models.services.IClienteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
@Api(tags = "clientes")
public class ClienteRestController {

    private final IClienteService clienteService;

    public ClienteRestController(IClienteService clienteService){
        this.clienteService = clienteService;
    }

    @ApiOperation(value = "Listar Clientes", notes = "Servicio para listar los clientes")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Lista de clientes")})
    @GetMapping("/clientes")
    public List<Cliente> index() {
        return this.clienteService.findAll();
    }

    @ApiOperation(value = "Paginar listado de Clientes", notes = "Servicio para listar los clientes paginados")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Lista de clientes paginados")})
    @GetMapping("/clientes/page/{page}")
    public Page<Cliente> index(@PathVariable Integer page){
        Pageable pageable = PageRequest.of(page, 5);
        return clienteService.findAll(pageable);
    }

    @ApiOperation(value = "Buscar cliente por Id", notes = "Servicio para buscar clientes por codigo")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Cliente encontrado"),
            @ApiResponse(code = 404, message = "Cliente no encontrado")})
    @GetMapping("/clientes/{nit}")
    public ResponseEntity<?> show(@PathVariable String nit){
        Map<String, Object> response = new HashMap<>();
        Cliente clienteEncontrado = this.clienteService.findByNit(nit);
        if (clienteEncontrado == null){
            response.put("mensaje", "Cliente no encontrado");
            return  new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        return  new ResponseEntity<Cliente>(clienteEncontrado, HttpStatus.OK);
    }

    @ApiOperation(value = "Crear Cliente", notes = "Servicio para crear un cliente")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Cliente creado"),
            @ApiResponse(code = 400, message = "Existen errores en el ingreso de datos"),
            @ApiResponse(code = 500, message = "Error en el servidor al crear cliente")})
    @PostMapping("/clientes")
    public ResponseEntity<?> create (@Valid @RequestBody Cliente elemento, BindingResult result){
        Cliente nuevo = null;
        Map<String, Object> response = new HashMap<>();
        if (result.hasErrors()){
            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(err -> err.getDefaultMessage())
                    .collect(Collectors.toList());
            response.put("errors", errors);
            return  new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

        try {
            nuevo = this.clienteService.save(elemento);
        }catch (DataAccessException e){
            response.put("mensaje", "Error al realizar el insert en la base de datos");
            response.put("error", e.getMessage().concat(": ".concat(e.getMostSpecificCause().getMessage())));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje","El cliente ha sido creado con éxito");
        response.put("cliente", nuevo);
        return  new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @PutMapping("/clientes/{nit}")
    public  ResponseEntity<?> update (@Valid @RequestBody Cliente cliente, BindingResult result, @PathVariable String nit){

        Map<String, Object> response = new HashMap<>();
        Cliente update = this.clienteService.findByNit(nit);
        Cliente clienteUpdate = null;

        if (result.hasErrors()){
            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(err -> err.getDefaultMessage())
                    .collect(Collectors.toList());
            response.put("errors", errors);
            return  new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

        if (update == null){
            response.put("mensaje", "Error: no se puede editar el cliente ID "
                    + nit.toString()
                    + " no existe en la base de datos");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        try {
            update.setDpi(cliente.getDpi());
            update.setNombre(cliente.getNombre());
            update.setDireccion(cliente.getDireccion());
            clienteUpdate = this.clienteService.save(cliente);
        }catch (DataAccessException e){
            response.put("mensaje", "Error al actualizar los datos");
            response.put("error", e.getMessage().concat(": ".concat(e.getMostSpecificCause().getMessage())));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje","El cliente ha sido actualizado correctamente!!!");
        response.put("cliente", clienteUpdate);
        return  new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("clientes/{nit}")
    public ResponseEntity<?> delete (@PathVariable String nit){
        Map<String, Object> response = new HashMap<>();
        try{
            this.clienteService.delete(nit);
        }catch (DataAccessException e){
            response.put("mensaje", "Error al eliminar el cliente en la base datos");
            response.put("error", e.getMessage().concat(": ".concat(e.getMostSpecificCause().getMessage())));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje","El cliente ha sido eliminado correctamente!!!");
        return  new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }
}