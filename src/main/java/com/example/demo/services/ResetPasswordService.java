@Service // Define essa classe como um componente de serviço gerenciado pelo Spring
public class ResetPasswordService {

    @Autowired // Injeta automaticamente a dependência do UserRepository
    private UserRepository userRepository;

    // Método responsável por alterar a senha de um usuário
    public String alterarSenha(String email, String novaSenha, String confirmarSenha) {

        // Busca o usuário pelo e-mail. Se não encontrar, lança exceção
        User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        // Obtém a senha atual do usuário
        String senhaAntiga = user.getPassword();

        // Validações da nova senha:
        if (novaSenha.length() < 8) {
            return "A senha deve ter no mínimo 8 caracteres.";
        }

        if (!novaSenha.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,}$")) {
            return "A senha deve conter letras maiúsculas, minúsculas, números e caracteres especiais.";
        }

        if (novaSenha.equals(senhaAntiga)) {
            return "Não foi possível alterar a senha, pois as senhas são iguais.";
        }

        if (!novaSenha.equals(confirmarSenha)) {
            return "As senhas não conferem.";
        }

        // Se tudo estiver ok, atualiza a senha e salva no banco
        user.setPassword(novaSenha);
        userRepository.save(user);

        return "Senha alterada com sucesso.";
    }
}