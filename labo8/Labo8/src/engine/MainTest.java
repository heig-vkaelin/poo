package engine;

import chess.ChessController;
import chess.ChessView;
import chess.views.gui.GUIView;

/**
 * Classe lançant le programme de test du jeu d'échecs.
 * Il est possible de définir quelle position initiale choisir pour les pièces
 * afin de tester une pièce ou un mouvement spécifique.
 *
 * @author Jonathan Friedli
 * @author Valentin Kaelin
 */
public class MainTest {
    public static void main(String[] args) {
        // Choix du test à lancer
        GameManagerTest.Type type = GameManagerTest.Type.QUEEN;
        ChessController controller = new GameManagerTest(type);
        
        ChessView view = new GUIView(controller);
        
        controller.start(view);
    }
}
