# Tucil 3 Strategi Algoritma
### Penyelesaian Permainan Word Ladder Menggunakan Algoritma UCS, Greedy Best First Search, dan A*

> Tugas kecil ini adalah implementasi dari algoritma Uniform Cost Search (UCS), Greedy Best First Search (GBFS), dan A* untuk menyelesaikan permasalahan Word Ladder. Word Ladder adalah permainan teka-teki di mana pemain harus mengubah satu kata menjadi kata lainnya, dengan setiap langkah hanya mengubah satu huruf dan memastikan bahwa setiap kata yang dihasilkan adalah kata yang valid. Dalam tugas kecil ini, kami mengimplementasikan ketiga algoritma tersebut dalam bahasa pemrograman Java dan melakukan pengujian untuk membandingkan kinerja dan efektivitas masing-masing algoritma. Hasil pengujian menunjukkan bahwa A* memberikan solusi yang optimal dan efisien, sementara UCS memiliki waktu eksekusi paling lama, dan GBFS cenderung lebih cepat tetapi tidak menjamin solusi optimal dalam beberapa kasus.

## Table of Contents
* [Technologies Used](#technologies-used)
* [Features](#features)
* [Setup](#setup)
* [Usage](#usage)
* [Project Status](#project-status)
* [Contact](#contact)

## Technology Used
- Next.js 14
- Apache Maven 3.9.6
- Java
- Spring Boot

## Feautures
- Algoritma UCS
- Algoritma GBFS
- Algoritma A*
- Penyelesaian word ladder problem

## Setup
Untuk kepastian program berjalan sesuai dengan keinginan anda dapat melakukan setup sebagai berikut:
- Installasi mvn
- Installasi extension pack java pada VsCode
- Installasi extension pack spring boot pada VsCode
- Installasi java serta setup environment java_home yang benar

## Usage

>Clone repo ini ke dalam folder yang diinginkan.
Agar command `'.'` dapat dijalankan diharapkan untuk membuka folder dalam VsCode atau code editor semacam yang dapat menjalankan command `'.'`
Program ini dapat dijalankan dengan dua cara; terminal dan website:
### Website
- Dari root directory lakukan command berikut untuk menuju ke directory frontend 
```bash
cd src/frontend
```
- Jalankan command berikut untuk installasi kebutuhan website:
```bash
npm i
```
- Ubah directory ke bin untuk menjalankan frontend dan backend
```bash
cd ../../bin
```
- Pastikan bahwa localhost:3000 dan localhost:8080 tidak digunakan oleh program lain
- Lalu jalankan command .bat pada terminal anda
```bash
./run_febe.bat
```
- Tunggu sebentar frontend dan backend akan dijalankan
- Buka browsert keinginan kalian lalu buka http://localhost:3000/
- Website akan dijalankan
- Disini kalian dapat memasukkan startWord dan endWord serta algoritma yang kalian inginkan

### Terminal
- Dari root directory lakukan command berikut untuk menuju ke directory bin
```bash
cd bin
```
- Lalu buka terminal dan jalankan command berikut:
```bash
./run_main.bat
```
- Terminal baru akan dibuat dan menjalankan main program
- Pada terminal anda dapat memasukkan startWord, endWord serta algoritma yang kalian inginkan.

## Project Status
Project is: _complete_

## Contact
Created by
Ahmad Thoriq Saputra (13522141) 