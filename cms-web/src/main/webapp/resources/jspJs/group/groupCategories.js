//保存所有父节点
var pNodes = [];
//保存所有子节点
var cNodes = [];

var setting = {
		check: {
			enable: true,
			chkDisabledInherit: true,
			chkboxType: { "Y": "p", "N": "s" }
		},
		view : {
			showIcon : showIconForTree
		},
		data : {
			simpleData : {
				enable : true
			}
		},
		callback: {
			beforeCheck: zTreeBeforeCheck
		}
	};

function showIconForTree(treeId, treeNode) {
	return !treeNode.isParent;
}

function zTreeBeforeCheck(treeId, treeNode) {
	//操作当前节点的父节点
	//当前节点未被勾选，则选中之前，获取所有未选中的父节点
	if(!treeNode.checked){
		//清空历史父节点
		pNodes.length = 0;
		getPnodes(treeNode);
		//添加当前节点的关联关系
		groupCate.addGroupCategory($("#groupId").val(),treeNode.id);
		for(var i=0;i<pNodes.length;i++){
			//添加所有的父节点关联关系
			groupCate.addGroupCategory($("#groupId").val(),pNodes[i].id);
		}
		
		
	}
	else{
		//当前节点已经被勾选，去勾选的时候顺便清空已经收集的父节点数组
		pNodes.length = 0;
		//去除当前节点的关联关系
		groupCate.deleteGroupCategory($("#groupId").val(),treeNode.id);
		
		//当前节点如果有子节点
		if(treeNode.children){
			//还需要将所有已勾选的子节点去除关联关系
			for(var i = 0;i < treeNode.children.length;i++){
				//当前的子节点已经被选中，那么去除关联
				if(treeNode.children[i].checked){
					groupCate.deleteGroupCategory($("#groupId").val(),treeNode.children[i].id);
				}
			}
		}
	}
    return true;
};

/**
 * 获取当前节点的所有未选中的父节点
 * @param treeNode
 */
function getPnodes(treeNode){
	var pNode = treeNode.getParentNode();
	//当前节点存在父节点,且父节点未被选中
	if(pNode&&!pNode.checked){
		pNodes.push(pNode);
		getPnodes(pNode);
	}
}

$(function() {
	$.fn.zTree.init($("#tree"), setting, zNodes);
	
	//展示组内的栏目，全部展开
	var treeObj = $.fn.zTree.getZTreeObj("tree");
	treeObj.expandAll(true);
	
	$("#tree span").addClass("label label-primary");
	
});

