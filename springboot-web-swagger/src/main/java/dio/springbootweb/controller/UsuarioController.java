package dio.springbootweb.controller;

import java.util.List;
import java.util.Optional;

import dio.springbootweb.model.Usuario;
import dio.springbootweb.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioRepository repository;

    @PostMapping
    public ResponseEntity<Usuario> post(@Validated @RequestBody Usuario usuario){
        Usuario savedUsuario = repository.save(usuario);
        return new ResponseEntity<>(savedUsuario, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> put(@PathVariable("id") Long id, @Validated @RequestBody Usuario usuario){
        Optional<Usuario> optionalUsuario = repository.findById(id);
        if (!optionalUsuario.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        usuario.setId(id);
        repository.save(usuario);
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }

    @GetMapping
    public List<Usuario> getAll(){
        return repository.listAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getOne(@PathVariable("id") Long id){
        Optional<Usuario> optionalUsuario = repository.findById(id);
        if (!optionalUsuario.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(optionalUsuario.get(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        repository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}