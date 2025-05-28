package service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class ResetPasswordServicee {

    @Autowired
    private UserRepository userRepository;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public String alterarSenha(String email, String novaSenha, String confirmarSenha) {

        User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        // Verifica se nova senha é igual à senha atual
        if (encoder.matches(novaSenha, user.getPassword())) {
            return "Não foi possível alterar a senha, pois as senhas são iguais.";
        }

        if (!novaSenha.equals(confirmarSenha)) {
            return "As senhas não conferem.";
        }

        if (!novaSenha.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,}$")) {
            return "A senha deve conter letras maiúsculas, minúsculas, números e caracteres especiais.";
        }

        user.setPassword(encoder.encode(novaSenha));
        userRepository.save(user);

        return "Senha alterada com sucesso.";
    }
}
