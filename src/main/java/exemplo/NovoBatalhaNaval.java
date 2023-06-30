 package exemplo;

import java.util.Scanner;
import java.util.Random;

public class NovoBatalhaNaval {

   public static void main(String[] args) {
    Scanner ler = new Scanner(System.in);
    char[][] TabuleiroJogador1 = new char[10][10];
    char[][] TabuleiroJogador2 = new char[10][10];
    char[][] tabuleiroMarcadoJogador1 = new char[10][10];
    char[][] tabuleiroMarcadoJogador2 = new char[10][10];
    boolean contraMaquina = false;
    boolean contraum = false;
    String alfabeto = "ABCDEFGHIJ";
    String coluna2;

    // Inicializando os tabuleiros
    inicializarTabuleiro(TabuleiroJogador1);
    inicializarTabuleiro(TabuleiroJogador2);

    System.out.println("Selecione o modo de jogo:");
    System.out.println("1 - Dois jogadores");
    System.out.println("2 - Contra a máquina");

    int modoJogo = 0;
    boolean entradaValida = false;

    while (!entradaValida) {
        modoJogo = ler.nextInt();

        switch (modoJogo) {
            case 1:
                contraum = true;
                entradaValida = true;
                break;
            case 2:
                contraMaquina = true;
                entradaValida = true;
                break;
            default:
                System.out.println("Número inválido. Digite novamente.");
                break;
        }
    }
 

    // Início do jogo
    System.out.println("Início do jogo!");
boolean jogadaExtraJogador1 = false;
boolean jogadaExtraJogador2 = false;

while (true) {
    // Jogador 1 - Seu turno
    if (!jogadaExtraJogador1) {
        realizarJogadaJogador1(TabuleiroJogador2, tabuleiroMarcadoJogador2, ler);

        // Verifica se o Jogador 1 acertou um navio e pode jogar novamente
        if (verificarAcertoNavio(tabuleiroMarcadoJogador2)) {
            System.out.println("Jogador 1 acertou um navio e joga novamente!");
            jogadaExtraJogador1 = true; // Habilita jogada extra do jogador 1
        }
    } else {
        // Jogador 1 - Jogada extra
        realizarJogadaJogador1(TabuleiroJogador2, tabuleiroMarcadoJogador2, ler);

        // Verifica se o Jogador 1 acertou um navio e pode jogar novamente
        if (!verificarAcertoNavio(tabuleiroMarcadoJogador2)) {
            jogadaExtraJogador1 = false; // Desabilita jogada extra do jogador 1
        }
    }

    // Verifica se o Jogador 1 venceu o jogo
    if (verificarVitoria(TabuleiroJogador2)) {
        System.out.println("Jogador 1 venceu!");
        break;
    }

    if (contraMaquina) {
        realizarJogadaMaquina(TabuleiroJogador2, tabuleiroMarcadoJogador1);
    } else {
        // Jogador 2 - Seu turno
        if (!jogadaExtraJogador2) {
            realizarJogadaJogador2(TabuleiroJogador1, tabuleiroMarcadoJogador1, ler);

            // Verifica se o Jogador 2 acertou um navio e pode jogar novamente
            if (verificarAcertoNavio(tabuleiroMarcadoJogador1)) {
                System.out.println("Jogador 2 acertou um navio e joga novamente!");
                jogadaExtraJogador2 = true; // Habilita jogada extra do jogador 2
            }
        } else {
            // Jogador 2 - Jogada extra
            realizarJogadaJogador2(TabuleiroJogador1, tabuleiroMarcadoJogador1, ler);

            // Verifica se o Jogador 2 acertou um navio e pode jogar novamente
            if (!verificarAcertoNavio(tabuleiroMarcadoJogador1)) {
                jogadaExtraJogador2 = false; // Desabilita jogada extra do jogador 2
            }
        }
    }

    // Verifica se o Jogador 2 venceu o jogo
    if (verificarVitoria(TabuleiroJogador1)) {
        System.out.println("Jogador 2 venceu!");
        break;
    }
}

System.out.println("Fim de jogo");
}



public static void realizarJogadaJogador1(char[][] tabuleiro, char[][] tabuleiroMarcado, Scanner ler) {
    boolean jogadaValida = false;
    
    while (!jogadaValida) {
        System.out.println("Jogador 1 - Seu turno!");
        int linha, coluna;
        String coluna2;

        do {
            System.out.println("Informe a linha (0 a 9): ");
            linha = ler.nextInt();
            System.out.println("Informe a coluna (A a J): ");
            coluna2 = ler.next().toUpperCase();
            String alfabeto = "ABCDEFGHIJ";
            coluna = alfabeto.indexOf(coluna2);

            if (linha > 9 || linha < 0 || coluna > 9 || coluna < 0) {
                System.out.println("Números inválidos. Digite novamente.");
            }
        } while (linha > 9 || linha < 0 || coluna > 9 || coluna < 0);

        // Verifica se a jogada acertou um navio
        if (tabuleiro[linha][coluna] == 'N') {
            System.out.println("Você acertou um navio!");
            tabuleiroMarcado[linha][coluna] = 'X'; // Marca o acerto no tabuleiro marcado
            exibirTabuleiro(tabuleiroMarcado);
            jogadaValida = true;
        } else {
            System.out.println("Você não acertou nenhum navio.");
            jogadaValida = true;
        }
    }
}



    // Método para realizar uma jogada

public static void realizarJogadaJogador2(char[][] tabuleiro, char[][] tabuleiroMarcado, Scanner ler) {
    boolean jogadaValida = false;
    
    while (!jogadaValida) {
        System.out.println("Jogador 2 - Seu turno!");
        int linha, coluna;
        String coluna2;

        do {
            System.out.println("Informe a linha (0 a 9): ");
            linha = ler.nextInt();
            System.out.println("Informe a coluna (A a J): ");
            coluna2 = ler.next().toUpperCase();
            String alfabeto = "ABCDEFGHIJ";
            coluna = alfabeto.indexOf(coluna2);

            if (linha > 9 || linha < 0 || coluna > 9 || coluna < 0) {
                System.out.println("Números inválidos. Digite novamente.");
            }
        } while (linha > 9 || linha < 0 || coluna > 9 || coluna < 0);

        // Verifica se a jogada acertou um navio
        if (tabuleiro[linha][coluna] == 'N') {
            System.out.println("Você acertou um navio!");
            tabuleiroMarcado[linha][coluna] = 'X'; // Marca o acerto no tabuleiro marcado
            exibirTabuleiro(tabuleiroMarcado);
            jogadaValida = true;
        } else {
            System.out.println("Você não acertou nenhum navio.");
            jogadaValida = true;
        }
    }
}

public static boolean verificarAcertoNavio(char[][] tabuleiroMarcado) {
    for (int i = 0; i < tabuleiroMarcado.length; i++) {
        for (int j = 0; j < tabuleiroMarcado[i].length; j++) {
            if (tabuleiroMarcado[i][j] == 'X') {
                return true; // Houve acerto de um navio
            }
        }
    }
    return false; // Não houve acerto de nenhum navio
}



    public static boolean verificarPosicaoDisponivel(char[][] tabuleiro, int linha, int coluna, int tamanhoNavio, boolean horizontal) {
        int tamanho = tabuleiro.length;

        if (horizontal) {
            if (coluna + tamanhoNavio > tamanho) {//Verifica se o navio cabe na linha atual, se não ele retorna false
                return false;
            }

            for (int i = coluna; i < coluna + tamanhoNavio; i++) {//Verifica se tem navios na coluna atual 
                if (tabuleiro[linha][i] != ' ') {
                    return false;
                }
            }
        } else { //Caso o navio for na vertical ele percorrerá as linhas
            if (linha + tamanhoNavio > tamanho) {
                return false;
            }

            for (int i = linha; i < linha + tamanhoNavio; i++) {
                if (tabuleiro[i][coluna] != ' ') {
                    return false;
                }
            }
        }

        return true;
    }

    public static void inserirNavio(char[][] tabuleiro, int linha, int coluna, int tamanhoNavio, boolean horizontal) {
        if (horizontal) {
            for (int i = coluna; i < coluna + tamanhoNavio; i++) {
                tabuleiro[linha][i] = 'N';
            }
        } else {
            for (int i = linha; i < linha + tamanhoNavio; i++) {
                tabuleiro[i][coluna] = 'N';
            }
        }
    }

    public static void posicionarNaviosMaquina(char[][] tabuleiro) {
        Random random = new Random();
        int tamanho = tabuleiro.length;//inserir o tamanho do tabuleiro
        int[] navioTamanhos = {4, 3, 3, 2, 2, 2, 1, 1, 1, 1}; // Tamanhos dos navios

        for (int i = 0; i < navioTamanhos.length; i++) {//percorre o tamanho especifo do barco no array 
        int tamanhoNavio = navioTamanhos[i];
        boolean navioInserido = false;
        while (!navioInserido) {
            int linha = random.nextInt(tamanho);
            int coluna = random.nextInt(tamanho);
            boolean horizontal = random.nextBoolean();

            if (verificarPosicaoDisponivel(tabuleiro, linha, coluna, tamanhoNavio, horizontal)) {
                inserirNavio(tabuleiro, linha, coluna, tamanhoNavio, horizontal);
                navioInserido = true;
            }
        }
    }
}
  


    // Método para realizar uma jogada da máquina
    public static void realizarJogadaMaquina(char[][] tabuleiro, char[][] tabuleiroMarcado) {
        Random random = new Random();
        int tamanho = tabuleiro.length;

        // Escolher uma posição aleatória para atacar
        int linha = random.nextInt(tamanho);
        int coluna = random.nextInt(tamanho);

        // Verificar se a jogada acertou um navio
        if (tabuleiro[linha][coluna] == 'N') {
            System.out.println("A máquina acertou um navio!");

            // Marcar o acerto no tabuleiro marcado
            tabuleiroMarcado[linha][coluna] = 'X';
            exibirTabuleiro(tabuleiroMarcado);
        } else {
            System.out.println("A máquina não acertou nenhum navio.");
        }
    }

    // Método para inicializar o tabuleiro
    public static void inicializarTabuleiro(char[][] tabuleiro) {
        for (int i = 0; i < tabuleiro.length; i++) {
            for (int j = 0; j < tabuleiro[i].length; j++) {
                tabuleiro[i][j] = ' '; 
            }
        }
    }

    // Método para exibir o tabuleiro
    public static void exibirTabuleiro(char[][] Tabuleiro) {
    char Espaco = ' ';
    int tamanho = 10;
    System.out.print("        ");
    for (int i = 0; i < tamanho; i++) {
        char coluna = (char) ('A' + i); // Converter o valor numérico da coluna em letra
        System.out.print(coluna + "    \t");
    }
    System.out.println();

    for (int i = 0; i < tamanho; i++) {
        System.out.print((i + 0) + "\t");
        for (int j = 0; j < tamanho; j++) {
            if (Tabuleiro[i][j] == Espaco) { // Verifica se a célula está vazia
                System.out.print("\t");
            } else {
                System.out.print(Tabuleiro[i][j] + "\t");
            }
        }
        System.out.println("");
        System.out.println("   ---------------------------------------------------------------------------------");
    }
}

    // Método para posicionar os navios no tabuleiro
    public static void posicionarNavios(char[][] tabuleiro, Scanner ler) {
        int Navioum = 4;
        int Naviodois = 3;
        int Naviotres = 2;
        int Navioquatro = 1;
        int TamanhoNavioum = 1;
        int TamanhoNaviodois = 2;
        int TamanhoNaviotres = 3;
        int TamanhoNavioquatro = 4;
        int totalBarcos = 10; // Número total de navios a serem posicionados
        int tamanhoNavio = 4; // Tamanho de cada navio

        while (totalBarcos > 0) {
            System.out.printf("(1) %d barcos de 1 posição\n", Navioum);
            System.out.printf("(2) %d barcos de 2 posição\n", Naviodois);
            System.out.printf("(3) %d barcos de 3 posição\n", Naviotres);
            System.out.printf("(4) %d barcos de 4 posição\n", Navioquatro);
            System.out.printf("Você tem %d para colocar ainda\n", totalBarcos);
            System.out.println("Escolha qual navio você quer alocar: ");
            int alocar = ler.nextInt();
            switch (alocar) {
                case 1:
                        System.out.println("Informe a linha da posição inicial: ");
                        int linha = ler.nextInt();
                        System.out.println("Informe a coluna da posição inicial: ");
                        String coluna2 = ler.next().toUpperCase();
                        String alfabeto = "ABCDEFGHIJ";
                        int coluna = alfabeto.indexOf(coluna2);

                        if (coluna + TamanhoNavioum > tabuleiro[0].length) {
                            System.out.println("Tamanho excedido. Tente novamente.");
                            break;
                        }

                        boolean ocupado = false;
                        for (int i = coluna; i < coluna + TamanhoNavioum; i++) {
                            if (tabuleiro[linha][i] == 'N') {
                                ocupado = true;
                                break;
                            }
                        }
                        if (ocupado) {
                            System.out.println("Essa posição já está ocupada. Tente novamente.");
                            break;
                        }

                        for (int i = coluna; i < coluna + TamanhoNavioum; i++) {
                            tabuleiro[linha][i] = 'N'; // 'N' representa um navio
                            Navioum--;
                        }

                        totalBarcos--;
                        exibirTabuleiro(tabuleiro);
                        break;
                    
                     case 2:
          
                        System.out.println("Informe a linha da posição inicial: ");
                         linha = ler.nextInt();
                        System.out.println("Informe a coluna da posição inicial: ");
                         coluna2 = ler.next().toUpperCase();
                         alfabeto = "ABCDEFGHIJ";
                         coluna = alfabeto.indexOf(coluna2);
                        System.out.println("Posicionar o navio na horizontal (H) ou vertical (V)?");
                        String orientacao = ler.next();

                        if (linha < 0 || linha >= tabuleiro.length || coluna < 0 || coluna >= tabuleiro[0].length) {
                            System.out.println("Posição inválida. Tente novamente.");
                            break;
                        }

                        if (orientacao.equalsIgnoreCase("H")) {
                            if (coluna + TamanhoNaviodois > tabuleiro[0].length) {
                                System.out.println("Tamanho excedido. Tente novamente.");
                               break;
                            }

                             ocupado = false;
                            for (int i = coluna; i < coluna + TamanhoNaviodois; i++) {
                                if (tabuleiro[linha][i] == 'N') {

                                    ocupado = true;
                                    break;
                                }
                            }

                            if (ocupado) {
                                System.out.println("Essa posição já está ocupada. Tente novamente.");
                                break;
                            }

                            for (int i = coluna; i < coluna + TamanhoNaviodois; i++) {
                                tabuleiro[linha][i] = 'N'; // 'N' representa um navio

                            }
                            Naviodois--;
                        } else if (orientacao.equalsIgnoreCase("V")) {
                            if (linha + TamanhoNaviodois > tabuleiro.length) {
                                System.out.println("Tamanho excedido. Tente novamente.");
                               break;
                            }

                             ocupado = false;
                            for (int i = linha; i < linha + TamanhoNaviodois; i++) {
                                if (tabuleiro[i][coluna] == 'N') {
                                    ocupado = true;
                                    break;
                                }
                            }

                            if (ocupado) {
                                System.out.println("Essa posição já está ocupada. Tente novamente.");
                               break;
                            }

                            for (int i = linha; i < linha + TamanhoNaviodois; i++) {
                                tabuleiro[i][coluna] = 'N'; // 'N' representa um navio

                            }
                            Naviodois--;
                        }

                        totalBarcos--;
                        exibirTabuleiro(tabuleiro);

                        break;


                case 3:
      
                        System.out.println("Informe a linha da posição inicial: ");
                         linha = ler.nextInt();
                        System.out.println("Informe a coluna da posição inicial: ");
                        coluna2 = ler.next().toUpperCase();
                         alfabeto = "ABCDEFGHIJ";
                        coluna = alfabeto.indexOf(coluna2);
                        System.out.println("Posicionar o navio na horizontal (H) ou vertical (V)?");
                         orientacao = ler.next();

                        if (linha < 0 || linha >= tabuleiro.length || coluna < 0 || coluna >= tabuleiro[0].length) {
                            System.out.println("Posição inválida. Tente novamente.");
                          break;
                        }

                        if (orientacao.equalsIgnoreCase("H")) {
                            if (coluna + TamanhoNaviotres > tabuleiro[0].length) {
                                System.out.println("Tamanho excedido. Tente novamente.");
                               break;
                            }

                             ocupado = false;
                            for (int i = coluna; i < coluna + TamanhoNaviotres; i++) {
                                if (tabuleiro[linha][i] == 'N') {
                                    ocupado = true;
                                    break;
                                }
                            }

                            if (ocupado) {
                                System.out.println("Essa posição já está ocupada. Tente novamente.");
                               break;
                            }

                            for (int i = coluna; i < coluna + TamanhoNaviotres; i++) {
                                tabuleiro[linha][i] = 'N'; // 'N' representa um navio

                            }
                            Naviotres--;
                        } else if (orientacao.equalsIgnoreCase("V")) {
                            if (linha + TamanhoNaviotres > tabuleiro.length) {
                                System.out.println("Tamanho excedido. Tente novamente.");
                                break;
                            }

                             ocupado = false;
                            for (int i = linha; i < linha + TamanhoNaviotres; i++) {
                                if (tabuleiro[i][coluna] == 'N') {
                                    ocupado = true;
                                    break;
                                }
                            }

                            if (ocupado) {
                                System.out.println("Essa posição já está ocupada. Tente novamente.");
                                break;
                            }

                            for (int i = linha; i < linha + TamanhoNaviotres; i++) {
                                tabuleiro[i][coluna] = 'N'; // 'N' representa um navio

                            }
                            Naviotres--;
                        }

                        totalBarcos--;
                        exibirTabuleiro(tabuleiro);

                        break;

                
                case 4:
               
                        System.out.println("Informe a linha da posição inicial: ");
                         linha = ler.nextInt();
                        System.out.println("Informe a coluna da posição inicial: ");
                          coluna2 = ler.next().toUpperCase();
                         alfabeto = "ABCDEFGHIJ";
                         coluna = alfabeto.indexOf(coluna2);
                        System.out.println("Posicionar o navio na horizontal (H) ou vertical (V)?");
                         orientacao = ler.next();

                        if (linha < 0 || linha >= tabuleiro.length || coluna < 0 || coluna >= tabuleiro[0].length) {
                            System.out.println("Posição inválida. Tente novamente.");
                            break;
                        }

                        if (orientacao.equalsIgnoreCase("H")) {
                            if (coluna + TamanhoNavioquatro > tabuleiro[0].length) {
                                System.out.println("Tamanho excedido. Tente novamente.");
                                break;
                            }

                            ocupado = false;
                            for (int i = coluna; i < coluna + TamanhoNavioquatro; i++) {
                                if (tabuleiro[linha][i] == 'N') {
                                    ocupado = true;
                                    break;
                                }
                            }

                            if (ocupado) {
                                System.out.println("Essa posição já está ocupada. Tente novamente.");
                                break;
                            }

                            for (int i = coluna; i < coluna + TamanhoNavioquatro; i++) {
                                tabuleiro[linha][i] = 'N'; // 'N' representa um navio

                            }
                            Navioquatro--;
                        } else if (orientacao.equalsIgnoreCase("V")) {
                            if (linha + TamanhoNavioquatro > tabuleiro.length) {
                                System.out.println("Tamanho excedido. Tente novamente.");
                               break;
                            }

                            ocupado = false;
                            for (int i = linha; i < linha + TamanhoNavioquatro; i++) {
                                if (tabuleiro[i][coluna] == 'N') {
                                    ocupado = true;
                                    break;
                                }
                            }

                            if (ocupado) {
                                System.out.println("Essa posição já está ocupada. Tente novamente.");
                               break;
                            }

                            for (int i = linha; i < linha + TamanhoNavioquatro; i++) {
                                tabuleiro[i][coluna] = 'N'; // 'N' representa um navio

                            }
                            Navioquatro--;
                        }

                        totalBarcos--;
                        exibirTabuleiro(tabuleiro);

                        break;

                    }
            }

        }
    
    // Método para verificar se um jogador venceu o jogo
    public static boolean verificarVitoria(char[][] tabuleiro) {
        for (int i = 0; i < tabuleiro.length; i++) {
            for (int j = 0; j < tabuleiro[i].length; j++) {
                if (tabuleiro[i][j] == 'N') {
                    return false; // Ainda há navios não afundados
                }
            }
        }
        return true; // Todos os navios foram afundados
    }

    public static int automaticamente() {
        Scanner ler = new Scanner(System.in);
        System.out.println("Colocar os barcos automaticamente (1) Sim (2) Não:");
        int colocar = ler.nextInt();
        return colocar;
    }


    public static boolean verificarDisponibilidade(char[][] tabuleiro, int linha, int coluna, int tamanhoNavio) {
        if (linha < 0 || linha >= tabuleiro.length || coluna < 0 || coluna >= tabuleiro[0].length) {
            return false; // Verifica se as coordenadas estão dentro do tabuleiro
        }

        for (int i = 0; i < tamanhoNavio; i++) {
            if (coluna + i >= tabuleiro[0].length || tabuleiro[linha][coluna + i] == 'N') {
                return false; // Verifica se a posição está ocupada
            }
        }

        return true; // Todas as posições estão disponíveis
    }

    public static void alocarNavio(char[][] tabuleiro, int linha, int coluna, int tamanhoNavio) {
        for (int i = 0; i < tamanhoNavio; i++) {
            tabuleiro[linha][coluna + i] = 'N';
        }
    }
    
    public static void alocarNaviosAutomaticamente(char[][] tabuleiro) {
    Random random = new Random();
    int naviosAlocados = 0;
    int NUM_NAVIOS=20;

    while (naviosAlocados < NUM_NAVIOS) {
        int linha = random.nextInt(10);
        int coluna = random.nextInt(10);

        if (tabuleiro[linha][coluna] != 'N') {
            tabuleiro[linha][coluna] = 'N';
            naviosAlocados++;
        }
    }
}
}
