/*
 * 确认密码一致性校验
 * */
$.extend($.fn.validatebox.defaults.rules, {
	eqPwd : {
		validator : function(value, param) {
			return value == $(param[0]).val();
		},
		message : '密码不一致！'
	}
});
$.extend($.fn.validatebox.defaults.rules, {
	eqvalidateCode : {
		validator : function(value) {
			return value == $.cookie("validateCode");
		},
		message : '验证码错误！'
	}
});

$.extend($.fn.validatebox.defaults.rules, {
	validateSameName : {
		validator : function(value, param) {
			var result;
			$.ajax({
				url : 'userAction!validateName.action',
				data : {
					name : value
				},
				type : 'post',
				dataType : "json",
				async : false,
				success : function(data) {
					result = data.success;
				}
			});
			return result;
		},
		message : ' 当前用户名已存在。'
	}
});


//扩展树加载失败的方法
$.fn.tree.defaults.onLoadError = function(arguments) {
	var d = arguments.status;
    if(d==402){
    	// TODO
    	alert_totalQuery('Token is error,please re-acquire!','',10);
		setTimeout(() => {
//			清理cookie
		$.cookie('token', '', {
			expires : -1
		});
		$.cookie('refreshToken', '', {
			expires : -1
		});
		$.cookie('role', '', {
			expires : -1
		});
			window.location.reload();
		}, 10000);
    }
    if(d==403){
    	// TODO
    	alert_totalQuery('This token has expired!','',10);
		setTimeout(() => {
//			清理cookie
		$.cookie('token', '', {
			expires : -1
		});
		$.cookie('refreshToken', '', {
			expires : -1
		});
		$.cookie('role', '', {
			expires : -1
		});
			window.location.reload();
		}, 10000);
    } 
}
// 扩展树加载数据的方法
$.fn.tree.defaults.loadFilter = function(data, parent) {
	var opt = $(this).data().tree.options;
	var idFiled, textFiled, parentField;
	if (opt.parentField) {
		idFiled = opt.idFiled || 'id';
		textFiled = opt.textFiled || 'text';
		parentField = opt.parentField;
		var i, l, treeData = [], tmpMap = [];
		for (i = 0, l = data.length; i < l; i++) {
			tmpMap[data[i][idFiled]] = data[i];
		}
		for (i = 0, l = data.length; i < l; i++) {
			if (tmpMap[data[i][parentField]] && data[i][idFiled] != data[i][parentField]) {
				if (!tmpMap[data[i][parentField]]['children'])
					tmpMap[data[i][parentField]]['children'] = [];
				data[i]['text'] = data[i][textFiled];
				tmpMap[data[i][parentField]]['children'].push(data[i]);
			} else {
				data[i]['text'] = data[i][textFiled];
				treeData.push(data[i]);
			}
		}
		return treeData;
	}
	return data;
};
// 序列化form表单为对象
serializeObject = function(form) {
	var o = {};
	$.each(form.serializeArray(), function(index) {
		if (o[this['name']]) {
			o[this['name']] = o[this['name']] + "," + this['value'];
		} else {
			o[this['name']] = this['value'];
		}
	});
	return o;
};

// 提示框关闭倒计时函数
alert_totalQuery = function(msg,icon,tm){
	var interval;
	var time=1000;
	var x;
	if(null==tm||''==tm){
		x=Number(3);
	}else{
		x=Number(tm);
	}
	//
	if(null==icon||''==icon){
		icon="infoSunnyIcon";
	}
	$.messager.alert(' ','<font size=\"2\" color=\"#666666\"><strong>'+msg+'</strong></font>',icon,function(){});
	$(".messager-window .window-header .panel-title").append("系统提示（"+x+"秒后自动关闭）");
	interval=setInterval(fun,time);
	function fun(){
		--x;
		if(x==0){
	      clearInterval(interval);
		  $(".messager-body").window('close');	
		}
		$(".messager-window .window-header .panel-title").text("");
		$(".messager-window .window-header .panel-title").append("系统提示（"+x+"秒后自动关闭）");
	}
}  

/**
 * @author 孙宇
 * 
 * @requires jQuery,EasyUI
 * 
 * 防止panel/window/dialog组件超出浏览器边界
 * @param left
 * @param top
 */
var easyuiPanelOnMove = function(left, top) {
	var l = left;
	var t = top;
	if (l < 1) {
		l = 1;
	}
	if (t < 1) {
		t = 1;
	}
	var width = parseInt($(this).parent().css('width')) + 14;
	var height = parseInt($(this).parent().css('height')) + 14;
	var right = l + width;
	var buttom = t + height;
	var browserWidth = $(window).width();
	var browserHeight = $(window).height();
	if (right > browserWidth) {
		l = browserWidth - width;
	}
	if (buttom > browserHeight) {
		t = browserHeight - height;
	}
	$(this).parent().css({/* 修正面板位置 */
		left : l,
		top : t
	});
};
$.fn.dialog.defaults.onMove = easyuiPanelOnMove;
$.fn.window.defaults.onMove = easyuiPanelOnMove;
$.fn.panel.defaults.onMove = easyuiPanelOnMove;