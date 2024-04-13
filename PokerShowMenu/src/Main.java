import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            showMenu();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    //  menú interactivo
    public static void showMenu() throws Exception {
        Scanner scanner = new Scanner(System.in);
        Deck deck = new Deck();

        System.out.println("Bienvenido al Poker Sr!");
        int option;

        do {
            System.out.println("\nSelecciona una opción:");
            System.out.println("1 Mezclar mazo");
            System.out.println("2 Sacar una carta del mazo");
            System.out.println("3 Carta al azar de mazo");
            System.out.println("4 Generar una mano de 5 cartas");
            System.out.println("0 Salir de la app");

            // Leer la opción escogida
            option = scanner.nextInt();
            scanner.nextLine();

            // Ejecutar la opción que se selecciono
            switch (option) {
                case 1:
                    deck.shuffle();
                    break;
                case 2:
                    deck.head();
                    break;
                case 3:
                    deck.pick();
                    break;
                case 4:
                    deck.hand();
                    break;
                case 0:
                    System.out.println("Gracias por jugar a Poker!");
                    break;
                default:
                    throw new Exception("Opción no válida escoga un que sea 1,2,3,4 o 0!.");
            }
        } while (option != 0);
    }

    // Clase que representa una carta de poker
    static class Card {
        private String palo;
        private String color;
        private String valor;

        public Card(String palo, String color, String valor) {
            this.palo = palo;
            this.color = color;
            this.valor = valor;
        }

        public String getPalo() {
            return palo;
        }

        public String getColor() {
            return color;
        }

        public String getValor() {
            return valor;
        }

        @Override
        public String toString() {
            return palo + "," + color + "," + valor;
        }
    }

    // Clase del mazo de cartas de poker
    static class Deck {
        private List<Card> cards;

        public Deck() {
            cards = new ArrayList<>();
            initializeDeck();
        }

        // Inicialización del mazo con las cartas de poker
        private void initializeDeck() {
            String[] palos = {"Tréboles", "Corazones", "Picas", "Diamantes"};
            String[] colores = {"Negro", "Rojo"};
            String[] valores = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "A", "J", "Q", "K"};

            for (String palo : palos) {
                for (String color : colores) {
                    for (String valor : valores) {
                        cards.add(new Card(palo, color, valor));
                    }
                }
            }
        }

        // Mezcla de mazo
        public void shuffle() {
            Collections.shuffle(cards);
            System.out.println("Se ha mezclado tu Deck.");
        }

        // Muestra y remueve la primera carta del mazo
        public void head() throws Exception {
            if (!cards.isEmpty()) {
                Card card = cards.remove(0);
                System.out.println(card);
                System.out.println("Quedan " + cards.size() + " cartas en deck");
            } else {
                throw new Exception("Se han agotado las cartas.");
            }
        }

        // Selecciona una carta al azar del mazo y la quita
        public void pick() throws Exception {
            if (!cards.isEmpty()) {
                int randomIndex = (int) (Math.random() * cards.size());
                Card card = cards.remove(randomIndex);
                System.out.println(card);
                System.out.println("Quedan " + cards.size() + " cartas en deck");
            } else {
                throw new Exception("Se han agotado las cartas.");
            }
        }

        // Reparte una mano de 5 cartas
        public void hand() throws Exception {
            if (cards.size() >= 5) {
                for (int i = 0; i < 5; i++) {
                    Card card = cards.remove(0);
                    System.out.println(card);
                }
                System.out.println("Quedan " + cards.size() + " cartas en deck");
            } else {
                throw new Exception("Se han agotado las cartas.");
            }
        }
    }
}
