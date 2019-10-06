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
   <form 
   	  id="albaForm"
   	  action="";
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
            <td>
            <input type="hidden" id="selectedGr" value="${alba.gr_code}">
            <select name="gr_code">
            	<option value>학력선택</option>
            </select>
             <span class="error">${errors.gr_code}</span>
            </td>
         </tr>
         <tr>
            <th>경력</th>
            <td><input type="text"  class="form-control"
               name="al_career" value="${alba.al_career}" /></td>
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
       
   <h4>자격증 정보</h4>
   <input type="button"	class="btn btn-secondary" id="licAddBtn" value="자격증추가">
       <table class="table table-bordered" >
       <thead>
   			<tr>
	   			<th>자격증</th>
	   			<th>자격증 사본등록</th>
	   			<th>비 고</th>
   			</tr>
   			</thead>
   			<tbody id="licArea">
   	<c:if test="${not empty alba.licList }">
   		<c:forEach items="${alba.licList}" var="lic">
   		<c:if test="${not empty lic.lic_code }">
   			<tr>
   			<c:url value="/alba/licenseImage.do" var="licImgURL">
   			<c:param name="al_id" value="${alba.al_id }"/>
   			<c:param name="lic_code" value="${lic.lic_code}"/>
   			</c:url>
   			<td>
   				<a href="${licImgURL}" target="_blank">${lic.lic_name}</a>
   				<input type="hidden" name="lic_code" value="${lic.lic_code}">
   			</td>
           	<td>
     			<input type="file" accept="image" name="lic_image"/>
           	</td>
	           	<td>
    	       		<input type="button" data-lic="${lic.lic_code }"  value="삭제" id="licImgDelBtn" class="btn btn-warning">
            	</td>
   			</tr>
 			</c:if>
   		</c:forEach>
   	</c:if>
   		</tbody>
   </table>
	    <input type="button" id="insertBtn" class="btn btn-info" value="추가">
	    <input type="button" id="updateBtn" class="btn btn-info" value="저장">
	   	<input type="reset" id="resetBtn" class="btn btn-info" value="취소">
		<input type="button" class="btn btn-primary" value="돌아가기" onclick="location.href='${cPath}/'">
 	</form>
 
 
 
<script type="text/javascript">
	var licArea = $('#licArea');
	var gradeTag = $("[name='gr_code']")
	var selectedGradeCode = $('#selectedGr').val();
	var insertBtn = $('#insertBtn');
	var updateBtn = $('#updateBtn');
	var albaForm = $('#albaForm');
	var licAddBtn = $('#licAddBtn');
	
	
	licArea.on('click','#licImgDelBtn',function(){
		let lic_code = $(this).data("lic");
		let al_id = $('[name="al_id"]').val();
		$.ajax({
			type : "post",
			url : "${cPath}/alba/albaLicenseDelete.do",
			data : {"lic_code":lic_code, "al_id":al_id},
			dataType: "text",
			success : function(resp){
				alert(resp);
				location.reload();
			},
			error : function(errorResp) {
				console.log(errorResp.status);
			}
		});
	});
	
	licAddBtn.on('click',function(){
		$.ajax({
			url : "${cPath}/others/getLicenseList.do",
			dataType : "json",
			success : function(resp) {
				var code = "<tr><td><select name='lic_code'>"; 
				$.each(resp,function(i,v){
    	    			code+="<option value="+v.lic_code+">"+v.lic_name+"</option>";
		        	});
				code+="</td><td><input type='file' accept='image' name='lic_image'/></td>";
				code+="<td><input type='button'  value='추가취소' id='licAddCancelBtn' class='btn btn-warning'></tr>";
	        	$(licArea).append(code);
			},
			error : function(errorResp) {
				console.log(errorResp.status);
			}
		});
	});
	licArea.on('click','#licAddCancelBtn',function(){
		$(this).closest('tr').remove();
	});
	
	$(function(){
		let requestURI = window.location.href;
		if(requestURI.indexOf("albaList.do")>0){
			insertBtn.show();
			updateBtn.hide();
		}else if(requestURI.indexOf("albaUpdate.do")>0){
			insertBtn.hide();
			updateBtn.show();
		}
	});
	
	
	
	insertBtn.on('click',function(){
		albaForm.attr("action", "${pageContext.request.contextPath}/alba/albaInsert.do")
		albaForm.submit();
	});
	
	updateBtn.on('click',function(){
		albaForm.attr("action", "${pageContext.request.contextPath}/alba/albaUpdate.do")
		albaForm.submit();
	});
	
	$('#exampleModal').on('hidden.bs.modal', function () {
		$(this).find("form")[0].reset();
	})
	generateGrade = function(){
	    $.ajax({
	        url: "${cPath}/others/getGradeList.do",
	        dataType: "json",
	        success: function (resp) {
	        	let options = [];
	        	$.each(resp,function(i,v){
	        		options.push(
	        			$("<option>").text(v.gr_name).attr({value:v.gr_code}).
	        			prop("selected",v.gr_code==selectedGradeCode)		
	        		);
	        	});
	        	$(gradeTag).append(options);
	        },
	        error : function(errorResp){
	        	console.log(errorResp.status);
	        }
	    });
	}
	
	
	$('#exampleModal').on('hidden.bs.modal', function () {
		$(this).find("form")[0].reset();
	})
	generateGrade();
</script>
<c:remove  var="errors" scope="session" />
</body>
</html>