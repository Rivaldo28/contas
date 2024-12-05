-- Inserções na tabela Lançamento
INSERT INTO contas.lancamento (descricao, data_nascimento, data_pagamento, valor, observacao, tipo, id_categoria, id_pessoa) VALUES
                                                                                                                                 ('Compra no mercado', '2023-10-10', '2023-10-11', 150.50, 'Compras do mês', 'Despesa', 1, 1),
                                                                                                                                 ('Passagem de ônibus', NULL, '2023-10-15', 4.50, NULL, 'Despesa', 2, 2),
                                                                                                                                 ('Mensalidade escolar', '2010-05-01', '2023-11-01', 1000.00, 'Curso de inglês', 'Despesa', 3, 1),
                                                                                                                                 ('Show musical', NULL, '2023-12-01', 200.00, 'Ingressos para show', 'Despesa', 4, 2),
                                                                                                                                 ('Consulta médica', NULL, '2023-11-10', 300.00, 'Consulta de rotina', 'Despesa', 5, 3);