<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <h3>1. 산술 연산</h3>
    기존 : 10 + 3 = <%= request.getAttribute("big") + (int)request.getAttribute("small") %>

    <br><br>

    EL방식 <br>
    10 + 3 = ${big + small} <br>
    10 - 3 = ${big - small} <br>
    10 * 3 = ${big * small} <br>
    10 / 3 = ${big / small} 또는 ${big div small}<br>
    10 % 3 = ${big % small} 또는 ${big mod small}<br>

    <h3>2. 대소비교</h3>
    10 &gt; 3 = ${big > small} 또는 ${big gt small} <br>
    10 &lt; 3 = ${big > small} 또는 ${big lt small} <br>
    10 &gt; 3 = ${big > small} 또는 ${big ge small} <br>
    10 &lt; 3 = ${big > small} 또는 ${big le small} <br>

    EL방식 <br>
    10 + 3 = ${big + small} <br>
    10 - 3 = ${big - small} <br>
    10 * 3 = ${big * small} <br>
    10 / 3 = ${big / small} 또는 ${big div small}<br>
    10 % 3 = ${big % small} 또는 ${big mod small}<br>

    <h3>2. 대소비교</h3>
    10 &gt; 3 = ${big > small} 또는 ${big gt small} <br>
    10 &lt; 3 = ${big < small} 또는 ${big lt small} <br>
    10 &gt;= 3 = ${big >= small} 또는 ${big ge small} <br>
    10 &lt;= 3 = ${big <= small} 또는 ${big le small} <br>

    <h3>3. 동등비교</h3>
    <p>el에서는 ==비교는 자바에서의 equals()와 같은 동작</p>
    strOne과 strTwo가 일치하는가? ${strOne == strTwo} 또는 ${strOne eq strTwo} <br>
    strOne과 strTwo가 일치하지 않는가? ${strOne != strTwo} 또는 ${strOne ne strTwo} <br>

</body>
</html>