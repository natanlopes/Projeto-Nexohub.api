-- 1. Aumenta a coluna
ALTER TABLE usuarios ALTER COLUMN senha TYPE VARCHAR(255);

-- 2. Atualiza a senha de TODOS os usuários para o hash de "123456"
UPDATE usuarios SET senha = '$2a$10$Y50UaMFOxteibQEYCzwnzu.7jF.mjgT.y.0.h/1.f8/j/4.z.u';