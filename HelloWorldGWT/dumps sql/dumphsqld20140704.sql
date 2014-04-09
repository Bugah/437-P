CREATE TABLE IF NOT EXISTS administrador (
  id_admin integer  IDENTITY,
  nome_adm varchar(200) NOT NULL,
  email_adm varchar(200) NOT NULL,
  senha_adm varchar(50) NOT NULL,
  username_adm varchar(200) DEFAULT NULL
);



CREATE TABLE IF NOT EXISTS categorias (
  id_categoria integer IDENTITY,
  categoria varchar(200) NOT NULL,
  descricao varchar(2000) DEFAULT NULL,
  imagem_categoria varchar(200) DEFAULT NULL,
  deletado integer DEFAULT 0 NOT NULL
);



CREATE TABLE IF NOT EXISTS categorias_subcategorias (
  id_subcat_e_cat integer IDENTITY,
  id_sub_categoria integer DEFAULT NULL,
  id_categoria integer DEFAULT NULL
);



CREATE TABLE IF NOT EXISTS clientes (
  id_cliente integer IDENTITY,
  nome varchar(300) NOT NULL,
  email varchar(300) NOT NULL,
  senha varchar(100) DEFAULT NULL,
  cpf varchar(100) NOT NULL,
  endereco varchar(200) DEFAULT NULL,
  cidade varchar(100) DEFAULT NULL,
  estado varchar(50) DEFAULT NULL,
  telefone varchar(50) DEFAULT NULL,
  username varchar(200) DEFAULT NULL,
  UNIQUE(cpf)
);



CREATE TABLE IF NOT EXISTS compras (
  id_compra integer IDENTITY,
  id_cliente integer NOT NULL,
  total double DEFAULT NULL,
  id_status_compra integer NOT NULL,
  data datetime NOT NULL,
  codigo_rastreio varchar(500) DEFAULT NULL,
  protocolo_moip varchar(500) DEFAULT NULL,
  valor_frete double DEFAULT NULL
  
  );


CREATE TABLE IF NOT EXISTS compras_produtos (
  id_compra_produto integer IDENTITY,
  id_compra integer DEFAULT NULL,
  id_produto integer DEFAULT NULL,
  qtd integer DEFAULT NULL,
  valor_por_produto double DEFAULT NULL );



CREATE TABLE IF NOT EXISTS imagem (
  id_imagem integer IDENTITY,
  nome_arquivo varchar(200) NOT NULL,
  titulo date DEFAULT NULL,
  descricao integer DEFAULT NULL,
  deletado integer DEFAULT 0 NOT NULL
) ;



CREATE TABLE IF NOT EXISTS imagens_produto (
  id_imagens_produto integer IDENTITY,
  id_imagem integer DEFAULT NULL,
  id_produto integer DEFAULT NULL
);



CREATE TABLE IF NOT EXISTS lista_desejos (
  id_lista_desejo integer IDENTITY,
  id_cliente integer DEFAULT NULL,
  id_produto integer DEFAULT NULL
);



CREATE TABLE IF NOT EXISTS produtos (
  id_produto integer IDENTITY,
  nome varchar(150) NOT NULL,
  descricao varchar(2000) DEFAULT NULL,
  preco double NOT NULL,
  estoque integer NOT NULL,
  preco_promocional double DEFAULT NULL,
  id_admin integer DEFAULT NULL ,
  deletado integer DEFAULT 0 NOT NULL
) ;



CREATE TABLE IF NOT EXISTS produtos_subcategorias (
  id_categoria_produto integer IDENTITY,
  id_sub_categoria integer DEFAULT NULL,
  id_produto integer DEFAULT NULL
)  ;



CREATE TABLE IF NOT EXISTS sub_categorias (
  id_sub_categoria integer IDENTITY,
  subcategoria varchar(200) NOT NULL,
  descricao varchar(2000) DEFAULT NULL,
  imagem_subcategoria varchar(2000) DEFAULT NULL,
  deletado integer DEFAULT 0 NOT NULL
) ;



CREATE TABLE IF NOT EXISTS tipo_status_compra (
  id_status_compra integer IDENTITY,
  status_compra varchar(100) NOT NULL,
  descricao varchar(1000) DEFAULT NULL
) ;



CREATE TABLE IF NOT EXISTS vale_compra (
  id_vale_compra integer IDENTITY,
  codigo_valecompra integer NOT NULL,
  status_uso integer DEFAULT 0 NOT NULL,
  id_cliente integer DEFAULT NULL
) ;
