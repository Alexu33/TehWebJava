-- Delete all rows
delete
from reviews;
delete
from order_item;
delete
from orders;
delete
from product;
delete
from category;
delete
from users;

-- Reset sequences
ALTER SEQUENCE reviews_id_seq RESTART WITH 1;
ALTER SEQUENCE order_item_id_seq RESTART WITH 1;
ALTER SEQUENCE orders_id_seq RESTART WITH 1;
ALTER SEQUENCE product_id_seq RESTART WITH 1;
ALTER SEQUENCE category_id_seq RESTART WITH 1;
ALTER SEQUENCE users_id_seq RESTART WITH 1;


-- Insert Users
INSERT INTO users (username, password, email, role)
VALUES ('admin', 'adminpass', 'admin@example.com', 'ADMIN'),
       ('customer', 'customerpass', 'customer@example.com', 'CUSTOMER');

-- Insert Categories
INSERT INTO category (name)
VALUES ('Electronics'),
       ('Books');

-- Insert Products
INSERT INTO product (name, description, price, stock_quantity, category_id)
VALUES ('Smartphone', 'Latest model smartphone', 699.99, 50, 1),
       ('Laptop', 'High-performance laptop', 1299.99, 30, 1),
       ('Mystery Novel', 'A gripping mystery book', 19.99, 100, 2);

-- Insert Orders
INSERT INTO orders (user_id, order_date, status)
VALUES (2, '2025-01-07 10:00:00', 'Completed'),
       (2, '2025-01-07 12:00:00', 'Pending');

-- Insert Order Items
INSERT INTO order_item (order_id, product_id, quantity, price)
VALUES (1, 1, 1, 699.99),
       (1, 2, 1, 1299.99),
       (2, 3, 2, 39.98);

-- Insert Reviews
INSERT INTO reviews (content, rating, user_id, product_id)
VALUES ('Great smartphone, highly recommend!', 5, 2, 1),
       ('The laptop is good, but it gets hot under heavy load.', 3, 2, 2),
       ('A fantastic book!', 4, 2, 3);
