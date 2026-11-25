create database ecommerce_user_db;
show databases;

use ecommerce_user_db;
show tables;

select * from users;

desc users;
select * from users;

create database ecommerce_prod_db;
show databases;

create database ecommerce_order_db;
show databases;

create  database ecommerce_auth_db;
show databases;

create database ecommerce_auth_db;
show databases;



-- For checking user id is valid or not
USE ecommerce_order_db;

SELECT *
FROM orders
WHERE id = 16;

SELECT * FROM orders WHERE id = 16;
SELECT id, user_id, created_at, updated_at
FROM orders
WHERE id = 17;

select 1;

USE ecommerce_order_db;

DESC orders;
SHOW CREATE TABLE orders;

SELECT * FROM customers;

SELECT id, user_id, product_id, customer_id
FROM orders;


UPDATE orders
SET customer_id = 1
WHERE id = 17;

SELECT
    o.id AS order_id,
    o.customer_id,
    c.name AS customer_name,
    c.email
FROM orders o
JOIN customers c
ON o.customer_id = c.id
WHERE o.id = 17;

SELECT id, user_id, product_id, customer_id
FROM orders
WHERE id = 18;

show databases;

use ecommerce_order_db;
SELECT * FROM customers
ORDER BY id DESC;

SELECT id, user_id, product_id, customer_id, quantity, status
FROM orders
ORDER BY id DESC;

SELECT id, customer_id, product_id, status
FROM orders
WHERE customer_id = 2;

SELECT * FROM customers
WHERE id = 1;

SELECT * FROM orders
WHERE customer_id = 1;

SELECT * FROM customers WHERE id = 2;
SELECT * FROM orders WHERE customer_id = 2;

use ecommerce_auth_db;
show tables;
select * from auth_users;

show databases;
use ecommerce_user_db;
show tables;

select * from users;
select id,email,password,name from users where name = "Harsh Rathore";