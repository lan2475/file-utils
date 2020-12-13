/**
 * 文件工具类
 */
class fileUtils{

    /**
     * 创建表单元素 用于储存form值
     * @param name
     * @param value
     * @returns {HTMLElement}
     * @private
     */
    _createInput(name,value){
        let inp = document.createElement("INPUT");
        inp.setAttribute("NAME",name);
        inp.value = value;
        return inp;
    }

    /**
     * 创建iframe
     * @param isShow 是否显示
     * @returns {HTMLElement}
     * @private
     */
    _createIframe(isShow){
        let iframe = document.createElement("IFRAME");
        !isShow&&(iframe.style.display="none");
        return iframe;
    }

    /**
     * 创建iframe并插入当前页面
     * @param isShow
     * @returns {HTMLElement}
     * @private
     */
    _createAndInsertIframe(isShow){
        return document.getElementsByTagName("BODY")[0].appendChild(this._createIframe(isShow));
    }

    /**
     * 模拟表单提交
     * @param url
     * @param data
     * @param iframe
     * @param onload
     * @private
     */
    _simulationForm(url,data,iframe,onload){
        let ifrDocument = iframe.contentDocument;
        let ifrWindow = iframe.contentWindow;
        if(!ifrDocument||!ifrWindow) throw "请确保 <iframe> 元素已被创建且已添加至页面内";
        if(onload&&iframe.onload) onload = function(){onload.apply(iframe)}
        iframe.onload = onload;
        let form = document.createElement("FORM");
        form.setAttribute("action",url);
        form.method="POST";
        data&&Object.keys(data).forEach(it=>{
            let val = data[it];
            if(Array.isArray(val)){
                if(!it.endsWith("[]")) it=it+"[]";
                val.forEach(item=>{
                    form.appendChild(this._createInput(it,item));
                })
            }else {
                form.appendChild(this._createInput(it,val));
            }
        });
        ifrDocument.getElementsByTagName("BODY")[0].appendChild(form);
        form.submit();
    }

    /**
     * 下载
     * @param url
     * @param data
     */
    download(url,data){
        this._simulationForm(url,data,this._createAndInsertIframe());
    }

    /**
     * 预览
     * @param url
     * @param data
     * @param ifr 必填，一个创建好的iframe
     */
    preview(url,data,ifr){
        if(!ifr) throw "请传入 <iframe> 的 dom 对象";
        return this._simulationForm(url,data,ifr);
    }

    /**
     * 打印
     * @param url
     * @param data
     */
    print(url,data){
        return this._simulationForm(url,data,this._createAndInsertIframe(),function (){
            console.log(666);
            this.contentWindow.print();
        });
    }

}
