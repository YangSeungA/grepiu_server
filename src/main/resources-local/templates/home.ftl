<!DOCTYPE html>
<html>
<head>
  <title>Hello WebSocket</title>
  <link href="/webjars/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <link href="/resources/css/main.css" rel="stylesheet">
  <script src="/webjars/jquery/jquery.min.js"></script>
  <script src="/webjars/sockjs-client/sockjs.min.js"></script>
  <script src="/webjars/stomp-websocket/stomp.min.js"></script>
  <script src="/resources/js/app.js"></script>
  <meta charset="UTF-8">
  <style>
    .table-fixed tbody {
      height: 230px;
      overflow-y: auto;
      width: 100%;
    }
    .table-fixed thead, .table-fixed tbody, .table-fixed tr, .table-fixed td, .table-fixed th {
      display: block;
    }
  </style>
</head>
<body>
<noscript><h2 style="color: #ff0000">자바스크립트를 지원하지 않습니다.</h2></noscript>
<div id="main-content" class="container">
   <#if currentUser??>
  <form action="/logout" method="post">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <button type="submit">Log out</button>
  </form>
     ${currentUser.email}
   </#if>
  <div class="row">
    <div class="col-md-6">
      <form class="form-inline">
        <div class="form-group">
          <label for="connect"></label>
          <input type="text" id="name" class="form-control" placeholder="성명">
          <button id="connect" class="btn btn-default" type="submit">접속</button>
          <button id="disconnect" class="btn btn-default" type="submit" disabled="disabled">연결취소</button>
        </div>
      </form>
    </div>
  </div>
  <div class="row" id="conversation" class="w-100">
    <div class="col-md-12">
      <table class="table table-fixed">
        <thead>
        <tr>
          <th>내용</th>
        </tr>
        </thead>
        <tbody class="table-fixed" id="greetings"></tbody>
      </table>
      <div class="col-md-6 w-100">
        <form class="form-inline">
          <div class="form-group">
            <label for="name" id="user_name"></label>
            <input type="text" id="message" class="form-control" placeholder="메세지입력">
            <button id="send" class="btn btn-default" type="submit">입력</button>
          </div>
        </form>
      </div>
    </div>
  </div>
</div>
</body>
</html>