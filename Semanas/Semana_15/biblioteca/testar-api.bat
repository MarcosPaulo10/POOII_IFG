@echo off
chcp 65001 >nul
title Biblioteca API - Testes
color 0A

set BASE=http://localhost:8080/sistema-biblioteca

echo.
echo ============================================
echo   TESTE DA API - BIBLIOTECA
echo   Base: %BASE%
echo ============================================
echo.
echo IMPORTANTE: A API precisa estar rodando antes!
echo   Abra outro terminal e execute: iniciar-api.bat
echo.
pause

echo.
echo [1/11] POST - Cadastrar Autor
curl -s -w "\nHTTP Status: %%{http_code}\n" -X POST "%BASE%/autores" -H "Content-Type: application/json" -d "{\"nome\":\"Machado de Assis\",\"nacionalidade\":\"Brasileira\",\"dataNascimento\":\"1839-06-21\"}"
echo.
pause

echo.
echo [2/11] GET - Listar Autores
curl -s -w "\nHTTP Status: %%{http_code}\n" "%BASE%/autores"
echo.
pause

echo.
echo [3/11] GET - Buscar Autor por ID
curl -s -w "\nHTTP Status: %%{http_code}\n" "%BASE%/autores/1"
echo.
pause

echo.
echo [4/11] POST - Cadastrar Livro
curl -s -w "\nHTTP Status: %%{http_code}\n" -X POST "%BASE%/livros" -H "Content-Type: application/json" -d "{\"titulo\":\"Dom Casmurro\",\"isbn\":\"978-8535910557\",\"anoPublicacao\":1899,\"numeroPaginas\":256,\"autor\":{\"id\":1}}"
echo.
pause

echo.
echo [5/11] GET - Listar Livros
curl -s -w "\nHTTP Status: %%{http_code}\n" "%BASE%/livros"
echo.
pause

echo.
echo [6/11] GET - Buscar Livro por ID
curl -s -w "\nHTTP Status: %%{http_code}\n" "%BASE%/livros/1"
echo.
pause

echo.
echo [7/11] GET - Livros por Autor
curl -s -w "\nHTTP Status: %%{http_code}\n" "%BASE%/livros/autor/1"
echo.
pause

echo.
echo [8/11] PUT - Atualizar Autor
curl -s -w "\nHTTP Status: %%{http_code}\n" -X PUT "%BASE%/autores/1" -H "Content-Type: application/json" -d "{\"nome\":\"Machado de Assis\",\"nacionalidade\":\"Brasileiro\",\"dataNascimento\":\"1839-06-21\"}"
echo.
pause

echo.
echo [9/11] PUT - Atualizar Livro
curl -s -w "\nHTTP Status: %%{http_code}\n" -X PUT "%BASE%/livros/1" -H "Content-Type: application/json" -d "{\"titulo\":\"Dom Casmurro - Edicao Especial\",\"isbn\":\"978-8535910557\",\"anoPublicacao\":1899,\"numeroPaginas\":300,\"autor\":{\"id\":1}}"
echo.
pause

echo.
echo [10/11] DELETE - Remover Livro
curl -s -w "\nHTTP Status: %%{http_code}\n" -X DELETE "%BASE%/livros/1"
echo.
pause

echo.
echo [11/11] DELETE - Remover Autor
curl -s -w "\nHTTP Status: %%{http_code}\n" -X DELETE "%BASE%/autores/1"
echo.
pause

echo.
echo ============================================
echo   TESTES CONCLUIDOS!
echo   Tire print de cada tela (Win+Shift+S)
echo ============================================
pause
