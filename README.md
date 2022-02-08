# Descrição

Este projeto tem como objetivo encontar os possíveis caminhos de um grafo
tendo como entrada um arquivo csv no seguinte formato

````
A;B;C;D;E;F;G;H
0;1;0;0;0;0;0;0
0;0;0;0;0;0;0;0
1;0;0;0;1;0;0;1
1;0;1;0;1;0;0;0
1;0;0;0;0;1;1;0
1;0;0;0;0;0;0;0
0;0;0;0;0;0;0;0
0;0;0;0;1;0;0;0
````

Onde a primeira linha contém o nome dos nós e as demais contém
a relação (0 se não conectado, 1 se conectado).

## Aplicação
O arquivo **repo2.csv** possui as dependências de um projeto verificado
pelo comando *mvn dependencies:tree*. A partir disso configuramos o
_Jenkinsfile_ de cada projeto informando o _upstreamProject_ com base no
resultado dessa aplicação.

## Execução
No arquivo Main.java deve-se descomentar um dos três arquivos

````
  static final String FILENAME = "src/br/com/graph/path/repo2.csv";
  static final String FILENAME = "src/br/com/graph/path/repo_dependencies.csv";
  static final String FILENAME = "src/br/com/graph/path/example1.csv";
````

Em seguida deve-se indicar qual o nó raiz (**NO_RAIZ**) e qual o tamanho do caminho 
(**SIZE_PATH_WANTED**).
Para o exem plo acima, escolhendo o NO_RAIZ 2 teremos o seguinte resultado

````
Printar caminho maiores do que: Caminhos maiores do que 1
index: 0 | caminho: [C, A, B]
index: 1 | caminho: [C, E, A, B]
index: 2 | caminho: [C, E, F, A, B]
index: 3 | caminho: [C, E, G]
index: 4 | caminho: [C, H, E, A, B]
index: 5 | caminho: [C, H, E, F, A, B]
index: 6 | caminho: [C, H, E, G]
######################

Maior(es) caminho(s)
Index = 5
Caminho = [C, H, E, F, A, B]
````

