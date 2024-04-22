# Autores

Arquitetado e desenvolvido por **Andre Dal Negro** como trabalho somativo para a diciplina de **Segurança da informação**.

# Arquitetura

projeto foi desenvolvido para realizar a gestão de arquivos com sistemas de permissoes, logins e manuseio de diretorio

## auth

pacote auth foi criado para salvar e gerenciar a sessao do atual usuário que esta utilziando o sistema

## controllers

pacote designado para gerenciar dados, arquivos, paginas e permissoes

## data

pacote desenvolvido para gerenciar tabelas.

Cada tabela possui um gerenciador que herda do controlador de dados

## interfaces

utilizado para armazenar interfaces com metodos para as classes como paginas, dados e etc.

paginas precisam ter alguns metodos necessarios para realizar cast, entao possui uma interface implementada.

## pages

pacote designado para guardar as paginas (menus) 

cada menu criado deve ser registrado no arquivo RegistrationPages.java

## types

designado para guardar tipagens gerais
