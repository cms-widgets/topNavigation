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
            var nodes = $.getTreeViewData();
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
            var that = this;
            if (that.properties.pageIds == undefined) {
                var node = [{id: 1, name: '首页', flag: 0, linkPath: 'www'}, {
                    id: 2,
                    name: '首页2',
                    flag: 0,
                    linkPath: 'www'
                }, {id: 3, name: '首页3', flag: 0, linkPath: 'www'}]
                $('#treeView').addTreeView({
                    treeNodes: node
                });
            } else {
                $('#treeView').addTreeView({
                    treeNodes:that.properties.pageIds
                });
            }

        },
        open: function (globalId) {
            this.properties = widgetProperties(globalId);
            this.initProperties();
        },
        close: function (globalId) {
        }
    }
})

