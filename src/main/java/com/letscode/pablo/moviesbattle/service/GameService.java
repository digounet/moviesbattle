package com.letscode.pablo.moviesbattle.service;

import com.letscode.pablo.moviesbattle.dataprovider.GameMovieRepository;
import com.letscode.pablo.moviesbattle.dataprovider.GameRepository;
import com.letscode.pablo.moviesbattle.dataprovider.UserRepository;
import com.letscode.pablo.moviesbattle.entity.Game;
import com.letscode.pablo.moviesbattle.entity.User;
import com.letscode.pablo.moviesbattle.entrypoint.entity.QuizzResponse;
import com.letscode.pablo.moviesbattle.entrypoint.entity.RankingResponse;
import com.letscode.pablo.moviesbattle.infrastructure.exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private GameMovieRepository gameMovieRepository;

    @Autowired
    private MovieService movieService;

    @Autowired
    private UserService userService;

    public Game startGame(int userId) throws GameAlreadyStartedException {
        var currentGame = gameRepository.getActiveGame(userId);

        if (currentGame.isEmpty()) {
            var movies = movieService.getRandomMoviesPairs();
            return gameRepository.startGame(userId, movies);
        } else {
            throw new GameAlreadyStartedException("The user has an active game. Please finish the current game");
        }
    }

    public Game endGame(int userId) throws GameNotFoundException {
        var currentGame = gameRepository.getActiveGame(userId);

        if (currentGame.isPresent()) {
            var endedGame = gameRepository.endGame(currentGame.get());

            var user = userService.loadUserById(userId);
            user.setTotalGames((user.getTotalGames() == null ? 0 : user.getTotalGames()) + 1);
            user.setTotalHits((user.getTotalHits() == null ? 0 : user.getTotalHits()) + endedGame.getHits());
            user.setTotalMistakes((user.getTotalMistakes() == null ? 0 : user.getTotalMistakes()) + endedGame.getMistakes());
            userService.update(user);

            return endedGame;
        } else {
            throw new GameNotFoundException("The user hasn't active games");
        }
    }

    public QuizzResponse quizz(int loggedUser) throws MovieNotFoundException, GameNotFoundException, GameOverException {
        var currentGame = gameRepository.getActiveGame(loggedUser);

        if (currentGame.isPresent()) {
            var movieList = currentGame.get().getMovieList();
            if (movieList.size() > 0) {
                var pair = movieList.get(0);
                var movieA = movieService.getMovieById(pair.getMovieA());
                var movieB = movieService.getMovieById(pair.getMovieB());

                return QuizzResponse.builder()
                        .movieAId(pair.getMovieA())
                        .movieA(movieA.getTitle())
                        .movieBId(pair.getMovieB())
                        .MovieB(movieB.getTitle())
                        .build();
            } else {
                throw new GameOverException("Ther's no movies pairs. Please finalize the game.");
            }
        } else {
            throw new GameNotFoundException("The user hasn't active games");
        }
    }

    public boolean quizzResponse(int loggedUser, String movieId) throws MovieNotFoundException, GameOverException, GameNotFoundException, WrongAnswerException {
        var currentGame = gameRepository.getActiveGame(loggedUser);

        if (currentGame.isPresent()) {
            var movieList = currentGame.get().getMovieList();
            if (movieList.size() > 0) {
                var pair = movieList.get(0);
                if (pair.getMovieA().equals(movieId) || pair.getMovieB().equals(movieId)) {
                    var movieA = movieService.getMovieById(pair.getMovieA());
                    var movieB = movieService.getMovieById(pair.getMovieB());
                    var hit = movieA.isSelectedTheWinner(movieId, movieB);

                    var game = currentGame.get();
                    game.getMovieList().remove(0);

                    if (hit) {
                        game.setHits(game.getHits() + 1);
                    } else {
                        game.setMistakes(game.getMistakes() + 1);
                    }

                    gameRepository.update(game);
                    gameMovieRepository.delete(pair);

                    return hit;
                } else {
                    throw new WrongAnswerException("Invalid response. Please select the movie id");
                }
            } else {
                throw new GameOverException("Ther's no movies pairs. Please finalize the game.");
            }
        } else {
            throw new GameNotFoundException("The user hasn't active games");
        }
    }

    public List<RankingResponse> loadRanking() {
        return userService.ranking();
    }
}
