/**
 * Created by admin on 2016/7/1.
 */
CMSWidgets.initWidget({
    editor: {
        properties: null,
        saveComponent: function (onSuccess, onFailed) {
            var that = this;
            that.properties.pagingTColor = $("input[name='pagingTColor']").val();
            that.properties.pagingHColor = $("input[name='pagingHColor']").val();
            var treeObj = $.fn.zTree.getZTreeObj("treeView");
            var nodes = treeObj.transformTozTreeNodes(treeObj.getNodes());
            that.properties.pageIds = nodes;
            if (that.properties.pagingTColor != '' && that.properties.pagingHColor != '' && that.properties.pageIds.length > 0) {
                onSuccess(that.properties)
                return that.properties;
            } else {
                onFailed("组件参数缺少,未能保存,请完善。");
                return true;
            }
        },
        initProperties: function () {
            var treeNode = null;
            if(this.properties.pageIds==undefined || this.properties.pageIds.length==0){
                this.properties.pageIds = [
                    {id:1,name:'1',pagePath:'/'}
                    ,{id:2,name:'2',pagePath:'/'}
                    ,{id:3,name:'3',pagePath:'/'}
                ];
            }
            var setting = {
                data: {
                    simpleData: {
                        enable: true
                    }
                },
                callback: {
                    onClick: onClick
                }
            };

            function onClick(event, treeId, treenode) {
                treeNode = treenode;
                $("input[name='name'] ").val(treenode.name);
                $("input[name='pagePath'] ").val(treenode.pagePath);
            };

            $.fn.zTree.init($("#treeView"), setting, this.properties.pageIds);

            $(".form-horizontal").on("click", ".addRootNodes", function () {
                var rootNode = {name: "rootNode1", uri: ''};
                $.fn.zTree.getZTreeObj("treeView").addNodes(null, rootNode);
            });
            $(".form-horizontal").on("click", ".addNodes", function () {
                if (treeNode.level >= 1) {
                    return;
                }
                var childNode = {name: "childNode", uri: '', pid: treeNode.pid};
                $.fn.zTree.getZTreeObj("treeView").addNodes(treeNode, childNode);
            });

            $(".form-horizontal").on("click", ".delNodes", function () {
                $.fn.zTree.getZTreeObj("treeView").removeNode(treeNode);
                treeNode = null;
            });

            $(".form-horizontal").on("click", ".reset", function () {
                $.fn.zTree.init($("#treeView"), setting, '');
                treeNode = null;
            });
            $(".form-horizontal").on("click", ".saveNode", function () {
                treeNode.name = $("input[name='name'] ").val();
                treeNode.pagePath = $("input[name='pagePath'] ").val();
                $.fn.zTree.getZTreeObj("treeView").updateNode(treeNode);
            });
        },
        open: function (globalId) {
            this.properties = widgetProperties(globalId);
            this.initProperties();
        },
        close: function (globalId) {
            $(".form-horizontal").off("click", ".addRootNodes");
            $(".form-horizontal").off("click", ".saveNode");
            $(".form-horizontal").off("click", ".reset");
            $(".form-horizontal").off("click", ".delNodes");
            $(".form-horizontal").off("click", ".addNodes");
        }
    }
})

