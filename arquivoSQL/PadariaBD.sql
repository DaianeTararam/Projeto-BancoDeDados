CREATE DATABASE PadariaBD
GO
USE PadariaBD
GO
CREATE TABLE Categoria(
codigo				INT				IDENTITY(1,1),
nome				VARCHAR(100)	NOT NULL

PRIMARY KEY(codigo)
)
GO
CREATE TABLE Produto (
codigo				INT				IDENTITY(1000, 1),
nome				VARCHAR(50)		NOT NULL,
valorUnitario		DECIMAL(4,2)	NOT NULL,
categoriaCodigo		INT				NOT NULL

PRIMARY KEY(codigo)
FOREIGN KEY(categoriaCodigo) REFERENCES Categoria(codigo)
)
GO
CREATE TABLE Comanda(
codigo				INT				NOT NULL

PRIMARY KEY(codigo)
)
GO
CREATE TABLE ItemComanda(
comandaCodigo		INT				NOT NULL,
produtoCodigo		INT				NOT NULL,
quantidade			INT				NOT NULL

PRIMARY KEY(comandaCodigo, produtoCodigo)
FOREIGN KEY(comandaCodigo) REFERENCES Comanda(codigo),
FOREIGN KEY(produtoCodigo) REFERENCES Produto(codigo)
) 
GO
GO
CREATE TABLE Pedido(
codigo               INT			IDENTITY(1, 1),
dataHora             DATE           DEFAULT GETDATE(),
valorTotal           INT            NULL

PRIMARY KEY(codigo)
)
GO
CREATE TABLE ItemPedido(
pedidoCodigo		INT				NOT NULL,
produtoCodigo		INT				NOT NULL,
quantidade			INT				NOT NULL

PRIMARY KEY(pedidoCodigo, produtoCodigo)
FOREIGN KEY(pedidoCodigo) REFERENCES Pedido(codigo)
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
GO
INSERT INTO Comanda VALUES
(100),
(101),
(102),
(103),
(104),
(105)
