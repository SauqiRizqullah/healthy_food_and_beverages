# Spring Boot Healthy Food and Beverages

### Program ini dirancang untuk mendukung bisnis yang bertemakan makanan dan minuman sehat. Bisnis ini memberikan pelayanan untuk memenuhi kepuasan customer, serta memberikan edukasi betapa pentingnya menjaga pola hidup sehat dengan adanya pelayanan bisnis ini.

### Program ini mendukung fitur spesial bagi customer yang membeli membership untuk memberikan benefit kepada customer.

## Daftar Fitur
1. Menu (Create new Menu(POST), Get Menu by ID(GET), Get All Menus(GET), Update Price by Menu Name(PUT), Delete Menu by ID(DELETE))
2. Bonus (Create new Bonus(POST), Get Bonus by ID(GET), Get All Bonuses(GET), Update Poin by ID(PUT), Delete Bonus by ID(DELETE))
3. Membership (Create new Membership(POST), Get Membership by ID(GET), Get All Memberships(GET), Update Benefit by ID(PUT), Delete Membership by ID(DELETE))
4. Customer (Create new Customer(POST), Get Customer by ID(GET), Get All Customers(GET), Update Membership by ID(PUT), Delete Customer by ID(DELETE)) 
5. Transaction (Create Transaction(POST), Get All Transaction(GET))

## Ilustrasi Penggunaan Fitur

## 1. Menu

### Menu - Create new Menu
URL:
```
http://localhost:8080/api/v1/menus
```

Postman:

```
@RequestBody:
{
    "menuName" : "Chicken Cordon Bleu",
    "menuPrice" : 45000
}

```

### Menu - Get Menu by Id
URL:
```
http://localhost:8080/api/v1/menus/{menuId}
```

Postman:

```
@PathVariable (M001)

```

### Menu - Get All Menus
URL:
```
http://localhost:8080/api/v1/menus
```

Postman:
``` 
@RequestParam:

page: 1
size: 5
sortBy: menuName
direction: ASC
menuName: Chicken Cordon Bleu
```

### Menu - Update Price by Menu Name
URL:
``` 
http://localhost:8080/api/v1/menus/updatePrice?
```

Postman:
```
@RequestParam(Chicken Cordon Bleu)
@RequestParam(47000)
```

### Menu - Delete by Id
URL:
```
http://localhost:8080/api/v1/menus/{menuId}
```

Postman:
``` 
@PathVariable(M001)
```

## 2. Bonus

### Bonus - Create New Bonus
URL:
``` 
http://localhost:8080/api/v1/bonuses
```

Postman:
``` 
@RequestBody:

{
    "bonusName": "Shaker",
    "poin": 3
}
```

### Bonus - Get Bonus by Id
URL:
```
http://localhost:8080/api/v1/bonuses/{bonusId}
```

Postman:
``` 
@PathVariable(B001)
```

### Bonus - Get All Bonuses
URL:
``` 
http://localhost:8080/api/v1/bonuses
```

Postman:
```
@RequestParam:

bonusName: Shaker
```

### Bonus - Update Poin by Id
URL:
```
http://localhost:8080/api/v1/bonuses/{bonusId}
```

Postman:
```
@PathVariable(B001)
@RequestParam(5)

```

### Bonus - Delete by Id
URL:
```
http://localhost:8080/api/v1/bonuses/{bonusId}
```

Postman:
```
@PathVariable(B001)
```

## 3. Membership

### Membership - Create New Membership
URL:
```
http://localhost:8080/api/v1/memberships
```

Postman:
```
@RequestBody

{
    "membershipName": "SILVER",
    "benefit": "Anda akan mendapatkan 1 poin disetiap transaksinya"
}
```

### Membership - Get Membership by Id
URL:
```
http://localhost:8080/api/v1/memberships/{membershipId}
```

Postman:
```
@PathVariable(MEMBER002)
```

### Membership - Get All Memberships
URL:
```
http://localhost:8080/api/v1/memberships
```

Postman:
```
@RequestParam:

membershipName : SILVER
```

### Membership - Update Benefit by ID
URL:
```
http://localhost:8080/api/v1/memberships/{membershipId}
```

Postman:
```
@PathVariable(MEMBER001)
@RequestParam(Anda akan mendapatkan penawaran khusus berupa paket menu eksklusif)
```

### Membership - Delete by Id
URL:
```
http://localhost:8080/api/v1/memberships/{membershipId}
```

Postman:
```
@PathVariable(M002)
```


## 4. Customer

### Customer - Create new Customer
URL:
```
http://localhost:8080/api/v1/customers
```

Postman:

```
@RequestBody
{
    "customerName": "Sauqi",
    "mobilePhoneNo": "081331928191",
    "membershipId": "MEMBER002"
}

```

### Customer - Get by Id
URL:
```
http://localhost:8080/api/v1/customers/{customerId}
```

Postman:

```
@PathVariable(C001)

```

### Customer - Get All Customers
URL:
```
http://localhost:8080/api/v1/customers
```

Postman:
``` 
@RequestParam:

customerName: {customerName}
```

### Customer - Update Membership Phone Number by Id
URL:
``` 
http://localhost:8080/api/v1/customers/{customerId}
```

Postman:
```
@PathVariable(C001)
@RequestParam(MEMBER003)
```

### Customer - Delete by Id
URL:
```
http://localhost:8080/api/v1/customers/{customerId}
```

Postman:
``` 
@PathVariable(C001)
```

## 5. Transaction

### Transaction - Create New Transaction
URL:
```
http://localhost:8080/api/v1/transactions
```

Postman:
```
@RequestBody:

{
    "customerId" : "C006",
    "bonusId" : "B004",
    "transactionDetails" : [
        {
            "menuId": "M001",
            "qty": 2
        },
        {
            "menuId": "M002",
            "qty": 1
        }
    ]
}
```

### Transaction - Get All Transactions
URL:
```
http://localhost:8080/api/v1/transactions
```

Postman: No additional request required while testing this method

# DONE