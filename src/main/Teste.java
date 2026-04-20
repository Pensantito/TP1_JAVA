package main;

import javax.swing.JOptionPane;
import model.*;
import java.util.ArrayList;

public class Teste {

    private static ArrayList<Funcionario> funcionarios = new ArrayList<>();
    private static ArrayList<Professor> professores = new ArrayList<>();
    private static ArrayList<Aluno> alunos = new ArrayList<>();
    private static ArrayList<Livro> acervo = new ArrayList<>();
    private static ArrayList<Cargo> cargos = new ArrayList<>();

    public static void main(String[] args) {
        
        String senhaProfAleatoria = String.format("%04d", (int)(Math.random() * 10000));
        
        System.out.println("=============================================");
        System.out.println(">> LOG DO SISTEMA | CREDENCIAIS GERADAS <<");
        System.out.println("Senha de acesso Prof. (CPF 456): " + senhaProfAleatoria);
        System.out.println("=============================================");

        Cargo cargoBase = new Cargo("C01", "Bibliotecário", "Gestão do Acervo", 40);
        cargos.add(cargoBase);
        
        funcionarios.add(new Funcionario("123", "Admin Geral", "admin@est.uea.br", cargoBase, 3500.0, "admin123"));
        professores.add(new Professor("456", "Docente Teste", "docente@uea.edu.br", "Doutor", senhaProfAleatoria));
        acervo.add(new Livro("Java: Como Programar", "Deitel", "Pearson", 2015, 968, "978-8576055631", "Tecnologia", "Guia completo", "Português"));
        
        int opcaoEscolhida = -1;
        
        do {
            try {
                String menuInicial = 
                    "+-------------------------------------------------+\n" +
                    "|       PORTAL DA BIBLIOTECA - EST / UEA          |\n" +
                    "+-------------------------------------------------+\n\n" +
                    "[ 1 ] -> Autenticar no Sistema\n" +
                    "[ 0 ] -> Encerrar Aplicação\n\n" +
                    "Digite a opção desejada:";

                String input = JOptionPane.showInputDialog(null, menuInicial, "Menu Principal", JOptionPane.PLAIN_MESSAGE);
                
                if (input == null) break; 
                opcaoEscolhida = Integer.parseInt(input);

                switch (opcaoEscolhida) {
                    case 1:
                        realizarLogin();
                        break;
                    case 0:
                        JOptionPane.showMessageDialog(null, "Fechando o sistema. Até logo!", "Desconectado", JOptionPane.INFORMATION_MESSAGE);
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Atenção: Escolha uma opção válida do menu.", "Aviso", JOptionPane.WARNING_MESSAGE);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Caracter inválido. Por favor, insira apenas números.", "Falha de Entrada", JOptionPane.ERROR_MESSAGE);
            }
        } while (opcaoEscolhida != 0);
    }

    private static void realizarLogin() {
        String cpf = JOptionPane.showInputDialog(null, "Insira o CPF do utilizador:", "Autenticação", JOptionPane.QUESTION_MESSAGE);
        if (cpf == null) return; 

        String senha = JOptionPane.showInputDialog(null, "Insira a senha de acesso:", "Autenticação", JOptionPane.QUESTION_MESSAGE);
        if (senha == null) return;

        Pessoa usuarioValidado = verificarCredenciais(cpf, senha);

        if (usuarioValidado != null) {
            if (usuarioValidado instanceof Funcionario) {
                painelDoFuncionario((Funcionario) usuarioValidado);
            } else if (usuarioValidado instanceof Professor) {
                painelDoProfessor((Professor) usuarioValidado);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Falha no Login: Credenciais não encontradas no banco de dados.", "Acesso Negado", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static Pessoa verificarCredenciais(String cpf, String senha) {
        for (Funcionario func : funcionarios) {
            if (func.efetuarLogin(cpf, senha)) return func;
        }
        for (Professor prof : professores) {
            if (prof.efetuarLogin(cpf, senha)) return prof;
        }
        return null;
    }

    private static void painelDoFuncionario(Funcionario f) {
        int opcao = -1;
        do {
            try {
                String menuAdmin = 
                    "MODO ADMINISTRATIVO | Operador: " + f.getNome() + "\n" +
                    "=======================================\n" +
                    "[ 1 ] -> Adicionar Livro ao Acervo\n" +
                    "[ 2 ] -> Exibir Catálogo da Biblioteca\n" +
                    "[ 3 ] -> Cadastrar Novo Cargo\n" +
                    "[ 4 ] -> Cadastrar Aluno\n" +
                    "[ 5 ] -> Cadastrar Professor\n" +
                    "[ 6 ] -> Cadastrar Funcionário\n" +
                    "[ 7 ] -> Relatório Geral de Utilizadores e Cargos\n" +
                    "[ 0 ] -> Desconectar (Logout)\n\n" +
                    "O que deseja fazer?";

                String input = JOptionPane.showInputDialog(null, menuAdmin, "Gestão da Biblioteca", JOptionPane.PLAIN_MESSAGE);
                
                if (input == null) break;
                opcao = Integer.parseInt(input);

                switch (opcao) {
                    case 1:
                        String tit = JOptionPane.showInputDialog("Título da Obra:");
                        String aut = JOptionPane.showInputDialog("Autor(es):");
                        String edi = JOptionPane.showInputDialog("Editora:");
                        String anoStr = JOptionPane.showInputDialog("Ano de Publicação:");
                        String pagStr = JOptionPane.showInputDialog("Número de Páginas:");
                        String isbn = JOptionPane.showInputDialog("Código ISBN:");
                        String gen = JOptionPane.showInputDialog("Gênero Literário:");
                        String sin = JOptionPane.showInputDialog("Sinopse Breve:");
                        String idi = JOptionPane.showInputDialog("Idioma:");
                        
                        if (tit != null && anoStr != null && pagStr != null) {
                            int ano = Integer.parseInt(anoStr);
                            int pag = Integer.parseInt(pagStr);
                            acervo.add(new Livro(tit, aut, edi, ano, pag, isbn, gen, sin, idi));
                            JOptionPane.showMessageDialog(null, "Obra '" + tit + "' adicionada com sucesso ao acervo!");
                        }
                        break;

                    case 2:
                        StringBuilder txtLivros = new StringBuilder("<<< CATÁLOGO ATUAL >>>\n\n");
                        for (Livro l : acervo) {
                            txtLivros.append(">> Título: ").append(l.getTitulo())
                                     .append("\n   Autor: ").append(l.getAutores())
                                     .append(" | ISBN: ").append(l.getIsbn())
                                     .append("\n   Editora: ").append(l.getEditora()).append(" (").append(l.getAnoPublicacao()).append(")\n\n");
                        }
                        JOptionPane.showMessageDialog(null, acervo.isEmpty() ? "Nenhum livro registado ainda." : txtLivros.toString());
                        break;

                    case 3:
                        String codCargo = JOptionPane.showInputDialog("Código do Cargo (Ex: C02):");
                        String nomCargo = JOptionPane.showInputDialog("Nome do Cargo (Ex: Analista):");
                        String desCargo = JOptionPane.showInputDialog("Descrição das atividades:");
                        String cargaStr = JOptionPane.showInputDialog("Carga Horária Semanal:");
                        
                        if (codCargo != null && cargaStr != null) {
                            int carga = Integer.parseInt(cargaStr);
                            cargos.add(new Cargo(codCargo, nomCargo, desCargo, carga));
                            JOptionPane.showMessageDialog(null, "Novo cargo registado no sistema!");
                        }
                        break;

                    case 4:
                        String nomeA = JOptionPane.showInputDialog("Nome completo do Aluno:");
                        String cpfA = JOptionPane.showInputDialog("Registro de CPF:");
                        String emailA = JOptionPane.showInputDialog("Endereço de E-mail:");
                        String inst = JOptionPane.showInputDialog("Instituição de Ensino:");
                        String curso = JOptionPane.showInputDialog("Qual o curso matriculado?");
                        String notaEnem = JOptionPane.showInputDialog("Pontuação no último ENEM:");
                        
                        if (nomeA != null && notaEnem != null) {
                            alunos.add(new Aluno(cpfA, nomeA, emailA, inst, curso, Double.parseDouble(notaEnem)));
                            JOptionPane.showMessageDialog(null, "Matrícula do aluno concluída!");
                        }
                        break;

                    case 5:
                        String nomeP = JOptionPane.showInputDialog("Nome completo do Docente:");
                        String cpfP = JOptionPane.showInputDialog("Registro de CPF:");
                        String emailP = JOptionPane.showInputDialog("Endereço de E-mail:");
                        String grau = JOptionPane.showInputDialog("Titulação Académica (Ex: Mestre, Doutor):");
                        
                        if (nomeP != null && cpfP != null) {
                            String codeGerado = String.format("%04d", (int)(Math.random() * 10000));
                            professores.add(new Professor(cpfP, nomeP, emailP, grau, codeGerado));
                            JOptionPane.showMessageDialog(null, "Docente inserido.\n>> IMPORTANTE: A senha de acesso gerada é " + codeGerado + " <<");
                        }
                        break;

                    case 6:
                        if (cargos.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Não existem cargos registados. Registe um cargo primeiro (Opção 3).");
                            break;
                        }
                        
                        String nFunc = JOptionPane.showInputDialog("Nome do Colaborador:");
                        String cFunc = JOptionPane.showInputDialog("CPF:");
                        String eFunc = JOptionPane.showInputDialog("E-mail corporativo:");
                        String sFunc = JOptionPane.showInputDialog("Salário:");
                        String senhaFunc = JOptionPane.showInputDialog("Crie a senha inicial do colaborador:");
                        
                        StringBuilder listaC = new StringBuilder("Escolha o Código do Cargo para este funcionário:\n\n");
                        for (Cargo c : cargos) listaC.append(c.getCodigo()).append(" - ").append(c.getNome()).append("\n");
                        
                        String codEscolhido = JOptionPane.showInputDialog(listaC.toString());
                        
                        Cargo cargoSelecionado = cargos.get(0);
                        for (Cargo c : cargos) {
                            if (c.getCodigo().equalsIgnoreCase(codEscolhido)) cargoSelecionado = c;
                        }
                        
                        if (nFunc != null && sFunc != null) {
                            funcionarios.add(new Funcionario(cFunc, nFunc, eFunc, cargoSelecionado, Double.parseDouble(sFunc), senhaFunc));
                            JOptionPane.showMessageDialog(null, "Novo colaborador contratado e associado ao cargo de " + cargoSelecionado.getNome() + ".");
                        }
                        break;

                    case 7:
                        StringBuilder txtUsers = new StringBuilder("<<< RELATÓRIO DO SISTEMA >>>\n\n");
                        
                        txtUsers.append("--- CARGOS CRIADOS ---\n");
                        for (Cargo c : cargos) txtUsers.append(" >> [").append(c.getCodigo()).append("] ").append(c.getNome()).append(" (").append(c.getCargaHoraria()).append("h)\n");
                        
                        txtUsers.append("\n--- EQUIPA (FUNCIONÁRIOS) ---\n");
                        for (Funcionario fun : funcionarios) txtUsers.append(" >> ").append(fun.getNome()).append(" | Cargo: ").append(fun.getCargo().getNome()).append(" | Salário: R$").append(fun.getSalario()).append("\n");
                        
                        txtUsers.append("\n--- CORPO DOCENTE ---\n");
                        for (Professor p : professores) txtUsers.append(" >> ").append(p.getNome()).append(" | Titulação: ").append(p.getTitulacao()).append("\n");
                        
                        txtUsers.append("\n--- DISCENTES (ALUNOS) ---\n");
                        for (Aluno a : alunos) txtUsers.append(" >> ").append(a.getNome()).append(" | ").append(a.getInstituicaoEnsino()).append(" - ").append(a.getCurso()).append("\n");
                        
                        JOptionPane.showMessageDialog(null, txtUsers.toString());
                        break;

                    case 0:
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Opção inválida do menu administrativo.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Erro de formatação! Certifique-se de que insere números onde é pedido (ex: Anos, Páginas, Notas).", "Erro no Processamento", JOptionPane.ERROR_MESSAGE);
            }
        } while (opcao != 0);
    }

    private static void painelDoProfessor(Professor p) {
        int opcao = -1;
        do {
            try {
                String menuProf = 
                    "SISTEMA DE CONSULTA DOCENTE\n" +
                    "Bem-vindo(a), Prof. " + p.getNome() + "\n" +
                    "==================================\n" +
                    "[ 1 ] -> Pesquisar Obras Disponíveis\n" +
                    "[ 0 ] -> Desconectar (Logout)\n\n" +
                    "Insira o código da operação:";

                String input = JOptionPane.showInputDialog(null, menuProf, "Acesso Restrito", JOptionPane.PLAIN_MESSAGE);
                
                if (input == null) break;
                opcao = Integer.parseInt(input);

                if (opcao == 1) {
                    StringBuilder lista = new StringBuilder("<<< DISPONIBILIDADE NA BIBLIOTECA >>>\n\n");
                    for (Livro l : acervo) {
                        lista.append("-> ").append(l.getTitulo()).append(" (").append(l.getAnoPublicacao()).append(")\n");
                    }
                    JOptionPane.showMessageDialog(null, acervo.isEmpty() ? "O catálogo está vazio." : lista.toString());
                } else if (opcao != 0) {
                    JOptionPane.showMessageDialog(null, "Comando não reconhecido.");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Por favor, digite uma opção numérica válida.");
            }
        } while (opcao != 0);
    }
}