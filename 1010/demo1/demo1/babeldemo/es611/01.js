'use strict';

Object.defineProperty(exports, "__esModule", {
    value: true
});
exports.getList = getList;
exports.save = save;
//定义方法，设置哪些方法还可以被js调用
function getList() {
    console.log('获取数据列表');
}

function save() {
    console.log('保存数据');
}