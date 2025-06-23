# Projeto-BancoDeDados - Sistema de Comanda Eletrônica
Este projeto foi desenvolvido para o gerenciamento de comandas eletrônicas em padaria, no qual é possível acompanhar pedidos e finalizar pagamentos de forma intuitiva. Abaixo estão todas as fases para desenvolvimento do projeto.

## FASE 1 - Levantamento e Análise de Requisitos

Nesta fase, realizamos a identificação e documentação dos requisitos do sistema, conforme estudado em Engenharia de Software.

### 📌 Requisitos Funcionais

| **ID**  | **Descrição**                                      | **Ator**    |
|--------|--------------------------------------------------|-------------|
| RF01   | O sistema deve permitir criar uma nova comanda. | Sistema     |
| RF02   | O sistema deve gerar um ID para a comanda automaticamente. | Sistema |
| RF03   | O sistema deve permitir registrar produtos à comanda. | Atendente |
| RF04   | O sistema deve permitir remover produtos da comanda. | Atendente |
| RF05   | O sistema deve permitir editar a quantidade de um produto na comanda. | Atendente |
| RF06   | O sistema deve calcular automaticamente o total da comanda. | Sistema |
| RF07   | O sistema deve registrar o pagamento e finalizar a comanda. | Atendente |
| RF08   | O sistema deve gerar um histórico das comandas. | Atendente |
| RF09   | O sistema deve armazenar o histórico das comandas finalizadas. | Sistema |
| RF10   | O sistema deve permitir consultar comandas finalizadas. | Atendente |

### 📌 Requisitos Não Funcionais

| **ID**   | **Descrição** |
|----------|--------------|
| RNF01    | O sistema deve ser desenvolvido em Java com JavaFX. |
| RNF02    | O sistema deve ser SQL Server. |
| RNF03    | O sistema deve permitir atualizações de produtos e preços de forma simples e rápida. |
| RNF04    | O processo de fechamento da comanda deve ser transparente para os clientes. |
| RNF05    | O armazenamento de dados deve ser organizado para facilitar a consulta de comandas anteriores. |
| RNF06    | A interface do sistema deve ser intuitiva e de fácil uso para atendentes e caixas. |
| RNF07    | O desempenho do sistema deve permitir múltiplos acessos simultâneos sem perda de performance. |
| RNF08    | O sistema deve garantir a segurança dos dados dos clientes e das transações. |
| RNF09    | O banco de dados deve suportar escalabilidade para o crescimento da padaria. |
| RNF10    | O cálculo dos valores das comandas deve ser preciso, evitando erros nos pagamentos. |
| RNF11    | O processamento de dados deve ser rápido para evitar filas no caixa. |

## FASE 2 - Modelagem e Diagramação  

Nesta fase, realizamos a **modelagem do sistema**, incluindo alguns diagramas essenciais para entender a estrutura do sistema.  

### Diagramas Criados  

✅ **Diagrama de Caso de Uso**  
<div align="center">
  <img src="https://github.com/DaianeTararam/Projeto-BancoDeDados/blob/main/docs/diagramas/DiagramaCasoUso.png?raw=true" width="600px">
</div>

✅ **Diagrama de Classes**  
<div align="center">
  <img src="https://github.com/DaianeTararam/Projeto-BancoDeDados/blob/main/docs/diagramas/DiagramaClasses.png?raw=true" width="600px">
</div>

✅ **Diagrama de Entidade Relacionamento**  
<div align="center">
  <img src="https://github.com/DaianeTararam/Projeto-BancoDeDados/blob/main/docs/diagramas/DiagramaBancoDados.png?raw=true" width="600px">
</div>


## FASE 3 - Protótipos

Nesta fase, desenvolvemos os **protótipos** do sistema utilizando a ferramenta **Balsamiq**.

### Protótipos Criados  

✅ **Tela de Menu Principal**  
<div align="center">
  <img src="https://github.com/DaianeTararam/Projeto-BancoDeDados/blob/main/docs/prototipos/Menu%20Principal%20-%20parte%201.png?raw=true" width="500px">
</div>


✅ **Tela de Histórico de Comandas**  
<div align="center">
  <img src="https://github.com/DaianeTararam/Projeto-BancoDeDados/blob/main/docs/prototipos/Prot%C3%B3tipo%20-%20Hist%C3%B3rico%20das%20Comandas.png?raw=true" width="500px">
</div>

✅ **Tela da Comanda**  
<div align="center">
  <img src="https://github.com/DaianeTararam/Projeto-BancoDeDados/blob/main/docs/prototipos/Prot%C3%B3tipo%20-%20Add%20Produtos.png?raw=true" width="500px">
</div>


## FASE 4 - Desenvolvimento em Java com JavaFX e SGBD SQL Server  

Nesta fase, desenvolvemos a aplicação utilizando **Java com JavaFX** na interface e o **SQL Server** para o banco de dados.

🔗 **Links para os códigos e arquivos**:

- [Interface JavaFX](https://github.com/DaianeTararam/Projeto-BancoDeDados/tree/main/programa)  
- [SQL Server](https://github.com/DaianeTararam/Projeto-BancoDeDados/tree/main/arquivoSQL)


### Alunos que contribuiram com o projeto:

<a href="https://github.com/DaianeTararam/Projeto-BancoDeDados/graphs/contributors">
  <img src="https://contrib.rocks/image?repo=DaianeTararam/Projeto-BancoDeDados&cache_bust=1"/>
</a>

### 
<div>
  Criado por <a href="https://github.com/Joaoftito">João Francisco</a>,  
  <a href="https://github.com/DaianeTararam">Daiane Tararam</a> e  
  <a href="https://github.com/JuanMitsuhiro">Juan Mitsuhiro</a>.
</div>

