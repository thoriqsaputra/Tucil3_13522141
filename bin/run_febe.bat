@echo off

rem

cd ../src/frontend
start cmd /k npm run dev

cd ../backend/solver

start cmd /k java -jar solver-0.0.1-SNAPSHOT.jar