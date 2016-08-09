/**
 * Created by admin on 2016/7/1.
 */
CMSWidgets.initWidget({
    editor: {
        properties: null,
        saveComponent: function (onSuccess, onFailed) {
            var that = this;
            $.each($(".navPagingTColor"), function (index, obj) {
                that.properties.pagingTColor = $(obj).val();
            });
            $.each($(".navPagingHColor"), function (index, obj) {
                that.properties.pagingHColor = $(obj).val();
            });
            if (this.properties.pagingTColor != '' && this.properties.pagingHColor != ''
                && this.properties.logoFileUri != '' && this.properties.pageIds.length > 0) {
                onSuccess(this.properties)
                return this.properties;
            } else {
                onFailed("组件参数缺少,未能保存,请完善。");
                return ;
            }
        },
        uploadImage: function () {
            var that = this;
            uploadForm({
                ui: '#logoFile',
                inputName: 'file',
                maxWidth: 1920,
                maxHeight: 540,
                isCongruent: false,
                maxFileCount: 1,
                successCallback: function (files, data, xhr, pd) {
                    that.properties.logoFileUri = data.fileUri;
                },
                deleteCallback: function (resp, data, jqXHR) {
                    that.properties.logoFileUri = "";
                }
            });
        },
        initProperties: function () {
            this.properties.pageIds = [];
            this.properties.pagingTColor = "";
            this.properties.pagingHColor = "";
            this.properties.logoFileUri = "";
            var that = this;
            var setting = {
                check: {
                    enable: true
                },
                data: {
                    simpleData: {
                        enable: true
                    }
                },
                callback:{
                    onCheck:onCheck
                }
            };
            function onCheck(e,treeId,treeNode){
                that.properties.pageIds = [];
                var treeObj=$.fn.zTree.getZTreeObj("treeView");
                var nodes=treeObj.getCheckedNodes(true);
                for(var i=0;i<nodes.length;i++){
                    var item = {
                        id: nodes[i].id,
                        pid: nodes[i].pid,
                        name: nodes[i].name,
                        pagePath: nodes[i].pagePath
                    }
                    that.properties.pageIds.push(item)
                }
            }

            /*<![CDATA[*/
            var data = /*[[${@cmsDataSourceService.findSitePage()}]]*/ '[]';
            /*]]>*/

            $.fn.zTree.init($("#treeView"), setting, jQuery.parseJSON(data));
        },
        open: function (globalId) {
            this.properties = widgetProperties(globalId);
            this.initProperties();
            this.uploadImage();
        },
        close:function (globalId) {
            
        }
    }
})

