package py.com.mtess.blog_app.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import py.com.mtess.blog_app.dto.ClienteDTO;
import py.com.mtess.blog_app.model.Cliente;
import py.com.mtess.blog_app.repository.ClienteRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository repository;

    private ClienteDTO convertToDto(Cliente cliente, ClienteDTO dto) {
        //System.out.println("ClienteService.convertToDto");
        //ClienteDTO dto = new ClienteDTO();
        dto.setId(cliente.getId());
        dto.setName(cliente.getName());
        dto.setEmail(cliente.getEmail());
        dto.setEmpresa(cliente.getEmpresa());
        dto.setPuesto(cliente.getPuesto());
        dto.setEstado(cliente.isEstado());
        return dto;
    }

    public List<ClienteDTO> obtenerClientes() {
        System.out.println("ClienteService.obtenerClientes");
        List<Cliente> clientes = repository.findAll();
        return clientes.stream()
                //.map(this::convertToDto)
                .map(cliente -> convertToDto(cliente, new ClienteDTO()))
                .collect(Collectors.toList());
    }

    // Método para crear cliente
    @Transactional
    public ClienteDTO crearCliente(ClienteDTO clienteDTO) {
        System.out.println("ClienteService.crearCliente");
        Cliente cliente = new Cliente();
        cliente.setName(clienteDTO.getName());
        cliente.setEmail(clienteDTO.getEmail());
        cliente.setEmpresa(clienteDTO.getEmpresa());
        cliente.setPuesto(clienteDTO.getPuesto());
        cliente.setEstado(clienteDTO.isEstado());

        Cliente clienteGuardado = repository.save(cliente);
        ClienteDTO clienteDTO1 = new ClienteDTO();
        return convertToDto(clienteGuardado, clienteDTO1);
    }

    public ClienteDTO obtenerClientePorId(Long id) throws Exception {
        ClienteDTO clienteDTO = new ClienteDTO();
        Cliente cliente = repository.findById(id)
                .orElseThrow(() -> new Exception("Cliente no encontrado con id: " + id));
        return convertToDto(cliente, clienteDTO);
    }

    @Transactional
    public ClienteDTO actualizarCliente(Long id, ClienteDTO clienteDTO) throws Exception {
        // 1. Buscar el cliente existente
        Cliente cliente = repository.findById(id)
                .orElseThrow(() -> new Exception("Cliente no encontrado con id: " + id));

        // 2. Actualizar los campos
        cliente.setName(clienteDTO.getName());
        cliente.setEmail(clienteDTO.getEmail());
        cliente.setEmpresa(clienteDTO.getEmpresa());
        cliente.setPuesto(clienteDTO.getPuesto());
        cliente.setEstado(clienteDTO.isEstado());

        // 3. Guardar los cambios
        Cliente clienteActualizado = repository.save(cliente);

        // 4. Convertir a DTO y retornar
        ClienteDTO clienteDTO1 = new ClienteDTO();
        return convertToDto(clienteActualizado, clienteDTO1);
    }

}
