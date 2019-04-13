
# Exercício 3: Microservices (+Docker)

## Inicializar projeto

```sh
mvn sprint-boot:run
```

### Gerar container

```sh
./mvnw install dockerfile:build
```

### Rodar container

```sh
docker run -p 8000:8000 -t springio/order-service
```

## Pedidos

#### Listar

```sh
curl -X GET \
  http://localhost:8080/orders \
  -H 'content-type: application/json'
```

#### Inserir

```sh
curl -X POST \
  http://localhost:8080/orders \
  -H 'content-type: application/json' \
  -d '{
    "email": "userone@gmail.com",
    "fullName": "Primeiro Usuário",
    "shippingAddress": "Rua Um, 1",
    "quantity": 2,
    "price": 1000.00,
    "paymentMethod": "CREDIT",
    "date": "19/03/2019",
    "payment": {
        "brand": "VISA",
        "transactionType": "CREDIT",
        "cardNumber": "1568 4089 6557 9856",
        "expDate": "03/22",
        "cvv": 175,
        "price": 1000.00,
        "uuidAcquirerTransaction": null
    },
    	"items": [{
    	"description": "Item 1",
    	"price": 900.00
    },{
    	"description": "Item 2",
    	"price": 100.00
    }]
}'
```

#### Carregar

```sh
curl -X GET \
  http://localhost:8080/orders/1 \
  -H 'content-type: application/json'
```

#### Alterar

```sh
curl -X PUT \
  http://localhost:8080/orders/1 \
  -H 'content-type: application/json' \
  -d '{
    "email": "userone@gmail.com",
    "fullName": "Primeiro Usuário",
    "shippingAddress": "Rua Um, 1",
    "quantity": 2,
    "price": 1000.00,
    "paymentMethod": "CREDIT",
    "status": "PAYMENT_CONFIRMED",
    "date": "19/03/2019",
    "payment": {
        "brand": "VISA",
        "transactionType": "CREDIT",
        "cardNumber": "1568 4089 6557 9856",
        "expDate": "03/22",
        "cvv": 175,
        "price": 1000.00,
        "uuidAcquirerTransaction": -abcd1
    },
    "items": [{
    	"description": "Item 1",
    	"price": 900.00
    },{
    	"description": "Item 2",
    	"price": 100.00
    }]
}'
```

#### Remover

```sh
curl -X DELETE \
  http://localhost:8080/orders/1
```
