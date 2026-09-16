package cr.ac.ucr.paraiso.ie.c5j263.expresofast.controller;

import cr.ac.ucr.paraiso.ie.c5j263.expresofast.business.EnvioService;
import cr.ac.ucr.paraiso.ie.c5j263.expresofast.domain.Envio;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/envios")
@CrossOrigin(origins = "*")
public class EnvioController {

    private final EnvioService envioService;

    public EnvioController(EnvioService envioService) {
        this.envioService = envioService;
    }

    @GetMapping("/optimizados")
    public ResponseEntity<List<Envio>> getEnviosOptimizados() {
        return ResponseEntity.ok(envioService.obtenerEnviosOptimizados());
    }

    @PostMapping
    public ResponseEntity<Envio> registrarEnvio(@RequestBody Envio envio) {
        return ResponseEntity.ok(envioService.registrarEnvio(envio));
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<Envio> actualizarEstado(@PathVariable Integer id, @RequestBody Map<String, String> body) {
        return ResponseEntity.ok(envioService.actualizarEstado(id, body));
    }
}
