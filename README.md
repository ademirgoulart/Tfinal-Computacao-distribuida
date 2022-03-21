# Tfinal-Computacao-distribuida

Projeto - Implementação do Padrão SAGA
O padrão SAGA (Garcia Molina & Salem, 1987) foi proposto originalmente visando a execução de transações distribuídas de longa duração. Segundo seus autores, os protocolos tradicionalmente usados para coordenação de transações (como 2PC e 3PC) causam indisponibilidade dos dados durante a execução, o que pode ser proibitivo no caso de transações de longa duração devido ao impacto causado no desempenho do sistema. Buscando evitar a indisponibilidade do sistema, o padrão SAGA emprega operações de compensação para desfazer partes da transação executadas anteriormente caso esta venha a ser abortada.

Segundo o padrão SAGA, uma transação distribuída de longa duração T é composta por n subtransações {T1, T2, ..., Tn}. A execução correta da transação implica em executar todas as subtransações corretamente. Caso haja algum problema ao longo da execução, devem ser executadas operações de compensação {C1, C2, ..., Cn-1}, na ordem inversa de execução, para cada subtransação executada anteriormente. 

Apesar de ter mais de três décadas, o padrão SAGA ganhou grande importância com a criação das primeiras aplicações de comércio eletrônico, cenário no qual ele continua sendo utilizado até os dias atuais. 

Cenário de Aplicação
Você deve implementar o padrão SAGA em uma plataforma de cursos online. O padrão será usado quando o usuário se inscrever em algum curso oferecido pela plataforma. A inscrição no curso será executada utilizando quatro subsistemas:

Módulo de Inscrição: recebe os dados necessários para inscrição no curso e cria um registro de inscrição no sistema, com estado 'pendente'; somente ao final da transação esse estado será modificado para 'vigente'; em caso de falha nos passos subsequentes, o estado será modificado para 'cancelada'. 
Módulo de Pagamento: responsável por processar o pagamento da taxa de inscrição no curso; deve cancelar a inscrição caso o pagamento (por meio de cartão de crédito) seja recusado. 
Emissão de Nota Fiscal: gera uma nota fiscal de serviço para o curso adquirido pelo usuário; em caso de falha na geração da nota fiscal (por exemplo, caso o usuário tenha fornecido um CPF incorreto), o pagamento e a inscrição devem ser cancelados. 
Controle de Acesso: libera o acesso ao conteúdo do curso para o usuário, concluindo a transação.
A execução da transação completa implica  em executar todas as operações necessárias (ou seja, as subtransações) corretamente em cada subsistema. Caso a execução de uma das operações resulte em falha, as operações executadas anteriormente devem ser compensadas, ou seja, deve ser executada uma operação para desfazer o que foi feito anteriormente. Uma falha permanente de comunicação com um dos subsistemas também deve ser interpretada como uma falha na execução da transação, que deve resultar no seu cancelamento e na compensação das operações executadas previamente. 

A sequência de operações pode ser executada seguindo uma das abordagens descritas a seguir:

Orquestração: um elemento central - o orquestrador - é responsável por invocar a sequência de operações de cada um dos subsistemas que compõem a transação;
Coreografia: os próprios subsistemas coordenam a execução da transação de forma cooperativa e autônoma, sem necessitar de um elemento centralizador que os diga quando devem executar as operações. 
Requisitos do Projeto

Observe os seguintes requisitos na sua implementação:

A interação entre os subsistemas deve ser feita utilizando uma das tecnologias vistas ao longo da disciplina (inclusive nos seminários);
Pode ser usada qualquer linguagem de programação de alto nível (ou seja, legível por humanos); 
Cada subsistema deve ser implementado em um processo independente, podendo ser executados na mesma máquina ou em máquinas virtuais/contêineres/etc. diferentes (opte pelo que lhe parecer mais prático);
O sistema (e cada um de seus subsistemas) deve suportar a execução de transações de forma concorrente;
O processamento da transação em cada subsistema deve ser registrado por meio de mensagens impressas na tela, em uma interface gráfica simples ou em arquivos de log;
A falha na execução das operações pode ser determinada de forma aleatória ou por meio da interface do programa;
Crie um programa de teste que envie requisições ao sistema a uma taxa que permita acompanhar o andamento da execução.

# Solução usando JAVA RMI 

