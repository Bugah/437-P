SET SCHEMA PUBLIC
drop table user
CREATE TABLE IF NOT EXISTS administrador (  id_admin integer  IDENTITY,  nome_adm varchar(200) NOT NULL,  email_adm varchar(200) NOT NULL,  senha_adm varchar(50) NOT NULL,  username_adm varchar(200) DEFAULT NULL)
CREATE TABLE IF NOT EXISTS categorias (  id_categoria integer IDENTITY,  categoria varchar(200) NOT NULL,  descricao varchar(2000) DEFAULT NULL,  imagem_categoria varchar(200) DEFAULT NULL,  deletado integer DEFAULT 0 NOT NULL)
CREATE TABLE IF NOT EXISTS categorias_subcategorias (  id_subcat_e_cat integer IDENTITY,  id_sub_categoria integer DEFAULT NULL,  id_categoria integer DEFAULT NULL)
CREATE TABLE IF NOT EXISTS clientes (  id_cliente integer IDENTITY,  nome varchar(300) NOT NULL,  email varchar(300) NOT NULL,  senha varchar(100) DEFAULT NULL,  cpf varchar(100) NOT NULL,  endereco varchar(200) DEFAULT NULL,  cidade varchar(100) DEFAULT NULL,  estado varchar(50) DEFAULT NULL,  telefone varchar(50) DEFAULT NULL,  username varchar(200) DEFAULT NULL,  UNIQUE(cpf))
CREATE TABLE IF NOT EXISTS compras (  id_compra integer IDENTITY,  id_cliente integer NOT NULL,  total double DEFAULT NULL,  id_status_compra integer NOT NULL,  data datetime NOT NULL,  codigo_rastreio varchar(500) DEFAULT NULL,  protocolo_moip varchar(500) DEFAULT NULL,  valor_frete double DEFAULT NULL    )
CREATE TABLE IF NOT EXISTS compras_produtos (  id_compra_produto integer IDENTITY,  id_compra integer DEFAULT NULL,  id_produto integer DEFAULT NULL,  qtd integer DEFAULT NULL,  valor_por_produto double DEFAULT NULL )
CREATE TABLE IF NOT EXISTS imagem (  id_imagem integer IDENTITY,  nome_arquivo varchar(200) NOT NULL,  titulo varchar(255) DEFAULT NULL,  descricao varchar(255) DEFAULT NULL,  deletado integer DEFAULT 0 NOT NULL) 
CREATE TABLE IF NOT EXISTS imagens_produto (  id_imagens_produto integer IDENTITY,  id_imagem integer DEFAULT NULL,  id_produto integer DEFAULT NULL)
CREATE TABLE IF NOT EXISTS lista_desejos (  id_lista_desejo integer IDENTITY,  id_cliente integer DEFAULT NULL,  id_produto integer DEFAULT NULL)
CREATE TABLE IF NOT EXISTS produtos (  id_produto integer IDENTITY,  nome varchar(150) NOT NULL,  descricao varchar(2000) DEFAULT NULL,  preco double NOT NULL,  estoque integer NOT NULL,  preco_promocional double DEFAULT NULL,  id_admin integer DEFAULT NULL ,  deletado integer DEFAULT 0 NOT NULL) 
CREATE TABLE IF NOT EXISTS produtos_subcategorias (  id_categoria_produto integer IDENTITY,  id_sub_categoria integer DEFAULT NULL,  id_produto integer DEFAULT NULL)  
CREATE TABLE IF NOT EXISTS sub_categorias (  id_sub_categoria integer IDENTITY,  subcategoria varchar(200) NOT NULL,  descricao varchar(2000) DEFAULT NULL,  imagem_subcategoria varchar(2000) DEFAULT NULL,  deletado integer DEFAULT 0 NOT NULL) 
CREATE TABLE IF NOT EXISTS tipo_status_compra (  id_status_compra integer IDENTITY,  status_compra varchar(100) NOT NULL,  descricao varchar(1000) DEFAULT NULL) 
CREATE TABLE IF NOT EXISTS vale_compra (  id_vale_compra integer IDENTITY,  codigo_valecompra integer NOT NULL,  status_uso integer DEFAULT 0 NOT NULL,  id_cliente integer DEFAULT NULL) 
INSERT INTO ADMINISTRADOR VALUES(1,'STEPHANE','STEPHANE','STEPHANE','STEPHANE')
COMMIT
INSERT INTO ADMINISTRADOR VALUES(2,'MARIANE','MARIANE','MARIANE','MARIANE')
COMMIT
INSERT INTO ADMINISTRADOR VALUES(3,'BRYAN','BRYAN','BRYAN','BRYAN')
COMMIT
INSERT INTO ADMINISTRADOR VALUES(4,'GUSTAVO','GUSTAVO','GUSTAVO','GUSTAVO')
COMMIT
INSERT INTO ADMINISTRADOR VALUES(5,'THARINE','THARINE','THARINE','THARINE')
COMMIT
INSERT INTO ADMINISTRADOR VALUES(6,'MADEU','MADEU','MADEU','MADEU')
COMMIT
INSERT INTO CATEGORIAS VALUES(1,'proteinas','blabla','images\proteinas.jpg',0)
COMMIT
INSERT INTO CATEGORIAS VALUES(2,'moleculas','blabla','images\moleculas.jpg',0)
COMMIT
INSERT INTO CATEGORIAS VALUES(3,'diet','blabla','images\diet.jpg',0)
COMMIT
INSERT INTO CATEGORIAS VALUES(4,'machin','blabla','images\diet.jpg',1)
COMMIT
/*C3*/SET SCHEMA PUBLIC
INSERT INTO SUB_CATEGORIAS VALUES(1,'proteinas de musclo','blibli','images\protmusc.jpg',0)
COMMIT
INSERT INTO SUB_CATEGORIAS VALUES(2,'proteinas de osso','blibli','images\protosso.jpg',0)
COMMIT
INSERT INTO SUB_CATEGORIAS VALUES(3,'proteinas de cerebro','blibli','images\protcer.jpg',0)
COMMIT
INSERT INTO SUB_CATEGORIAS VALUES(4,'h2o','blibli','images\h2o.jpg',0)
COMMIT
INSERT INTO SUB_CATEGORIAS VALUES(5,'ferro','blibli','images\fe.jpg',0)
COMMIT
INSERT INTO SUB_CATEGORIAS VALUES(6,'proteinas Light','blibli','images\protelight.jpg',0)
COMMIT
INSERT INTO SUB_CATEGORIAS VALUES(7,'refregirente zero','blibli','images\refzero.jpg',0)
COMMIT
INSERT INTO SUB_CATEGORIAS VALUES(8,'bidule','blibli','images\bidule.jpg',1)
COMMIT
INSERT INTO CATEGORIAS_SUBCATEGORIAS VALUES(1,1,1)
COMMIT
INSERT INTO CATEGORIAS_SUBCATEGORIAS VALUES(2,2,1)
COMMIT
INSERT INTO CATEGORIAS_SUBCATEGORIAS VALUES(3,3,1)
COMMIT
INSERT INTO CATEGORIAS_SUBCATEGORIAS VALUES(5,4,2)
COMMIT
INSERT INTO CATEGORIAS_SUBCATEGORIAS VALUES(6,5,2)
COMMIT
INSERT INTO CATEGORIAS_SUBCATEGORIAS VALUES(7,6,1)
COMMIT
INSERT INTO CATEGORIAS_SUBCATEGORIAS VALUES(8,6,3)
COMMIT
INSERT INTO CATEGORIAS_SUBCATEGORIAS VALUES(9,7,3)
COMMIT
INSERT INTO CATEGORIAS_SUBCATEGORIAS VALUES(10,8,4)
COMMIT
INSERT INTO PRODUTOS VALUES(1,'Musclo 4000','blabal',12.0E0,48,NULL,1,0)
COMMIT
INSERT INTO PRODUTOS VALUES(2,'Musclo 2000','blabal',8.0E0,48,NULL,1,0)
COMMIT
INSERT INTO PRODUTOS VALUES(3,'Musclo 1000','blabal',6.0E0,45,NULL,1,0)
COMMIT
INSERT INTO PRODUTOS VALUES(4,'Mega Femur','blabal',40.0E0,45,NULL,1,0)
COMMIT
INSERT INTO PRODUTOS VALUES(5,'Super Femur','blabal',30.0E0,45,NULL,1,0)
COMMIT
INSERT INTO PRODUTOS VALUES(6,'inteligencia mais','blabal',30.0E0,45,NULL,1,0)
COMMIT
INSERT INTO PRODUTOS VALUES(7,'inteligencia mais mais','blabal',35.0E0,45,NULL,1,0)
COMMIT
INSERT INTO PRODUTOS VALUES(8,'agua','blabal',35.0E0,45,NULL,1,0)
COMMIT
INSERT INTO PRODUTOS VALUES(9,'agua com gas','blabal',35.0E0,45,NULL,1,0)
COMMIT
INSERT INTO PRODUTOS VALUES(10,'popeye mais','blabal',35.0E0,45,NULL,1,0)
COMMIT
INSERT INTO PRODUTOS VALUES(11,'coca cola 0','blabal',35.0E0,45,NULL,1,0)
COMMIT
INSERT INTO PRODUTOS VALUES(12,'master musclo light','blabal',35.0E0,45,NULL,1,0)
COMMIT
INSERT INTO PRODUTOS VALUES(13,'mega musclo light','blabal',35.0E0,45,NULL,1,0)
COMMIT
INSERT INTO PRODUTOS VALUES(14,'proteina radical','blabal',35.0E0,45,NULL,1,0)
COMMIT
INSERT INTO PRODUTOS VALUES(15,'pro whey proteins','blabal',35.0E0,45,NULL,1,0)
COMMIT
INSERT INTO PRODUTOS VALUES(16,'nada','blabal',35.0E0,45,NULL,1,1)
COMMIT
INSERT INTO PRODUTOS VALUES(17,'proteina velha','blabal',35.0E0,45,NULL,1,1)
COMMIT
INSERT INTO PRODUTOS_SUBCATEGORIAS VALUES(4,2,3)
COMMIT
INSERT INTO PRODUTOS_SUBCATEGORIAS VALUES(18,1,17)
COMMIT
INSERT INTO PRODUTOS_SUBCATEGORIAS VALUES(5,2,5)
COMMIT
INSERT INTO PRODUTOS_SUBCATEGORIAS VALUES(6,3,6)
COMMIT
INSERT INTO PRODUTOS_SUBCATEGORIAS VALUES(7,3,7)
COMMIT
INSERT INTO PRODUTOS_SUBCATEGORIAS VALUES(8,4,8)
COMMIT
INSERT INTO PRODUTOS_SUBCATEGORIAS VALUES(9,4,9)
COMMIT
INSERT INTO PRODUTOS_SUBCATEGORIAS VALUES(10,5,10)
COMMIT
INSERT INTO PRODUTOS_SUBCATEGORIAS VALUES(11,7,11)
COMMIT
INSERT INTO PRODUTOS_SUBCATEGORIAS VALUES(12,6,12)
COMMIT
INSERT INTO PRODUTOS_SUBCATEGORIAS VALUES(13,6,13)
COMMIT
INSERT INTO PRODUTOS_SUBCATEGORIAS VALUES(14,7,13)
COMMIT
INSERT INTO PRODUTOS_SUBCATEGORIAS VALUES(15,6,14)
COMMIT
INSERT INTO PRODUTOS_SUBCATEGORIAS VALUES(16,6,15)
COMMIT
INSERT INTO PRODUTOS_SUBCATEGORIAS VALUES(17,8,16)
COMMIT
INSERT INTO PRODUTOS_SUBCATEGORIAS VALUES(1,1,1)
COMMIT
INSERT INTO PRODUTOS_SUBCATEGORIAS VALUES(2,1,2)
COMMIT
INSERT INTO PRODUTOS_SUBCATEGORIAS VALUES(3,1,3)
COMMIT
ALTER TABLE produtos ADD url_imagem_unica VARCHAR(200) 
COMMIT
UPDATE produtos SET url_imagem_unica = 'http://images.tcdn.com.br/img/img_prod/106996/2498_1_20120801140348.jpg' WHERE id_produto = 1
COMMIT
UPDATE produtos SET url_imagem_unica = 'http://i.mlcdn.com.br/1500x1500/hipercalorico-nutri-mass-7000-1-5kg-baunilhaintegralmedica-p-ganhar-massa-muscular-203511300.jpg' WHERE id_produto = 2
COMMIT
UPDATE produtos SET url_imagem_unica = 'http://www.evolutionsuplementos.com/ecommerce_site/arquivos9758/arquivos/1375385429_1.jpg' WHERE id_produto = 3
COMMIT
UPDATE produtos SET url_imagem_unica = 'http://lg.madrugaosuplementos.com.br/media/catalog/product/cache/2/small_image/9df78eab33525d08d6e5fb8d27136e95/m/e/meganitro22dark_1.jpg' WHERE id_produto = 4
COMMIT
UPDATE produtos SET url_imagem_unica = 'http://www.miamiaqui.com.br/prdfotos/super-charge-suplemento-alimentar-frutas-_1031.jpg' WHERE id_produto = 5
COMMIT
UPDATE produtos SET url_imagem_unica = 'http://hypescience.com/wp-content/uploads/2012/11/26.jpg' WHERE id_produto = 6
COMMIT
UPDATE produtos SET url_imagem_unica = 'http://hypescience.com/wp-content/uploads/2012/11/411.jpg' WHERE id_produto = 7
COMMIT
UPDATE produtos SET url_imagem_unica = 'http://www.tocadacotia.com/wp-content/gallery/gelo-seco/gelo-1.jpg' WHERE id_produto = 8
COMMIT
UPDATE produtos SET url_imagem_unica = 'http://colunistas.ig.com.br/bebidinhas/files/2008/08/agua.jpg' WHERE id_produto = 9
COMMIT
UPDATE produtos SET url_imagem_unica = 'http://turbeaucurbeau.files.wordpress.com/2010/12/popeyes-tattoo.jpg' WHERE id_produto = 10
COMMIT
UPDATE produtos SET url_imagem_unica = 'http://upload.wikimedia.org/wikipedia/commons/8/87/CocaColaBottle_background_free.jpg' WHERE id_produto = 11
COMMIT
UPDATE produtos SET url_imagem_unica = 'http://www.drogariaclassic.com.br/media/catalog/product/cache/1/image/700x700/9df78eab33525d08d6e5fb8d27136e95/m/u/muscle_milk_light.jpg' WHERE id_produto = 12
COMMIT
UPDATE produtos SET url_imagem_unica = 'http://lg.madrugaosuplementos.com.br/media/catalog/product/cache/2/small_image/9df78eab33525d08d6e5fb8d27136e95/m/e/meganitro22dark_1.jpg' WHERE id_produto = 13
COMMIT
UPDATE produtos SET url_imagem_unica = 'http://www.corpussuplementos.com/ecommerce_site/arquivos7249/arquivos/1354645530_1.jpg' WHERE id_produto = 14
COMMIT
UPDATE produtos SET url_imagem_unica = 'http://www.emporionarede.com.br/produtos/978_22172.jpg' WHERE id_produto = 15
COMMIT
UPDATE produtos SET url_imagem_unica = 'http://2.bp.blogspot.com/-gFrGa4C9xsY/UJEmqtyWUQI/AAAAAAAAAag/StaR4WemMtY/s1600/NADA.jpg' WHERE id_produto = 16
COMMIT
UPDATE produtos SET url_imagem_unica = 'http://midia.folhavitoria.com.br/img/lib/2013/02/guia_boa_forma_suplemento__dd71802dc3.jpg' WHERE id_produto = 17