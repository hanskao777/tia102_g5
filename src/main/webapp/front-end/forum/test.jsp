<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="zh-Hant">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+TC:wght@100..900&display=swap" rel="stylesheet">
    <link href="css/forum_styles.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    <title>論壇</title>
</head>
<body>
    <div id="header"></div>
   
   

	<div class="container-fluid">
		<div class="row">
			<!-- 側邊欄 -->
			<div class="col-md-2 " style="height: 100%; background-color: white;">
                <div class="button-container" style="height: 96%; background-color: #fff3f5; margin: 15px;">
			<a href="/zh-TW/" class="d-flex align-items-center mb-3 mb-md-0 me-md-auto text-black text-decoration-none">
			    <svg class="bi pe-none me-2" width="40" height="32">
			        <use href="#bootstrap"></use>
			    </svg>
			    <span class="fs-5">討論版分類</span>
			</a>
				<hr>
				<ul class="nav nav-pills flex-column mb-auto">
					<li class="nav-item"><a
						href="/zh-TW/docs/5.2/examples/sidebars/#" 
                        class="nav-link"
                        style="background-color: #FDBCB4; color: #FEFEFA;"
						aria-current="page"> 
							<svg class="bi pe-none me-2" width="16" height="16">
                                <use href="#home"></use>
                            </svg> IVE
					</a></li>
					<li><a href="/zh-TW/docs/5.2/examples/sidebars/#"
						class="nav-link"> <svg class="bi pe-none me-2"
								width="16" height="16">
                                <use href="#speedometer2"></use>
                            </svg> BTS
					</a></li>
					<li><a href="/zh-TW/docs/5.2/examples/sidebars/#"
						class="nav-link"> <svg class="bi pe-none me-2"
								width="16" height="16">
                                <use href="#table"></use>
                            </svg> Seventeen
					</a></li>
					<li><a href="/zh-TW/docs/5.2/examples/sidebars/#"
						class="nav-link"> <svg class="bi pe-none me-2"
								width="16" height="16">
                                <use href="#grid"></use>
                            </svg> Mamamoo
					</a></li>
					<li>
						<a href="/zh-TW/docs/5.2/examples/sidebars/#"
						class="nav-link"> <svg class="bi pe-none me-2"
								width="16" height="16">
                                <use href="#people-circle"></use>
                            </svg> TWS
						</a></li>
                    <!-- <div class="dropdown">
                        <a href="/zh-TW/docs/5.2/examples/sidebars/#"
                            class="d-flex align-items-center text-black text-decoration-none dropdown-toggle"
                            data-bs-toggle="dropdown" aria-expanded="false"> <img
                            src="https://github.com/mdo.png" alt=""
                            class="rounded-circle me-2" width="32" height="32"> <strong>mdo</strong>
                        </a>
                        <ul class="dropdown-menu dropdown-menu-dark text-small shadow">
                            <li><a class="dropdown-item"
                                href="/zh-TW/docs/5.2/examples/sidebars/#">新項目...</a></li>
                            <li><a class="dropdown-item"
                                href="/zh-TW/docs/5.2/examples/sidebars/#">設置</a></li>
                            <li><a class="dropdown-item"
                                href="/zh-TW/docs/5.2/examples/sidebars/#">輪廓</a></li>
                            <li><hr class="dropdown-divider"></li>
                            <li><a class="dropdown-item"
                                href="/zh-TW/docs/5.2/examples/sidebars/#">登出</a></li>
                        </ul>
                      </div> -->
				</ul>
				<!-- <hr> -->
				
				</div>
			</div>
			<!-- 主內容 -->
			<div class="col-md-10">
				<div class="container">
					<nav aria-label="breadcrumb">
						<ol class="breadcrumb">
							<li class="breadcrumb-item"><a href="#" style="color: black;">Home</a></li>
							<li class="breadcrumb-item active" aria-current="page">IVE</li>
						</ol>
					</nav>
					<div class="container my-3">
						<nav class="navbar navbar-light bg-light d-flex justify-content-between align-items-center">
                            <div class="btn-group flex-grow-1 me-2" role="group">
                                <button type="button" class="btn btn-outline-secondary flex-grow-1">公告</button>
                                <button type="button" class="btn btn-outline-secondary flex-grow-1">情報</button>
                                <button type="button" class="btn btn-outline-secondary flex-grow-1">活動</button>
                                <button type="button" class="btn btn-outline-secondary flex-grow-1">圖片</button>
                                <button type="button" class="btn btn-outline-secondary flex-grow-1">影音</button>
                                <button type="button" class="btn btn-outline-secondary flex-grow-1">周邊</button>
                                <button type="button" class="btn btn-outline-secondary flex-grow-1">閒聊</button>
                            </div>
                            <form class="d-flex">
                                <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
                                <button class="btn btn-outline-success" type="submit">Search</button>
                            </form>
                        </nav>
					</div>
					<div>
						<table class="table table-hover">
							<thead >
								<tr>
									<th scope="col">#</th>
									<th scope="col">文章標題</th>
									<th scope="col">發文者</th>
									<th scope="col">文章更新時間</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<th scope="row">1</th>
									<td>[公告] IVE 板規</td>
									<td>Mark</td>
									<td>2024-07-16 17:11:41</td>
								</tr>
								<tr>
									<th scope="row">2</th>
									<td>[情報] 2024年7月行程表</td>
									<td>Thornton</td>
									<td>2023-11-10 17:11:41</td>
								</tr>
								<tr>
									<th scope="row">2</th>
									<td>[圖片] 安兪真 X 'MAXIM Supreme Gold' 廣告拍攝現場幕後花絮</td>
									<td>Larry8212</td>
									<td>2023-05-05 17:11:41</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>



    <div id="footer"></div>


    <script>
        // 載入 header
        fetch('../header.jsp')
            .then(response => response.text())
            .then(data => {
                document.getElementById('header').innerHTML = data;
            })
            .catch(error => console.error('Error loading header:', error));

        // 載入 footer
        fetch('../footer.jsp')
            .then(response => response.text())
            .then(data => {
                document.getElementById('footer').innerHTML = data;
            })
            .catch(error => console.error('Error loading footer:', error));
    </script>


</body>
</html>