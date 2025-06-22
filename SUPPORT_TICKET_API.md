# Support Ticket API Documentation

## Overview
Esta documentação descreve as APIs CRUD para gerenciamento de Support Tickets no sistema Click Sala.

## Base URL
```
/api/v1/supporttickets
```

## Endpoints

### 1. Listar todos os Support Tickets
**GET** `/api/v1/supporttickets`

**Response:**
```json
[
  {
    "id": 1,
    "reason": "Problema com o projetor",
    "attendentId": 2,
    "attendentName": "João Silva",
    "roomBookingId": 5,
    "roomBookingTitle": "Reunião de Projeto",
    "createdAt": "2024-01-15T10:30:00Z",
    "lastModifiedAt": "2024-01-15T10:30:00Z"
  }
]
```

### 2. Buscar Support Ticket por ID
**GET** `/api/v1/supporttickets/{id}`

**Response:**
```json
{
  "id": 1,
  "reason": "Problema com o projetor",
  "attendentId": 2,
  "attendentName": "João Silva",
  "roomBookingId": 5,
  "roomBookingTitle": "Reunião de Projeto",
  "createdAt": "2024-01-15T10:30:00Z",
  "lastModifiedAt": "2024-01-15T10:30:00Z"
}
```

### 3. Criar novo Support Ticket
**POST** `/api/v1/supporttickets`

**Request Body:**
```json
{
  "reason": "Ar condicionado não está funcionando",
  "attendentId": 3,
  "roomBookingId": 7
}
```

**Response:** `201 Created`
```json
{
  "id": 2,
  "reason": "Ar condicionado não está funcionando",
  "attendentId": 3,
  "attendentName": "Maria Santos",
  "roomBookingId": 7,
  "roomBookingTitle": "Aula de Matemática",
  "createdAt": "2024-01-15T11:00:00Z",
  "lastModifiedAt": "2024-01-15T11:00:00Z"
}
```

### 4. Atualizar Support Ticket
**PUT** `/api/v1/supporttickets/{id}`

**Request Body:**
```json
{
  "reason": "Problema com o projetor - resolvido parcialmente",
  "attendentId": 2,
  "roomBookingId": 5
}
```

**Response:**
```json
{
  "id": 1,
  "reason": "Problema com o projetor - resolvido parcialmente",
  "attendentId": 2,
  "attendentName": "João Silva",
  "roomBookingId": 5,
  "roomBookingTitle": "Reunião de Projeto",
  "createdAt": "2024-01-15T10:30:00Z",
  "lastModifiedAt": "2024-01-15T11:15:00Z"
}
```

### 5. Deletar Support Ticket
**DELETE** `/api/v1/supporttickets/{id}`

**Response:** `204 No Content`

## Validações

### SupportTicketRequestDTO
- `reason`: Obrigatório, não pode estar em branco
- `roomBookingId`: Opcional, se fornecido deve referenciar um RoomBooking existente
- `attendentId`: Opcional, se fornecido deve referenciar um User existente

## Códigos de Erro

- `400 Bad Request`: Dados de entrada inválidos
- `404 Not Found`: Support Ticket, RoomBooking ou User não encontrado
- `500 Internal Server Error`: Erro interno do servidor

## Exemplos de Uso

### Criar um Support Ticket sem atendente
```bash
curl -X POST http://localhost:8080/api/v1/supporttickets \
  -H "Content-Type: application/json" \
  -d '{
    "reason": "Problema com a iluminação",
    "roomBookingId": 10
  }'
```

### Criar um Support Ticket sem room booking
```bash
curl -X POST http://localhost:8080/api/v1/supporttickets \
  -H "Content-Type: application/json" \
  -d '{
    "reason": "Problema geral no sistema"
  }'
```

### Atualizar um Support Ticket
```bash
curl -X PUT http://localhost:8080/api/v1/supporttickets/1 \
  -H "Content-Type: application/json" \
  -d '{
    "reason": "Problema com a iluminação - resolvido",
    "attendentId": 5,
    "roomBookingId": 10
  }'
```
