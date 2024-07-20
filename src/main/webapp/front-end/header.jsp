<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-Hant">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Header</title>
        <link href="css/styles.css" rel="stylesheet"> <!-- 導入自定義 CSS -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css"> <!-- 導入 Font Awesome -->
    </head>
<body>
    <!-- header.html -->
<div class="header">
    <div class="top-bar">
        <div class="logo">
            <a href="./"> <!-- 添加超連結標籤和href屬性 -->
                <img src="img/logo.png" alt="Logo">
            </a>
        </div>
        <div class="search-container">
            <div class="search-bar">
                <input type="text" placeholder="Search">
            </div>
            <div class="icons">
                <a href="#"></i>會員登入/註冊</a>
                <a href="#"><i class="fas fa-shopping-cart">購物車</i></a>
            </div>
        </div>
    </div>
    <div class="nav-bar">
        <a href="#">最新消息</a>
        <a href="#">節目資訊</a>
        <a href="#">票務須知</a>
        <a href="#">場館介紹</a>
        <a href="#">社群空間</a>
        <a href="#">購物商城</a>
        <a href="#">常見問題</a>
    </div>
</div>

</body>
</html>