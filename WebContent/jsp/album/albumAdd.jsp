<div id="albumAdd" style="display:none;" align="center">
	<h1> <fmt:message key="album.add"/></h1>
	<form id="albumAddForm" name="albumAddForm" method="post" action="albumCreate">
		<label for="albumName"><fmt:message key="album.name"/></label>
		<input type="text" name="albumName" id="albumName" class="txtfield" tabindex="1" />
	<br>
	<br>
		<label for="description" ><fmt:message key="album.description"/></label>&nbsp;
		<textarea rows="5" cols="15" name="description" id="description" tabindex="2" ></textarea>
	<br>
		<div align="center"><input type="button" name="albumAddBt" id="albumAddBt" class="flatbtn-blu hidemodal" value="<fmt:message key="album.submit"/>" tabindex="3"></div>
	</form>
</div>



 