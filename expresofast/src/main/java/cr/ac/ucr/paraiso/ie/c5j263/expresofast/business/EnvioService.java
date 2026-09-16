package cr.ac.ucr.paraiso.ie.c5j263.expresofast.business;

import cr.ac.ucr.paraiso.ie.c5j263.expresofast.data.EnvioRepository;
import cr.ac.ucr.paraiso.ie.c5j263.expresofast.data.VehiculoRepository;
import cr.ac.ucr.paraiso.ie.c5j263.expresofast.domain.Envio;
import cr.ac.ucr.paraiso.ie.c5j263.expresofast.domain.Vehiculo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
public class EnvioService {

    private final EnvioRepository envioRepository;
    private final VehiculoRepository vehiculoRepository;

    public EnvioService(EnvioRepository envioRepository, VehiculoRepository vehiculoRepository) {
        this.envioRepository = envioRepository;
        this.vehiculoRepository = vehiculoRepository;
    }

    @Transactional(readOnly = true)
    public List<Envio> obtenerEnviosOptimizados() {
        return envioRepository.findAllWithDetails();
    }

    public Envio registrarEnvio(Envio envio) {
        if (envio.getVehiculo() != null && envio.getVehiculo().getId() != null) {
            Vehiculo vehiculo = vehiculoRepository.findById(envio.getVehiculo().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Vehículo no encontrado"));
            
            if (envio.getPesoKg().compareTo(vehiculo.getCapacidadKg()) > 0) {
                throw new IllegalArgumentException("El peso del envío supera la capacidad máxima del vehículo.");
            }
        }
        
        envio.setEstadoEnvio("PENDIENTE");
        return envioRepository.save(envio);
    }

    public Envio actualizarEstado(Integer id, Map<String, String> updates) {
        Envio envio = envioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Envío no encontrado"));
                
        if (updates.containsKey("estadoEnvio")) {
            envio.setEstadoEnvio(updates.get("estadoEnvio"));
        }
        
        return envioRepository.save(envio);
    }
}
