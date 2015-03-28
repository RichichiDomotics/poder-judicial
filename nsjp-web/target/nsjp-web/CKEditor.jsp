<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>NSJP Editor</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta name="robots" content="noindex, nofollow" />
		<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/jquery-1.5.1.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/ckeditor/ckeditor.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath()%>/resources/js/ckeditor/adapters/jquery.js"></script>
		
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/bloqueaTecla.js"></script>
		
		<script type="text/javascript">
			$(document).ready(function() {
			var config = {
				toolbar:
				[
					['Source','-','Save','NewPage','Preview','-','Templates'],
					['Cut','Copy','Paste','PasteText','PasteFromWord','-','Print', 'SpellChecker', 'Scayt'],
					['Undo','Redo','-','Find','Replace','-','SelectAll','RemoveFormat'],
					['Form', 'Checkbox', 'Radio', 'TextField', 'Textarea', 'Select', 'Button', 'ImageButton', 'HiddenField'],
					'/',
					['Bold','Italic','Underline','Strike','-','Subscript','Superscript'],
					['NumberedList','BulletedList','-','Outdent','Indent','Blockquote','CreateDiv'],
					['JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock'],
					['BidiLtr', 'BidiRtl' ],
					['Link','Unlink','Anchor'],
					['Image','Flash','Table','HorizontalRule','Smiley','SpecialChar','PageBreak'],
					'/',
					['Styles','Format','Font','FontSize'],
					['TextColor','BGColor'],
					['Maximize', 'ShowBlocks','-']
				]

			};			
			
			$('.jquery_ckeditor').ckeditor(config);
			$('#guardar').click(guarda);				
			});
			function guarda() {
				alert ($('.jquery_ckeditor').val());
			}
		</script>
	</head>
	<body>
		<textarea class="jquery_ckeditor" cols="80" id="editor1" name="editor1" rows="10"></textarea>
		<input type="button" id="guardar" name="guardar" value="Guardar" class="btn_Generico"/>
	</body>
</html>