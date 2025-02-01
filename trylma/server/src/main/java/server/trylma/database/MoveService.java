package server.trylma.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MoveService {
	@Autowired
	private MoveRepository moveRepository;

	public void addMove(int port, int game, String board) {
		Move move = new Move();
		move.setPort(port);
		move.setGame(game);
		move.setBoard(board);
		moveRepository.save(move);
		System.out.println("Saved move");
	}
}