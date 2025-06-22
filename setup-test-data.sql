-- Script para inserir dados de teste para as APIs de Support Ticket

-- Inserir Campus
INSERT INTO campus (id, name, address, created_at, last_modified_at) VALUES 
(1, 'Campus Central', 'Rua Principal, 123', NOW(), NOW())
ON CONFLICT (id) DO NOTHING;

-- Inserir Category
INSERT INTO category (id, name, description, created_at, last_modified_at) VALUES 
(1, 'Sala de Aula', 'Salas para aulas regulares', NOW(), NOW())
ON CONFLICT (id) DO NOTHING;

-- Inserir Room
INSERT INTO room (id, identifier, floor, capacity, descrition, status, campus_id, category_id, created_at, last_modified_at) VALUES 
(1, 'SALA-101', 1, 30, 'Sala de aula com projetor e ar condicionado', 'AVAILABLE', 1, 1, NOW(), NOW())
ON CONFLICT (id) DO NOTHING;

-- Inserir User
INSERT INTO users (id, full_name, cpf, phone_number, email, password, status, created_at, last_modified_at) VALUES 
(1, 'João Silva', '12345678901', '11999999999', 'joao@example.com', 'senha123', 'ACTIVE', NOW(), NOW()),
(2, 'Maria Santos', '98765432109', '11888888888', 'maria@example.com', 'senha456', 'ACTIVE', NOW(), NOW())
ON CONFLICT (id) DO NOTHING;

-- Inserir RoomBooking
INSERT INTO room_booking (id, title, reason, check_in, check_out, status, room_id, created_at, last_modified_at) VALUES
(1, 'Reunião de Projeto', 'Discussão sobre o projeto X', '2025-06-22 09:00:00', '2025-06-22 11:00:00', 'APPROVED', 1, NOW(), NOW()),
(2, 'Aula de Matemática', 'Aula regular de matemática', '2025-06-22 14:00:00', '2025-06-22 16:00:00', 'APPROVED', 1, NOW(), NOW())
ON CONFLICT (id) DO NOTHING;

-- Resetar sequences para evitar conflitos
SELECT setval('campus_id_seq', (SELECT MAX(id) FROM campus));
SELECT setval('category_id_seq', (SELECT MAX(id) FROM category));
SELECT setval('room_id_seq', (SELECT MAX(id) FROM room));
SELECT setval('users_id_seq', (SELECT MAX(id) FROM users));
SELECT setval('room_booking_id_seq', (SELECT MAX(id) FROM room_booking));
