/* CreateTime:2020-7-20 15:31:36 */
layui.define('layer', function (exports) {
    var $ = layui.jquery;
    var layer = layui.layer;
    var tool = {version: '1.0.0'};

    tool.download = function (url, params, fileName) {
        url += '?' + parseParams(params);
        var xhr = new XMLHttpRequest();
        xhr.open('GET', url, true);
        xhr.responseType = 'blob';
        xhr.onload = function (ev) {
            if (this.status === 200) {
                var blob = this.response;
                var reader = new FileReader();
                reader.readAsDataURL(blob);
                reader.onload = function (e) {
                    var link = document.createElement('a');
                    link.style.display = 'none';
                    link.href = e.target.result;
                    link.setAttribute('download', fileName);
                    document.body.appendChild(link);
                    link.click();
                    $(link).remove();
                }
            } else {
                layer.msg('下载失败', {icon: 2});
            }
        };
        xhr.send();
    };

    function parseParams(param, key, encode) {
        if (param == null) return '';
        var arr = [];
        var t = typeof (param);
        if (t === 'string' || t === 'number' || t === 'boolean') {
            arr.push(key + '=' + ((encode == null || encode) ? encodeURIComponent(param) : param));
        } else {
            for (var i in param) {
                var k = key == null ? i : key + (param instanceof Array ? '[' + i + ']' : '.' + i);
                arr.push(parseParams(param[i], k, encode));
            }
        }
        return arr.join("&");
    }

    //输出test接口
    exports('tool', tool);
});