CREATE DATABASE PadariaBD
GO
USE PadariaBD

GO
CREATE TABLE Categoria(
codigo				INT                         IDENTITY(1,1),
nome				VARCHAR(100)				NOT NULL
PRIMARY KEY(codigo)
)

GO
CREATE TABLE Produto (
codigo				INT							IDENTITY(1000, 1),
nome				VARCHAR(50)					NOT NULL,
valorUnitario		DECIMAL(4,2)				NOT NULL,
CategoriaCodigo		INT							NOT NULL
PRIMARY KEY(codigo)
FOREIGN KEY(CategoriaCodigo) REFERENCES Categoria(codigo)
)

GO
CREATE TABLE Comanda(
codigo				INT,
PRIMARY KEY(codigo)
)

GO
CREATE TABLE ProdutoPedido(
quantidade			INT							NOT NULL,
produtoCodigo		INT							NOT NULL,
pedidoCodigo        INT                         NOT NULL
PRIMARY KEY(produtoCodigo, pedidoCodigo)
FOREIGN KEY(produtoCodigo) REFERENCES Produto(codigo),
FOREIGN KEY(pedidoCodigo) REFERENCES Pedido(codigo)
)

GO
CREATE TABLE FormaPagamento(
codigo				INT							NOT NULL,
nome                VARCHAR(30)                 NOT NULL
PRIMARY KEY(codigo)
)

GO
CREATE TABLE Pedido(
codigo              INT                         IDENTITY(10000, 1),
dataHora            DATETIME                    DEFAULT GETDATE(),
comandaCodigo       INT                         NOT NULL,
formaPagamentoCodigo INT                        NOT NULL
PRIMARY KEY(codigo)
FOREIGN KEY(comandaCodigo) REFERENCES Comanda(codigo),
FOREIGN KEY(formaPagamentoCodigo) REFERENCES FormaPagamento(codigo)
)

GO
INSERT INTO Categoria VALUES
('Paes'),
('Doces e Confeitaria'),
('Salgados'),
('Lanches e Sanduiches'),
('Bebidas')

GO
INSERT INTO Produto VALUES
('Pao frances', 1.50, 1),
('Pao leite', 2.00, 1),
('Baguete', 2.50, 1),
('Sonho', 3.68, 2),
('Bolo (fatia)', 7.90, 2),
('Tiramisu', 18.00, 2),
('Brownie', 8.00, 2),
('Coxinha', 5.00, 3),
('Bauru', 5.00, 3),
('Esfirra', 5.00, 3),
('Kibe', 5.00, 3),
('Misto quente', 9.50, 4),
('X-Burguer', 13.00, 4),
('X-Egg', 14.00, 4),
('Cafe', 2.50, 5),
('Refrigerante 350ml', 4.50, 5),
('Suco natural', 10.00, 5)

INSERT INTO Comanda (codigo) VALUES
(100),
(101),
(102),
(103),
(104),
(105)

INSERT INTO FormaPagamento VALUES
(1, 'Cartão'),
(2, 'Dinheiro'),
(3, 'Pix')

INSERT INTO ProdutoPedido VALUES
(5, 1000, 10000),
(5, 1001, 10000),
(2, 1002, 10000),
(2, 1003, 10001),
(1, 1004, 10001),
(1, 1007, 10001),
(1, 1008, 10002),
(1, 1009, 10002),
(1, 1010, 10003),
(1, 1015, 10004)

INSERT INTO Pedido (comandaCodigo, formaPagamentoCodigo) VALUES
(100, 1),
(101, 2),
(102, 3),
(103, 3),
(101, 1)

-- Consulta de pedidos anteriores da comanda
-- Mostra o código do pedido, o código da comanda, o valor total do pedido, a forma de pagamento e da data da transação
SELECT pe.codigo AS codigoPedido, c.codigo AS codigoComanda, 
       SUM(pr.valorUnitario * pp.quantidade) AS valorTotal, fp.nome AS formaPagamento, pe.dataHora
FROM Produto pr INNER JOIN ProdutoPedido pp
ON pr.codigo = pp.produtoCodigo
INNER JOIN Pedido pe
ON pe.codigo = pp.pedidoCodigo
INNER JOIN Comanda c
ON c.codigo = pe.comandaCodigo
INNER JOIN FormaPagamento fp
ON fp.codigo = pe.formaPagamentoCodigo
GROUP BY pe.codigo, c.codigo, fp.nome, pe.dataHora

--Total de vendas (R$) realizadas por todos os pedidos
SELECT SUM(pr.valorUnitario * pp.quantidade) AS valorTotalVendas
FROM Produto pr INNER JOIN ProdutoPedido pp
ON pr.codigo = pp.produtoCodigo
INNER JOIN Pedido pe
ON pe.codigo = pp.pedidoCodigo

--Quantidade total de produtos vendidos por categoria
SELECT SUM(pp.quantidade) AS qtdProdutosVendidos
FROM Categoria ct INNER JOIN Produto pr
ON ct.codigo = pr.CategoriaCodigo
INNER JOIN ProdutoPedido pp
ON pr.codigo = pp.produtoCodigo
GROUP BY ct.nome

--Pedidos com valor total acima de R$ 20
SELECT pe.codigo, SUM(pp.quantidade * pr.valorUnitario) AS totalPedido
FROM Produto pr INNER JOIN ProdutoPedido pp
ON pr.codigo = pp.produtoCodigo
INNER JOIN Pedido pe
ON pe.codigo = pp.pedidoCodigo
GROUP BY pe.codigo
HAVING SUM(pp.quantidade * pr.valorUnitario) > 20

--Valor total de uma comanda específica
SELECT c.codigo, SUM(pp.quantidade * pr.valorUnitario) AS totalComanda
FROM Produto pr INNER JOIN ProdutoPedido pp
ON pr.codigo = pp.produtoCodigo
INNER JOIN Pedido pe
ON pe.codigo = pp.pedidoCodigo
INNER JOIN Comanda c
ON c.codigo = pe.comandaCodigo
WHERE c.codigo = 101           --Aqui deve colocar o id da comanda que se deseja saber o valor total
GROUP BY c.codigo