import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/servers")
public class ServerController {
    @Autowired
    private ServerRepository serverRepository;

    @GetMapping
    public List<Server> getServers(@RequestParam(required = false) String name) {
        if (name != null) {
            return serverRepository.findByNameContaining(name);
        } else {
            return serverRepository.findAll();
        }
    }

    @GetMapping("/{id}")
    public Server getServerById(@PathVariable String id) {
        return serverRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Server createServer(@RequestBody Server server) {
        return serverRepository.save(server);
    }

    @PutMapping("/{id}")
    public Server updateServer(@PathVariable String id, @RequestBody Server server) {
        server.setId(id);
        return serverRepository.save(server);
    }

    @DeleteMapping("/{id}")
    public void deleteServer(@PathVariable String id) {
        serverRepository.deleteById(id);
    }
}
