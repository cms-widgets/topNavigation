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
                // var node = [
                //     {id: 1, name: '${login.getLoginName()}', flag: 0, linkPath: 'www',visible:"#{login.loginOrNot()}"}
                //     , { id: 2, name: '首页2', flag: 0, linkPath: 'www', visible:""}
                //     , {id: 3, name: '首页3', flag: 0, linkPath: 'www',visible:""}
                //     ];

                var node = [
                    {
                        id: 1,
                        name: '',
                        visibleName: 'hello lihuaixin',
                        flag: 0,
                        linkPath: '/memberCenter',
                        visible: 'true',
                        visibleValue: ''
                    },
                    {
                        id: 2,
                        name: '你好请登录，注册',
                        visibleName: '',
                        flag: 0,
                        linkPath: '/login',
                        visible: '!true',
                        visibleValue: ''
                    },

                    {
                        id: 3,
                        name: '我的订单',
                        visibleName: '',
                        flag: 0,
                        linkPath: '/orders',
                        visible: 'true',
                        visibleValue: ''
                    },
                    {
                        id: 4,
                        name: '我的订单',
                        visibleName: '',
                        flag: 0,
                        linkPath: '/login',
                        visible: '!true',
                        visibleValue: ''
                    },
                    {
                        id: 5,
                        name: '个人中心',
                        visibleName: '',
                        flag: 0,
                        linkPath: '/memberCenter',
                        visible: 'true',
                        visibleValue: ''
                    },
                    {
                        id: 5,
                        name: '个人中心',
                        visibleName: '',
                        flag: 0,
                        linkPath: '/login',
                        visible: '!true',
                        visibleValue: ''
                    },

                    {
                        id: 5,
                        name: '企业采购',
                        visibleName: '',
                        flag: 0,
                        linkPath: '/EnterpriseProcurement',
                        visible: '',
                        visibleValue: ''
                    },
                    {
                        id: 5,
                        name: '企业采购',
                        visibleName: '',
                        flag: 0,
                        linkPath: '/EnterpriseProcurement',
                        visible: '',
                        visibleValue: ''
                    }
                ];

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
            this.initProperties();
        }
    }
})

var node = [
    {
        id: 1,
        name: '',
        visibleName: 'getLoginUserName()',
        flag: 0,
        linkPath: '/memberCenter',
        visible: 'loginOrNot()',
        visibleValue: ''
    },
    {id: 2, name: '你好请登录，注册', visibleName: '', flag: 0, linkPath: '/login', visible: '!loginOrNot()', visibleValue: ''},

    {id: 3, name: '我的订单', visibleName: '', flag: 0, linkPath: '/orders', visible: 'loginOrNot()', visibleValue: ''},
    {id: 4, name: '我的订单', visibleName: '', flag: 0, linkPath: '/login', visible: '!loginOrNot()', visibleValue: ''},
    {
        id: 5,
        name: '个人中心',
        visibleName: '',
        flag: 0,
        linkPath: '/memberCenter',
        visible: 'loginOrNot()',
        visibleValue: ''
    },
    {id: 5, name: '个人中心', visibleName: '', flag: 0, linkPath: '/login', visible: '!loginOrNot()', visibleValue: ''},

    {id: 5, name: '企业采购', visibleName: '', flag: 0, linkPath: '/EnterpriseProcurement', visible: '', visibleValue: ''},
    {id: 5, name: '企业采购', visibleName: '', flag: 0, linkPath: '/EnterpriseProcurement', visible: '', visibleValue: ''}
];