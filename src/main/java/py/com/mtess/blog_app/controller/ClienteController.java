package py.com.mtess.blog_app.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import py.com.mtess.blog_app.dto.ClienteDTO;
import py.com.mtess.blog_app.service.ClienteService;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@CrossOrigin(origins = "http://localhost:3000")
public class ClienteController {
    @Autowired
    private ClienteService service;

    // Endpoint para obtener clientes
    @GetMapping
    public ResponseEntity<List<ClienteDTO>> obtenerClientes() {
        System.out.println("ClienteController.obtenerClientes");
        List<ClienteDTO> clientes = service.obtenerClientes();
        System.out.println("ClienteController.obtenerClientes.clientes " + clientes);
        return ResponseEntity.ok(clientes);
    }

    // Endpoint para crear cliente
    @PostMapping
    public ResponseEntity<ClienteDTO> crearCliente(@Valid @RequestBody ClienteDTO clienteDTO) {
        System.out.println("ClienteController.crearCliente " + clienteDTO);
        ClienteDTO nuevoCliente = service.crearCliente(clienteDTO);
        return new ResponseEntity<>(nuevoCliente, HttpStatus.CREATED);
    }

    // Endpoint para obtener un cliente por ID
    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> obtenerClientePorId(@PathVariable Long id) throws Exception {
        ClienteDTO cliente = service.obtenerClientePorId(id);
        return ResponseEntity.ok(cliente);
    }

    // Actualizar cliente existente
    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> actualizarCliente(@PathVariable Long id, @Valid @RequestBody ClienteDTO clienteDTO) throws Exception {
        ClienteDTO clienteActualizado = service.actualizarCliente(id, clienteDTO);
        return ResponseEntity.ok(clienteActualizado);
    }

    // Eliminar cliente
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable Long id) throws Exception {
        service.eliminarCliente(id);
        return ResponseEntity.noContent().build();
    }


}
