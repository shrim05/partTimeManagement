<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>alba View</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
<style type="text/css">
   .error{
      color:red;
   }
   img{
   width:300px;
   height:300px;
   }
</style>
</head>
  <body>
      <c:if test="${not empty message }">
      <script type="text/javascript">
         alert("${message}"); 
      </script>
      </c:if>
      <c:remove var="message" scope="session" />
   <form action="${pageContext.request.contextPath}/alba/albaUpdate.do"
      method="post"
      enctype="multipart/form-data" >
      <table class="table table-bordered">
         <tr>
            <th>아이디</th>
            <td>${alba.al_id}</td>
         </tr>
         <tr>
            <th>이름</th>
            <td>${alba.al_name}</td>
         </tr>
         <tr>
            <th>나이</th>
            <td>${alba.al_age}</td>
         </tr>
         <tr>
            <th>주소</th>
            <td>${alba.al_address} </td>
         </tr>
         <tr>
            <th>연락처</th>
            <td>${alba.al_hp}
               </td>
         </tr>
         <tr>
            <th>스펙</th>
            <td>${alba.al_spec}
               </td>
         </tr>
         <tr>
            <th>상세정보</th>
            <td>
              ${alba.al_desc}
         </tr>
         <tr>
            <th>학력</th>
               <td>${alba.gr_name}</td>
         </tr>
         <tr>
            <th>경력</th>
            <td>${alba.al_career}</td>
         </tr>
         <tr>
            <th>성별</th>
            <td>${alba.al_gen} </td>
         </tr>
         <tr>
            <th>혈액형</th>
            <td>${alba.al_btype}  
                 </td>
         </tr>
         <tr>
            <th>이메일</th>
            <td>${alba.al_mail}</td>
         </tr>
         
      </table>
   <h4>자격증</h4>
       <table class="table table-bordered">
   	<c:if test="${not empty alba.licList }">
       <thead>
   			<tr>
	   			<th>자격증코드</th>
	   			<th>자격증명</th>
	   			<th>자격증사본</th>
   			</tr>
   			</thead>
   			<tbody>
   		<c:forEach items="${alba.licList}" var="lic">
   			<tr>
	   			<td>${lic.lic_code }
	   			<td>${lic.lic_name}</td>
   			  <c:if test="${not empty lic.lic_image }">
            	<td>
            		<img src="data:images/*;base64,${lic.lic_imageBase64 }" />
            	</td>
           	</c:if>
           	<c:if test="${empty lic.lic_image }">
           	<td>
           		자격증 사본 등록 요망
           	</td>
           	</c:if>
   			</tr>
   		</c:forEach>
   		</tbody>
   	</c:if>
   	<c:if test="${empty alba.licList }">
   	<tr><td colspan="6">자격증 없음</td></tr>
   	</c:if>
   </table>
   <c:url value="/alba/albaUpdate.do" var="updateURL">
   <c:param name="al_id" value="${alba.al_id }" />
   </c:url>
  
   <input type="button" class="btn btn-info" value="수정"
   onclick="location.href='${updateURL}'">
   <input type="button" id="deleteBtn" class="btn btn-info" value="삭제" >
 </form >
<script type="text/javascript">
	
	
</script>
<c:remove  var="errors" scope="session" />
</body>
</html>