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
| RF07   | O sistema deve permitir a leitura da comanda no caixa. | Caixa |
| RF08   | O sistema deve registrar o pagamento e finalizar a comanda. | Caixa |
| RF09   | O sistema deve gerar um histórico das comandas. | Gerente |
| RF10   | O sistema deve armazenar o histórico das comandas finalizadas. | Sistema |
| RF11   | O sistema deve permitir consultar comandas finalizadas. | Gerente |

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

## 🏗️ FASE 2 - Modelagem e Diagramação  

Nesta fase, realizamos a **modelagem do sistema**, incluindo alguns diagramas essenciais para entender a estrutura do sistema.  

### 🔹 Diagramas Criados  
✅ **Diagrama de Caso de Uso** 

![Diagrama de Caso de Uso](https://raw.githubusercontent.com/DaianeTararam/Projeto-BancoDeDados/refs/heads/main/docs/diagramas/Caso%20de%20Uso%20Padaria.png?token=GHSAT0AAAAAADFVRGANWYTJDFXJP4QPNJBY2COG5IA)

✅ **Diagrama de Classes** 

![Diagrama de Classes](https://raw.githubusercontent.com/DaianeTararam/Projeto-BancoDeDados/refs/heads/main/docs/diagramas/Diagrama%20de%20Classe.png?token=GHSAT0AAAAAADFVRGANDOVHMXAIVO46A6TW2COG6AQ)

✅ **Diagrama de Relacionamento Entidade** → Estruturação das tabelas e relacionamentos  

![Diagrama de Relacionamento Entidade](https://raw.githubusercontent.com/DaianeTararam/Projeto-BancoDeDados/refs/heads/main/docs/diagramas/DiagramaBD.png?token=GHSAT0AAAAAADFVRGANYCLOKRIF2I637FDK2COHAFA)  

## FASE 3 - Prototipação da Interface  

Nesta fase, desenvolvemos os **protótipos** do sistema utilizando a ferramenta **Balsamiq**.

### 📌 Protótipos Criados  
✅ **Tela de Menu Principal**

![Tela de Menu Principal](https://raw.githubusercontent.com/DaianeTararam/Projeto-BancoDeDados/refs/heads/main/docs/prototipos/Menu%20Principal%20-%20parte%201.png?token=GHSAT0AAAAAADFVRGAMEW4JPJTSPAOXIREG2COHFKA.png)

✅ **Tela de Login do Controle de Vendas**

![Tela de Login do Controle de Vendas](https://raw.githubusercontent.com/DaianeTararam/Projeto-BancoDeDados/refs/heads/main/docs/prototipos/Menu%20Principal%20-%20parte%202.png?token=GHSAT0AAAAAADFVRGAN5XIZAS4CE4WAKFGU2COHNEQ.png)  

✅ **Tela de Histórico de Comandas**

![Tela de Histórico de Comandas](https://raw.githubusercontent.com/DaianeTararam/Projeto-BancoDeDados/refs/heads/main/docs/prototipos/Prot%C3%B3tipo%20-%20Hist%C3%B3rico%20das%20Comandas.png?token=GHSAT0AAAAAADFVRGAMQUDY3O2GSAKENJCI2COHDOQ.png)

✅ **Tela para Adicionar Produtos**

![Tela para Adicionar Produtos](https://raw.githubusercontent.com/DaianeTararam/Projeto-BancoDeDados/refs/heads/main/docs/prototipos/Prot%C3%B3tipo%20-%20Add%20Produtos.png?token=GHSAT0AAAAAADFVRGANFD5D74M4FNQ2DMNI2COHLTA.png) 

✅ **Tela para Finalizar Pagamento**
![Tela para Finalizar Pagamento](https://github.com/DaianeTararam/Projeto-BancoDeDados/blob/main/docs/prototipos/Prot%C3%B3tipo%20-%20Finalizar%20Pagamento.png) 

## FASE 4 - Desenvolvimento em Java com JavaFX e SGBD SQL Server  

links...

### Alunos que contribuiram com o projeto:

<a href="https://github.com/DaianeTararam/Projeto-BancoDeDados/graphs/contributors">
  <img src="https://contrib.rocks/image?repo=DaianeTararam/Projeto-BancoDeDados"/>
</a>

### 
<div>
  Criado por <a href="https://github.com/Joaoftito">João Francisco</a>,  
  <a href="https://github.com/DaianeTararam">Daiane Tararam</a> e  
  <a href="https://github.com/JuanMitsuhiro">Juan Mitsuhiro</a>.
</div>

