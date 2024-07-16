CREATE TABLE Customer (
    customer_id INT PRIMARY KEY,
    customer_last_name VARCHAR(50),
    customer_first_name VARCHAR(50),
    customer_phone VARCHAR(15)
);

INSERT INTO Customer (customer_id, customer_last_name, customer_first_name, customer_phone) VALUES
(1, 'Chenda', 'Sovisal', '092888999'),
(2, 'Kom', 'Lina', '092006999'),
(3, 'Chan', 'Seyha', '092777666');
