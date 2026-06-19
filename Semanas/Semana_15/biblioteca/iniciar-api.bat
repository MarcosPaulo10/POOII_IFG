@echo off
chcp 65001 >nul
title Biblioteca API - Servidor

cd /d "%~dp0"

for /d %%i in ("C:\Program Files\Eclipse Adoptium\jdk-*") do set "JAVA_HOME=%%i"
if not defined JAVA_HOME (
    echo Java 17 nao encontrado. Instale em: https://adoptium.net/temurin/releases/?version=17
    pause
    exit /b 1
)
set "PATH=%JAVA_HOME%\bin;%PATH%"

echo.
echo ============================================
echo   INICIANDO API BIBLIOTECA
echo ============================================
echo.
echo Java: %JAVA_HOME%
echo.
echo Aguarde aparecer: Started BibliotecaApplication
echo Depois rode: testar-api.bat
echo.
echo Para parar: Ctrl+C
echo.

call mvnw.cmd spring-boot:run

pause
