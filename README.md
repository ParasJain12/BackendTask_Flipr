This is my Flipr Backend Task, i.e., E-Commerce Website that contains components:
1. Authentication
2. Product Management
3. Cart Management
4. Order Management

I implmeneted using Java Spring Boot Framework and for security constraints, I use Spring Security and for storage I use MySQL.
**For database configuration:**
1. create database fliprbackend
2. use fliprbackend
3. change to your accordingly and remember that you can change in **application.properties** file

To run this project, I use PostMan:
1. For Authentication
   1. SignUp
      Enter request in postman: localhost:8080/auth/signup [Use POST METHOD]
      and Go to BODY TAG -> raw -> Then write JSON like this
      {
         "name": "Paras Jain",
         "email": "paras@example.com",
         "password": "jain",
         "address": "indore"
      }
      Then see success message
   2. Signin
      Enter request in postman: localhost:8080/auth/signin [Use POST METHOD]
      and Go to BODY TAG -> raw -> Then write JSON like this
      {
          "email": "paras@example.com",
          "password":"jain"
      }
      Then see Login Successfully message

      
2. For Product Management
   1. Add Product
      Enter request in postman: localhost:8080/products/addproduct [Use POST METHOD]
      and Go to BODY TAG -> raw -> Then write JSON like this
      {
         "name": "Product 1",
         "description": "This is product 1",
         "price": 100.0,
         "category": "Category 1"
      }
      Remember that use Basic Auth where username=admin and password=admin123
   2. Update Product
       Enter request in postman: localhost:8080/products/updateproduct/{productId} [Use PUT METHOD]
       and Go to BODY TAG -> raw -> Then write JSON like this
       {
          "name": "Product 1",
          "description": "This is product 1 of category 1",
          "price": 100.0,
          "category": "Category 1"
       }
      Remember that use Basic Auth where username=admin and password=admin123
   3. Delete Product
       Enter request in postman: localhost:8080/products/deleteproduct/{productId} [Use DELETE METHOD]
       Remember that use **Basic Auth** where username=admin and password=admin123
   4. Get All Products
        Enter request in postman: localhost:8080/products [USE GET METHOD]
        Then see all products 

        
3. For Cart Management
   1. Add Product to Cart
          Enter request in postman: localhost:8080/cart/add [Use POST METHOD]
          and Go to BODY TAG -> form-data -> Then write like this
          cartId: 1 (Assume it)
          productId: 1 
          quantity: 2
   2. Update Cart
          Enter request in postman: localhost:8080/cart/update [Use PUT METHOD]
          and Go to BODY TAG -> form-data -> Then write like this
          cartId: 1 (Assume it)
          productId: 1 
          quantity: 3
   3. Delete Product from Cart
          Enter request in postman: localhost:8080/cart/delete [Use DELETE METHOD]
          and Go to BODY TAG -> form-data -> Then write like this
          cartId: 1 (Assume it)
          productId: 1 
   4. Get Cart
          Enter request in postman: localhost:8080/cart [Use GET METHOD]
          Go to PARAMS tag and write cartId: 1


4. For Order Management
     1. Place Order
        Enter request in postman: localhost:8080/order/placeorder [Use POST METHOD]
        Then write in PARAMS tag:
        cartId: 1
        customerId: 1
        and then come to BODY -> raw -> Write JSON Data (Shipping Detail)
        {
           "address": "Malwa Mill",
           "city": "Indore",
           "state": "MP",
           "pinCode": "452003",
           "country": "India",
           "phoneNumber": "123-456-7890"
        }
    2. Get All Orders
        Enter request in postman: localhost:8080/order/getallorders [Use GET METHOD]
        Remember to write admin crediantals. i.e., username=admin and password=admin123 [USE Basic Auth]
    3. Get Orders By Customer Id
        Enter request in postman: localhost:8080/order/orders/customer/{customerId} [Use GET METHOD]

So, That's all about the project.
My Postman API Documentation link is: https://documenter.getpostman.com/view/32612738/2sAXjF9aYn
         
