insert into artist (name) values ('Daniel Coll');
insert into artist (name) values ('Alejandro Barragán');
insert into artist (name) values ('Lucía Garrido');
insert into artist (name) values ('Adán Pagán');
insert into artist (name) values ('Fran Molina');
insert into artist (name) values ('Fran García');
insert into artist (name) values ('Mireya Martínez');
insert into artist (name) values ('Santi Gonzálvez');

insert into category (name) values ('Abstracto');
insert into category (name) values ('Clásico');
insert into category (name) values ('Contemporáneo');
insert into category (name) values ('Moderno');

insert into piece (name,price,category_id,artist_id) values ('VandalAIze',50,1,1);
insert into piece (name,price,category_id,artist_id) values ('Celeste',15,2,2);
insert into piece (name,price,category_id,artist_id) values ('La noche estrellada',20,3,3);
insert into piece (name,price,category_id,artist_id) values ('Hamilton',32,4,4);
insert into piece (name,price,category_id,artist_id) values ('Inside',5,1,5);
insert into piece (name,price,category_id,artist_id) values ('Hall del rey de la montaña',98,2,6);
insert into piece (name,price,category_id,artist_id) values ('Hysteria',75,3,7);
insert into piece (name,price,category_id,artist_id) values ('Chiquitin',46,4,8);
insert into piece (name,price,category_id,artist_id) values ('Hollow Knight',20,1,1);
insert into piece (name,price,category_id,artist_id) values ('Bloodborne',12,2,2);
insert into piece (name,price,category_id,artist_id) values ('Limbo',120,3,3);
insert into piece (name,price,category_id,artist_id) values ('The last of us',39,4,4);
insert into piece (name,price,category_id,artist_id) values ('In the Heights',22,1,5);
insert into piece (name,price,category_id,artist_id) values ('Cocoon',12,2,6);
insert into piece (name,price,category_id,artist_id) values ('DiscoXapas',99,3,7);

insert into exposition (name) values ('Gandía Expo 2024');
insert into exposition (name) values ('Málaga Jam 2024');
insert into exposition (name) values ('Expo Madrid 2024');

insert into Piece_expositions (piece_id,expositions_id) values (1,1);
insert into Piece_expositions (piece_id,expositions_id) values (3,2);
insert into Piece_expositions (piece_id,expositions_id) values (5,3);
insert into Piece_expositions (piece_id,expositions_id) values (7,1);
insert into Piece_expositions (piece_id,expositions_id) values (9,2);
insert into Piece_expositions (piece_id,expositions_id) values (11,3);