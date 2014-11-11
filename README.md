jchampionship
=============

jchampionship

Tecnologias utilizadas
- Java 7
- Eclipse
- Mysql
- Spring MVC
- AngularJS
- JPA
- Bean Validation
- TDD
- Maven
- Bootstrap

# Regras

- Cadastrar Nova Edicao de um Campeonato
Ao Cadastrar nova edicao usuario tera um formulario wizard.
1. Edicao selecionando campeonato.
2. Grupos (A, B ou Serie A)
3. Jogadores
4. Times (Classificacao)
5. Jogos
6. Confirmacao do Cadastro da Edicao

# Calculo

- Ao lancar Eventos de um jogo (Gol, CA, CV), placar do jogo com status em andamento eh atualizado,
e ao finalizar o jogo o calculo eh disparado.
1. Para o calculo acontecer o jogo deve estar com status 2 (em andamento)
2. Classificacao de todos os times sera atualizado, Vencedor 3 pontos, Empate 1 ponto, GP, GC, J.
3. Se Jogador nao tem Registro no Jogador Info da Edicao, cadastra o mesmo.
4. Atualizacao Jogador para Edicao, Gols, jogos, CA, CV.
5. Jogo eh atualizado para Status Finalizado.
5. Apos setar o jogo para Finalizado, classificacao atual eh gravada no Historico, caso o historico ainda nao tenha
 sido gravado ou uma rodada a frente tenha sido tambem gravado (se alguma rodada ficou incompleta para atraz nao sera mais guardada no historico).  
    
# Retorna Calculo
...

