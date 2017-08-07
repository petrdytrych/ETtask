ALTER TABLE product ADD `serial_number` varchar(255) DEFAULT NULL;

Update `product` set serial_number = 'sn1' where id = 1;
Update `product` set serial_number = 'sn2' where id = 2;
Update `product` set serial_number = 'sn3' where id = 3;
