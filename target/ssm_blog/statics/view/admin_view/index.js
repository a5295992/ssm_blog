	/*
	 * 添加面板 自定义函数
	 * @title 面板标题
	 * @url 访问地址
	 * @author along 2017-7-30 周日
	 * */
	function addTab(title, url){
		if ($('#tt').tabs('exists', title)){
		$('#tt').tabs('select', title);
		} else {
		var content = '<iframe scrolling="auto" frameborder="0"  src="'+url+'" style="width:100%;height:100%;"></iframe>';
		$('#tt').tabs('add',{
			title:title,
			content:content,
			closable:true
		});
		}
	}