# Trabalho Prático — Etapa 1 (TDD)

## Resumo
Projeto de TDD para implementação de um sistema de simulação do campeonato brasileiro de futebol, o Brasileirão, com rodadas, partidas, classificação.

## Tecnologias utilizadas

* Java
* JUnit 4
* Eclipse IDE
* 
## Conteúdo principal

- src/app — implementação
- src/exceptions — exceções do projeto
- src/tst — testes unitários JUnit e suíte AllTests

## O que o projeto faz? (visão geral)
Esta aplicação gerencia o Brasileirão, executando as seguintes funcionalidades principais:

* **Sorteio de Partidas:** Realiza o sorteio dos jogos para compor cada rodada do campeonato.
* **Garantia de Jogos Únicos:** Assegura que não existam jogos duplicados (mesmo mandante vs. visitante) ao longo de todas as rodadas.
* **Cálculo de Pontuação:** Calcula a pontuação dos times a cada rodada, seguindo os critérios de vitória (3 pontos), empate (1 ponto) e derrota (0 pontos).
* **Cálculo de Estatísticas:** Processa os resultados de cada partida para calcular o número de vitórias, gols marcados, gols sofridos e o saldo de gols de cada time.
* **Critério de Desempate:** Aplica o critério de desempate por **número de vitórias** para classificar corretamente times que possuem a mesma pontuação, além de garantir também os outros critérios explicitados no enunciado do Trabalho Prático 1.

## Testes relevantes
Testes que verificam as funcionalidades principais solicitadas no Trabalho Prático 1: 

- tst/TstSorteioRodada.java
- tst/TstJogoRepetidoRodada.java
- tst/TstRankingPorRodada.java

Cabe lembrar que outros testes também foram realizados para verificação das funcionalidades implementadas, e todos estão englobados na suíte.

### Como executar os testes
- Pelo IDE (como Eclipse, Visual Studio): executar a suíte `tst.AllTests` ou as classes de teste individuais.

## Equipe

Os nomes dos integrantes da equipe podem ser encontrados na Tabela 1.

<div align="center">
<font size="3"><p style="text-align: center"><b>Tabela 1:</b> Integrantes da equipe</p></font>

<table>
  <tr>
    <td align="center"><a href="http://github.com/ailujana"><img style="border-radius: 50%;" src="http://github.com/ailujana.png" width="100px;" alt=""/><br /><sub><b>Ana Júlia Mendes</b></sub></a><br/>
    <td align="center"><a href="http://github.com/julia-fortunato"><img style="border-radius: 50%;" src="http://github.com/julia-fortunato.png" width="100px;" alt=""/><br /><sub><b>Júlia Fortunato</b></sub></a><br/><a href="Link git" title="Rocketseat"></a></td>
    <td align="center"><a href="https://github.com/luanasoares0901"><img style="border-radius: 50%;" src="https://github.com/luanasoares0901.png" width="100px;" alt=""/><br/><sub><b>Luana Ribeiro</b></sub></a><br/>
    <td align="center"><a href="http://github.com/Oleari19"><img style="border-radius: 50%;" src="http://github.com/Oleari19.png" width="100px;" alt=""/><br><sub><b>Maria Clara Oleari</b></sub></a><br/>
  </tr>
</table>

<font size="3"><p style="text-align: center"><b>Autor:</b> <a href="https://github.com/julia-fortunato">Júlia Fortunato</a>, 2025</p></font>

</div>
