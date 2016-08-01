/**
 * Created by admin on 2016/7/1.
 */
CMSWidgets.initWidget({
    editor: {
        properties: null,
        saveComponent: function (onSuccess, onFailed) {
            $.each($(".navPagingTColor"), function (index, obj) {
                topNavigation.properties.pagingTColor = $(obj).val();
            });
            $.each($(".navPagingHColor"), function (index, obj) {
                topNavigation.properties.pagingHColor = $(obj).val();
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
            uploadForm({
                ui: '#logoFile',
                inputName: 'file',
                maxWidth: 1920,
                maxHeight: 540,
                isCongruent: false,
                maxFileCount: 1,
                successCallback: function (files, data, xhr, pd) {
                    this.properties.logoFileUri = data.fileUri;
                },
                deleteCallback: function (resp, data, jqXHR) {
                    console.log(data);
                    this.properties.logoFileUri = "";
                }
            });
        },
        initProperties: function () {
            this.properties.pageIds = [];
            this.properties.pagingTColor = "";
            this.properties.pagingHColor = "";
            this.properties.logoFileUri = "";
            /*<![CDATA[*/
            var data = /*[[${@cmsDataSourceService.findSitePage()}]]*/ 'Sebastian';
            /*]]>*/
            var $checkableTree = $('#navbar-treeview').treeview({
                levels: 1,
                data: data,
                showIcon: false,
                showCheckbox: true,
                onNodeChecked: function (event, node) {
                    var item = {
                        pageId: node.pageId,
                        parentId: node.parentId,
                        text: node.text,
                        href: node.href,
                        nodes: []
                    }
                    this.properties.pageIds.push(item)
                },
                onNodeUnchecked: function (event, node) {
                    $.grep(this.properties.pageIds, function (cur, index) {
                        if (cur.pageId == node.pageId && cur.parentId == node.parentId && cur.text == node.text
                            && cur.href == node.href) {
                            this.properties.pageIds.splice(index, 1);
                        }
                    });
                }
            });
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

