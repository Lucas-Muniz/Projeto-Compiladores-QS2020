# Projeto-Compiladores-QS2020
Projeto de transpilador da IsiLanguage para a linguagem Java desenvolvido para o curso de Compiladores (QS 2020) da UFABC.

Implementado por: Daniel Di Gênova, Gabriel Santos, Lucas Muniz e Luisa Salles.

* Tipos de variáveis:
  * Texto
  * Numérico (decimal)
  * Booleano (`verdadeiro` e `falso`)
  
* Comandos presentes:
  * Declaração de variáveis: `declare var.`
  * Atribuição de valores em variáveis: `var := valor.`
  * Leitura do teclado: `leia (var).`
  * Impressão na tela: `escreva ("exemplo").`
  * Estruturas de decisão:
    - Se - Senão: `se (condição) { comandos } senao { comandos }`
  * Estruturas de repetição:
    - Enquanto: `enquanto (condição) { comandos }`
    - Faça ... Enquanto : `faca { comandos } enquanto (condição).`
    - Para : `para (atribuição . condição . passo) { comandos }`
 
* Recursos disponíveis:
  * Verificação se as variáveis utilizadas foram declaradas.
  * Verificação se um valor do mesmo tipo foi atribuído a uma variável.
  * Suporte a operações matemáticas envolvendo os operadores: `+`, `-`, `/` e `*`. Pode ser utilizado parênteses para definição de precedência de operações.
  * Suporte aos operadores relacionais: `>`, `<`, `>=`, `<=`, `==` e `!=`.
  * Suporte a números com sinal positivo `+` e negativo `-`.
  * Suporte a operações booleanas envolvendo os seguintes operadores booleanos: `nao`, `e` e `ou`. Pode ser utilizado parênteses para definição de precedência de operações.
  * Verificação se uma variável declarada foi utilizada.
  * Geração do código da IsiLanguage para a linguagem Java. O código gerado é disponibilizado com indentação correta.
  * Suporte a códigos que possuem comentários da forma `/* texto do comentário */`. Os comentários são transferidos para o código gerado em Java.
  * Exibição dos comandos lidos no código de entrada.
