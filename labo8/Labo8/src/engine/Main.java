package engine;

import chess.ChessController;
import chess.ChessView;
import chess.views.console.ConsoleView;
import chess.views.gui.GUIView;

/**
 * Classe lançant le programme du jeu d'échecs.
 * Il est possible de jouer via un GUI ou en Console selon les envies.
 *
 * @author Jonathan Friedli
 * @author Valentin Kaelin
 */
public class Main {
    
    public static void main(String[] args) {
        ChessController controller = new GameManager();
        
        // Choix de la vue : mode GUI ou mode Console
        ChessView view = new GUIView(controller);
//         ChessView view = new ConsoleView(controller);
        
        controller.start(view);
    }
}
