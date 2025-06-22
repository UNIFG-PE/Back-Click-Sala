#!/bin/bash

# Script para testar as APIs de Support Ticket
BASE_URL="http://localhost:8080/api/v1/supporttickets"

echo "=== Testando APIs de Support Ticket ==="
echo

# Função para fazer requisições com autenticação básica (conforme configurado no SecurityConfig)
make_request() {
    curl -u "SDMUnifgOdaback8gs2:SDM7Unifg9DJEwh" "$@"
}

echo "1. Testando status do controller..."
make_request -X GET "$BASE_URL/status"
echo -e "\n"

echo "2. Listando todos os Support Tickets..."
make_request -X GET "$BASE_URL" -H "Content-Type: application/json"
echo -e "\n"

echo "3. Tentando criar um novo Support Ticket (pode falhar se não houver dados)..."
make_request -X POST "$BASE_URL" \
  -H "Content-Type: application/json" \
  -d '{
    "reason": "Problema com o projetor da sala",
    "roomBookingId": 1
  }' || echo "Erro esperado: RoomBooking com ID 1 não existe ainda"
echo -e "\n"

echo "4. Tentando criar outro Support Ticket com atendente (pode falhar se não houver dados)..."
make_request -X POST "$BASE_URL" \
  -H "Content-Type: application/json" \
  -d '{
    "reason": "Ar condicionado não está funcionando",
    "attendentId": 1,
    "roomBookingId": 1
  }' || echo "Erro esperado: User ou RoomBooking não existem ainda"
echo -e "\n"

echo "5. Buscando Support Ticket por ID (ID: 1)..."
make_request -X GET "$BASE_URL/1" -H "Content-Type: application/json"
echo -e "\n"

echo "6. Atualizando Support Ticket (ID: 1)..."
make_request -X PUT "$BASE_URL/1" \
  -H "Content-Type: application/json" \
  -d '{
    "reason": "Problema com o projetor - resolvido parcialmente",
    "attendentId": 1,
    "roomBookingId": 1
  }'
echo -e "\n"

echo "7. Listando todos os Support Tickets novamente..."
make_request -X GET "$BASE_URL" -H "Content-Type: application/json"
echo -e "\n"

echo "8. Deletando Support Ticket (ID: 2)..."
make_request -X DELETE "$BASE_URL/2"
echo -e "\n"

echo "9. Verificando se foi deletado..."
make_request -X GET "$BASE_URL" -H "Content-Type: application/json"
echo -e "\n"

echo "=== Testes concluídos ==="
