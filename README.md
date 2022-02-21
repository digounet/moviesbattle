# Movies Battle #

API desenvolvida em SpringBoot

### **Endpoints** ###

**Todos os endpoints do jogo requerem que o usuário esteja autenticado. Passar o token no header**

- __Autenticação (POST): `http://localhost:8080/auth`.
- Request: ``
{
  "username": "pablo",
  "password": "123456"
  }
``
- Response: Bearer token
- __Novo jogo (GET): `http://localhost:8080/startgame`.
- Response: JSON contendo detalhes do jogo
- __Quizz (GET): `http://localhost:8080/quizz`.
- Response: JSON contendo filmes para votação
- __Quizz Response (GET): `http://localhost:8080/quizz/response?movieId=tt0106062`.
- Request (query string): ID do filme votado
- Response: JSON informando se acertou ou não
- __End Game (GET): `http://localhost:8080/endgame`.
- Response: JSON contendo detalhes do jogo
- __End Game (GET): `http://localhost:8080/ranking`.
- Response: JSON com os melhores 10 jpgadores
-