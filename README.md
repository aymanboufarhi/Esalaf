# Esalaf

The Esalaf project aims to manage orders, customers, and products for local small markets, replacing the old methods that required writing in a book, by providing them with a GUI to manage all that. It is developed using JavaFX

## Concept
The Esalaf project aims to manage orders, customers, and products.

I have created five interfaces: Start (which is displayed when you start), Menu (with buttons directing you to the next three interfaces), Clients, Commande, and Produits.

I have decided to represent :

- Customers by their name, phone number, and their unique and National Identification Card (CIN) number.

- Products are represented by their name, price, quantity in the supermarket/store, and category. 

- Orders contain the unique CIN of the customer, the product name, the quantity purchased by the customer, and the status of the order (whether it is paid or not). If it is not paid, then there is credit involved.

For CRUD operations, especially for deletion and modification, I needed the ID to perform the requests. Therefore, I created a text field reserved for the ID that does not appear in the interface. By clicking on a row, this field is filled with the required ID to then execute the SQL query.

I also created a search field that can, for example, help retrieve the complete information of a customer using only their CIN.
