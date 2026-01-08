package br.com.nexohub.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.nexohub.api.domain.usuario.DadosAutenticacao;
import br.com.nexohub.api.domain.usuario.Usuario;
import br.com.nexohub.api.infra.security.DadosTokenJWT;
import br.com.nexohub.api.infra.security.TokenService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity efetuarLogin(@RequestBody @Valid DadosAutenticacao dados) {
        try {
            // A senha chega "123456" aqui e o manager faz o hash para comparar com o banco
            var authenticationToken = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
            var authentication = manager.authenticate(authenticationToken);

            var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());

            return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
        } catch (Exception e) {
            // Isso imprime o erro no console do IntelliJ/Eclipse
            e.printStackTrace();
            // Isso devolve o motivo do erro no corpo da resposta (Bad Request)
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}


