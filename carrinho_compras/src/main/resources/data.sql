INSERT INTO restaurante (id, cep, complemento, nome) VALUES
(1L, '00000001', 'Endereço Complemento 1', 'Restaurante 1'),
(2L, '00000002', 'Endereço Complemento 2', 'Restaurante 2');

INSERT INTO cliente (id, cep, complemento, nome) VALUES
(1l, '00000003', 'Endereço Cliente 1', 'Carlos'),
(2l, '00000004', 'Endereço Cliente 2', 'Alonso'),
(3l, '00000005', 'Endereço Cliente 3', 'Clóvis');

INSERT INTO produto (id, nome, valor_unitario, disponivel, restaurante_id) VALUES
(1L, 'Arroz e Feijão Executivo', 25.99, true, 1L),
(2L, 'Arroz e Feijão Executivo', 22.00, true, 2L),
(3L, 'Arroz e Feijão Executivo + Bife', 29.99, true, 1L),
(4L, 'Arroz e Feijão Executivo + Filé de Frango', 27.90, true, 2L);

INSERT INTO sacola (id, forma_pagamento, cliente_id, valor_total, finalizada) VALUES
(1L, 0, 1L, 0, false),
(2L, 1, 2L, 0, true),
(3L, 0, 1L, 0, false);