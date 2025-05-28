
import jakarta.validation.Valid;

@PostMapping("/alterar-senha")
public ResponseEntity<String> alterarSenha(@Valid @RequestBody AlterarSenhaDTO dto) {
    String resultado = resetPasswordServicee.alterarSenha(dto.getEmail(), dto.getNovaSenha(), dto.getConfirmarSenha());
    return ResponseEntity.ok(resultado);
}
package com.example.demo.controller;
import com.example.demo.dto.AlterarSenhaDTOo;