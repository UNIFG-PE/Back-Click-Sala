import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Scanner criado

        String senhaantiga = "12345"; // vindo do banco de dados

        System.out.println("Digite sua nova senha.");
        String novasenha = scanner.nextLine();

        
        if (novasenha.length() < 8) {
            System.out.println("A senha deve ter no mínimo 8 caracteres.");
        } 
        // Verifica se contém letras e números
        else if (!novasenha.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,}$")) {
            System.out.println("A senha deve ter no mínimo 8 caracteres, contendo letras maiúsculas, minúsculas, números e caracteres especiais.");
        } 
        else {
            System.out.println("Digite sua senha de confirmação.");
            String confirmarsenha = scanner.nextLine();

            if (novasenha.equals(senhaantiga)) {
                System.out.println("Não foi possível alterar a senha, pois as senhas são iguais.");
            } else {
                if (novasenha.equals(confirmarsenha)) {
                    System.out.println("Senha alterada com sucesso.");
                } else {
                    System.out.println("As senhas não conferem.");
                }
            }
        }

        scanner.close();
    }
}
