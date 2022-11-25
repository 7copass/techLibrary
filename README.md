<h1> Projeto Library</h1>

 
#### Projeto desenvolvido utilizando as tecnologias:
+ Java
+ SpringBoot
+ SpringSecurity
+ Postgres

## Orientações:

Nessa aplicação temos dois tipos de usuário, são eles CLIENT e ADMIN, porém só podemos cadastrar o tipo CLIENT, 
já o tipo ADMIN é persistido no banco de dados!
cada usuário pode:
Criar livros
Buscar todos os livros
Buscar Apenas um único livro
Atualizar um livro
Deletar um livro

## Observação:

O tipo de usuário CLIENT, só tem permissão de deletar e atualizar livros que ele mesmo cadastrou,
e o tipo ADMIN pode deletar e atualizar qualquer livro

