package com.letscode.pablo.moviesbattle.service;

import com.letscode.pablo.moviesbattle.dataprovider.GameMovieRepository;
import com.letscode.pablo.moviesbattle.dataprovider.GameRepository;
import com.letscode.pablo.moviesbattle.entity.Game;
import com.letscode.pablo.moviesbattle.entity.GameMovie;
import com.letscode.pablo.moviesbattle.entity.Movie;
import com.letscode.pablo.moviesbattle.entity.User;
import com.letscode.pablo.moviesbattle.infrastructure.exception.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class GameServiceTest {

    @Mock
    private GameRepository gameRepository;

    @Mock
    private GameMovieRepository gameMovieRepository;

    @Mock
    private MovieService movieService;

    @Mock
    private UserService userService;

    @InjectMocks
    private GameService gameService;

    @Test
    void startGameSuccess() throws GameAlreadyStartedException {
        when(gameRepository.getActiveGame(eq(1000))).thenReturn(Optional.empty());
        when(gameRepository.startGame(eq(1000), any())).thenReturn(new Game());
        when(movieService.getRandomMoviesPairs()).thenReturn(new ArrayList<>());

        var game = gameService.startGame(1000);

        assertNotNull(game);
    }

    @Test
    void startGameException()  {
        when(gameRepository.getActiveGame(eq(1000))).thenReturn(Optional.of(new Game()));

        var exception = assertThrows(GameAlreadyStartedException.class, () ->
                gameService.startGame(1000));

        assertNotNull(exception);
        assertTrue(exception.getMessage().contains("The user has an active game. Please finish the current game"));
    }

    @Test
    void endGameSuccess() throws GameNotFoundException {
        var game = new Game(1, 1, new Date(), new Date(), 0, 0, new ArrayList<>());
        var user = new User(1, "dfsdfs", "rerwerw", 0,0,0);

        when(gameRepository.getActiveGame(eq(1000))).thenReturn(Optional.of(game));
        when(gameRepository.endGame(any())).thenReturn(game);
        when(userService.loadUserById(eq(1000))).thenReturn(user);
        doNothing().when(userService).update(any());

        var gameSaved = gameService.endGame(1000);

        assertNotNull(gameSaved);
    }

    @Test
    void endGameException() {
        when(gameRepository.getActiveGame(eq(1000))).thenReturn(Optional.empty());

        var exception = assertThrows(GameNotFoundException.class, () ->
                gameService.endGame(1000));

        assertNotNull(exception);
        assertTrue(exception.getMessage().contains("The user hasn't active games"));
    }

    @Test
    void quizzSuccess() throws MovieNotFoundException, GameOverException, GameNotFoundException {
        var movie = new Movie("dfsdfs", "dfsdfs", 10.5, 45);
        var gameMovie = new GameMovie(1,  null, "dsadas", "fdfsd");
        var game = new Game(1, 1, new Date(), new Date(), 0, 0, List.of(gameMovie));

        when(gameRepository.getActiveGame(eq(1000))).thenReturn(Optional.of(game));
        when(movieService.getMovieById(any())).thenReturn(movie);

        var quizz = gameService.quizz(1000);

        assertNotNull(quizz);
    }

    @Test
    void quizzGameOverException() throws MovieNotFoundException {
        var movie = new Movie("dfsdfs", "dfsdfs", 10.5, 45);
        var game = new Game(1, 1, new Date(), new Date(), 0, 0, new ArrayList<>());

        when(gameRepository.getActiveGame(eq(1000))).thenReturn(Optional.of(game));
        when(movieService.getMovieById(any())).thenReturn(movie);

        var exception = assertThrows(GameOverException.class, () ->
                gameService.quizz(1000));

        assertNotNull(exception);
        assertTrue(exception.getMessage().contains("Ther's no movies pairs. Please finalize the game."));
    }

    @Test
    void quizzGameNotFoundException()  {

        when(gameRepository.getActiveGame(eq(1000))).thenReturn(Optional.empty());

        var exception = assertThrows(GameNotFoundException.class, () ->
                gameService.quizz(1000));

        assertNotNull(exception);
        assertTrue(exception.getMessage().contains("The user hasn't active games"));
    }

    @Test
    void quizzResponseSuccessHit() throws MovieNotFoundException, WrongAnswerException, GameOverException, GameNotFoundException {
        var movie = new Movie("dfsdfs", "dfsdfs", 10.5, 45);
        var gameMovie = new GameMovie(1,  null, "dfsdfs", "fdfsd");
        var gameMovieList = new ArrayList<GameMovie>();
        gameMovieList.add(gameMovie);

        var game = new Game(1, 1, new Date(), new Date(), 0, 0, gameMovieList);

        when(gameRepository.getActiveGame(eq(1000))).thenReturn(Optional.of(game));
        when(movieService.getMovieById(any())).thenReturn(movie);
        doNothing().when(gameRepository).update(any());
        doNothing().when(gameMovieRepository).delete(any());

        var hit = gameService.quizzResponse(1000, gameMovie.getMovieA());

        assertTrue(hit);
    }

    @Test
    void quizzResponseSuccessMistake() throws MovieNotFoundException, WrongAnswerException, GameOverException, GameNotFoundException {
        var movie = new Movie("dfsdfs", "dfsdfs", 10.5, 45);
        var gameMovie = new GameMovie(1,  null, "dsadas", "dfsdfs");
        var gameMovieList = new ArrayList<GameMovie>();
        gameMovieList.add(gameMovie);

        var game = new Game(1, 1, new Date(), new Date(), 0, 0, gameMovieList);

        when(gameRepository.getActiveGame(eq(1000))).thenReturn(Optional.of(game));
        when(movieService.getMovieById(any())).thenReturn(movie);
        doNothing().when(gameRepository).update(any());
        doNothing().when(gameMovieRepository).delete(any());

        var hit = gameService.quizzResponse(1000, gameMovie.getMovieA());

        assertFalse(hit);
    }

    @Test
    void quizzResponseWrongAnswerException() throws MovieNotFoundException, GameOverException, GameNotFoundException {
        var movie = new Movie("dfsdfs", "dfsdfs", 10.5, 45);
        var gameMovie = new GameMovie(1,  null, "dsadas", "fdfsd");
        var gameMovieList = new ArrayList<GameMovie>();
        gameMovieList.add(gameMovie);

        var game = new Game(1, 1, new Date(), new Date(), 0, 0, gameMovieList);

        when(gameRepository.getActiveGame(eq(1000))).thenReturn(Optional.of(game));
        when(movieService.getMovieById(any())).thenReturn(movie);
        doNothing().when(gameRepository).update(any());
        doNothing().when(gameMovieRepository).delete(any());

        var exception = assertThrows(WrongAnswerException.class, () ->
                gameService.quizzResponse(1000, "fsdfsdfs"));

        assertNotNull(exception);
        assertTrue(exception.getMessage().contains("Invalid response. Please select the movie id"));
    }

    @Test
    void quizzResponseGameOverException()  {
        var gameMovieList = new ArrayList<GameMovie>();

        var game = new Game(1, 1, new Date(), new Date(), 0, 0, gameMovieList);

        when(gameRepository.getActiveGame(eq(1000))).thenReturn(Optional.of(game));

        var exception = assertThrows(GameOverException.class, () ->
                gameService.quizzResponse(1000, "fsdfsdfs"));

        assertNotNull(exception);
        assertTrue(exception.getMessage().contains("Ther's no movies pairs. Please finalize the game."));
    }

    @Test
    void quizzResponseGameNotFoundException()  {

        when(gameRepository.getActiveGame(eq(1000))).thenReturn(Optional.empty());

        var exception = assertThrows(GameNotFoundException.class, () ->
                gameService.quizzResponse(1000, "fsdfsdfs"));

        assertNotNull(exception);
        assertTrue(exception.getMessage().contains("The user hasn't active games"));
    }

    @Test
    void testLoadRanking() {
        when(userService.ranking()).thenReturn(new ArrayList<>());

        var ranking = gameService.loadRanking();

        assertNotNull(ranking);
    }
}