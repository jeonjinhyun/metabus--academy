<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
    <h1>Request Parameter</h1>
    <h3>GET 방식의 요청</h3>
    <h4>form 태그를 이용한 get 방식 요청</h4>
    <form action="querystring" method="get">
        <label>이름 : </label><input type="text" value="" name="name" placeholder="이름을 입력해 주세요">
        <br>
        <label>나이 : </label><input type="text" value="" name="age" placeholder="나이을 입력해 주세요">
        <br>
        <label>생일 : </label><input type="date" name="birthday">
        <br>
        <label>성별 : </label>
        <input type="radio" name="gender" id="male" value="M">
        <label for="male">남자</label>
        <input type="radio" name="gender" id="female" value="F">
        <label for="female">여자</label>
        <br>
        <label>국적 :</label>
        <select name="national">
            <option value="ko">한국</option>
            <option value="cn">증극</option>
            <option value="jp">일본</option>
            <option value="en">미국</option>
            <option value="etc">기타</option>
        </select>
        <br>
        <label>취미</label>
        <input type="checkbox" name="hobbies" id="movie" value="movie">
        <label for="movie">영화</label>
        <input type="checkbox" name="hobbies" id="music" value="music">
        <label for="music">음악</label>
        <input type="checkbox" name="hobbies" id="sleep" value="sleep">
        <label for="sleep">수면</label>

        <input type="submit" value="전송">
    </form>

    <h4>a 태그의 href 속성에 직접 파라미터를 쿼리스트링 형태로 작성하여 get 요청</h4>
    <a href="querystring?name=전진현&age=20&birthday=2024-07-05&gender=M&national=ko&hobbies=music&hobbies=sleep">
        쿼리스트링을 이용한 값 전달
    </a>

    <h4>form 태그를 이용한 post 방식 요청</h4>
    <form action="formdata" method="post">
        <label>이름 : </label><input type="text" value="" name="name" placeholder="이름을 입력해 주세요">
        <br>
        <label>나이 : </label><input type="text" value="" name="age" placeholder="나이을 입력해 주세요">
        <br>
        <label>생일 : </label><input type="date" name="birthday">
        <br>
        <label>성별 : </label>
        <input type="radio" name="gender" id="male2" value="M">
        <label for="male">남자</label>
        <input type="radio" name="gender" id="female2" value="F">
        <label for="female">여자</label>
        <br>
        <label>국적 :</label>
        <select name="national">
            <option value="ko">한국</option>
            <option value="cn">증극</option>
            <option value="jp">일본</option>
            <option value="en">미국</option>
            <option value="etc">기타</option>
        </select>
        <br>
        <label>취미</label>
        <input type="checkbox" name="hobbies" id="movie2" value="movie">
        <label for="movie">영화</label>
        <input type="checkbox" name="hobbies" id="music2" value="music">
        <label for="music">음악</label>
        <input type="checkbox" name="hobbies" id="sleep2" value="sleep">
        <label for="sleep">수면</label>

        <input type="submit" value="전송">
    </form>

    <h4>a 태그의 href 속성에 직접 파라미터를 쿼리스트링 형태로 작성하여 get 요청</h4>


</body>
</html>