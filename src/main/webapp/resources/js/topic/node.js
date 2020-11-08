function getNode(){
	var tabName = $("#tab").val();
	// var url = "/node/tab/"+tabName;
	var url = "/nodes";
	$.ajax({
		url:url,
		type:"get",
		dataType:"json",
		success : function(data){
			$("#node").html('');
			for(var i = 0;i < data.data.length;i++){
				$("#node").append('<option value="'+data.data[i].nodeCode+'">'+data.data[i].nodeTitle+'</option>');
			}
		},
		error : function(data){
			
		}
	});
};
//Open page default trigger event
window.onLoad = getNode();