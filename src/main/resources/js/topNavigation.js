/**
 * Created by admin on 2016/7/1.
 */
CMSWidgets.initWidget({
    editor: {
        properties: null,
        saveComponent: function (onFailed) {
            this.properties.pagingTColor = $("input[name='pagingTColor']").val();
            this.properties.pagingHColor = $("input[name='pagingHColor']").val();
            var nodes = $.getTreeViewData();
            this.properties.pageIds = nodes;
            if (this.properties.pagingTColor == '' && this.properties.pagingHColor == '' && this.properties.pageIds.length <= 0) {
                onFailed("组件参数缺少,未能保存,请完善。");
                return true;
            }
        },
        initProperties: function () {
            var that = this;
            if (that.properties.pageIds == undefined) {

                var node = [{
                    "flag": 0,
                    "isParent": "false",
                    "visible": "",
                    "linkPath": "",
                    "name": "首页",
                    "visibleName": "",
                    "visibleValue": "",
                    "id": 1
                }, {
                    "isParent": "true",
                    "visible": "",
                    "flag": 0,
                    "linkPath": "",
                    "children": [{
                        "flag": 0,
                        "visible": "true",
                        "linkPath": "",
                        "name": "hello lihuaixin",
                        "visibleName": "'hello lihuaixin'",
                        "visibleValue": true,
                        "pid": 2
                    }, {
                        "flag": 0,
                        "visible": "true",
                        "linkPath": "",
                        "name": "hello lihuaixin",
                        "visibleName": "'hello lihuaixin'",
                        "visibleValue": true,
                        "pid": 2
                    }, {
                        "visible": "!true",
                        "flag": 0,
                        "linkPath": "",
                        "name": "行业动态",
                        "visibleName": "",
                        "visibleValue": false,
                        "pid": 2
                    }],
                    "name": "动态资讯",
                    "visibleName": "",
                    "visibleValue": "",
                    "id": 2
                }, {
                    "isParent": "false",
                    "visible": "true",
                    "flag": 0,
                    "linkPath": "",
                    "name": "关于我们",
                    "visibleName": "",
                    "visibleValue": true,
                    "id": 3
                }];

                $('#treeView').addTreeView({
                    treeNodes: node
                });
            } else {
                $('#treeView').addTreeView({
                    treeNodes: that.properties.pageIds
                });
            }
        },
        open: function (globalId) {
            this.initProperties();
        }
    }
})
