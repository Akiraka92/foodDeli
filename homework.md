
# 1. event storming model
![スクリーンショット (15)](https://github.com/Akiraka92/foodDeli/assets/37332739/7fc71466-9f89-4add-9a68-d11a525af248)


# 2. pub/sub
![スクリーンショット (16)](https://github.com/Akiraka92/foodDeli/assets/37332739/b8ca2c61-5194-4344-9afa-ac0fa2796f4a)
![スクリーンショット (17)](https://github.com/Akiraka92/foodDeli/assets/37332739/0be4c977-0442-4280-913a-f8747f1df0b0)
![スクリーンショット (18)](https://github.com/Akiraka92/foodDeli/assets/37332739/c61b14f0-a1f7-4e7e-90f9-60aacba451ca)
![スクリーンショット (19)](https://github.com/Akiraka92/foodDeli/assets/37332739/5bab8a03-b278-4807-962c-b10bf4bd58b8)

# 3. CQRS(failed)
![image](https://github.com/Akiraka92/foodDeli/assets/37332739/e8c85059-dee5-4271-92ac-934798925131)
![image](https://github.com/Akiraka92/foodDeli/assets/37332739/bc03d07a-c18a-4c64-8b2d-5e7ab4f3114c)


# 4. Compensation / Correlation
![image](https://github.com/Akiraka92/foodDeli/assets/37332739/b9929121-a0c6-4257-88e7-971e6d7341ce)

```
gitpod /workspace/foodDeli (main) $ http POST :8081/orders restaurantNo=0000321 userNo=999618 menu=pizza amount=5 status=ORDER_PLACED adress=kashimada
HTTP/1.1 201 
Connection: keep-alive
Content-Type: application/json
Date: Thu, 11 May 2023 16:33:02 GMT
Keep-Alive: timeout=60
Location: http://localhost:8081/orders/1
Transfer-Encoding: chunked
Vary: Origin
Vary: Access-Control-Request-Method
Vary: Access-Control-Request-Headers

{
    "_links": {
        "order": {
            "href": "http://localhost:8081/orders/1"
        },
        "self": {
            "href": "http://localhost:8081/orders/1"
        }
    },
    "adress": "kashimada",
    "amount": 5,
    "menu": "pizza",
    "restaurantNo": 321,
    "status": "ORDER_PLACED",
    "userNo": 999618
}


gitpod /workspace/foodDeli (main) $ http GET :8081/orders/1
HTTP/1.1 200 
Connection: keep-alive
Content-Type: application/hal+json
Date: Thu, 11 May 2023 16:34:07 GMT
Keep-Alive: timeout=60
Transfer-Encoding: chunked
Vary: Origin
Vary: Access-Control-Request-Method
Vary: Access-Control-Request-Headers

{
    "_links": {
        "order": {
            "href": "http://localhost:8081/orders/1"
        },
        "self": {
            "href": "http://localhost:8081/orders/1"
        }
    },
    "adress": "kashimada",
    "amount": 5,
    "menu": "pizza",
    "restaurantNo": 321,
    "status": "ORDER_PLACED",
    "userNo": 999618
}


gitpod /workspace/foodDeli (main) $ http GET :8084/payments/1
HTTP/1.1 200 
Connection: keep-alive
Content-Type: application/hal+json
Date: Thu, 11 May 2023 16:34:21 GMT
Keep-Alive: timeout=60
Transfer-Encoding: chunked
Vary: Origin
Vary: Access-Control-Request-Method
Vary: Access-Control-Request-Headers

{
    "_links": {
        "payment": {
            "href": "http://localhost:8084/payments/1"
        },
        "self": {
            "href": "http://localhost:8084/payments/1"
        }
    },
    "orderId": 1,
    "status": "ORDER_PLACED",
    "userId": 999618
}


gitpod /workspace/foodDeli (main) $ 
```
