var setting = {
		view : {
			showIcon : showIconForTree
		},
		data : {
			simpleData : {
				enable : true
			}
		},
		callback : {
			onClick : zTreeOnClick,
		}
	};

function showIconForTree(treeId, treeNode) {
	return !treeNode.isParent;
}

//单击栏目，右侧显示子栏目
function zTreeOnClick(event, treeId, treeNode) {
	window.location.href  = root + "/category/subCates/"+treeNode.id;
}

$(function() {
	$.fn.zTree.init($("#tree"), setting, zNodes);
	$("#tree span").addClass("label label-primary");
});

