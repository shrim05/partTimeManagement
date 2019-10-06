<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
<title>alba List</title>
</head>
<body>
  <c:if test="${not empty message }">
      <script type="text/javascript">
         alert("${message}"); 
      </script>
      </c:if>
   <c:remove var="message" scope="session" />
<button type="button" id="addAlbaBtn" class="btn btn-primary">알바 등록</button>
<table class="table table-condensed">
	<thead>
		<tr>
		<th>아이디</th>
		<th>이름</th>
		<th>나이</th>
		<th>주소</th>
		<th>연락처</th>
		<th>성별</th>
		</tr>
	</thead>
	<tbody id="listBody">
	</tbody>
	<tfoot>
			<tr>
			<td colspan="6" >
				<form id="searchForm">
				<input type ="hidden" name="page" />
					<select name="searchType">
						<option value>전체</option>					
						<option value="name">이름</option>					
					</select>
					<input type="text" name="searchWord" />
					<input type="submit" value="검색" />
				</form>
				<div id="pagingArea">
				
				</div>
			</td>
			</tr>
		</tfoot>
</table>
	<!-- Modal -->
   <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog"
      aria-labelledby="exampleModalLabel" aria-hidden="true">
      <div class="modal-dialog" role="document">
         <div class="modal-content">
            <div class="modal-header">
               <h5 class="modal-title" id="exampleModalLabel">상세 조회</h5>
               <button type="button" class="close" data-dismiss="modal"
                  aria-label="Close">
                  <span aria-hidden="true">&times;</span>
               </button>
            </div>
            <div class="modal-body"></div>
            <div class="modal-footer">
               <button type="button" class="btn btn-secondary"
                  data-dismiss="modal">Close</button>
            </div>
         </div>
      </div>
   </div>


   <form id="listForm"
      action="${cPath}/alba/albaView.do">
      <input type="hidden" name="who" />
   </form>
  <script type="text/javascript">
	var listBody =$('#listBody');
	var pagingArea = $('#pagingArea');
	var searchForm = $('#searchForm');
	var pageTag = $('[name="page"]');
	var listForm = $("#listForm");
	var exampleModal = $("#exampleModal");
	var searchBtn = $("#searchBtn");
	var pageTag = $("[name='page']");
	var albaAddBtn = $('#addAlbaBtn');
	
	albaAddBtn.on('click', function(){
		 $.ajax({
	         url : "${cPath}/alba/albaInsert.do",
	         dataType : "html",
	         success : function(resp) {
	            exampleModal.find(".modal-body").html(resp);
	            exampleModal.modal("show");
	         },
	         error : function(errorResp) {
	            console.log(errorResp.status);
	         }
	      });
		
	});
	
	searchForm.on("submit",function(event){
		event.preventDefault();
		var action = $(this).attr("action");
		var method = $(this).attr("method");
		var queryString = $(this).serialize();
		$.ajax({
			url : action,
			method : method,
			data : queryString,
			dataType : "json",
			success : function(resp) {
				let albaList = resp.dataList;
				let trTags = [];
				$.each(albaList,function(i,v){
					let trTag = $("<tr>").append(
						$("<td>").text(v.al_id),
						$("<td>").text(v.al_name),
						$("<td>").text(v.al_age),
						$("<td>").text(v.al_address),
						$("<td>").text(v.al_hp),
						$("<td>").text(v.al_gen)
					).prop('id',v.al_id);
					trTags.push(trTag);  
				});
				listBody.html(trTags);
				pagingArea.html(resp.pagingHTML);
				pageTag.val("1");
			},
			error : function(errorResp) {
				console.log(errorResp.status);
			}
		});
		
	});
	pagingArea.on("click","a",function(){
		let page = $(this).data("page");
		paging(page);
	});
	
	
	function paging(page){
		if(page<1) return false;
		pageTag.val(page);
		searchForm.submit();				
	}
	
	 searchBtn.on("click", function(){
	      let value = $("#text").val();
	      let searchWay = $(search).val();
	      data.attr("name", searchWay);
	      data.val(value);
	      searchForm.submit();
	   })
	     exampleModal.on("hidden.bs.modal",function(){
      $(this).find(".modal-body").remove("table");
   });

	   listForm.on("submit",function(){
		      let action = $(this).attr("action");
		      let method = $(this).attr("method");
		      let queryString = $(this).serialize();
		      $.ajax({
		         url : action,
		         method : method?method : "get",
		         data : queryString,
		         dataType : "html",
		         success : function(resp) {
		            exampleModal.find(".modal-body").html(resp);
		            exampleModal.modal("show");
		         },
		         error : function(errorResp) {
		            console.log(errorResp.status);
		         }
		      });
		      return false;
		   });
	$('#listBody').on("click","tr",function(){
		let who = ($(this).prop("id"));
		listForm.find("[name='who']").val(who);
		listForm.submit();
   	});
	paging(1);
</script>
</body>
</html>