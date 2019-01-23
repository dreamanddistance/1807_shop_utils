
/**
 * 
 * @param parentIdEleId 元素的id
 * @param parentId 被选中的元素id
 * @param goodsTypeParentId 父类别的id(要根据父类別id查询小类)
 * @returns
 */
function initGoodsType(parentIdEleId,parentId,goodsTypeParentId) {
	$.post("GoodsTypeServlet?action=getGoodsTypeListByParentId&parentId="+goodsTypeParentId,
			function(data) {
				// 1.先知道父类別的元素对象
				var parentIdEle = $("#"+parentIdEleId);

				for (var i = 0; i < data.length; i++) {

					// 2.创建一个option对象
					var option = document.createElement("option");

					// 3.给option对象中的属性赋值
					option.value = data[i].id; // value属性
					option.text = data[i].goods_type_name; // 文本值

					// 4.把option添加到父类元素中
					parentIdEle.append(option);
				}
				
				// 选中父类別
				if(parentId){
					parentIdEle.val(parentId);
				}
				
			}, "JSON");
}
