-- Inserir Relação Usuário-Perfil com IDs existentes
INSERT INTO tb_usuario_perfil (id_usuario, id_perfil, id_usuario_criacao)
VALUES
    (2, 1, 2),  -- João Silva como Admin (ID 2)
    (3, 2, 2),  -- Maria Oliveira como Usuário (ID 3)
    (4, 3, 2);  -- Carlos Pereira como Moderador (ID 4)