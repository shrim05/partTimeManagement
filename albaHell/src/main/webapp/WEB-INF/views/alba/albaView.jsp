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
<!--       // flash attribute 방식: 공유가 목적이 아니라 한번 꺼내쓰고 지워지는 것 -->
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
            <td><input type="text" readonly required class="form-control"
               name="al_id" value="${alba.al_id}" />
               <span class="error">${errors["al_id"]}</span></td>
         </tr>
         <tr>
            <th>이름</th>
            <td><input type="text" required class="form-control"
               name="al_name" value="${alba.al_name}" />
               <span class="error">${errors.al_name}</span></td>
         </tr>
         <tr>
            <th>나이</th>
            <td><input type="number" required class="form-control"
               name="al_age" value="${alba.al_age}" /></td>
         </tr>
         <tr>
            <th>주소</th>
            <td><input type="text" required class="form-control"
               name="al_address" value="${alba.al_address}" />
          	    <span class="error">${errors.al_address}</span>
       	    </td>
         </tr>
         <tr>
            <th>연락처</th>
            <td><input type="text" required class="form-control" name="al_hp"
               value="${alba.al_hp}" />
                 <span class="error">${errors.al_hp}</span>
               </td>
         </tr>
         <tr>
            <th>스펙</th>
            <td><input type="text" class="form-control" name="al_spec"
               value="${alba.al_spec}" />
               </td>
         </tr>
         <tr>
            <th>상세정보</th>
            <td><input type="text" class="form-control" name="al_desc"
               value="${alba.al_desc}" /></td>
         </tr>
         <tr>
            <th>학력</th>
            <td><input type="text" required class="form-control"
               name="gr_name" value="${alba.gr_name}" /></td>
         </tr>
         <tr>
            <th>경력</th>
            <td><input type="text"  class="form-control"
               name="AL_CAREER" value="${alba.al_career}" /></td>
         </tr>
         <tr>
            <th>성별</th>
            <td><input type="text" required class="form-control"
               name="al_gen" value="${alba.al_gen}" />
                 <span class="error">${errors.al_gen}</span>
                 </td>
         </tr>
         <tr>
            <th>혈액형</th>
            <td><input type="text" required class="form-control" name="al_btype"
               value="${alba.al_btype}" />   
               <span class="error">${errors.al_btype}</span>
                 </td>
         </tr>
         <tr>
            <th>이메일</th>
            <td><input type="email" required class="form-control" name="al_mail"
               value="${alba.al_mail}" /><span class="error">${errors.al_mail}</span>
                 </td>
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
	   			<td>${lic.lic_code }>
	   			<td>${lic.lic_name}</td>
   			  <c:if test="${not empty lic.lic_image }">
            	<td>
            		<img src="data:images/*;base64,${lic.lic_imageBase64 }" />
            	</td>
           	</c:if>
           	<c:if test="${empty lic.lic_image }">
           	<td>
           		자격증 사본 등록 요망
       		  	<input type="file" name="lic_image" />
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
   <input type="submit" class="btn btn-info" value="저장">
    <input type="reset" class="btn btn-info" value="취소">
   <input type="button" id="deleteBtn" class="btn btn-info" value="삭제" >
 </form >
<script type="text/javascript">
	$('#deleteBtn').on('click',function(){
	
	});
	
	
	
	$('#exampleModal').on('hidden.bs.modal', function () {
		$(this).find("form")[0].reset();
	})
</script>
<c:remove  var="errors" scope="session" />
</body>
</html>