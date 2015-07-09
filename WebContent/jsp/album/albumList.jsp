<%@ page contentType="text/html; charset=utf-8" language="java"
	import="java.util.*" errorPage=""%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page isELIgnored="false"%>
<fmt:setLocale value="zh_CN" />
<fmt:setBundle basename="message" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/common.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/lean.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/album.css" />
<link rel="Shortcut Icon" href="image/album.ico" />

<script src="<%=request.getContextPath()%>/js/jquery-1.11.3.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery.leanModal.min.js"></script>
<script type="text/javascript">
	$(function() {
		$("#createAlbumBt").leanModal({
			top : 100,
			closeButton : ".modal_close"
		});
		$("#uploadBt").leanModal();

		var $albumName = $("#albumName");
		var $description = $("#description");
		$("#albumAddBt").click(function() {
			if ($albumName.val().length > 0 && $description.val().length > 0) {
				$("#albumAddForm").submit();
			}
		});
	});
</script>

<title><fmt:message key="albumList" /></title>

</head>

<body style="background: none">


	<div class="middle" id="middle">
		<a id="uploadBt" href="#photoAdd" rel="leanModal" class="flatbtn"><fmt:message key="photo.add"/></a>
		<a id="createAlbumBt" href="#albumAdd" rel="leanModal" class="flatbtn"><fmt:message key="album.add" /></a> <br></br>
   </div>
		<div id="albumList">
			<ul>
				<c:forEach var="album" items="${requestScope.list}"
					varStatus="status">
					<li>
						<div class="albumCover">
							<img src="${album.cover}"  onerror="this.src='image/cover.png'" width="146px" height="146px" />
						</div>
						<div align="center">${album.name}</div>

					</li>
				</c:forEach>
			</ul>
		</div>


	<%@ include file="albumAdd.jsp"%>
	<%@ include file="../photo/photoAdd.jsp"%>

	<p>&nbsp;</p>
</body>
</html>